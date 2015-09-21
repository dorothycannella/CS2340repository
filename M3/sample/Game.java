package sample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Random;

public class Game {
    private static int playerNum = 0;
    private static int difficulty = 0;
    private static int mapType = 0;
    private static int round = 1;
    private static int selectionIndex = 0;
    private static int pass = 0;
    private static boolean selectionPhase = true;
    private static MULEPerson[] players = new MULEPerson[4];
    private static MULEPerson[] selectionList = new MULEPerson[8];
    private static Tile[][] map = new Tile[5][9];
    private static PriorityQueue<MULEPerson> next = new PriorityQueue<>(4);

    public static void setPlayerNum(int p) {
        playerNum = p;
    }

    public static void setDifficulty(int d) {
        difficulty = d;
    }

    public static void setMapType(int m) {
        mapType = m;
    }

    public static void setPlayer(MULEPerson p, int n) {
        players[n] = p;
    }

    public static void setComputers(ArrayList<String> colorCodes) {
        String[] raceCodes = {"Human", "Flapper", "Bonzoid", "Buzzite",
                "Ugaite"};
        Random rand = new Random();
        for (int i = 4; i > playerNum; i--) {
            players[i - 1] = new MULEPerson(i, raceCodes[rand.nextInt(5)],
                    colorCodes.get(rand.nextInt(colorCodes.size())),
                    "CPU" + (i - playerNum));
            colorCodes.remove(players[i - 1].getColor());
        }
        for (int i = 0; i < players.length; i++) {
            selectionList[i] = players[i];
            selectionList[i + 4] = players[3 - i];
        }
        Collections.addAll(next, players);
    }

    public static void makeMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = new Tile();
                if (i == 2 && j == 0 || i == 0 && j == 6 || i == 1 && j == 8) {
                    map[i][j].setMountains(3);
                } else if (i - j == 2 || i == 3 && j == 6 || i == 4 && j == 8) {
                    map[i][j].setMountains(2);
                } else if (i + j == 2 || i == 2 && j == 8) {
                    map[i][j].setMountains(1);
                } else if (j == 4) {
                    map[i][j].setRiver(true);
                }
            }
        }
    }

    public static boolean buyTile(int i, int j) {
        boolean ret = false;
        if (selectionPhase && map[i][j].getOwner() == 0 && (round <= 2
                || selectionList[selectionIndex % 8].getMoney() >= 300)) {
            MULEPerson cur = selectionList[selectionIndex % 8];
            map[i][j].setOwner(cur.getId());
            cur.addTile(map[i][j]);
            cur.addMoney(round > 2 ? -300 : 0);
            ret = true;
            nextTurn();
        } else if (map[i][j].getOwner() == 0 && next.peek().getMoney() >= 300) {
            map[i][j].setOwner(next.peek().getId());
            next.peek().addTile(map[i][j]);
            next.peek().addMoney(-300);
            ret = true;
        }
        return ret;
    }

    private static void nextTurn() {
        next.poll();
        selectionIndex++;
        if (next.size() == 0) {
            nextRound();
        }
    }

    private static void nextRound() {
        if (pass == 4 && selectionPhase) {
            endSelectionPhase();
        }
        Collections.addAll(next, players);
        pass = 0;
        round++;
    }

    private static void endSelectionPhase() {
        selectionPhase = false;
        round = 0;
        next.clear();
    }

    public static int getPlayerNum() {
        return playerNum;
    }

    public static int getDifficulty() {
        return difficulty;
    }

    public static int getMapType() {
        return mapType;
    }

    public static int getRound() {
        return round;
    }

    public static boolean getPhase() {
        return selectionPhase;
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

    public static int getNextTurn() {
        MULEPerson ret = next.peek();
        if (selectionPhase) {
            ret = selectionList[selectionIndex % 8];
        }
        return ret.getId();
    }

    public static String getTileType(int i, int j) {
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

    public static int getOwner(int i, int j) {
        return map[i][j].getOwner();
    }

    public static int getNumMountains(int i, int j) {
        return map[i][j].getMountains();
    }

    public static void pass() {
        pass++;
        nextTurn();
    }
}
