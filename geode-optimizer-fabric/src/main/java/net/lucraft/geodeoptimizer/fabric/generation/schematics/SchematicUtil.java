package net.lucraft.geodeoptimizer.fabric.generation.schematics;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Property;
import net.minecraft.util.registry.Registry;

import java.util.*;

public final class SchematicUtil {

    /**
     * @see SchematicUtil#fromString(String)
     * @param blockState the {@link BlockState} to convert to a {@link String}
     * @return the {@link BlockState} as a {@link String}
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static String toString(BlockState blockState) {
        StringBuilder builder = new StringBuilder();
        builder.append(Registry.BLOCK.getRawId(blockState.getBlock())).append('[');
        
        boolean append = false;
        for (Map.Entry<Property<?>, Comparable<?>> entry : blockState.getEntries().entrySet()) {
            if (append) {
                builder.append(',');
            }
            append = true;

            Property p = entry.getKey();
            Comparable c = entry.getValue();

            builder.append(p.getName()).append('=').append(p.name(c));
        }
        return builder.append(']').toString();
    }

    /**
     * @see SchematicUtil#toString(BlockState)
     * @param blockStr the blockstate as a string (created with {@link SchematicUtil#toString(BlockState)})
     * @return the string as a {@link BlockState}
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static BlockState fromString(String blockStr) {
        Block block = Registry.BLOCK.get(Integer.parseInt(blockStr.split("\\[")[0]));
        StateManager<Block, BlockState> stateManager = block.getStateManager();
        BlockState blockState = block.getDefaultState();

        String propsStr = blockStr.substring(blockStr.indexOf('[') + 1, blockStr.length() - 2);

        for (String prop : propsStr.split(",")) {
            String name = prop.split("=")[0];
            String value = prop.split("=")[1];

            Property<?> property = stateManager.getProperty(name);
            assert property != null;

            Optional<? extends Comparable<?>> optional = property.parse(value);
            if (optional.isPresent()) {
                blockState = blockState.with((Property) property, (Comparable) optional.get());
            }
        }
        return blockState;
    }
}
