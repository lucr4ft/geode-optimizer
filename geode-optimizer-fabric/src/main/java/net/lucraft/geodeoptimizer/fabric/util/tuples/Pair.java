package net.lucraft.geodeoptimizer.fabric.util.tuples;

/**
 *
 * @param <L> type of the left value
 * @param <R> type of the right value
 */
public record Pair<L, R>(L left, R right) {

    /**
     *
     * @param left first/left value
     * @param right second/right value
     * @param <L> type of left value
     * @param <R> type of right value
     * @return a new pair with left and right as values
     */
    public static <L, R> Pair<L, R> of(L left, R right) {
        return new Pair<>(left, right);
    }

}
