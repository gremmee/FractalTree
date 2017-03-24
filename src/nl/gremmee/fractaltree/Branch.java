package nl.gremmee.fractaltree;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.Random;

public class Branch extends TreeObject {
    public Branch parent;
    public int count;
    public int len;
    public Point2D.Double dir;
    public Point2D.Double origDir;
    public Point2D.Double pos;
    private ID id;
    private Random random = new Random();

    public Branch(Branch aParent, Point2D.Double aPos, Point2D.Double aDir) {
        super(ID.Branch);
        this.pos = aPos;
        this.parent = aParent;
        this.dir = aDir;
        this.origDir = dir; // copy
        this.count = 0;
        this.len = 5;
    }

    public void doRender(Graphics aGraphics) {
        if (parent != null) {
            aGraphics.setColor(Color.ORANGE);
            aGraphics.drawLine((int) this.pos.x, (int) this.pos.y, (int) this.parent.pos.x, (int) this.parent.pos.y);
        }
    }

    public void doUpdate() {
    }

    public ID getID() {
        return this.id;
    }

    public Random getRandom() {
        return this.random;
    }

    public Branch next() {
        this.dir = Utils.normalize(this.dir);
        Point2D.Double nextDir = Utils.multiplyPoints2D(this.dir, this.len);
        Point2D.Double nextPos = Utils.addPoints2D(this.pos, nextDir);
        Branch nextBranch = new Branch(this, nextPos, this.dir); // copy
        return nextBranch;
    }

    public void reset() {
        this.dir = origDir; // copy
        this.count = 0;
    }
}
