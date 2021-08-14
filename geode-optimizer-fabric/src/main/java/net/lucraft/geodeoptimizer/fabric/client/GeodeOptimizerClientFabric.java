package net.lucraft.geodeoptimizer.fabric.client;

import net.lucraft.geodeoptimizer.fabric.GeodeOptimizerFabric;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class GeodeOptimizerClientFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        System.out.println("[" + GeodeOptimizerFabric.MOD_ID.toUpperCase() + "/Client]: Initializing...");

        // register renderer


        System.out.println("[" + GeodeOptimizerFabric.MOD_ID.toUpperCase() + "/Client]: Initialized");
    }
}


//public static final Identifier GENERATE_LAYOUT_PACKET = new Identifier(MOD_ID, "packets/generate_layout");
//public static final Identifier SET_BLOCK_PACKET = new Identifier(MOD_ID, "packets/setblock");
//            ServerPlayNetworking.registerGlobalReceiver(SET_BLOCK_PACKET, (server, player, handler, buf, responseSender) -> {
//            BlockPos pos = buf.readBlockPos();
//            Block block = Blocks.BONE_BLOCK;
//            Block b2 = net.minecraft.util.registry.Registry.BLOCK.get(buf.readIdentifier());
//            server.execute(() -> {
//                player.getServerWorld().setBlockState(pos, block.getDefaultState());
//                player.getServerWorld().setBlockState(pos.east(), b2.getDefaultState());
//                int y = blocks.length;
//            });
//        });

//    private void registerCommands() {
//        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
//            dispatcher.register(literal("test").executes(context -> {
//                PacketByteBuf buf = PacketByteBufs.create();
//                buf.writeBlockPos(MinecraftClient.getInstance().player.getBlockPos().down());
//                buf.writeIdentifier(new Identifier("minecraft", "stone"));
//                ClientPlayNetworking.send(SET_BLOCK_PACKET, buf);
//                return 0;
//            }));
//        });
//    }