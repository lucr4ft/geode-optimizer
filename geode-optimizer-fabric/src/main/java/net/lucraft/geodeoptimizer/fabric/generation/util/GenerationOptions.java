package net.lucraft.geodeoptimizer.fabric.generation.util;

public record GenerationOptions(boolean useWaterCollectionSystem, boolean generateStorageSystem) {
    public static GenerationOptions DEFAULT = new GenerationOptions(false, true);
}