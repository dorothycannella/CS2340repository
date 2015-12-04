package model;

interface Event {
    int NUM_EVENTS = 10;

    /**
     * Add/remove resources from the current player based
     * on the type of event that was triggered.
     * @param first true if the player has the lowest score
     * @param round current round
     * @param current current player
     */
    void trigger(boolean first, int round, Actor current);

    /**
     * Deactivates the recently triggered event.
     */
    void disarm();

    /**
     * Get the type of this event.
     * @return the type
     */
    int getType();
}
