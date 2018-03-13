package silverlib.geo;

/**
 * <h1> Basic Polar Point Class </h1>
 * A class that represents a polar point in a 2D polar coordinate plane.
 *
 * @since 1.9.1.3
 * @author Jake Trimble
 */
public class PolarPoint {
    double radius;
    float theta;

    /**
     * Initializes a <code>PolarPoint</code> object from radius and theta.
     *
     * @param r The radius
     * @param t The theta-value
     *
     * @since 1.9.1.3
     */
    public PolarPoint(double r, float t) {
        radius = r;
        theta = t;
    }

    /**
     * Initializes a <code>PolarPoint</code> from a <code>String</code> in the format
     * <code>(r,θ)</code>
     *
     * @param s A <code>String</code> representation of the <code>PolarPoint</code> in the form <code>(r,θ)</code>
     *
     * @throws PointFormatException If <code>s</code> does not match format <code>(r,θ)</code>
     * @since 1.9.1.3
     */
    public PolarPoint(String s) throws PointFormatException {
        if (s.matches("^[(]\\d+[,]\\d+[)]")) {
            String in = s.substring(1, s.length() - 1);
            String[] pts = in.split(",");
            radius = Double.parseDouble(pts[0]);
            theta = Float.parseFloat(pts[1]);
        }
        else {
            throw new PointFormatException(s);
        }
    }

    /**
     * Returns the radius of the <code>PolarPoint</code>
     *
     * @return <code>r</code>
     *
     * @since 1.9.1.3
     */
    public double radius() {
        return radius;
    }

    /**
     * Returns the theta value of the <code>PolarPoint</code>
     *
     * @return <code>y</code>
     *
     * @since 1.9.1.3
     */
    public float theta() {
        return theta;
    }

    /**
     * Converts <code>PolarPoint</code> to a <code>Point</code> with rectangular coordinates
     * @return <code>Point</code> with rectangular coordinates
     *
     * @since 1.9.1.3
     */
    public Point toRectangular(){
        int x = (int) (radius * Math.cos(theta));
        int y = (int) (radius * Math.sin(theta));

        return new Point(x,y);
    }

    /**
     * Returns the a String representation of the <code>PolarPoint</code>, in the
     * form <code>(x,y)</code>.
     *
     * @return String representation of this <code>PolarPoint</code>
     *
     * @since 1.9.1.3
     */
    public String toString() {
        return "(" + radius + "," + theta + ")";
    }
}
