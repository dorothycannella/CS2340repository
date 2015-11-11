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
