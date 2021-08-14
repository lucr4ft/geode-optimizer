package net.lucraft.geodeoptimizer.core.blocks;

import net.lucraft.geodeoptimizer.core.util.Vector;

public enum Direction {
    UP(new Vector(0, 1, 0)),
    DOWN(new Vector(0, -1, 0)),
    NORTH(new Vector(0, 0, -1)),
    SOUTH(new Vector(0, 0, 1)),
    WEST(new Vector(-1, 0, 0)),
    EAST(new Vector(1, 0, 0));

    private Vector vector;

    Direction(Vector vector) {
        this.vector = vector;
    }

    public Vector getVector() {
        return vector;
    }

    public int getOffsetX() {
        return this.vector.getX();
    }

    public int getOffsetY() {
        return this.vector.getY();
    }

    public int getOffsetZ() {
        return this.vector.getZ();
    }
}
