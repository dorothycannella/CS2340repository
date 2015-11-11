package controller;

import java.io.IOException;
import java.util.List;

public interface SystemManager {
    int MAX_PLAYERS = 4;

    /**
     * Creates a Player with the given parameters.
     * @param id player's number (1-4)
     * @param race player's race
     * @param color player's color
     * @param name player's name
     */
    void setPlayer(int id, String race, String color, String name);

    /**
     * Fills remaining players slots with CPUs.
     * @param colorCodes remaining unused colors
     */
    void setComputers(List<String> colorCodes);

    /**
     * Attempts to sell the specified tile to the current player.
     * @param i y coordinate for Tile
     * @param j x coordinate for Tile
     */
    void buyTile(int i, int j);

    /**
     * Checks if the specified tile was successfully sold.
     * @param i y coordinate for Tile
     * @param j x coordinate for Tile
     * @param id tile buyer
     * @return boolean confirming the purchase
     */
    boolean confirmPurchase(int i, int j, int id);

    /**
     * Pass the current player's turn.
     */
    void pass();

    /**
     * Reset the turn timer for the next player.
     */
    void resetTurnTime();

    /**
     * Gamble at the pub for the current player.
     */
    void pub();

    /**
     * Disable the recently activated event.
     */
    void disarm();

    /**
     * Attempts to sell one unit of the specified
     * resource to the current player.
     * @param order id of the resource
     */
    void buy(int order);

    /**
     * Attempts to buy one unit of the specified
     * resource from the current player.
     * @param order id of the resource
     */
    void sell(int order);

    /**
     * Checks the remaining number of the given resource in stock.
     * @param type id of the resource
     * @return number of remaining resource
     */
    int getStock(int type);

    /**
     * Gets the type of tile at the specified coordinates.
     * @param i y coordinate of the Tile
     * @param j x coordinate of the Tile
     * @return the type
     */
    String getTileType(int i, int j);

    /**
     * Gets the owner of the tile at the specified coordinates.
     * @param i y coordinate of the Tile
     * @param j x coordinate of the Tile
     * @return id of the owner
     */
    int getOwner(int i, int j);

    /**
     * Gets the number of mountains at the specified tile.
     * @param i y coordinate of the Tile
     * @param j x coordinate of the Tile
     * @return the number of mountains in the tile
     */
    int getNumMountains(int i, int j);

    /**
     * Checks if the specified tile has a mule
     * @param i y coordinate of the Tile
     * @param j x coordinate of the Tile
     * @return if the tile has a mule
     */
    boolean hasMule(int i, int j);

    /**
     * Places the current player's mule on the specified tile.
     * @param i y coordinate of the Tile
     * @param j x coordinate of the Tile
     */
    void placeMule(int i, int j);

    /**
     * Removes the current player's mule.
     */
    void removeMule();

    /**
     * Gets the number of players in this game.
     * @return the number of players
     */
    int getPlayerNum();

    /**
     * Gets the difficulty of this game.
     * @return the difficulty
     */
    int getDifficulty();

    /**
     * Gets the map type of this game.
     * @return the map type
     */
    int getMapType();

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
     * Gets the current phase.
     * @return true if selection, false if main
     */
    boolean getPhase();

    /**
     * Gets the type of event that was recently triggered.
     * @return the id of the event
     */
    int getEventType();

    /**
     * Gets specific information about the specified player.
     * @param type id of the information
     * @param index id of the player
     * @return the data requested
     */
    String getData(int type, int index);

    /**
     * Gets the specified player's remaining amount of a specified resource.
     * @param type id of the resource
     * @param index id of the player
     * @return the amount of resource
     */
    int getResources(int type, int index);

    /**
     * Checks if the specified player has a mule.
     * @param index id of the player
     * @return true if he/she has a Mule
     */
    boolean getMule(int index);

    /**
     * Gets the specified player's score.
     * @param index id of the player
     * @return the score
     */
    int getScore(int index);

    /**
     * Serialize the game data and write it to a file.
     * @throws IOException
     */
    void saveGame() throws IOException;

    /**
     * Read data from a file and load it into the game.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    void loadGame() throws IOException, ClassNotFoundException;
}
