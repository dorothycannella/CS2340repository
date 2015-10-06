package controller;

import model.Map;
import model.Player;
import model.Round;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    private static int playerNum = 0;
    private static int difficulty = 0;
    private static int mapType = 0;
    private static Random rand = new Random();
    private static Map map = new Map();
    private static Round round = new Round();
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
                players[i - 1] = new Player(i, raceCodes[rand.nextInt(5)],
                        colorCodes.get(rand.nextInt(colorCodes.size())),
                        "CPU" + (i - playerNum));
                colorCodes.remove(players[i - 1].getColor());
            }
        }
        round.addPlayers(players);
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

    public static void pub() {
        round.gamble();
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

    public static int getPlayerNum() {
        return playerNum;
    }

    public static void setPlayerNum(int p) {
        if (playerNum == 0) {
            playerNum = p;
        }
    }

    public static int getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(int d) {
        if (difficulty == 0) {
            difficulty = d;
        }
    }

    public static int getMapType() {
        return mapType;
    }

    public static void setMapType(int m) {
        if (mapType == 0) {
            mapType = m;
        }
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

    public static String getName(int index) {
        String ret = "None";
        if (index >= 1 && index <= 4) {
            ret = players[index - 1].getName();
        }
        return ret;
    }

    public static String getRace(int index) {
        String ret = "Human";
        if (index >= 1 && index <= 4) {
            ret = players[index - 1].getRace();
        }
        return ret;
    }

    public static String getColor(int index) {
        String ret = "White";
        if (index >= 1 && index <= 4) {
            ret = players[index - 1].getColor();
        }
        return ret;
    }

    public static int getMoney(int index) {
        int ret = 0;
        if (index >= 1 && index <= 4) {
            ret = players[index - 1].getMoney();
        }
        return ret;
    }

    public static int getFood(int index) {
        int ret = 0;
        if (index >= 1 && index <= 4) {
            ret = players[index - 1].getFood();
        }
        return ret;
    }

    public static int getEnergy(int index) {
        int ret = 0;
        if (index >= 1 && index <= 4) {
            ret = players[index - 1].getEnergy();
        }
        return ret;
    }

    public static int getSmithore(int index) {
        int ret = 0;
        if (index >= 1 && index <= 4) {
            ret = players[index - 1].getSmithore();
        }
        return ret;
    }

    public static int getCrystite(int index) {
        int ret = 0;
        if (index >= 1 && index <= 4) {
            ret = players[index - 1].getCrystite();
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
