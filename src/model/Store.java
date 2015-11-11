package model;

import java.io.Serializable;

public class Store implements Business, Serializable {
    private static final int[] PRICE = {30, 25, 50, 100, 125, 150, 175, 200};
    private static final long serialVersionUID = -5822034114816416291L;
    private final int[] stock;

    @SuppressWarnings("unused")
    public Store(int difficulty) {
        stock = new int[STOCK_SIZE];
        stock[0] = difficulty == 1 ? BEGINNER_BASICS : STANDARD_BASICS;
        stock[1] = stock[0];
        stock[2] = difficulty == 1 ? BEGINNER_SMITHORE : STANDARD_SMITHORE;
        stock[4] = difficulty == 1 ? BEGINNER_CRYSTITE : STANDARD_CRYSTITE;
    }

    @Override
    public final void buy(int order, Actor buyer) {
        if (order < STOCK_SIZE - 1 && buyer.getResources(0) >= PRICE[order]
                && stock[order] > 0) {
            buyer.addResources(order + 1, 1);
            buyer.addResources(0, PRICE[order] * -1);
            stock[order]--;
        } else if (order >= STOCK_SIZE - 1 && buyer.getResources(0)
                >= PRICE[order] && stock[STOCK_SIZE - 1] > 0
                && buyer.getMule() == null) {
            buyer.setMule(new Mule(order - STOCK_SIZE + 1));
            buyer.addResources(0, PRICE[order] * -1);
            stock[STOCK_SIZE - 1]--;
        }
    }

    @Override
    public final void sell(int order, Actor seller) {
        Device mule = seller.getMule();
        if (order < STOCK_SIZE - 1 && seller.getResources(order + 1) > 0) {
            seller.addResources(order + 1, -1);
            seller.addResources(0, PRICE[order]);
            stock[order]++;
        } else if (order >= STOCK_SIZE - 1 && mule != null
                && mule.getType() == order - STOCK_SIZE + 1) {
            seller.setMule(null);
            seller.addResources(0, PRICE[order]);
            stock[STOCK_SIZE - 1]++;
        }
    }

    @Override
    public final int getStock(int type) {
        return stock[type];
    }
}
