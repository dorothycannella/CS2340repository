package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Actor, Comparable<Actor>, Serializable {
    private final int id;
    private final String[] data;
    private final int[] resources;
    private final List<Location> tiles;
    private Device mule;

    @SuppressWarnings("unused")
    public Player(int d, int i, String race, String color, String name) {
        id = i;
        data = new String[DATA_SIZE];
        data[0] = race;
        data[1] = color;
        data[2] = name;
        resources = new int[RESOURCES_SIZE];
        resources[0] = DEFAULT_MONEY;
        if (race.equals("Flapper")) {
            resources[0] = FLAPPER_MONEY;
        } else if (race.equals("Human")) {
            resources[0] = HUMAN_MONEY;
        } else if (race.equals("Black Mamba")) {
            resources[0] = BLACK_MAMBA_MONEY;
        } else if (race.equals("Whumpoos")) {
            resources[0] = WHUMPOOS_MONEY;
        }
        resources[1] = d == 1 ? BEGINNER_RESOURCES : STANDARD_RESOURCES;
        resources[2] = resources[1] / 2;
        tiles = new ArrayList<>();
    }

    @Override
    public final void calculateProduction() {
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

    @Override
    public final void addResources(int type, int quantity) {
        resources[type] += quantity;
    }

    @Override
    public final void addTile(Location tile) {
        tiles.add(tile);
    }

    @Override
    public final int getId() {
        return id;
    }

    @Override
    public final String getData(int type) {
        return data[type];
    }

    @Override
    public final int getResources(int type) {
        return resources[type];
    }

    @Override
    public final Device getMule() {
        return mule;
    }

    @Override
    public final void setMule(Device m) {
        mule = m;
    }

    @Override
    public final int getScore() {
        return resources[0] + TILES_SCORE * tiles.size() + FOOD_SCORE
                * resources[1] + ENERGY_SCORE * resources[2] + SMITHORE_SCORE
                * resources[3] + CRYSTITE_SCORE * resources[4];
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public final int compareTo(Actor other) {
        return this.getScore() - other.getScore();
    }
}
