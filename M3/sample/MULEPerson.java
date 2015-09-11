package sample;

public class MULEPerson {

    private String race;
    private String color;
    private String name;
    private int money;

    public MULEPerson(String ra, String col, String nam) {
        race = ra;
        color = col;
        name = nam;
    }

    public void setRace(String ra) {
        race = ra;
    }

    public void setColor(String co){
        color = co;
    }

    public void setName(String nam) {
        name = nam;
    }

    public void setMoney(int mon) {
        money = mon;
    }

    public void addMoney(int mon) {
        money += mon;
    }

    public void reduceMoney(int mon) {
        money -= mon;
    }

    public int getMoney() {
        return money;
    }

    public String getName() {
        return name;
    }

    public String getRace() {
        return race;
    }

    public String getColor() {
        return color;
    }
}
