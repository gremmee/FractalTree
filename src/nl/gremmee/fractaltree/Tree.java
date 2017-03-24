package nl.gremmee.fractaltree;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.Random;

public class Tree extends TreeObject {
    private ID id;

    private Random random = new Random();

    public Tree() {
        super(ID.Tree);
        for (int i = 0; i < 100; i++) {
            FractalTree.handler.addObject(new Leaf());
        }

        Point2D.Double pos = new Point2D.Double(FractalTree.WIDTH / 2, FractalTree.HEIGHT);
        Point2D.Double dir = new Point2D.Double(0, -1);
        Branch root = new Branch(null, pos, dir);
        FractalTree.handler.addObject(root);

        Branch current = root;

        boolean found = false;

        while (!found) {
            for (Leaf leaf : FractalTree.handler.getLeaves()) {
                Double d = Point2D.Double.distance(current.pos.x, current.pos.y, leaf.pos.x, leaf.pos.y);
                if (d < FractalTree.MAX_DIST) {
                    found = true;
                }

                if (!found) {
                    Branch branch = current.next();
                    current = branch;
                    FractalTree.handler.addObject(current);
                }
            }
        }
    }

    public void doRender(Graphics aGraphics) {
        java.util.List<Leaf> leaves = FractalTree.handler.getLeaves();
        for (Leaf leaf : leaves) {
            leaf.render(aGraphics);
        }

        java.util.List<Branch> branches = FractalTree.handler.getBranches();
        for (Branch branch : branches) {
            branch.render(aGraphics);
        }
        grow();
    }

    public void doUpdate() {
    }

    public ID getID() {
        return this.id;
    }

    public Random getRandom() {
        return this.random;
    }

    public void grow() {
        java.util.List<Leaf> leaves = FractalTree.handler.getLeaves();
        for (Leaf leaf : leaves) {
            Branch closestBranch = null;
            Double record = 10000.0;
            java.util.List<Branch> branches = FractalTree.handler.getBranches();
            for (Branch branch : branches) {
                Double d = Point2D.Double.distance(leaf.pos.x, leaf.pos.y, branch.pos.x, branch.pos.y);
                if (d < FractalTree.MIN_DIST) {
                    leaf.reached = true;
                    closestBranch = null;
                    break;
                } else if (d > FractalTree.MAX_DIST) {
                } else if ((closestBranch == null) || (d < record)) {
                    closestBranch = branch;
                    record = d;
                }
            }

            if (closestBranch != null) {
                Point2D.Double newDir = Utils.subPoints2D(leaf.pos, closestBranch.pos);
                newDir = Utils.normalize(newDir);
                closestBranch.dir = Utils.addPoints2D(closestBranch.dir, newDir);
                closestBranch.count++;
            }
        }

        java.util.List<Leaf> remLeaves = FractalTree.handler.getLeaves();
        for (int i = remLeaves.size() - 1; i >= 0; i--) {
            Leaf leaf = remLeaves.get(i);
            if (leaf.reached) {
                FractalTree.handler.removeObject(leaf);
            }
        }

        java.util.List<Branch> branches = FractalTree.handler.getBranches();
        for (int i = branches.size() - 1; i >= 0; i--) {
            Branch branch = branches.get(i);
            if (branch.count > 0) {
                FractalTree.handler.addObject(branch.next());
            }
            branch.reset();
        }
    }
}
