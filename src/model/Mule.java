package model;

import java.io.Serializable;

public class Mule implements Device, Serializable {
    private final int type;

    @SuppressWarnings("unused")
    public Mule(int t) {
        type = t;
    }

    public final int getType() {
        return type;
    }
}