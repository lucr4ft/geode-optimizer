package net.lucraft.geodeoptimizer.fabric;

import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GeodeOptimizer {

    private static final GeodeOptimizer instance = new GeodeOptimizer();

    /**
     *
     * @return the instance of {@link GeodeOptimizer}
     */
    public static GeodeOptimizer getInstance() {
        return instance;
    }

    private BlockPos pos1, pos2;

    private GeodeOptimizer() { }

    /**
     *
     * @return the left position ({@code null}, if pos1 was not set before)
     */
    @Nullable
    public BlockPos getPos1() {
        return pos1;
    }

    /**
     *
     * @return the right position ({@code null}, if pos2 was not set before)
     */
    @Nullable
    public BlockPos getPos2() {
        return pos2;
    }

    /**
     *
     * @param pos1 the left position
     */
    public void setPos1(@NotNull BlockPos pos1) {
        this.pos1 = pos1;
    }

    /**
     *
     * @param pos2 the left position
     */
    public void setPos2(@NotNull BlockPos pos2) {
        this.pos2 = pos2;
    }
}
