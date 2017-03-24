package nl.gremmee.fractaltree;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;

public class Handler {
    LinkedList<TreeObject> object = new LinkedList<>();

    public void addObject(TreeObject aObject) {
        this.object.add(aObject);
    }

    public java.util.List<Branch> getBranches() {
        java.util.List<Branch> branch = new ArrayList<>();
        for (TreeObject obj : object) {
            if (obj instanceof Branch) {
                branch.add((Branch) obj);
            }
        }
        return branch;
    }

    public int getBranchesSize() {
        int result = 0;
        for (TreeObject obj : object) {
            if (obj instanceof Branch) {
                result++;
            }
        }
        return result;
    }

    public TreeObject getGameObject(TreeObject aObject) {
        int index = this.object.indexOf(aObject);
        if (index != -1) {
            return getGameObject(index);
        }
        return null;
    }

    public TreeObject getGameObject(int aIndex) {
        if (aIndex < 0) {
            return null;
        }
        return this.object.get(aIndex);
    }

    public java.util.List<Leaf> getLeaves() {
        java.util.List<Leaf> leaves = new ArrayList<>();
        for (TreeObject obj : object) {
            if (obj instanceof Leaf) {
                leaves.add((Leaf) obj);
            }
        }
        return leaves;
    }

    public int getLeavesSize() {
        int result = 0;
        for (TreeObject obj : object) {
            if (obj instanceof Leaf) {
                result++;
            }
        }
        return result;
    }

    public void removeObject(TreeObject aObject) {
        this.object.remove(aObject);
    }

    public void render(Graphics aGraphics) {
        for (int i = 0; i < object.size(); i++) {
            TreeObject tempObject = object.get(i);
            tempObject.render(aGraphics);
        }
    }

    public void update() {
        for (int i = 0; i < object.size(); i++) {
            TreeObject tempObject = object.get(i);
            tempObject.update();
        }
    }
}
