package model;

import java.io.Serializable;

public class Mule implements Device, Serializable {
    private int type;

    public Mule(int t) {
        type = t;
    }

    public int getType() {
        return type;
    }
}