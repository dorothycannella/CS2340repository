package model;

import java.util.List;

public interface TurnProcessor {
    /**
     * Adds the given players for later use in determining the turn order.
     * @param players array of all the players
     */
    void addPlayers(Actor[] players);

    /**
     * Attempts to sell the given tile to the current player.
     * @param tile the tile
     */
    void buyTile(Location tile);

    /**
     * Reset the turn timer for the next player.
     */
    void resetTurnTime();

    /**
     * Pass the current player's turn.
     */
    void pass();

    /**
     * Gamble at the pub for the current player.
     */
    void gamble();

    /**
     * Disable the recently activated event.
     */
    void disarm();

    /**
     * Gets the remaining time of the current player's turn.
     * @return the time
     */
    int getTime();

    /**
     * Gets the current player.
     * @return id of the current player
     */
    int getNextTurn();

    /**
     * Gets the current round.
     * @return the round
     */
    int getRound();

    /**
     * Gets the type of event that was recently triggered.
     * @return the id of the event
     */
    int getEventType();

    /**
     * Gets the current phase.
     * @return true if selection, false if main
     */
    boolean getPhase();

    /**
     * Get a list of the remaining players for the current round.
     * @return the remaining players
     */
    List<Actor> getTurnOrder();

    /**
     * Reset the round using the given list of players.
     * @param load the players
     * @param phase true if selection, false if main
     */
    void setTurnOrder(List<Actor> load, boolean phase);
}
