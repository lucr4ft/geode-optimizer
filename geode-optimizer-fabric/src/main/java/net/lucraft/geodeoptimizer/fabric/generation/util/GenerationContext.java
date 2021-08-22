package net.lucraft.geodeoptimizer.fabric.generation.util;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class GenerationContext {

    private final ArrayList<BlockPos> positions;
    private final HashMap<BlockPos, BlockState> blocks;
    private final GenerationOptions options;

    private BlockPos afkPosition;

    /**
     *
     * @param positions
     * @param blocks
     */
    public GenerationContext(@NotNull ArrayList<BlockPos> positions, @NotNull HashMap<BlockPos, BlockState> blocks) {
        this.positions = positions;
        this.blocks = blocks;
        this.options = GenerationOptions.DEFAULT;
    }

    public GenerationContext(@NotNull ArrayList<BlockPos> positions, @NotNull HashMap<BlockPos, BlockState> blocks, @NotNull GenerationOptions options) {
        this.positions = positions;
        this.blocks = blocks;
        this.options = options;
    }

    /**
     *
     * @return
     */
    @NotNull
    public ArrayList<BlockPos> getPositions() {
        return positions;
    }

    /**
     *
     * @return
     */
    @NotNull
    public HashMap<BlockPos, BlockState> getBlocks() {
        return blocks;
    }

    /**
     *
     * @return
     */
    @NotNull
    public GenerationOptions getOptions() {
        return options;
    }

    /**
     *
     * @return
     */
    @Nullable
    public BlockPos getAfkPosition() {
        return afkPosition;
    }

    /**
     *
     * @param afkPosition
     */
    public void setAfkPosition(@NotNull BlockPos afkPosition) {
        this.afkPosition = afkPosition;
    }
}
