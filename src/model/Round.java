package model;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Round implements TurnProcessor, Serializable {
    private int round;
    private int pass;
    private int time;
    private boolean timeActive;
    private transient PriorityQueue<Actor> next;
    private Actor[] players;
    private final Event event;
    private static final long serialVersionUID = 777469922665534901L;

    @SuppressWarnings("unused")
    public Round() {
        round = 1;
        pass = 0;
        time = 5;
        event = new RandomEvent();
    }

    public final void addPlayers(Actor[] players) {
        this.players = players;
        next = new PriorityQueue<>(players.length,
                ((p1, p2) -> p1.getId() - p2.getId()));
        Collections.addAll(next, players);
    }

    public final void buyTile(Location tile) {
        Actor buyer = next.peek();
        if (next.comparator() != null && tile.getOwner() == 0 && (round <= 2
                || buyer.getResources(0) >= 300)) {
            tile.setOwner(buyer.getId());
            buyer.addTile(tile);
            buyer.addResources(0, round > 2 ? -300 : 0);
            nextTurn();
        }
    }

    private void nextTurn() {
        timeActive = false;
        next.poll();
        if (next.size() == 0) {
            nextRound();
        }
        if (next.comparator() == null && Math.random() * 100 < 27) {
            event.trigger(next.size() == players.length, round, next.peek());
        }
    }

    private void nextRound() {
        if (pass == players.length && next.comparator() != null) {
            round = 0;
            refreshGame();
        }
        for (Actor player: players) {
            player.calculateProduction();
        }
        Collections.addAll(next, players);
        pass = 0;
        round++;
    }

    private void refreshGame() {
        Timer timer = new Timer();
        next = new PriorityQueue<>(players.length);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                time--;
                if (time <= 0) {
                    nextTurn();
                }
            }
        }, 0, 1000);
    }

    public final void resetTurnTime() {
        Actor current = next.peek();
        if (!timeActive && current.getResources(1) >= 3 + (round - 1) / 4) {
            time = 50;
            current.addResources(1, -3 - (round - 1) / 4);
        } else if (!timeActive && current.getResources(1) > 0) {
            time = 30;
            current.addResources(1, current.getResources(1) * -1);
        } else if (!timeActive) {
            time = 5;
        }
        timeActive = true;
    }

    public final void pass() {
        pass++;
        nextTurn();
    }

    public final void gamble() {
        Actor current = next.peek();
        int roundBonus = (round / 4 + 1) * 50;
        int timeBonus = ((time + 1) / 13 + 1) * 50;
        int gambleBonus = roundBonus + (int) (Math.random() * (timeBonus + 1));
        if (gambleBonus > 250) {
            gambleBonus = 250;
        }
        current.addResources(0, gambleBonus);
        nextTurn();
    }

    public final void disarm() {
        event.disarm();
    }

    public final int getTime() {
        return time;
    }

    public final int getNextTurn() {
        Actor current = next.peek();
        return current.getId();
    }

    public final int getRound() {
        return round;
    }

    public final int getEventType() {
        return event.getType();
    }

    public final boolean getPhase() {
        return next.comparator() != null;
    }

    public final List<Actor> getTurnOrder() {
        return next.stream().collect(Collectors.toList());
    }

    public final void setTurnOrder(List<Actor> load, boolean phase) {
        if (phase) {
            next = new PriorityQueue<>(players.length,
                    ((p1, p2) -> p1.getId() - p2.getId()));
        } else {
            refreshGame();
        }
        next.addAll(load.stream().collect(Collectors.toList()));
    }
}
