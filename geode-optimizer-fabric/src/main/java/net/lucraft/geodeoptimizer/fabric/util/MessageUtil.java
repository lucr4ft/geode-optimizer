package net.lucraft.geodeoptimizer.fabric.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import java.util.UUID;

public class MessageUtil {

    /**
     *
     * @param text
     * @param uuid
     */
    public static void sendSystemMessage(Text text, UUID uuid) {
        assert MinecraftClient.getInstance().player != null;
        MinecraftClient.getInstance().player.sendSystemMessage(text, uuid);
    }

    /**
     *
     * @param text
     */
    public static void sendMessage(Text text) {
        assert MinecraftClient.getInstance().player != null;
        MinecraftClient.getInstance().player.sendMessage(text, false);
    }

    /**
     *
     * @param text
     */
    public static void sendGlobalMessage(String text) {
        assert MinecraftClient.getInstance().player != null;
        MinecraftClient.getInstance().player.sendChatMessage(text);
    }

    /**
     *
     * @param messageUUID
     */
    public static void updateMessage(UUID messageUUID) {
        // Todo (if possible)
    }

}
