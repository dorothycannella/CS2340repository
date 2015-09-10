package sample;

/**
 * Created by Jackie (/10/15)
 */
public class GameSetUp {

    private static int playerNum;
    private static int difficulty;
    private static int map;


    public static void setPlayerNum(int player) {
        playerNum = player;
    }

    public static void setDifficulty(int dif){
        difficulty = dif;
    }

    public static void setMap(int nam) {
        map = nam;
    }

    public static int getPlayerNum(){
        return playerNum;
    }

    public static int getDifficulty() {
        return difficulty;
    }

    public static int getMap(){
        return map;
    }
}
