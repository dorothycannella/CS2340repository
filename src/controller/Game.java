package controller;

import model.Player;
import model.Map;

import java.util.*;

public class Game {
    private static int playerNum = 0;
    private static int difficulty = 0;
    private static int mapType = 0;
    private static int round = 1;
    private static int selectionIndex = 0;
    private static int pass = 0;
    private static int time = 5;
    private static boolean selectionPhase = true;
    private static boolean timeActive = false;
    private static Player[] players = new Player[4];
    private static Map map = new Map();
    private static PriorityQueue<Player> next = new PriorityQueue<>(4);
    private static Timer timer = new Timer();
    private static Random rand = new Random();

    public static void setPlayerNum(int p) {
        if (playerNum == 0) {
            playerNum = p;
        }
    }

    public static void setDifficulty(int d) {
        if (difficulty == 0) {
            difficulty = d;
        }
    }

    public static void setMapType(int m) {
        if (mapType == 0) {
            mapType = m;
        }
    }

    public static void setPlayer(Player p, int n) {
        if (players[n] == null) {
            players[n] = p;
        }
    }

    public static void setComputers(ArrayList<String> colorCodes) {
        if (next.size() == 0) {
            String[] raceCodes = {"Human", "Flapper", "Bonzoid", "Buzzite",
                    "Ugaite"};
            for (int i = 4; i > playerNum; i--) {
                players[i - 1] = new Player(i, raceCodes[rand.nextInt(5)],
                        colorCodes.get(rand.nextInt(colorCodes.size())),
                        "CPU" + (i - playerNum));
                colorCodes.remove(players[i - 1].getColor());
            }
            Collections.addAll(next, players);
        }
    }

    public static boolean buyTile(int i, int j) {
        boolean ret = false;
        if (selectionPhase && map.getTile(i, j).getOwner() == 0 && (round <= 2
                || players[selectionIndex].getMoney() >= 300)) {
            Player cur = players[selectionIndex];
            map.getTile(i, j).setOwner(cur.getId());
            cur.addTile(map.getTile(i, j));
            cur.addMoney(round > 2 ? -300 : 0);
            ret = true;
            nextTurn();
        }
        return ret;
    }

    private static void nextTurn() {
        timeActive = false;
        next.poll();
        if (next.size() == 0) {
            nextRound();
        } else {
            selectionIndex = round % 2 == 0 && selectionPhase ?
                    selectionIndex - 1 : selectionIndex + 1;
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
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                time--;
                if (time <= 0) {
                    nextTurn();
                }
            }
        }, 0, 1000);
    }

    public static void resetTurnTime() {
        if (!timeActive) {
            time = 5;
            if (players[getNextTurn() - 1].getFood() >= 3 + (round - 1) / 4) {
                time = 50;
                players[getNextTurn() - 1].addFood(-3 - (round - 1) / 4);
            } else if (players[getNextTurn() - 1].getFood() > 0) {
                time = 30;
                players[getNextTurn() - 1].addFood(
                        players[getNextTurn() - 1].getFood() * -1);
            }
            timeActive = true;
        }
    }

    public static void gamble() {
        int roundBonus = (round / 4 + 1) * 50;
        int timeBonus = ((time + 1) / 13 + 1) * 50;
        int gambleBonus = roundBonus + rand.nextInt(timeBonus + 1);
        if (gambleBonus > 250) {
            gambleBonus = 250;
        }
        players[getNextTurn() - 1].addMoney(gambleBonus);
        nextTurn();
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
        Player ret = next.peek();
        if (selectionPhase) {
            ret = players[selectionIndex];
        }
        return ret.getId();
    }

    public static int getTime() {
        return time;
    }

    public static String getTileType(int i, int j) {
        String ret;
        if (i == 2 && j == 4) {
            ret = "Town";
        } else if (j == 4) {
            ret = "River";
        } else if (map.getTile(i, j).getMountains() > 0) {
            ret = "Mountains";
        } else {
            ret = "Plains";
        }
        return ret;
    }

    public static int getOwner(int i, int j) {
        return map.getTile(i, j).getOwner();
    }

    public static int getNumMountains(int i, int j) {
        return map.getTile(i, j).getMountains();
    }

    public static void pass() {
        pass++;
        nextTurn();
    }
}
