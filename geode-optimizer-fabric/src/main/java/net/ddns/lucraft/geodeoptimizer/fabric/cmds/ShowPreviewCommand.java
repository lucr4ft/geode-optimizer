package net.ddns.lucraft.geodeoptimizer.fabric.cmds;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.ddns.lucraft.geodeoptimizer.core.GeodeOptimizerCore;
import net.minecraft.server.command.ServerCommandSource;

public class ShowPreviewCommand implements Command<ServerCommandSource> {

    private final GeodeOptimizerCore core = GeodeOptimizerCore.getInstance();

    @Override
    public int run(CommandContext<ServerCommandSource> context) {
        core.showPreview = !core.showPreview;
        return 0;
    }
}
