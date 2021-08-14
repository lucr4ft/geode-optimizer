package net.lucraft.geodeoptimizer.core.blocks;

import net.lucraft.geodeoptimizer.core.Identifier;

public class Block {

    private Identifier identifier;
    private Position position;
    private Direction direction;

    public Position getPosition() {
        return position;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
