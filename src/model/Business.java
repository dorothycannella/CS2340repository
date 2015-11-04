package model;

public interface Business {
    void buy(int order, Actor buyer);

    void sell(int order, Actor seller);

    int getStock(int type);

    int[] getStockArray();

    void setStockArray(Object[] stock);
}
