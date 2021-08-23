package net.lucraft.geodeoptimizer.fabric.client;

import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.lucraft.geodeoptimizer.fabric.GeodeOptimizerFabric;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.lucraft.geodeoptimizer.fabric.commands.CommandManager;

@Environment(EnvType.CLIENT)
public class GeodeOptimizerClientFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        System.out.println("[" + GeodeOptimizerFabric.MOD_ID.toUpperCase() + "/Client]: Initializing...");


        CommandRegistrationCallback.EVENT.register(CommandManager::registerCommands);
        // register renderer


        System.out.println("[" + GeodeOptimizerFabric.MOD_ID.toUpperCase() + "/Client]: Initialized");
    }
}