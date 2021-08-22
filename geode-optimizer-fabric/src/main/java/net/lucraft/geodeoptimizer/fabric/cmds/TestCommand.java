package net.lucraft.geodeoptimizer.fabric.cmds;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;

public class TestCommand implements Command<ServerCommandSource> {
    @Override
    public int run(CommandContext<ServerCommandSource> context) {

        return 0;
    }

}
