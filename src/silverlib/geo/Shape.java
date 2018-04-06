package silverlib.geo;

import silverlib.err.NoSuchPropertyException;
import silverlib.img.*;

import java.util.*;
import java.awt.Color;

/**
 * <h1>Abstract class to represent a 2D shape</h1>
 */
public abstract class Shape {
    protected ArrayList<Point> pts;
    protected Point loc;
    protected Color col;
    protected int strokeWidth;

    /**
     * Initializes a <code>Shape</code> object from a <code>Point</code> and a
     * <code>Color</code>. Used as a <code>super()</code> constructor in all subclasses.
     *
     * @param l The location of the shape in a 2D coordinate plane.
     * @param c The <code>Color</code> of the shape
     *
     * @since 1.5
     */
    public Shape(Point l, Color c) {
        loc = l;
        col = c;
        pts = new ArrayList<Point>();
        strokeWidth = 1;

    }

    /**
     * Initializes a <code>Shape</code> object from a <code>Point</code>. Initializes <code>col</code> as <code>Color.BLACK</code>.
     * Used as a <code>super()</code> constructor in all subclasses.
     *
     * @param l The location of the shape in a 2D coordinate plane.
     *
     * @since 1.5
     */
    public Shape(Point l) {
        loc = l;
        col = Color.BLACK;
        pts = new ArrayList<Point>();
        strokeWidth = 1;

    }

    /**
     * Initializes a <code>Shape</code> object from x and y coordinates and a
     * <code>Color</code>. Used as a <code>super()</code> constructor in all subclasses.
     *
     * @param x The x coordinate of the shape in a 2D coordinate plane.
     * @param y The y coordinate of the shape in a 2D coordinate plane.
     * @param c The <code>Color</code> of the shape
     *
     * @since 1.5
     */
    public Shape(int x, int y, Color c) {
        loc = new Point(x, y);
        col = c;
        pts = new ArrayList<Point>();
        strokeWidth = 1;

    }

    /**
     * Initializes a <code>Shape</code> object from from x and y coordinates.
     * Initializes <code>col</code> as <code>Color.BLACK</code>.Used as a <code>super()</code>
     * constructor in all subclasses.
     *
     * @param x The x coordinate of the shape in a 2D coordinate plane.
     * @param y The y coordinate of the shape in a 2D coordinate plane.
     *
     * @since 1.5
     */
    public Shape(int x, int y) {
        loc = new Point(x, y);
        col = Color.BLACK;
        pts = new ArrayList<Point>();
        strokeWidth = 1;

    }

    /**
     * @return <code>ArrayList</code> of <code>Point</code>s.
     *
     * @since 1.5
     */
    public ArrayList<Point> pts() {
        return pts;
    }

    /**
     * @return <code>Point</code> where the shape is located.
     *
     * @since 1.5
     */
    public Point loc() {
        return loc;
    }

    /**
     * @return x coordinate of a the <code>Shape</code>.
     *
     * @since 1.5
     */
    public int x() {
        return loc.x();
    }

    /**
     * @return y coordinate of a the <code>Shape</code>.
     *
     * @since 1.5
     */
    public int y() {
        return loc.y();
    }

    /**
     * @return <code>Color</code> of the <code>Shape</code>.
     *
     * @since 1.5
     */
    public Color col() {
        return col;
    }

    /**
     * Draws the shape onto the given <code>Img</code> object.
     *
     * @param frame The <code>Img</code> to draw in.
     *
     * @since 1.5
     */
    public void draw(Img frame) {
        if (strokeWidth == 1) {
            for (Point pt : pts) {
                frame.drawPoint(pt, col);
            }
        } else {
            for (Point pt : pts){
                Circle dot = new Circle(pt,strokeWidth);
                dot.setColor(this.col);
                dot.setFill(this.col);

                dot.drawFill(frame);
            }
        }
    }

    /**
     * Sets the <code>Color</code> of the <code>Shape</code>.
     *
     * @param c The <code>Color</code> to set to
     *
     * @since 1.5
     */
    public void setColor(Color c) {
        col = c;
    }

    /**
     * Sets the stroke width of the <code>Shape</code>
     *
     * @param w The stroke width
     *
     * @since 1.9.3
     */
    public void setStrokeWidth(int w){
        strokeWidth = w;
    }

    /**
     * Method for all subclasses to implement so they return a <code>String</code> representation
     * of the <code>Shape</code>'s <code>Point</code>s.
     *
     * @return A text representation of the <code>Shape</code>'s <code>Point</code>s
     *
     * @since 1.5
     */
    public abstract String toString();
}
