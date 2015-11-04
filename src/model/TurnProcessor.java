package model;

public interface TurnProcessor {
    void stopTimer();
    
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

    Object[] getNext();

    void setNext(Object[] loadNext);
}
