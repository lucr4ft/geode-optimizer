package net.ddns.lucraft.geodeoptimizer;

import net.fabricmc.api.ModInitializer;

public class GeodeOptimizer implements ModInitializer {

    public static final String MOD_ID = "geode-optimizer";

    @Override
    public void onInitialize() {
        System.out.println("[" + MOD_ID.toUpperCase() + "]: Initializing...");
        System.out.println("[" + MOD_ID.toUpperCase() + "]: Initialized");
    }
}
