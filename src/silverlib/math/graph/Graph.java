package silverlib.math.graph;

import silverlib.geo.*;
import silverlib.img.Img;

import java.awt.Color;
import java.util.ArrayList;

/**
 * An image representation of a graph of a function
 *
 * @author Jake Trimble
 * @since 1.9.1
 */
public class Graph extends Img {
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
    public Graph(Function f, Point o, int w, int h) {
        super(w, h);

        Function fxTem = f.translateY(o.y());
        func = fxTem.reflectX();

        origin = o;

        matches = new ArrayList<>();
    }

    /**
     * Fills the <code>matches</code> array with <code>Point</code>s that make <code>Function func</code> true
     *
     * @since 1.9.1
     */
    public void populate() {
        for (int y = 0; y < height(); y++) {
            for (int x = 0; x < width(); x++) {
                Point p = Point.adjustForOrigin(x, y, origin, GraphType.CODE);

                if (func.fx(p.x()) == p.y()) {
                    matches.add(p);
                }
            }
        }
    }

    /**
     * Draws all points contained in <code>matches</code> to the image frame
     *
     * @since 1.9.1
     */
    public void drawSelf() {
        for (Point p : matches) {
            this.drawPoint(p, Color.WHITE);
        }
    }
}