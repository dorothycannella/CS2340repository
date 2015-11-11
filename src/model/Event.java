package model;

interface Event {
    int NUM_EVENTS = 7;

    void trigger(boolean first, int round, Actor current);

    void disarm();

    int getType();
}
