package model;

public interface World {
    int WIDTH = 9;
    int HEIGHT = 5;

    /**
     * Places the given mule on the specified tile.
     * @param i y coordinate of the Tile
     * @param j x coordinate of the Tile
     * @param mule the mule
     */
    void placeMule(int i, int j, Device mule);

    /**
     * Get the specified tile.
     * @param i y coordinate of the Tile
     * @param j x coordinate of the Tile
     * @return the tile
     */
    Location getTile(int i, int j);

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
    int getMountains(int i, int j);

    /**
     * Checks if the specified tile has a mule
     * @param i y coordinate of the Tile
     * @param j x coordinate of the Tile
     * @return if the tile has a mule
     */
    boolean hasMule(int i, int j);
}
