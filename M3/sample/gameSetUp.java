package sample;

/**
 * Created by Jackie (/10/15)
 */
public class gameSetUp {

    private int playerNum;
    private String difficulty;
    private String map;

    public gameSetUp(int player, String dif, String ma) {
        playerNum = player;
        difficulty = dif;
        map = ma;
    }

    public void setPlayerNum(int player) {
        playerNum = player;
    }

    public void setDifficulty(String dif){
        difficulty = dif;
    }

    public void setMap(String nam) {
        map = ma;
    }

    public String getPlayerNum(){
        return playerNum;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getMap(){
        return map;
    }
}
