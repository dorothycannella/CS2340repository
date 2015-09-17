package sample;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    private static int playerNum;
    private static int difficulty;
    private static int map;
    private static MULEPerson[] players = new MULEPerson[4];

    public static void setPlayerNum(int p) {
        playerNum = p;
    }

    public static void setDifficulty(int d) {
        difficulty = d;
    }

    public static void setMap(int m) {
        map = m;
    }

    public static void setPlayer(MULEPerson p, int n) {
        players[n] = p;
    }

    public static void setComputers(ArrayList<String> colorCodes) {
        String[] raceCodes = {"Human", "Flapper", "Bonzoid", "Buzzite",
                "Ugaite"};
        Random rand = new Random();
        for (int i = 4; i > playerNum; i--) {
            players[i - 1] = new MULEPerson(raceCodes[rand.nextInt(5)],
                    colorCodes.get(rand.nextInt(colorCodes.size())),
                    "CPU" + (i - playerNum));
            colorCodes.remove(players[i - 1].getColor());
        }
    }

    public static int getPlayerNum() {
        return playerNum;
    }

    public static int getDifficulty() {
        return difficulty;
    }

    public static int getMap() {
        return map;
    }

    public static MULEPerson[] getPlayers() {
        return players;
    }
}
