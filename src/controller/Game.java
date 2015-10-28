package controller;

import model.*;

import java.util.List;
import java.util.Random;

public class Game implements SystemManager {
    private int difficulty;
    private int mapType;
    private int playerNum;
    private Random rand;
    private World map;
    private TurnProcessor round;
    private Business store;
    private Actor[] players;

    public Game(int d, int m, int n) {
        difficulty = d;
        mapType = m;
        playerNum = n;
        rand = new Random();
        map = new Map();
        round = new Round();
        players = new Player[4];
    }

    public void stopTimer() {
        round.stopTimer();
    }

    public void setPlayer(int id, String race, String color, String name) {
        if (players[id - 1] == null) {
            players[id - 1] = new Player(difficulty, id, race, color, name);
        }
    }

    public void setComputers(List<String> colorCodes) {
        if (players[3] == null) {
            String[] raceCodes = {"Human", "Flapper", "Bonzoid", "Buzzite",
                    "Ugaite"};
            for (int i = 4; i > playerNum; i--) {
                players[i - 1] = new Player(difficulty, i,
                        raceCodes[rand.nextInt(5)],
                        colorCodes.get(rand.nextInt(colorCodes.size())),
                        "CPU" + (i - playerNum));
                colorCodes.remove(players[i - 1].getData(1));
            }
        }
        round.addPlayers(players);
        store = new Store(difficulty);
    }

    public void buyTile(int i, int j) {
        round.buyTile(map.getTile(i, j));
    }

    public boolean confirmPurchase(int i, int j, int id) {
        return map.getOwner(i, j) == id;
    }

    public void pass() {
        round.pass();
    }

    public void resetTurnTime() {
        round.resetTurnTime();
    }

    public void pub() {
        round.gamble();
    }

    public void disarm() {
        round.disarm();
    }

    public void buy(int order) {
        store.buy(order, players[round.getNextTurn() - 1]);
    }

    public void sell(int order) {
        store.sell(order, players[round.getNextTurn() - 1]);
    }

    public int getStock(int type) {
        return store.getStock(type);
    }

    public String getTileType(int i, int j) {
        return map.getTileType(i, j);
    }

    public int getOwner(int i, int j) {
        return map.getOwner(i, j);
    }

    public int getNumMountains(int i, int j) {
        return map.getMountains(i, j);
    }

    public boolean hasMule(int i, int j) {
        return map.hasMule(i, j);
    }

    public void placeMule(int i, int j) {
        map.placeMule(i, j, players[round.getNextTurn() - 1].getMule());
        players[round.getNextTurn() - 1].setMule(null);
    }

    public void removeMule() {
        players[round.getNextTurn() - 1].setMule(null);
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getMapType() {
        return mapType;
    }

    public int getTime() {
        return round.getTime();
    }

    public int getNextTurn() {
        return round.getNextTurn();
    }

    public int getRound() {
        return round.getRound();
    }

    public boolean getPhase() {
        return round.getPhase();
    }

    public int getEventType() {
        return round.getEventType();
    }

    public String getData(int type, int index) {
        String ret = type == 1 ? "White" : "None";
        if (index >= 1 && index <= 4) {
            ret = players[index - 1].getData(type);
        }
        return ret;
    }

    public int getResources(int type, int index) {
        int ret = 0;
        if (index >= 1 && index <= 4) {
            ret = players[index - 1].getResources(type);
        }
        return ret;
    }

    public boolean getMule(int index) {
        boolean ret = false;
        if (index >= 1 && index <= 4) {
            ret = players[index - 1].getMule() != null;
        }
        return ret;
    }

    public int getScore(int index) {
        int ret = 0;
        if (index >= 1 && index <= 4) {
            ret = players[index - 1].getScore();
        }
        return ret;
    }
}
