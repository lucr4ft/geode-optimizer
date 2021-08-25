package net.lucraft.geodeoptimizer.fabric.util;

public record Dimension(int width, int height, int depth) {

    /**
     *
     * @return width * depth
     */
    public long area() {
        return (long) width * (long) depth;
    }

    /**
     *
     * @return width * height * depth
     */
    public long volume() {
        return (long) width * height * depth;
    }

    /**
     *
     * @param width the width
     * @param height the height
     * @param depth the depth
     * @return a new Dimension with values of width, height and depth
     */
    public static Dimension of(int width, int height, int depth) {
        return new Dimension(width, height, depth);
    }

}