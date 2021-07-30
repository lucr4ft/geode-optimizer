package net.ddns.lucraft.geodeoptimizer.fabric.client;

import net.ddns.lucraft.geodeoptimizer.GeodeOptimizer;
import net.fabricmc.api.ClientModInitializer;

@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
public class GeodeOptimizerClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        System.out.println("[" + GeodeOptimizer.MOD_ID.toUpperCase() + "/Client]: Initializing...");
        System.out.println("[" + GeodeOptimizer.MOD_ID.toUpperCase() + "/Client]: Initialized");
    }
}
