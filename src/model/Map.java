package model;

public class Map {
    private Tile[][] map;

    public Map() {
        map = new Tile[5][9];
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

    public void placeMule(int i, int j, Mule mule) {
        map[i][j].placeMule(mule);
    }

    public Tile getTile(int i, int j) {
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
