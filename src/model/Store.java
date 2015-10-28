package model;

public class Store implements Business {
    private static final int[] PRICE = {30, 25, 50, 100, 125, 150, 175, 200};
    private int[] stock;

    public Store(int difficulty) {
        stock = new int[5];
        stock[0] = difficulty == 1 ? 16 : 8;
        stock[1] = stock[0];
        stock[2] = difficulty == 1 ? 0 : 8;
        stock[4] = difficulty == 1 ? 25 : 14;
    }

    public void buy(int order, Actor buyer) {
        if (order < 4 && buyer.getResources(0) >= PRICE[order]
                && stock[order] > 0) {
            buyer.addResources(order + 1, 1);
            buyer.addResources(0, PRICE[order] * -1);
            stock[order]--;
        } else if (order >= 4 && buyer.getResources(0) >= PRICE[order]
                && stock[4] > 0 && buyer.getMule() == null) {
            buyer.setMule(new Mule(order - 4));
            buyer.addResources(0, PRICE[order] * -1);
            stock[4]--;
        }
    }

    public void sell(int order, Actor seller) {
        Device mule = seller.getMule();
        if (order < 4 && seller.getResources(order + 1) > 0) {
            seller.addResources(order + 1, -1);
            seller.addResources(0, PRICE[order]);
            stock[order]++;
        } else if (order >= 4 && mule != null && mule.getType() == order - 4) {
            seller.setMule(null);
            seller.addResources(0, PRICE[order]);
            stock[4]++;
        }
    }

    public int getStock(int type) {
        return stock[type];
    }
}
