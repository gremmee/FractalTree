package nl.gremmee.fractaltree;

import java.awt.Graphics;

public abstract class TreeObject {
    private ID id;

    public TreeObject(ID aID) {
        this.id = aID;
    }

    public void render(Graphics aGraphics) {
        doRender(aGraphics);
    }

    public void setId(ID aId) {
        this.id = aId;
    }

    public void update() {
        doUpdate();
    }

    protected abstract void doRender(Graphics aGraphics);

    protected abstract void doUpdate();
}
