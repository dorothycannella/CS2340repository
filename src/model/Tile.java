package model;

public class Tile implements Location {
    private int owner;
    private int mountains;
    private Device mule;
    private int[] production = {2, 3, 1, 0};

    public int[] calculateProduction() {
        int[] ret = {0, 0, 0, 0};
        if (mule != null) {
            ret[mule.getType()] = production[mule.getType()];
        }
        return ret;
    }

    public void setOwner(int o) {
        owner = o;
    }

    public void setMountains(int m) {
        mountains = m;
        production[0] -= 1;
        production[1] -= 2;
        production[2] += mountains;
    }

    public void setRiver() {
        production[0] += 2;
        production[1] -= 1;
        production[2] -= 1;
    }

    public void placeMule(Device m) {
        mule = m;
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
