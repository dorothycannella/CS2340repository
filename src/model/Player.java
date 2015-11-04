package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Actor, Comparable<Actor> , Serializable {
    private int id;
    private String[] data;
    private int[] resources;
    private List<Location> tiles;
    private Device mule;

    public Player(int d, int i, String race, String color, String name) {
        id = i;
        data = new String[3];
        data[0] = race;
        data[1] = color;
        data[2] = name;
        resources = new int[5];
        resources[0] = 1000;
        if (race.equals("Flapper")) {
            resources[0] = 1600;
        } else if (race.equals("Human")) {
            resources[0] = 600;
        }
        resources[1] = d == 1 ? 8 : 4;
        resources[2] = resources[1] / 2;
        tiles = new ArrayList<>();
    }

    public void calculateProduction() {
        for (Location tile: tiles) {
            int[] income = tile.calculateProduction();
            for (int i = 0; i < income.length; i++) {
                if (income[i] > 0 && resources[2] > 0) {
                    resources[i + 1] += income[i];
                    resources[2]--;
                }
            }
        }
    }

    public void addResources(int type, int quantity) {
        resources[type] += quantity;
    }

    public void addTile(Location tile) {
        tiles.add(tile);
    }

    public int getId() {
        return id;
    }

    public String getData(int type) {
        return data[type];
    }

    public int getResources(int type) {
        return resources[type];
    }

    public Device getMule() {
        return mule;
    }

    public void setMule(Device m) {
        mule = m;
    }

    public int getScore() {
        return resources[0] + 500 * tiles.size() + 30 * resources[1]
                + 25 * resources[2] + 50 * resources[3] + 100 * resources[4];
    }

    public int compareTo(Actor other) {
        return this.getScore() - other.getScore();
    }
}
