package model;

public interface Location {
    /**
     * Calculate resources produced by the tile.
     * @return amount of each resource produced
     */
    int[] calculateProduction();

    /**
     * Set the specified player as the owner of this tile.
     * @param o id of the player
     */
    void setOwner(int o);

    /**
     * Set the number of mountains in this tile
     * @param m number of mountains
     */
    void setMountains(int m);

    /**
     * Set if this tile has a river.
     */
    void setRiver();

    /**
     * Place the given mule on this tile.
     * @param m the mule
     */
    void placeMule(Device m);

    /**
     * Get the owner of this tile.
     * @return id of the owner
     */
    int getOwner();

    /**
     * Get the number of mountains in this tile.
     * @return number of mountains
     */
    int getMountains();

    /**
     * Check if this tile has a river.
     * @return true if it has a river
     */
    boolean hasMule();
}
