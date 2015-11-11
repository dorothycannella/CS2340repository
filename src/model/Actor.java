package model;

public interface Actor {
    int DATA_SIZE = 3;
    int RESOURCES_SIZE = 5;
    int DEFAULT_MONEY = 1000;
    int FLAPPER_MONEY = 1600;
    int HUMAN_MONEY = 600;
    int BEGINNER_RESOURCES = 8;
    int STANDARD_RESOURCES = 4;
    int TILES_SCORE = 500;
    int FOOD_SCORE = 30;
    int ENERGY_SCORE = 25;
    int SMITHORE_SCORE = 50;
    int CRYSTITE_SCORE = 100;

    /**
     * Add resources based on the tiles and mules owned.
     */
    void calculateProduction();

    /**
     * Add the given quantity of the specified resource.
     * @param type id of the resource
     * @param quantity amount added
     */
    void addResources(int type, int quantity);

    /**
     * Add the given tile to the player's resources.
     * @param tile the tile
     */
    void addTile(Location tile);

    /**
     * Gets the player's id.
     * @return the id
     */
    int getId();

    /**
     * Gets the specified data about the player.
     * @param type id of the data
     * @return the data
     */
    String getData(int type);

    /**
     * Gets the specified number of resources owned by the player.
     * @param type id of the resource
     * @return amount of resource remaining
     */
    int getResources(int type);

    /**
     * Gets the player's mule.
     * @return the mule, null if no mule
     */
    Device getMule();

    /**
     * Adds the given mule to the player's resources.
     * @param m the mule
     */
    void setMule(Device m);

    /**
     * Calculates the player's score.
     * @return the score
     */
    int getScore();
}
