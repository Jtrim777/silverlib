package silverlib.math.graph;

import silverlib.geo.PinPoint;
import silverlib.math.Num;

/**
 * A representation of a vector in a 2D coordinate plane
 *
 * @author Jake Trimble
 * @since 1.9.0
 */
public class Vector {
    private float xPow, yPow;
    private double angle;
    private double magnitude;

    /**
     * Creates a <code>Vector</code> with given x and y directions and magnitude
     *
     * @param x The x direction of the vector
     * @param y The y direction of the vector
     * @param p The magnitude of the vector
     *
     * @since 1.9.0
     */
    public Vector(float x, float y, double p) {
        xPow = x;
        yPow = y;
        magnitude = Math.abs(p);

        double d = Math.sqrt(Num.square(x) + Num.square(1 - y));
        angle = Math.acos(1 - (Num.square(d) / 2));
        if (x < 0) {
            angle = 180 - angle + 180;
        }
    }

    public float x() {
        return xPow;
    }

    public float y() {
        return yPow;
    }

    public double mag() {
        return magnitude;
    }

    public double angle() {
        return angle;
    }

    /**
     * Moves a point along this vector
     *
     * @param in The point to move
     *
     * @return The edited point
     *
     * @since 1.9.0
     */
    public PinPoint tick(PinPoint in) {
        double x = in.x();
        double y = in.y();

        x += magnitude * xPow;
        y += magnitude * yPow;

        PinPoint out = new PinPoint(x, y);

        return out;
    }

    /**
     * Modifies this vector by combining it with another
     *
     * @param v The vector to add
     *
     * @since 1.9.0
     */
    public void add(Vector v) {
        angle += v.angle();
        if (angle >= 360) {
            angle -= 360;
        }

        magnitude += v.mag();
    }
}
