package net.lucraft.geodeoptimizer.fabric.cmds;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.lucraft.geodeoptimizer.fabric.GeodeOptimizer;
import net.minecraft.server.command.ServerCommandSource;

public class ShowPreviewCommand implements Command<ServerCommandSource> {

    private final GeodeOptimizer core = GeodeOptimizer.getInstance();

    @Override
    public int run(CommandContext<ServerCommandSource> context) {
//        core.showPreview = !core.showPreview;
        return 0;
    }
}
