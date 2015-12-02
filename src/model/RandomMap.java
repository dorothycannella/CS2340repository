package model;

import java.io.Serializable;

public class RandomMap implements World, Serializable {
    private Location[][] map;

    public RandomMap() {
        map = new Tile[5][9];
        for (int i = 0; i < map.length; i++) {
            int numMountains = (int) (Math.random() * 3 + 1);
            int mountainTile = (int) (Math.random() * 4);
            for (int j = 0; j < 4; j++) {
                map[i][j] = new Tile();
                if (j == mountainTile) {
                    map[i][j].setMountains(numMountains);
                }
            }
            map[i][4] = new Tile();
            map[i][4].setRiver();
            mountainTile = (int) (Math.random() * 4 + 5);
            for (int j = 5; j < map[0].length; j++) {
                map[i][j] = new Tile();
                if (j == mountainTile) {
                    map[i][j].setMountains(4 - numMountains);
                }
            }
        }
    }


    public void placeMule(int i, int j, Device mule) {
        map[i][j].placeMule(mule);
    }

    public Location getTile(int i, int j) {
        return map[i][j];
    }

    public String getTileType(int i, int j) {
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

    public int getOwner(int i, int j) {
        return map[i][j].getOwner();
    }

    public int getMountains(int i, int j) {
        return map[i][j].getMountains();
    }

    public boolean hasMule(int i, int j) {
        return map[i][j].hasMule();
    }

}
