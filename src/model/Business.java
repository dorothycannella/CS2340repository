package model;

public interface Business {
    int STOCK_SIZE = 5;
    int BEGINNER_BASICS = 16;
    int STANDARD_BASICS = 8;
    int BEGINNER_SMITHORE = 0;
    int STANDARD_SMITHORE = 8;
    int BEGINNER_CRYSTITE = 25;
    int STANDARD_CRYSTITE = 14;

    void buy(int order, Actor buyer);

    void sell(int order, Actor seller);

    int getStock(int type);
}
