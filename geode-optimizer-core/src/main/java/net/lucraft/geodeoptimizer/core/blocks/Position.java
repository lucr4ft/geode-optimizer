package net.lucraft.geodeoptimizer.core.blocks;

import java.util.Objects;

public record Position(int x, int y, int z) {

    public Position north() {
        return offset(Direction.NORTH);
    }

    public Position north(int distance) {
        return offset(Direction.NORTH, distance);
    }

    public Position west() {
        return offset(Direction.WEST);
    }

    public Position west(int distance) {
        return offset(Direction.WEST, distance);
    }

    public Position south() {
        return offset(Direction.SOUTH);
    }

    public Position south(int distance) {
        return offset(Direction.SOUTH, distance);
    }

    public Position east() {
        return offset(Direction.EAST);
    }

    public Position east(int distance) {
        return offset(Direction.EAST, distance);
    }

    public Position up() {
        return offset(Direction.UP);
    }

    public Position up(int distance) {
        return offset(Direction.UP, distance);
    }

    public Position down() {
        return offset(Direction.DOWN);
    }

    public Position down(int distance) {
        return offset(Direction.DOWN, distance);
    }

    public Position offset(Direction direction) {
        return offset(direction, 1);
    }

    public Position offset(Direction direction, int distance) {
        return distance == 0 ? this : new Position(
                getX() + direction.getOffsetX() * distance,
                getY() + direction.getOffsetY() * distance,
                getZ() + direction.getOffsetZ() * distance);
    }

    @Override
    public String toString() {
        return '[' + x +
                ", " + y +
                ", " + z + ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position position)) return false;
        return getX() == position.getX() && getY() == position.getY() && getZ() == position.getZ();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY(), getZ());
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
