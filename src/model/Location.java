package model;

public interface Location {
    int[] calculateProduction();

    void setOwner(int o);

    void setMountains(int m);

    void setRiver();

    void placeMule(Device m);

    int getOwner();

    int getMountains();

    boolean hasMule();
}
