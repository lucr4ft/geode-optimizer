package net.lucraft.geodeoptimizer.fabric.util.tuples;

/**
 *
 * @param <L> type of first value
 * @param <M> type of second value
 * @param <R> type of third value
 */
public record Triple<L, M, R>(L left, M middle, R right) {

    /**
     *
     * @param left first value
     * @param middle second value
     * @param right third value
     * @param <L> type of first value
     * @param <M> type of second value
     * @param <R> type of third value
     * @return a new {@link Triple} with values left, middle, right
     */
    public static <L, M, R> Triple<L, M, R> of(L left, M middle, R right) {
        return new Triple<>(left, middle, right);
    }
}
