package model;

public class RandomEvent implements Event {
    private int type;

    public RandomEvent() {
        type = -1;
    }

    public void trigger(boolean first, int round, Actor current) {
        type = (int) (Math.random() * 7);
        int m = (round / 4 + 1) * 25;
        if (type == 0) {
            current.addResources(1, 3);
            current.addResources(2, 2);
        } else if (type == 1) {
            current.addResources(3, 2);
        } else if (type == 2) {
            current.addResources(0, 8 * m);
        } else if (type == 3) {
            current.addResources(0, 2 * m);
        } else if (!first && type == 4) {
            current.addResources(0, -4 * m);
        } else if (!first && type == 5) {
            current.addResources(1, current.getResources(1) / -2);
        } else if (!first && type == 6) {
            current.addResources(0, -6 * m);
        } else {
            disarm();
        }
    }

    public void disarm() {
        type = -1;
    }

    public int getType() {
        return type;
    }
}
