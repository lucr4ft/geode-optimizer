package net.lucraft.geodeoptimizer.fabric.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.lucraft.geodeoptimizer.fabric.exceptions.GenerationException;
import net.lucraft.geodeoptimizer.fabric.generation.Generator;
import net.lucraft.geodeoptimizer.fabric.generation.GenerationOptions;
import net.lucraft.geodeoptimizer.fabric.util.MessageUtil;
import net.minecraft.server.command.ServerCommandSource;

import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static com.mojang.brigadier.arguments.StringArgumentType.word;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class GenerateCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("go").then(literal("gen").executes(context -> execute(context.getSource(), GenerationOptions.DEFAULT))
                .then(argument("collectionType", word()).suggests((context, builder) -> builder.suggest("useWaterCollection").suggest("useHopperCollection").buildFuture())
                        .executes(context -> execute(context.getSource(), new GenerationOptions(getString(context, "collectionType").equals("useWaterCollection"), GenerationOptions.DEFAULT.generateStorageSystem())))
                        .then(argument("genStorageSystem", word()).suggests((context, builder) -> builder.suggest("genStorageSystem").buildFuture())
                                .executes(context -> execute(context.getSource(), new GenerationOptions(getString(context, "collectionType").equals("useWaterCollection"), true)))))));
    }

    /**
     *
     * @param source the command source
     * @param options the generation options
     * @return result code
     */
    public static int execute(ServerCommandSource source, GenerationOptions options) {
        try {
            MessageUtil.sendMessage("§l§4Note: §cGeodeOptimizer is still in early development and the generation functionality has yet to be implemented.");
            MessageUtil.sendMessage("§cPlease report any issues on github (https://github.com/lucr4ft/geode-optimizer/issues)\n\n");

            Generator.generate(options);
        } catch (GenerationException e) {
            MessageUtil.sendMessage("§4Error: §c" + e.getMessage());
        }
        return Command.SINGLE_SUCCESS;
    }
}
