package silverlib.math.graph;

import silverlib.geo.Point;
import silverlib.geo.PolarPoint;
import silverlib.img.Img;

import java.awt.*;
import java.util.ArrayList;

/**
 * An image representation of a graph of a function
 *
 * @author Jake Trimble
 * @since 1.9.1.3
 */
public class PolarGraph extends Graph {
    private Function func;
    private Point origin;
    private int xScale;
    private int yScale;
    private ArrayList<Point> matches;

    /**
     * Initializes a <code>Graph</code> from a <code>Function</code>, origin <code>Point</code>, width, and height
     *
     * @param f The function to graph
     * @param o The origin point
     * @param w The horizontal bounds of the <code>Img</code>
     * @param h The vertical bounds of the <code>Img</code>
     */
    public PolarGraph(Function f, Point o, int w, int h) {
        super(f,o,w,h);

        func = f;

        origin = o;

        matches = new ArrayList<>();
    }

    /**
     * Fills the <code>matches</code> array with <code>Point</code>s that make <code>Function func</code> true
     *
     * @since 1.9.1.3
     */
    public void populate() {
        for (int y = 0; y < height(); y++) {
            for (int x = 0; x < width(); x++) {
                Point p1 = Point.adjustForOrigin(x,y,origin,GraphType.CODE);
                PolarPoint p = p1.toPolar();

                if (func.fx(p.theta()) == p.radius()) {
                    matches.add(p.toRectangular());
                }
            }
        }
    }

    /**
     * Draws all points contained in <code>matches</code> to the image frame
     *
     * @since 1.9.1.3
     */
    public void drawSelf() {
        for (Point p : matches) {
            this.drawPoint(p, Color.WHITE);
        }
    }
}