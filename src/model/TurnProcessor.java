package model;

import java.util.List;

public interface TurnProcessor {
    void addPlayers(Actor[] players);

    void buyTile(Location tile);

    void resetTurnTime();

    void pass();

    void gamble();

    void disarm();

    int getTime();

    int getNextTurn();

    int getRound();

    int getEventType();

    boolean getPhase();

    List<Actor> getTurnOrder();

    void setTurnOrder(List<Actor> load, boolean phase);
}
