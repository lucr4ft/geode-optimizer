package net.lucraft.geodeoptimizer.fabric.client;

import net.lucraft.geodeoptimizer.fabric.GeodeOptimizerFabric;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class GeodeOptimizerClientFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        System.out.println("[" + GeodeOptimizerFabric.MOD_ID.toUpperCase() + "/Client]: Initializing...");

        // register renderer


        System.out.println("[" + GeodeOptimizerFabric.MOD_ID.toUpperCase() + "/Client]: Initialized");
    }
}