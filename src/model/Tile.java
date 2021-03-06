package model;

import java.io.Serializable;

@SuppressWarnings("unused")
public class Tile implements Location , Serializable {
    private int owner;
    private int mountains;
    private Device mule;
    private final int[] production = {2, 3, 1, 0};

    @Override
    public final int[] calculateProduction() {
        int[] ret = {0, 0, 0, 0};
        if (mule != null) {
            ret[mule.getType()] = production[mule.getType()];
        }
        return ret;
    }

    @Override
    public final void setOwner(int o) {
        owner = o;
    }

    @Override
    public final void setMountains(int m) {
        mountains = m;
        production[0] -= 1;
        production[1] -= 2;
        production[2] += mountains;
    }

    @Override
    public final void setRiver() {
        production[0] += 2;
        production[1] -= 1;
        production[2] -= 1;
    }

    @Override
    public final void placeMule(Device m) {
        mule = m;
    }

    @Override
    public final int getOwner() {
        return owner;
    }

    @Override
    public final int getMountains() {
        return mountains;
    }

    @Override
    public final boolean hasMule() {
        return mule != null;
    }
}
