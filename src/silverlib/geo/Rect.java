package silverlib.geo;

import silverlib.math.*;
import silverlib.log.*;

import java.awt.Color;

import silverlib.img.*;

/**
 * <h1>Rectangle class</h1>
 * Contains a series of 4 <code>Line</code>s
 */
public class Rect extends Shape implements Fillable {
    private Line[] lines;
    private int width;
    private int height;
    private Color fill;

    /**
     * Initializes a <code>Rect</code> object from an x and y coordinate, and the width
     * and height of the <code>Shape</code>.
     *
     * @param x The x coordinate
     * @param y The y coordinate
     * @param w The width of the rectangle
     * @param h The height of the rectangle
     *
     * @since 1.5.1
     */
    public Rect(int x, int y, int w, int h) {
        super(x, y);
        fill = CLEAR;
        width = w;
        height = h;

        properties.put("width", width);
        properties.put("height", height);
        properties.put("fillR", fill.getRed());
        properties.put("fillG", fill.getGreen());
        properties.put("fillB", fill.getBlue());

        lines = new Line[4];
    
    /*
           0
       ---------
    2  |        | 3
       |        |
       ---------
           1
    */
        Point p1 = new Point(x + (w - 1), y);
        Point p2 = new Point(x + (w - 1), y + (h - 1));
        Point p3 = new Point(x, y + (h - 1));

        lines[0] = new Line(loc, p1);
        lines[1] = new Line(p3, p2);
        lines[2] = new Line(loc, p3);
        lines[3] = new Line(p1, p2);

        for (Line line : lines) {
            for (Point pt : line.pts) {
                pts.add(pt);
            }
        }
    }

    /**
     * Initializes a square <code>Rect</code> object from an x and y coordinate, and size
     * of the <code>Shape</code>.
     *
     * @param x The x coordinate
     * @param y The y coordinate
     * @param s The width and height of the square
     *
     * @since 1.5.1
     */
    public Rect(int x, int y, int s) {
        super(x, y);
        fill = CLEAR;
        width = s;
        height = s;

        properties.put("width", width);
        properties.put("height", height);
        properties.put("fillR", fill.getRed());
        properties.put("fillG", fill.getGreen());
        properties.put("fillB", fill.getBlue());

        lines = new Line[4];

        Point p1 = new Point(x + (s - 1), y);
        Point p2 = new Point(x + (s - 1), y + (s - 1));
        Point p3 = new Point(x, y + (s - 1));

        lines[0] = new Line(loc, p1);
        lines[1] = new Line(p3, p2);
        lines[2] = new Line(loc, p3);
        lines[3] = new Line(p1, p2);

        for (Line line : lines) {
            for (Point pt : line.pts) {
                pts.add(pt);
            }
        }
    }

    /**
     * Initializes a <code>Rect</code> object from a <code>Point</code>, and the width
     * and height of the <code>Shape</code>.
     *
     * @param p The location of the shape
     * @param w The width of the rectangle
     * @param h The height of the rectangle
     *
     * @since 1.5.1
     */
    public Rect(Point p, int w, int h) {
        super(p);
        fill = CLEAR;
        width = w;
        height = h;

        properties.put("width", width);
        properties.put("height", height);
        properties.put("fillR", fill.getRed());
        properties.put("fillG", fill.getGreen());
        properties.put("fillB", fill.getBlue());

        lines = new Line[4];

        Point p1 = new Point(p.x() + (w - 1), p.y());
        Point p2 = new Point(p.x() + (w - 1), p.y() + (h - 1));
        Point p3 = new Point(p.x(), p.y() + (h - 1));

        lines[0] = new Line(loc, p1);
        lines[1] = new Line(p3, p2);
        lines[2] = new Line(loc, p3);
        lines[3] = new Line(p1, p2);

        for (Line line : lines) {
            for (Point pt : line.pts) {
                pts.add(pt);
            }
        }
    }

