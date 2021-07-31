package net.ddns.lucraft.geodeoptimizer.fabric.client;

import net.ddns.lucraft.geodeoptimizer.core.Block;
import net.ddns.lucraft.geodeoptimizer.core.GeodeOptimizerCore;
import net.ddns.lucraft.geodeoptimizer.core.Position;
import net.ddns.lucraft.geodeoptimizer.fabric.GeodeOptimizer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;

import static com.mojang.brigadier.arguments.IntegerArgumentType.getInteger;
import static com.mojang.brigadier.arguments.IntegerArgumentType.integer;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

@Environment(EnvType.CLIENT)
public class GeodeOptimizerClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        System.out.println("[" + GeodeOptimizer.MOD_ID.toUpperCase() + "/Client]: Initializing...");
        // initialize the core module
        GeodeOptimizerCore.getInstance().initialize();

        // register commands
        registerCommands();

        System.out.println("[" + GeodeOptimizer.MOD_ID.toUpperCase() + "/Client]: Initialized");
    }

    private void registerCommands() {
        /*
         * commands:
         *
         *  /go pos 1
         *  /go pos 2
         *  /go gen <options>
         *  /go toggle show
         *  /go save <name>
         *  /go load <name>
         *
         */
        CommandRegistrationCallback.EVENT.register(((dispatcher, dedicated) -> {
            // command: /go pos <1/2>
            dispatcher.register(literal("go").then(literal("pos").then(argument("position", integer(1,2)).executes(context -> {
                assert MinecraftClient.getInstance().player != null;
                BlockPos pos = MinecraftClient.getInstance().player.getBlockPos();
                if (getInteger(context,"position") == 1) {
                    GeodeOptimizerCore.getInstance().pos1 = new Position(pos.getX(), pos.getY(), pos.getZ());
                    MinecraftClient.getInstance().player.sendSystemMessage(new LiteralText(String.format("§afirst position set to [%d, %d, %d]", pos.getX(), pos.getY(), pos.getZ())), Util.NIL_UUID);
                } else {
                    GeodeOptimizerCore.getInstance().pos2 = new Position(pos.getX(), pos.getY(), pos.getZ());
                    MinecraftClient.getInstance().player.sendSystemMessage(new LiteralText(String.format("§asecond position set to [%d, %d, %d]", pos.getX(), pos.getY(), pos.getZ())), Util.NIL_UUID);
                }
                return 0;
            }))));

            // command: /go gen
            dispatcher.register(literal("go").then(literal("gen").executes(context -> {
                // get instance of GeodeOptimizerCore for easier use
                GeodeOptimizerCore core = GeodeOptimizerCore.getInstance();

                // calculate coordinates
                int fromX = Math.min(core.pos1.x, core.pos2.x);
                int fromY = Math.min(core.pos1.y, core.pos2.y);
                int fromZ = Math.min(core.pos1.z, core.pos2.z);
                int toX = Math.max(core.pos1.x, core.pos2.x);
                int toY = Math.max(core.pos1.y, core.pos2.y);
                int toZ = Math.max(core.pos1.z, core.pos2.z);

                ClientWorld         world           = MinecraftClient.getInstance().world;
                BlockPos.Mutable    mutableBlockPos = new BlockPos.Mutable();
                ArrayList<Position> positions       = new ArrayList<>();

                // world could be null
                assert world != null;

                // loop through every block between pos1 & pos2
                // and check if a block is a budding amethyst
                // if there is a budding amethyst, then the position
                // is retrieved and added to positions
                for (int x = fromX; x < toX; x++) {
                    for (int y = fromY; y < toY; y++) {
                        for (int z = fromZ; z < toZ; z++) {
                            mutableBlockPos.set(x, y, z);
                            if (world.getBlockState(mutableBlockPos).getBlock() == Blocks.BUDDING_AMETHYST) {
                                positions.add(new Position(x, y, z));
                            }
                        }
                    }
                }

                // call generate method in GeodeOptimizerCore
                Block[] blocks = core.generate(positions.toArray(new Position[]{}));

                // replace blocks with generated blocks
                Position position;
                for (Block block : blocks) {
                    // update position
                    position = block.getPosition();
                    mutableBlockPos.set(position.x, position.y, position.z);
                    // set block type
                    BlockState state;
                    switch (block.getType()) {
                        case STICKY_PISTON:
                            state = Blocks.STICKY_PISTON.getDefaultState();
                            break;
                        case PISTON:
                            state = Blocks.PISTON.getDefaultState();
                            break;
                        default:
                            return 1;
                    }
                    // set block
                    world.setBlockState(mutableBlockPos, state);
                }
                return 0;
            })));
        }));

    }

}