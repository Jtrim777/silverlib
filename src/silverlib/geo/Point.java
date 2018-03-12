package silverlib.geo;

import silverlib.math.graph.GraphType;

/**
 * <h1> Basic Point Class </h1>
 * A class that represents a point in a 2D coordinate plane.
 */
public class Point {
    int x;
    int y;

    /**
     * Initializes a <code>Point</code> object from two integer x and y values.
     *
     * @param a The x-value
     * @param b The y-value
     *
     * @since 1.4
     */
    public Point(int a, int b) {
        x = a;
        y = b;
    }

    /**
     * Initializes a <code>Point</code> from a <code>String</code> in the format
     * <code>(x,y)</code>
     *
     * @param s A <code>String</code> representation of the <code>Point</code> in the form <code>(x,y)</code>
     *
     * @throws PointFormatException If <code>s</code> does not match <code>(x,y)</code>
     * @since 1.8.2
     */
    public Point(String s) throws PointFormatException {
        if (s.matches("^[(]\\d+[,]\\d+[)]")) {
            String in = s.substring(1, s.length() - 1);
            String[] pts = in.split(",");
            x = Integer.parseInt(pts[0]);
            y = Integer.parseInt(pts[1]);
        }
        else {
            throw new PointFormatException(s);
        }
    }

    /**
     * Returns the x-coordinate of the <code>Point</code>
     *
     * @return <code>x</code>
     *
     * @since 1.4
     */
    public int x() {
        return x;
    }

    /**
     * Returns the y-coordinate of the <code>Point</code>
     *
     * @return <code>y</code>
     *
     * @since 1.4
     */
    public int y() {
        return y;
    }

    public static Point adjustForOrigin(Point p, Point o, GraphType sourceType) {
        int xDiff = p.x - o.x;

        int yDiff = p.y - o.y;

        if (sourceType == GraphType.CODE) {
            yDiff = o.y - p.y;
        }

        return new Point(xDiff, yDiff);
    }

    public static Point adjustForOrigin(int xP, int yP, Point o, GraphType sourceType) {
        int xDiff = xP - o.x;

        int yDiff = yP - o.y;

        if (sourceType == GraphType.CODE) {
            yDiff = o.y - yP;
        }

        return new Point(xDiff, yDiff);
    }

    /**
     * Returns the a String representation of the <code>Point</code>, in the
     * form <code>(x,y)</code>.
     *
     * @return String rep of this <code>Point</code>
     *
     * @since 1.4.2
     */
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
