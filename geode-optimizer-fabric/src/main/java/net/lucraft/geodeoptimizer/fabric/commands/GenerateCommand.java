package net.lucraft.geodeoptimizer.fabric.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.lucraft.geodeoptimizer.fabric.exceptions.GenerationException;
import net.lucraft.geodeoptimizer.fabric.generation.Generator;
import net.lucraft.geodeoptimizer.fabric.generation.util.GenerationOptions;
import net.lucraft.geodeoptimizer.fabric.util.MessageUtil;
import net.minecraft.server.command.ServerCommandSource;

import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static com.mojang.brigadier.arguments.StringArgumentType.word;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;


public class GenerateCommand implements Command<ServerCommandSource> {

    @Override
    public int run(CommandContext<ServerCommandSource> context) {
        try {
            MessageUtil.sendMessage("§l§4Note: §cGeodeOptimizer is still in early development and the generation functionality has yet to be implemented.");
            MessageUtil.sendMessage("§cPlease report any issues on github (https://github.com/lucr4ft/geode-optimizer/issues)\n\n");

            boolean useWaterCollection = getString(context, "collectionType") == null || getString(context, "collectionType").equals("useWaterCollection");
            boolean genStorageSystem = getString(context, "genStorageSystem") == null || getString(context, "genStorageSystem").equals("genStorageSystem");

            // Todo parse options from command arguments
            GenerationOptions options = new GenerationOptions(
                    useWaterCollection,
                    genStorageSystem
            );

            Generator.getInstance().generate(options);
        } catch (GenerationException e) {
            MessageUtil.sendMessage("§4Error: §c" + e.getMessage());
        }
        return 0;
    }

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                literal("go").then(literal("gen")
                        .then(argument("collectionType", word())
                                .suggests((context, builder) -> builder.suggest("useWaterCollection").suggest("useHopperCollection").buildFuture()).executes(new GenerateCommand())
                                .then(argument("genStorageSystem", word()).suggests((context, builder) -> builder.suggest("genStorageSystem").buildFuture()).executes(new GenerateCommand())))
                ).executes(new GenerateCommand())
        );
    }
}
