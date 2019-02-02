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
     * @param a The start <code>Point</code>
     * @param b The end <code>Point</code>
     *
     * @since 1.6.10
     */
    public Line(Point a, Point b) {
        super(a);

        int numPoints = Math.abs(b.x()-a.x());
        int dir = b.x() > a.x() ? 1 : -1;
        double dirY = b.y() > a.y() ? 1 : -1;

        double slope = (double)(b.y()-a.y())/(double)(b.x()-a.x());

        for (int i = 0; i < numPoints; i++) {
            int newX = a.x() + (dir*i);
            int newY = a.y() + (int)(i*slope*dirY);
            pts.add(new Point(newX,newY));
        }

//        Point p1;
//        Point p2;
//
//        if (a.x() <= b.x()) {
//            p1 = a;
//            p2 = b;
//        }
//        else {
//            p1 = b;
//            p2 = a;
//        }
//
//        start = p1;
//        end = p2;
//
//        pts().add(p1);
//
//        Fraction slope = new Fraction((p1.y() - p2.y()), (p1.x() - p2.x()));
//        slope.simplify();
//
//        int xT = p1.x();
//        int yT = p1.y();
//        int rise = slope.num();
//        int run = slope.den();
//
//        if (rise == 0) {
//            run = Math.abs(p1.x() - p2.x());
//        }
//        if (run == 0) {
//            rise = Math.abs(p1.y() - p2.y());
//        }
//
//        do {
//            for (int i = 0; i < Math.abs(rise); i++) {
//                if (rise < 0) {
//                    yT--;
//                }
//                else {
//                    yT++;
//                }
//
//                pts().add(new Point(xT, yT));
//            }
//
//            for (int i = 0; i < Math.abs(run); i++) {
//                //Log.print("Adding "+run +" times");
//                if (run < 0) {
//                    xT--;
//                }
//                else {
//                    xT++;
//                }
//
//                pts().add(new Point(xT, yT));
//            }
//        } while (xT < p2.x() && yT < p2.y());
    }


    public void remake(Point a, Point b) {
        pts.clear();

        int numPoints = b.x()-a.x();
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
