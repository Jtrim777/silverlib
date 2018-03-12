package silverlib.math.graph;

/**
 * The four quadrants of a 2D coordinate plane
 *
 * @author Jake Trimble
 * @since 1.7.5
 */
public enum Quadrant {
    I(1, 1),
    II(-1, 1),
    III(-1, -1),
    IV(1, -1),
    N(0, 0);

    private final int xSign;
    private final int ySign;

    Quadrant(int x, int y) {
        xSign = x;
        ySign = y;
    }

    public int xSign() {
        return xSign;
    }

    public int ySign() {
        return ySign;
    }

    /**
     * Gives a <code>Quadrant</code> based on an x and y values
     *
     * @param x The x-coordinate
     * @param y The y-coordinate
     *
     * @return The <code>Quadrant</code> the coordinates exist in
     *
     * @since 1.7.5
     */
    public static final Quadrant match(int x, int y) {
        for (Quadrant q : Quadrant.values()) {
            try {
                if (x / Math.abs(x) == q.xSign() && y / Math.abs(y) == q.ySign()) {
                    return q;
                }
            } catch (ArithmeticException e) {
                if (x == 0) {
                    return y >= 0 ? I : III;
                }
                if (y == 0) {
                    return x >= 0 ? I : III;
                }
            }
        }
        return N;
    }
}
