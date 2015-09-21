package sample;

public class Tile {
    private int owner;
    private int mountains;
    private boolean river;

    public Tile() {
        this.owner = 0;
        this.mountains = 0;
        this.river = false;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public void setMountains(int mountains) {
        this.mountains = mountains;
    }

    public void setRiver(boolean river) {
        this.river = river;
    }

    public int getOwner() {
        return owner;
    }

    public int getMountains() {
        return mountains;
    }

    public boolean getRiver() {
        return river;
    }
}
