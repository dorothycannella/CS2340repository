package model;

public interface Actor {
    void calculateProduction();

    void addResources(int type, int quantity);

    void addTile(Location tile);

    int getId();

    String getData(int type);

    int getResources(int type);

    Device getMule();

    void setMule(Device m);

    int getScore();
}
