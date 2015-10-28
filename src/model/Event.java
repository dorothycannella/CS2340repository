package model;

public interface Event {
    void trigger(boolean first, int round, Actor current);

    void disarm();

    int getType();
}
