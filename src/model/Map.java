package model;

import java.io.Serializable;

public class Map implements World, Serializable {
    private final Location[][] map;

    @SuppressWarnings("unused")
    public Map() {
        map = new Tile[HEIGHT][WIDTH];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = new Tile();
                if (i == 2 && j == 0 || i == 0 && j == 6
                        || i == 1 && j == 8) {
                    map[i][j].setMountains(3);
                } else if (i - j == 2 || i == 3 && j == 6
                        || i == 4 && j == 8) {
                    map[i][j].setMountains(2);
                } else if (i + j == 2 || i == 2 && j == 8) {
                    map[i][j].setMountains(1);
                } else if (j == 4) {
                    map[i][j].setRiver();
                }
            }
        }
    }

    @Override
    public final void placeMule(int i, int j, Device mule) {
        map[i][j].placeMule(mule);
    }

    @Override
    public final Location getTile(int i, int j) {
        return map[i][j];
    }

    @Override
    public final String getTileType(int i, int j) {
        String ret;
        if (i == 2 && j == 4) {
            ret = "Town";
        } else if (j == 4) {
            ret = "River";
        } else if (map[i][j].getMountains() > 0) {
            ret = "Mountains";
        } else {
            ret = "Plains";
        }
        return ret;
    }

    @Override
    public final int getOwner(int i, int j) {
        return map[i][j].getOwner();
    }

    @Override
    public final int getMountains(int i, int j) {
        return map[i][j].getMountains();
    }

    @Override
    public final boolean hasMule(int i, int j) {
        return map[i][j].hasMule();
    }
}
