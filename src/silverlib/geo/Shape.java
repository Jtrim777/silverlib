package silverlib.geo;

import silverlib.math.*;
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
    protected HashMap<String, Integer> properties;

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
        properties = new HashMap<String, Integer>();
        properties.put("x", loc.x);
        properties.put("y", loc.y);
        properties.put("red", c.getRed());
        properties.put("green", c.getGreen());
        properties.put("blue", c.getBlue());
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
        properties = new HashMap<String, Integer>();
        properties.put("x", loc.x);
        properties.put("y", loc.y);
        properties.put("red", 0);
        properties.put("green", 0);
        properties.put("blue", 0);
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
        properties = new HashMap<String, Integer>();
        properties.put("x", loc.x);
        properties.put("y", loc.y);
        properties.put("red", c.getRed());
        properties.put("green", c.getGreen());
        properties.put("blue", c.getBlue());
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
        properties = new HashMap<String, Integer>();
        properties.put("x", loc.x);
        properties.put("y", loc.y);
        properties.put("red", 0);
        properties.put("green", 0);
        properties.put("blue", 0);
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
        for (Point pt : pts) {
            frame.drawPoint(pt, col);
        }
    }

    /**
     * Sets the <code>Color</code> of the <code>Shape</code>.
     *
     * @param c The <code>Color</code> to set to
     *
     * @since 1.5
     */
    public void setColor(Color c) throws NoSuchPropertyException {
        col = c;
        setProp("red", c.getRed());
        setProp("green", c.getGreen());
        setProp("blue", c.getBlue());
    }

    /**
     * Accesses the value of property <code>n</code>
     *
     * @param n The key of the value to be acessed
     *
     * @return The value at <code>properties.get(n)</code>
     *
     * @throws NoSuchPropertyException If <code>n</code> is not a valid property for this <code>Shape</code>
     * @since 1.7
     */
    public int getProp(String n) throws NoSuchPropertyException {
        if (!properties.keySet().contains(n)) {
            throw new NoSuchPropertyException(n);
        }

        return properties.get(n);
    }

    /**
     * Modifies the property with key <code>n</code> of this <code>Shape</code> object.
     *
     * @param n The key of the property to modify
     * @param v The value to set the property to
     *
     * @throws NoSuchPropertyException If <code>n</code> is not a valid property for this <code>Shape</code>
     * @since 1.7.0.1
     */
    public void setProp(String n, int v) throws NoSuchPropertyException {
        if (!properties.keySet().contains(n)) {
            throw new NoSuchPropertyException(n);
        }
        properties.put(n, v);
        loc.x = getProp("x");
        loc.y = getProp("y");
        col = new Color(getProp("red"), getProp("green"), getProp("blue"));
    }

    /**
     * @return A <code>Set<String></code> of all of the keys in <code>properties</code>
     *
     * @since 1.7.4
     */
    public Set<String> props() {
        return properties.keySet();
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
