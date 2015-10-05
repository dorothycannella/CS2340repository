package model;

public class Map {
    Tile[][] map;

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
                    map[i][j].setRiver(true);
                }
            }
        }
    }

    public Tile getTile(int i, int j) {
        return map[i][j];
    }
}
