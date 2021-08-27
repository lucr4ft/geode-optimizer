package net.lucraft.geodeoptimizer.fabric.generation;

public record GenerationOptions(boolean useWaterCollectionSystem, boolean generateStorageSystem) {
    public static final GenerationOptions DEFAULT = new GenerationOptions(false, true);
}