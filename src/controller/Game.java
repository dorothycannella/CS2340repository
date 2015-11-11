package controller;

import model.*;

import java.io.*;
import java.util.List;
import java.util.Random;

public class Game implements SystemManager, Serializable {
    private int difficulty;
    private int mapType;
    private int playerNum;
    private Random rand;
    private World map;
    private TurnProcessor round;
    private Business store;
    private Actor[] players;

    @SuppressWarnings("unused")
    public Game(int d, int m, int n) {
        difficulty = d;
        mapType = m;
        playerNum = n;
        rand = new Random();
        map = new Map();
        round = new Round();
        players = new Player[MAX_PLAYERS];
    }

    public final void setPlayer(int id, String race, String color, String name) {
        if (players[id - 1] == null) {
            players[id - 1] = new Player(difficulty, id, race, color, name);
        }
    }

    public final void setComputers(List<String> colorCodes) {
        if (players[MAX_PLAYERS - 1] == null) {
            String[] raceCodes = {"Human", "Flapper", "Bonzoid", "Buzzite",
                    "Ugaite"};
            for (int i = MAX_PLAYERS; i > playerNum; i--) {
                players[i - 1] = new Player(difficulty, i,
                        raceCodes[rand.nextInt(raceCodes.length)],
                        colorCodes.get(rand.nextInt(colorCodes.size())),
                        "CPU" + (i - playerNum));
                colorCodes.remove(players[i - 1].getData(1));
            }
        }
        round.addPlayers(players);
        store = new Store(difficulty);
    }

    public final void buyTile(int i, int j) {
        round.buyTile(map.getTile(i, j));
    }

    public final boolean confirmPurchase(int i, int j, int id) {
        return map.getOwner(i, j) == id;
    }

    public final void pass() {
        round.pass();
    }

    public final void resetTurnTime() {
        round.resetTurnTime();
    }

    public final void pub() {
        round.gamble();
    }

    public final void disarm() {
        round.disarm();
    }

    public final void buy(int order) {
        store.buy(order, players[round.getNextTurn() - 1]);
    }

    public final void sell(int order) {
        store.sell(order, players[round.getNextTurn() - 1]);
    }

    public final int getStock(int type) {
        return store.getStock(type);
    }

    public final String getTileType(int i, int j) {
        return map.getTileType(i, j);
    }

    public final int getOwner(int i, int j) {
        return map.getOwner(i, j);
    }

    public final int getNumMountains(int i, int j) {
        return map.getMountains(i, j);
    }

    public final boolean hasMule(int i, int j) {
        return map.hasMule(i, j);
    }

    public final void placeMule(int i, int j) {
        map.placeMule(i, j, players[round.getNextTurn() - 1].getMule());
        players[round.getNextTurn() - 1].setMule(null);
    }

    public final void removeMule() {
        players[round.getNextTurn() - 1].setMule(null);
    }

    public final int getPlayerNum() {
        return playerNum;
    }

    public final int getDifficulty() {
        return difficulty;
    }

    public final int getMapType() {
        return mapType;
    }

    public final int getTime() {
        return round.getTime();
    }

    public final int getNextTurn() {
        return round.getNextTurn();
    }

    public final int getRound() {
        return round.getRound();
    }

    public final boolean getPhase() {
        return round.getPhase();
    }

    public final int getEventType() {
        return round.getEventType();
    }

    public final String getData(int type, int index) {
        String ret = type == 1 ? "White" : "None";
        if (index >= 1 && index <= MAX_PLAYERS) {
            ret = players[index - 1].getData(type);
        }
        return ret;
    }

    public final int getResources(int type, int index) {
        int ret = 0;
        if (index >= 1 && index <= MAX_PLAYERS) {
            ret = players[index - 1].getResources(type);
        }
        return ret;
    }

    public final boolean getMule(int index) {
        boolean ret = false;
        if (index >= 1 && index <= MAX_PLAYERS) {
            ret = players[index - 1].getMule() != null;
        }
        return ret;
    }

    public final int getScore(int index) {
        int ret = 0;
        if (index >= 1 && index <= MAX_PLAYERS) {
            ret = players[index - 1].getScore();
        }
        return ret;
    }

    public final void saveGame() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("save.ser"));
        out.writeObject(difficulty);
        out.writeObject(mapType);
        out.writeObject(playerNum);
        out.writeObject(rand);
        out.writeObject(map);
        out.writeObject(round);
        out.writeObject(round.getTurnOrder());
        out.writeObject(round.getPhase());
        out.writeObject(store);
        out.writeObject(players);
        out.close();
    }

    @SuppressWarnings("unchecked")
    public final void loadGame() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("save.ser"));
        difficulty = (int) in.readObject();
        mapType = (int) in.readObject();
        playerNum = (int) in.readObject();
        rand = (Random) in.readObject();
        map = (World) in.readObject();
        round = (TurnProcessor) in.readObject();
        round.setTurnOrder((List<Actor>) in.readObject(), (boolean) in.readObject());
        store = (Business) in.readObject();
        players = (Actor[]) in.readObject();
        in.close();
    }
}
