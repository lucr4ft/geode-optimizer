package net.lucraft.geodeoptimizer.fabric.generation.schematics;

public abstract class Schematic {

    public void rotate(Rotate rotate) {

    }

    public enum Rotate {
        CLOCKWISE_90,
        CLOCKWISE_180,
        COUNTER_CLOCKWISE_90
    }

}
