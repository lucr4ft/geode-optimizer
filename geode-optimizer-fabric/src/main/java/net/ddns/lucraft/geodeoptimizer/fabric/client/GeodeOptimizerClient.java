package net.ddns.lucraft.geodeoptimizer.fabric.client;

import net.ddns.lucraft.geodeoptimizer.core.GeodeOptimizerCore;
import net.ddns.lucraft.geodeoptimizer.fabric.GeodeOptimizer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class GeodeOptimizerClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        System.out.println("[" + GeodeOptimizer.MOD_ID.toUpperCase() + "/Client]: Initializing...");
        // initialize the core module
        GeodeOptimizerCore.getInstance().initialize();
        System.out.println("[" + GeodeOptimizer.MOD_ID.toUpperCase() + "/Client]: Initialized");
    }

}