    /**
     * Initializes a square <code>Rect</code> object from a <code>Point</code>, and size
     * of the <code>Shape</code>.
     *
     * @param p The location of the shape
     * @param s The width and height of the square
     *
     * @since 1.5.1
     */
    public Rect(Point p, int s) {
        super(p);
        fill = CLEAR;
        width = s;
        height = s;

        properties.put("width", width);
        properties.put("height", height);
        properties.put("fillR", fill.getRed());
        properties.put("fillG", fill.getGreen());
        properties.put("fillB", fill.getBlue());

        lines = new Line[4];

        Point p1 = new Point(p.x() + (s - 1), p.y());
        Point p2 = new Point(p.x() + (s - 1), p.y() + (s - 1));
        Point p3 = new Point(p.x(), p.y() + (s - 1));

        //Log.print("Line 1:");
        lines[0] = new Line(loc, p1);
        //Log.print("Line 2:");
        lines[1] = new Line(p3, p2);
        //Log.print("Line 3:");
        lines[2] = new Line(loc, p3);
        //Log.print("Line 4:");
        lines[3] = new Line(p1, p2);

        for (Line line : lines) {
            //Log.print(line.toString());
            for (Point pt : line.pts) {
                pts.add(pt);
            }
        }
    }

    /**
     * @return The <code>Line</code>s that make up this rectangle
     *
     * @since 1.5.1
     */
    public Line[] lines() {
        return lines;
    }

    /**
     * @return The width of this rectangle
     *
     * @since 1.5.1
     */
    public int width() {
        return width;
    }

    /**
     * @return The height of this rectangle
     *
     * @since 1.5.1
     */
    public int height() {
        return height;
    }

    /**
     * @return The fill <code>Color</code> of this rectangle
     *
     * @since 1.6.2
     */
    public Color fill() {
        return fill;
    }

    /**
     * Sets a property and updates class fields, also redraws the rectangle if
     * necessary.
     *
     * @param n The key of the property to modify
     * @param v The value to set the property to
     *
     * @since 1.7.1
     */
    @Override
    public void setProp(String n, int v) throws NoSuchPropertyException {
        super.setProp(n, v);

        if (n.equals("width") || n.equals("height")) {
            width = getProp("width");
            height = getProp("height");
            remake();
        }
        else if (n.equals("x") || n.equals("y")) {
            remake();
        }
        else if (n.equals("fillR") || n.equals("fillG") || n.equals("fillB")) {
            fill = new Color(getProp("fillR"), getProp("fillG"), getProp("fillB"));
        }
    }

    /**
     * Redraws the rectangle
     *
     * @since 1.7.1
     */
    public void remake() {
        lines = new Line[4];

        Point p1 = new Point(loc.x() + (width - 1), loc.y());
        Point p2 = new Point(loc.x() + (width - 1), loc.y() + (height - 1));
        Point p3 = new Point(loc.x(), loc.y() + (height - 1));

        lines[0] = new Line(loc, p1);
        lines[1] = new Line(p3, p2);
        lines[2] = new Line(loc, p3);
        lines[3] = new Line(p1, p2);

        for (Line line : lines) {
            for (Point pt : line.pts) {
                pts.add(pt);
            }
        }
    }

    /**
     * Draws the rectangle into an <code>Img</code> object, filling it with color
     * if the fill color has been set
     *
     * @param im The <code>Img</code> to draw to
     *
     * @since 1.6.2
     */
    @Override
    public void draw(Img im) {
        if (fill.equals(CLEAR)) {
            super.draw(im);
            Log.print("Drawing border");
        }
        else {
            drawFill(im);
            Log.print("Drawing shape");
        }
    }

    /**
     * Draws the rectangle into an <code>Img</code> object, filling it with color
     *
     * @param im The <code>Img</code> object to draw to
     *
     * @since 1.6.2
     */
    public void drawFill(Img im) {
        for (int i = loc.y(); i < height + loc.y(); i++) {
            for (int j = loc.x(); j < width + loc.x(); j++) {
                Point d = new Point(j, i);
                im.drawPoint(d, fill);
            }
        }

        super.draw(im);
    }

    /**
     * Sets the fill Color
     *
     * @param c The <code>Color</code> to setFill
     *
     * @since 1.6.2
     */
    public void setFill(Color c) {
        fill = c;
    }

    /**
     * @return A <code>String</code> representation of the <code>Rect</code> object, in
     * the form <code>{[(x,y),(x2,y2),...],[(x,y),(x2,y2),...],...}</code>
     *
     * @since 1.5.1
     */
    public String toString() {
        String out = "{";

        for (Line i : lines) {
            out += i.toString() + ", ";
        }

        out = out.substring(0, out.length() - 2);
        out += "}";

        return out;
    }
}
