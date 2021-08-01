package net.ddns.lucraft.geodeoptimizer.fabric;

import net.ddns.lucraft.geodeoptimizer.core.GeodeOptimizerCore;
import net.ddns.lucraft.geodeoptimizer.fabric.cmds.Commands;
import net.fabricmc.api.ModInitializer;

public class GeodeOptimizerFabric implements ModInitializer {

    public static final String MOD_ID = "geode-optimizer-fabric";

    @Override
    public void onInitialize() {
        System.out.println("[" + MOD_ID.toUpperCase() + "]: Initializing...");

        GeodeOptimizerCore.getInstance().initialize();

        Commands.register();

        System.out.println("[" + MOD_ID.toUpperCase() + "]: Initialized");
    }
}
