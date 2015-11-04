package controller;

import java.io.IOException;
import java.util.List;

public interface SystemManager {
    void stopTimer();

    void setPlayer(int id, String race, String color, String name);

    void setComputers(List<String> colorCodes);

    void buyTile(int i, int j);

    boolean confirmPurchase(int i, int j, int id);

    void pass();

    void resetTurnTime();

    void pub();

    void disarm();

    void buy(int order);

    void sell(int order);

    int getStock(int type);

    String getTileType(int i, int j);

    int getOwner(int i, int j);

    int getNumMountains(int i, int j);

    boolean hasMule(int i, int j);

    void placeMule(int i, int j);

    void removeMule();

    int getPlayerNum();

    int getDifficulty();

    int getMapType();

    int getTime();

    int getNextTurn();

    int getRound();

    boolean getPhase();

    int getEventType();

    String getData(int type, int index);

    int getResources(int type, int index);

    boolean getMule(int index);

    int getScore(int index);

    void saveGame() throws IOException;

    void loadGame() throws IOException, ClassNotFoundException;
}
