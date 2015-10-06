package model;

import controller.Game;

import java.util.ArrayList;

public class Player implements Comparable<Player> {
    private String race;
    private String color;
    private String name;
    private int id;
    private int money;
    private int food;
    private int energy;
    private int smithore;
    private int crystite;
    private ArrayList<Mule> mules;
    private ArrayList<Tile> tiles;

    public Player(int id, String race, String color, String name) {
        this.id = id;
        this.race = race;
        this.color = color;
        this.name = name;
        money = 1000;
        if (race.equals("Flapper")) {
            money = 1600;
        } else if (race.equals("Human")) {
            money = 600;
        }
        food = Game.getDifficulty() == 1 ? 8 : 4;
        energy = food / 2;
        smithore = 0;
        mules = new ArrayList<>();
        tiles = new ArrayList<>();
    }

    public void addMoney(int money) {
        this.money += money;
    }

    public void addFood(int food) {
        this.food += food;
    }

    public void addEnergy(int energy) {
        this.energy += energy;
    }

    public void addSmithore(int smithore) {
        this.smithore += smithore;
    }

    public void addCrystite(int crystite) {
        this.crystite += crystite;
    }

    public void addMule(Mule mule) {
        mules.add(mule);
    }

    public void addTile(Tile tile) {
        tiles.add(tile);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRace() {
        return race;
    }

    public String getColor() {
        return color;
    }

    public int getMoney() {
        return money;
    }

    public int getFood() {
        return food;
    }

    public int getEnergy() {
        return energy;
    }

    public int getSmithore() {
        return smithore;
    }

    public int getCrystite() {
        return crystite;
    }

    public Mule getMule(int type) {
        Mule ret = null;
        for (Mule m : mules) {
            if (m.getType() == type) {
                ret = m;
            }
        }
        return ret;
    }

    public int getScore() {
        return money + (500 * tiles.size()) + (30 * food) + (25 * energy)
                + (50 * smithore) + (100 * crystite);
    }

    public int compareTo(Player other) {
        return this.getScore() - other.getScore();
    }
}
