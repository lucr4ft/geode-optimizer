package net.lucraft.geodeoptimizer.fabric;

import net.fabricmc.api.ModInitializer;

public class GeodeOptimizerFabric implements ModInitializer {

    public static final String MOD_ID = "geode-optimizer-fabric";
    public static final String PREFIX = "§7[§6GeodeOptimizer§7] ";

    @Override
    public void onInitialize() {
        System.out.println("[" + MOD_ID.toUpperCase() + "]: Initializing...");

        System.out.println("[" + MOD_ID.toUpperCase() + "]: Initialized");
    }
}
