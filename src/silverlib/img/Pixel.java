package silverlib.img;

import java.awt.Color;

import silverlib.geo.Point;

/**
 * Represents a single pixel in an <code>Img</code>
 *
 * @author Jake Trimble
 * @since 1.7.5
 */
public class Pixel {
    private Point loc;
    private Color col;

    /**
     * Initializes a <code>Pixel</code> from coordinates
     *
     * @param x The x-coodinate in the <code>Img</code> of the <code>Pixel</code>
     * @param y The y-coodinate in the <code>Img</code> of the <code>Pixel</code>
     * @param c The <code>Color</code> of the <code>Pixel</code>
     *
     * @since 1.7.5
     */
    public Pixel(int x, int y, Color c) {
        loc = new Point(x, y);
        col = c;
    }

    /**
     * Initializes a <code>Pixel</code> from a <code>Point</code>
     *
     * @param p The location of the <code>Pixel</code>
     * @param c The <code>Color</code> of the <code>Pixel</code>
     *
     * @since 1.7.5
     */
    public Pixel(Point p, Color c) {
        loc = p;
        col = c;
    }

    public Point loc() {
        return loc;
    }

    public Color col() {
        return col;
    }

    /**
     * Gives the linear location of this <code>Pixel</code> in an array given the width
     * of the <code>Img</code>
     *
     * @param w The width of the <code>Img</code>
     *
     * @return The location of the <code>Pixel</code>
     *
     * @since 1.7.5
     */
    public int locate(int w) {
        return loc.x() + (loc.y() * w);
    }

    /**
     * Gives the linear location of any <code>Pixel</code> in an array given the width
     * of the <code>Img</code>
     *
     * @param xi The x-coodinate in the <code>Img</code> of the <code>Pixel</code>
     * @param yi The y-coodinate in the <code>Img</code> of the <code>Pixel</code>
     * @param w  The width of the <code>Img</code>
     *
     * @return The location of the <code>Pixel</code>
     *
     * @since 1.7.5
     */
    public static int locate(int xi, int yi, int w) {
        return xi + (yi * w);
    }

    /**
     * Gives the coordinates of a <code>Pixel</code> in an <code>Img</code> given
     * its linear position in an array and the width of the <code>Img</code>
     *
     * @param pos The location of the <code>Pixel</code> in an array
     * @param w   The width of the <code>Img</code>
     *
     * @return The coordinates in array form where <code>[0] = x</code> and <code>[1] = y</code>
     *
     * @since 1.7.5
     */
    public static int[] coordinate(int pos, int w) {
        int ry = pos / w;
        int rx = pos - (ry * w);
        return new int[]{rx, ry};
    }
}
