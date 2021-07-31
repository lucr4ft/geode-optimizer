package net.ddns.lucraft.geodeoptimizer.core;

public class Block {

    private final Position position;
    private Direction direction;
    private Type type;

    public Block(Position position, Direction direction, Type type) {
        this.position = position;
        this.direction = direction;
        this.type = type;
    }

    public Block(Position position, Type type) {
        this.position = position;
        this.type = type;
    }

    public Block(Position position) {
        this.position = position;
    }

    public Block(Position position, Direction direction) {
        this.position = position;
        this.direction = direction;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Type getType() {
        return type;
    }

    public Direction getDirection() {
        return direction;
    }

    public Position getPosition() {
        return position;
    }
}
