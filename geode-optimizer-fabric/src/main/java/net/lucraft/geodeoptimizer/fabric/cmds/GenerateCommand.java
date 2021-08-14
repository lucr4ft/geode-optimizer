package net.lucraft.geodeoptimizer.fabric.cmds;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.lucraft.geodeoptimizer.fabric.GeodeOptimizer;
import net.lucraft.geodeoptimizer.fabric.exceptions.GenerationException;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Style;
import net.minecraft.util.Util;

public class GenerateCommand implements Command<ServerCommandSource> {

    private final GeodeOptimizer core = GeodeOptimizer.getInstance();

    @Override
    public int run(CommandContext<ServerCommandSource> context) {
        // call generate method in GeodeOptimizerCore
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        assert player != null;
        try {
            player.sendSystemMessage(new LiteralText("§l§4Note: §cGeodeOptimizer is still in early development and the generation functionality has yet be implemented."), Util.NIL_UUID);
            player.sendSystemMessage(new LiteralText("§cPlease report any issues on github (https://github.com/lucr4ft/geode-optimizer/issues)\n\n"), Util.NIL_UUID);
            core.generate();
        } catch (GenerationException e) {
            player.sendSystemMessage(new LiteralText("§4Error: §c" + e.getMessage()), Util.NIL_UUID);
        }
        return 0;
    }
}
