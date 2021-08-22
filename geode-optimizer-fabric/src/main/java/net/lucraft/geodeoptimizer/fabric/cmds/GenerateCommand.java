package net.lucraft.geodeoptimizer.fabric.cmds;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.lucraft.geodeoptimizer.fabric.exceptions.GenerationException;
import net.lucraft.geodeoptimizer.fabric.generation.Generator;
import net.lucraft.geodeoptimizer.fabric.generation.util.GenerationOptions;
import net.lucraft.geodeoptimizer.fabric.util.MessageUtil;
import net.minecraft.server.command.ServerCommandSource;

public class GenerateCommand implements Command<ServerCommandSource> {
    @Override
    public int run(CommandContext<ServerCommandSource> context) {
        try {
            MessageUtil.sendMessage("§l§4Note: §cGeodeOptimizer is still in early development and the generation functionality has yet to be implemented.");
            MessageUtil.sendMessage("§cPlease report any issues on github (https://github.com/lucr4ft/geode-optimizer/issues)\n\n");

            // Todo parse options from command arguments
            Generator.getInstance().generate(GenerationOptions.DEFAULT);
        } catch (GenerationException e) {
            MessageUtil.sendMessage("§4Error: §c" + e.getMessage());
        }
        return 0;
    }
}
