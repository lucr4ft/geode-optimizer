package net.ddns.lucraft.geodeoptimizer.core;

public class Position {

    private int x = 0;
    private int y = 0;
    private int z = 0;

    public Position(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }
}
