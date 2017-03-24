package nl.gremmee.fractaltree;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.Random;

public class Leaf extends TreeObject {
    public boolean reached;
    public Point2D.Double pos;
    private ID id;
    private Random random = new Random();

    public Leaf() {
        super(ID.Leaf);
        this.pos = new Point2D.Double(Utils.getRandomFloat(FractalTree.WIDTH),
                Utils.getRandomFloat(FractalTree.HEIGHT) - 100);
        this.reached = false;
    }

    public void doRender(Graphics aGraphics) {
        aGraphics.setColor(Color.WHITE);
        aGraphics.drawOval((int) pos.x, (int) pos.y, 4, 4);
    }

    public void doUpdate() {
    }

    public ID getID() {
        return this.id;
    }

    public Random getRandom() {
        return this.random;
    }
}
