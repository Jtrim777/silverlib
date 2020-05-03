package silverlib.geo;

import silverlib.err.PointFormatException;
import silverlib.math.graph.GraphType;

import java.io.Serializable;
import java.util.Iterator;

/**
 * <h1> Basic Point Class </h1>
 * A class that represents a point in a 2D coordinate plane.
 */
public class Point implements Serializable {
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

    /**
     * Converts <code>Point</code> to a <code>PolarPoint</code> with polar coordinates
     * @return <code>PolarPoint</code> with polar coordinates
     *
     * @since 1.9.1.3
     */
    public PolarPoint toPolar(){
        double r = Math.sqrt((x*x)+(y*y));

        float t = (float) Math.atan(y/x);

        return new PolarPoint(r,t);
    }

    /**
     * Adjusts point from (0,0) to new origin
     * @param p The input point
     * @param o The new origin
     * @param sourceType Whether the y axis increases down or up
     * @return The adjusted <code>Point</code>
     *
     * @since 1.9.1
     */
    public static Point adjustForOrigin(Point p, Point o, GraphType sourceType) {
        int xDiff = p.x + o.x;

        int yDiff = p.y + o.y;

//        if (sourceType == GraphType.CODE) {
//            yDiff = o.y - p.y;
//        }

        return new Point(xDiff, yDiff);
    }

    /**
     * Adjusts point from (0,0) to new origin
     * @param p The input point
     * @param o The new origin
     * @return The adjusted <code>Point</code>
     *
     * @since 1.9.1
     */
    public static Point adjustForOrigin(Point p, Point o) {
        int xDiff = p.x + o.x;

        int yDiff = p.y + o.y;

        return new Point(xDiff, yDiff);
    }

    /**
     * Adjusts point from (0,0) to new origin
     * @param xP The x component of the input point
     * @param yP The x component of the input point
     * @param o The new origin
     * @param sourceType Whether the y axis increases down or up
     * @return The adjusted <code>Point</code>
     *
     * @since 1.9.1
     */
    public static Point adjustForOrigin(int xP, int yP, Point o, GraphType sourceType) {
        int xDiff = xP + o.x;

        int yDiff = yP + o.y;

//        if (sourceType == GraphType.CODE) {
//            yDiff = o.y - yP;
//        }

        return new Point(xDiff, yDiff);
    }

    /**
     * Adjusts point from (0,0) to new origin
     * @param xP The x component of the input point
     * @param yP The x component of the input point
     * @param o The new origin
     * @return The adjusted <code>Point</code>
     *
     * @since 1.12.6
     */
    public static Point adjustForOrigin(int xP, int yP, Point o) {
        int xDiff = xP + o.x;

        int yDiff = yP + o.y;

        return new Point(xDiff, yDiff);
    }

    /**
     * Rotates this Point rad radians about Point o
     * @param o The Point to rotate about
     * @param rad The number of radians to rotate
     * @return The adjusted <code>Point</code>
     *
     * @since 1.6.10
     */
    public Point rotateAbout(Point o, double rad) {
        int adjX = this.x - o.x;
        int adjY = this.y - o.y;

        int rotX = (int) (adjX * Math.cos(rad) - adjY * Math.sin(rad));
        int rotY = (int) (adjY * Math.cos(rad) + adjX * Math.sin(rad));

        return new Point(rotX + o.x,rotY + o.y);
    }

    public double distanceTo(Point p) {
        return Math.sqrt(Math.pow(p.y-y,2)+Math.pow(p.x-x,2));
    }

    /**
     * Tests for equality with another <code>Point</code>
     * @param p The point to compare against
     * @return <code>true</code> if the x and y values of both <code>Point</code>s are equal, <code>false</code> otherwise
     *
     * @since 1.10.2
     */
    public boolean equals(Point p){
        return x == p.x && y == p.y;
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

    private static Iterator<Point> genIterator(int x1, int y1, int x2, int y2) {
        return new Iterator<>() {
            int cx = x1;
            int cy = y1;

            @Override
            public boolean hasNext() {
                return cx < x2 && cy < y2;
            }

            @Override
            public Point next() {
                Point p = new Point(cx, cy);

                cx++;
                if (cx >= x2) {
                    cx = x1;
                    cy ++;
                }

                return p;
            }
        };
    }

    public static Iterable<Point> iterPoints(int x1, int y1, int x2, int y2) {
        return () -> Point.genIterator(x1,y1,x2,y2);
    }
}
