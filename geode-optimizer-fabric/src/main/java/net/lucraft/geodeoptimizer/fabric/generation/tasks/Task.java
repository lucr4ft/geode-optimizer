package net.lucraft.geodeoptimizer.fabric.generation.tasks;

import net.lucraft.geodeoptimizer.fabric.generation.util.GenerationContext;

public interface Task {

    /**
     *
     * @param generationContext the {@link GenerationContext} of the current generation
     */
    void run(GenerationContext generationContext);

}
