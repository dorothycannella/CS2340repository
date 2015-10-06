package model;

import java.util.*;

public class Round {
    private int round;
    private int pass;
    private int time;
    private Timer timer;
    private Random rand;
    private PriorityQueue<Player> next;
    private Player[] players;

    public Round() {
        round = 1;
        pass = 0;
        time = 5;
        timer = new Timer();
        rand = new Random();
        next = new PriorityQueue<>(4, (p1, p2) -> p1.getId() - p2.getId());
    }

    public void stopTimer() {
        timer.cancel();
        timer.purge();
    }

    public void addPlayers(Player[] players) {
        this.players = players;
        Collections.addAll(next, players);
    }

    public void buyTile(Tile tile) {
        Player buyer = next.peek();
        if (next.comparator() != null && tile.getOwner() == 0 && (round <= 2
                || buyer.getMoney() >= 300)) {
            tile.setOwner(buyer.getId());
            buyer.addTile(tile);
            buyer.addMoney(round > 2 ? -300 : 0);
            nextTurn();
        }
    }

    private void nextTurn() {
        next.poll();
        if (next.comparator() == null) {
            resetTurnTime();
        }
        if (next.size() == 0) {
            nextRound();
        }
    }

    private void nextRound() {
        if (pass == 4 && next.comparator() != null) {
            round = 0;
            next = new PriorityQueue<>(4);
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
        Collections.addAll(next, players);
        pass = 0;
        round++;
    }

    private void resetTurnTime() {
        Player current = next.peek();
        time = 5;
        if (current.getFood() >= 3 + (round - 1) / 4) {
            time = 50;
            current.addFood(-3 - (round - 1) / 4);
        } else if (current.getFood() > 0) {
            time = 30;
            current.addFood(current.getFood() * -1);
        }
    }

    public void pass() {
        pass++;
        nextTurn();
    }

    public void gamble() {
        Player current = next.peek();
        int roundBonus = (round / 4 + 1) * 50;
        int timeBonus = ((time + 1) / 13 + 1) * 50;
        int gambleBonus = roundBonus + rand.nextInt(timeBonus + 1);
        if (gambleBonus > 250) {
            gambleBonus = 250;
        }
        current.addMoney(gambleBonus);
        nextTurn();
    }

    public int getTime() {
        return time;
    }

    public int getNextTurn() {
        Player current = next.peek();
        return current.getId();
    }

    public int getRound() {
        return round;
    }

    public boolean getPhase() {
        return next.comparator() != null;
    }
}
