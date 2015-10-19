package model;

public class Tile {
    private int owner;
    private int mountains;
    private Mule mule;
    private int[] production = {2, 3, 1, 0};

    public int[] calculateProduction() {
        int[] ret = {0, 0, 0, 0};
        if (mule != null) {
            ret[mule.getType()] = production[mule.getType()];
        }
        return ret;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public void setMountains(int mountains) {
        this.mountains = mountains;
        production[0] -= 1;
        production[1] -= 2;
        production[2] += mountains;
    }

    public void setRiver() {
        production[0] += 2;
        production[1] -= 1;
        production[2] -= 1;
    }

    public void placeMule(Mule mule) {
        this.mule = mule;
    }

    public int getOwner() {
        return owner;
    }

    public int getMountains() {
        return mountains;
    }

    public boolean hasMule() {
        return mule != null;
    }
}
