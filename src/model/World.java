package model;

public interface World {
    void placeMule(int i, int j, Device mule);

    Location getTile(int i, int j);

    String getTileType(int i, int j);

    int getOwner(int i, int j);

    int getMountains(int i, int j);

    boolean hasMule(int i, int j);
}
