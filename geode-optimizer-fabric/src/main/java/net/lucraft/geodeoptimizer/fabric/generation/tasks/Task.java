package net.lucraft.geodeoptimizer.fabric.generation.tasks;

import net.lucraft.geodeoptimizer.fabric.generation.GenerationContext;

@FunctionalInterface
public interface Task {

    /**
     *
     * @param context the {@link GenerationContext} of the current generation
     */
    void run(GenerationContext context);

}
