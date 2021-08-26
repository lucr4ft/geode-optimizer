package net.lucraft.geodeoptimizer.fabric.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.lucraft.geodeoptimizer.fabric.generation.schematics.SchematicManager;
import net.minecraft.server.command.ServerCommandSource;

import java.io.IOException;

import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static com.mojang.brigadier.arguments.StringArgumentType.word;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class SchematicCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("go").then(literal("schematic")
                .then(literal("load").then(argument("name", word()).executes(context -> {
                    SchematicManager.load(getString(context, "name"));
                    return Command.SINGLE_SUCCESS;
                })))
                .then(literal("create").then(argument("name", word()).executes(context -> {
                    SchematicManager.create(getString(context, "name"));
                    return Command.SINGLE_SUCCESS;
                })))
                .then(literal("save").then(argument("name", word()).executes(context -> {
                    try {
                        SchematicManager.save(getString(context, "name"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return Command.SINGLE_SUCCESS;
                })))
                .then(literal("place").then(argument("name", word()).executes(context -> {
                    SchematicManager.place(getString(context, "name"), context.getSource().getPlayer().getBlockPos());
                    return Command.SINGLE_SUCCESS;
                })))));
    }
}
