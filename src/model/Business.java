package model;

public interface Business {
    int STOCK_SIZE = 5;
    int BEGINNER_BASICS = 16;
    int STANDARD_BASICS = 8;
    int BEGINNER_SMITHORE = 0;
    int STANDARD_SMITHORE = 8;
    int BEGINNER_CRYSTITE = 25;
    int STANDARD_CRYSTITE = 14;

    /**
     * Sell the specified resource to the given player.
     * @param order id of the resource
     * @param buyer the player
     */
    void buy(int order, Actor buyer);

    /**
     * Buy the specified resource from the player.
     * @param order id of the resource
     * @param seller the player
     */
    void sell(int order, Actor seller);

    /**
     * Get the remaining stock of the specified resource.
     * @param type id of the resource
     * @return amount remaining
     */
    int getStock(int type);
}
