package model;

public class Mule implements Device {
    private int type;

    public Mule(int t) {
        type = t;
    }

    public int getType() {
        return type;
    }
}