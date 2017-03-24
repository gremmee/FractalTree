package nl.gremmee.fractaltree;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

public class FractalTree extends Canvas implements Runnable {
    public static final int WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public static final int HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    public static Handler handler;
    public static final float MAX_DIST = 200;
    public static final float MIN_DIST = 10;
    private boolean running = false;
    private int frames = 0;
    private Thread thread;

    public FractalTree() {
        handler = new Handler();
        handler.addObject(new Tree());
        new Window(WIDTH, HEIGHT, "FractalTree", this);
    }

    public static void main(String[] aArgs) {
        new FractalTree();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                update();
                delta--;
            }
            if (running) {
                render();
            }
            frames++;

            if ((System.currentTimeMillis() - timer) > 1000) {
                timer += 1000;
                int leaves = handler.getLeavesSize();
                int branches = handler.getBranchesSize();
                System.out.println("W x H : " + WIDTH + " x " + HEIGHT + " FPS: " + frames + " : Branches " + branches
                        + " : Leaves " + leaves);
                frames = 0;
            }
        }
        stop();
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(2);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        // g.setColor(new Color(0, 0, 0, 15));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        handler.render(g);

        g.dispose();
        bs.show();
    }

    private void update() {
        handler.update();
    }
}
