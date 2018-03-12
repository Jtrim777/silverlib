package silverlib.math.graph;

/**
 * A representation of a mathematical function in a 2D coordinate plane
 *
 * @author Jake Trimble
 * @since 1.9.1
 */
public abstract class Function {
    protected double slope;
    protected double xTrans;
    protected double yTrans;
    protected int xReflect;
    protected int yReflect;

    /**
     * Initializes a <code>Function</code> with given slope, x and y translation
     *
     * @param a Slope
     * @param h X-Translation
     * @param k Y-Translation
     *
     * @since 1.9.1
     */
    public Function(double a, double h, double k) {
        slope = a;
        xTrans = h;
        yTrans = k;
    }


    /**
     * Solves the function for x
     *
     * @param x The x value to pass into the function
     *
     * @return y given x
     *
     * @since 1.9.1
     */
    public abstract double fx(double x);

    /**
     * Solves the function for x
     *
     * @param x The x value to pass into the function
     *
     * @return y given x
     *
     * @since 1.9.1
     */
    public abstract int fx(int x);

    /**
     * Solves the function for x
     *
     * @param x The x value to pass into the function
     *
     * @return y given x
     *
     * @since 1.9.1
     */
    public abstract float fx(float x);

    /**
     * Translates the function along the y-axis
     *
     * @param amt The number of units to translate the function by
     *
     * @return The translated function
     *
     * @since 1.9.1
     */
    public Function translateY(double amt) {
        yTrans += amt;

        return this;
    }

    /**
     * Translates the function along the x-axis
     *
     * @param amt The number of units to translate the function by
     *
     * @return The translated function
     *
     * @since 1.9.1
     */
    public Function translateX(double amt) {
        xTrans += amt;

        return this;
    }

    /**
     * Reflects the function over the x-axis
     *
     * @return The translated function
     *
     * @since 1.9.1
     */
    public Function reflectX() {
        xReflect *= -1;

        return this;
    }

    /**
     * Reflects the function over the y-axis
     *
     * @return The translated function
     *
     * @since 1.9.1
     */
    public Function reflectY() {
        yReflect *= -1;

        return this;
    }


    /**
     * Generates a <code>String</code> representation of this function
     *
     * @return A <code>String</code> representation of this function
     *
     * @since 1.9.1
     */
    @Override
    public abstract String toString();
}