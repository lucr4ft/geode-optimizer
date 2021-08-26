package net.lucraft.geodeoptimizer.fabric.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

import java.util.UUID;

public class MessageUtil {

    /**
     *
     * @param text the {@link Text} to send
     * @param uuid the sender {@link UUID}
     */
    public static void sendSystemMessage(Text text, UUID uuid) {
        assert MinecraftClient.getInstance().player != null;
        MinecraftClient.getInstance().player.sendSystemMessage(text, uuid);
    }

    /**
     *
     * @param text the text to send to the player
     */
    public static void sendMessage(Text text) {
        assert MinecraftClient.getInstance().player != null;
        MinecraftClient.getInstance().player.sendMessage(text, false);
    }

    /**
     * Internally calls {@link MessageUtil#sendMessage(Text)}
     * @param text the text to send (is converted to {@link LiteralText})
     */
    public static void sendMessage(String text) {
        sendMessage(new LiteralText(text));
    }

    /**
     *
     * @param text the text to send
     */
    public static void sendGlobalMessage(String text) {
        assert MinecraftClient.getInstance().player != null;
        MinecraftClient.getInstance().player.sendChatMessage(text);
    }
}
