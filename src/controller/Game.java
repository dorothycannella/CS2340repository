package controller;

import model.Map;
import model.Player;
import model.Round;
import model.Store;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    private static int playerNum = 0;
    private static int difficulty = 0;
    private static int mapType = 0;
    private static Random rand = new Random();
    private static Map map = new Map();
    private static Round round = new Round();
    private static Store store;
    private static Player[] players = new Player[4];

    public static void stopTimer() {
        round.stopTimer();
    }

    public static void setPlayer(Player p, int n) {
        if (players[n] == null) {
            players[n] = p;
        }
    }

    public static void setComputers(ArrayList<String> colorCodes) {
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

    public static void buyTile(int i, int j) {
        round.buyTile(map.getTile(i, j));
    }

    public static boolean confirmPurchase(int i, int j, int id) {
        return map.getOwner(i, j) == id;
    }

    public static void pass() {
        round.pass();
    }

    public static void resetTurnTime() {
        round.resetTurnTime();
    }

    public static void pub() {
        round.gamble();
    }

    public static void buy(int order) {
        store.buy(order, players[round.getNextTurn() - 1]);
    }

    public static void sell(int order) {
        store.sell(order, players[round.getNextTurn() - 1]);
    }

    public static int getStock(int type) {
        return store.getStock(type);
    }

    public static String getTileType(int i, int j) {
        return map.getTileType(i, j);
    }

    public static int getOwner(int i, int j) {
        return map.getOwner(i, j);
    }

    public static int getNumMountains(int i, int j) {
        return map.getMountains(i, j);
    }

    public static boolean hasMule(int i, int j) {
        return map.hasMule(i, j);
    }

    public static void placeMule(int i, int j) {
        map.placeMule(i, j, players[round.getNextTurn() - 1].getMule());
        players[round.getNextTurn() - 1].setMule(null);
    }

    public static void removeMule() {
        players[round.getNextTurn() - 1].setMule(null);
    }

    public static int getPlayerNum() {
        return playerNum;
    }

    public static void setPlayerNum(int p) {
        playerNum = p;
    }

    public static int getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(int d) {
        difficulty = d;
    }

    public static int getMapType() {
        return mapType;
    }

    public static void setMapType(int m) {
        mapType = m;
    }

    public static int getTime() {
        return round.getTime();
    }

    public static int getNextTurn() {
        return round.getNextTurn();
    }

    public static int getRound() {
        return round.getRound();
    }

    public static boolean getPhase() {
        return round.getPhase();
    }

    public static String getData(int type, int index) {
        String ret = type == 1 ? "White" : "None";
        if (index >= 1 && index <= 4) {
            ret = players[index - 1].getData(type);
        }
        return ret;
    }

    public static int getResources(int type, int index) {
        int ret = 0;
        if (index >= 1 && index <= 4) {
            ret = players[index - 1].getResources(type);
        }
        return ret;
    }

    public static boolean getMule(int index) {
        boolean ret = false;
        if (index >= 1 && index <= 4) {
            ret = players[index - 1].getMule() != null;
        }
        return ret;
    }

    public static int getScore(int index) {
        int ret = 0;
        if (index >= 1 && index <= 4) {
            ret = players[index - 1].getScore();
        }
        return ret;
    }
}
