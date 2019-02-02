package silverlib.geo;

import silverlib.err.NoSuchPropertyException;
import silverlib.math.*;

/**
 * <h1>A class to represent a series of <code>Point</code>s between two endpoints</h1>
 */
public class Line extends Shape {
    protected Point start;
    protected Point end;

    /**
     * Initializes a <code>Line</code> object from two <code>Point</code>s.
     *
     * @param p1 The start <code>Point</code>
     * @param p2 The end <code>Point</code>
     *
     * @since 1.6.10
     */
    public Line(Point p1, Point p2) {
        super(p1);

        Point a = p2.x > p1.x ? p1 : p2;
        Point b = p2.x > p1.x ? p2 : p1;

        int numPoints = Math.abs(b.x()-a.x());
        int dir = b.x() > a.x() ? 1 : -1;
        double dirY = b.y() > a.y() ? 1 : -1;

        double slope = (double)(b.y()-a.y())/(double)(b.x()-a.x());

        for (int i = 0; i < numPoints; i++) {
            int newX = a.x() + (dir*i);
            int newY = a.y() + (int)(i*slope*dirY);
            pts.add(new Point(newX,newY));
        }
    }


    public void remake(Point p1, Point p2) {
        pts.clear();

        Point a = p2.x > p1.x ? p1 : p2;
        Point b = p2.x > p1.x ? p2 : p1;

        int numPoints = Math.abs(b.x()-a.x());
        int dir = b.x() > a.x() ? 1 : -1;
        double dirY = b.y() > a.y() ? 1 : -1;

        double slope = (double)(b.y()-a.y())/(double)(b.x()-a.x());

        for (int i = 0; i < numPoints; i++) {
            int newX = a.x() + (dir*i);
            int newY = a.y() + (int)(i*slope*dirY);
            pts.add(new Point(newX,newY));
        }
    }

    /**
     * @return The start <code>Point</code> of the <code>Line</code>
     *
     * @since 1.5.1
     */
    public Point start() {
        return pts().get(0);
    }

    /**
     * @return The end <code>Point</code> of the <code>Line</code>
     *
     * @since 1.5.1
     */
    public Point end() {
        return pts().get(pts().size() - 1);
    }

    /**
     * @return A <code>String</code> representation of the <code>Line</code> object, in
     * the form <code>[(x,y),(x2,y2),...]</code>
     *
     * @since 1.5.1
     */
    public String toString() {
        String out = "[";

        for (Point i : pts()) {
            out += i.toString() + ", ";
        }

        out = out.substring(0, out.length() - 2);
        out += "]";

        return out;
    }
}
