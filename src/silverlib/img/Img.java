package silverlib.img;

import java.awt.image.*;
import javax.imageio.*;
import java.awt.Color;
import java.io.*;

import silverlib.log.*;
//import silverlib.math.*;
import silverlib.geo.*;

import java.util.*;

/**
 * <h1>Simplified Image Class</h1>
 * A class that stores an image as a collection of <code>Color</code> objects, and provides
 * methods for examining, editing, dividing, and saving that image.
 */
public class Img {
    private Color[] pixels;
    private int height;
    private int width;

    /**
     * Initializes an <code>Img</code> object uploaded from an image file at the given location.
     * <code>nm</code> must be a valid file location that points to a .png, .jpg, or .jpeg
     * file.
     *
     * @param nm The absolute file location of the desired image
     *
     * @since 1.3
     */
    public Img(String nm) throws IOException {
        File f = new File(nm);
        BufferedImage rawImg = ImageIO.read(f);

        width = rawImg.getWidth();
        height = rawImg.getHeight();

        int[] rawPix = rawImg.getRGB(0, 0, width, height, null, 0, width);

        //Log.print(rawPix.length + "");

        pixels = new Color[width * height];

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = new Color(rawPix[i]);
        }
    }

    /**
     * Initializes an <code>Img</code> object given an array of <code>Color</code> objects
     * and the width of the image.The length of <code>pix</code> must be a multiple of <code>w</code>.
     *
     * @param pix The array of <code>Color</code> objects that defines the pixels in the image.
     * @param w   The width of the image, in pixels
     *
     * @since 1.3.1
     */
    public Img(Color[] pix, int w) {
        height = pix.length / w;
        width = pix.length / height;
        pixels = pix;
    }

    /**
     * Initializes an empty (Black) <code>Img</code> object with a given width and height.
     *
     * @param w The width of the image, in pixels
     * @param h The height of the image, in pixels
     *
     * @since 1.5
     */
    public Img(int w, int h) {
        height = h;
        width = w;

        pixels = new Color[width * height];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                int p = j + (i * width);
                if (p < (width * height)) {
                    try {
                        pixels[p] = Color.BLACK;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        Err.log("P:" + p + "I:" + i + "J:" + j);
                    }
                }
            }
        }

//        Log.print("Initialized");
    }

    /**
     * Initializes an empty <code>Img</code> object with a given width, height, and
     * background color.
     *
     * @param w The width of the image, in pixels
     * @param h The height of the image, in pixels
     * @param c The <code>Color</code> of the blank <code>Img</code>
     *
     * @since 1.7
     */
    public Img(int w, int h, Color c) {
        height = h;
        width = w;

        pixels = new Color[width * height];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                int p = j + (i * width);
                if (p < (width * height)) {
                    try {
                        pixels[p] = c;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        Err.log("P:" + p + "I:" + i + "J:" + j);
                    }
                }
            }
        }

//        Log.print("Initialized");
    }

    /**
     * @return The width of the image, in pixels.
     *
     * @since 1.3
     */
    public int width() {
        return width;
    }

    /**
     * @return The height of the image, in pixels.
     *
     * @since 1.3
     */
    public int height() {
        return height;
    }

    /**
     * @return The array of <code>Color</code> objects that form the pixels of the image.
     *
     * @since 1.3
     */
    public Color[] pixels() {
        return pixels;
    }

    /**
     * Sets the pixel at <code>(x,y)</code> to the <code>Color</code> n.
     *
     * @param x The x-coordinate of the pixel to change
     * @param y The y-coordinate of the pixel to change
     * @param n The <code>Color</code> to change the pixel to
     *
     * @since 1.3
     */
    public void set(int x, int y, Color n) {
        int i = x + (y * width);
        pixels[i] = n;
    }

    /**
     * Sets the pixel at <code>Point</code> p to the <code>Color</code> n.
     *
     * @param p The Point of the pixel to change
     * @param n The <code>Color</code> to change the pixel to
     *
     * @since 1.4.3
     */

    public void drawPoint(Point p, Color n) {
        if (p.x() < width && p.y() < height && p.x() >= 0 && p.y() >= 0 ){
            set(p.x(), p.y(), n);
        }
    }

    /**
     * @param x The x-coordinate of the pixel to query
     * @param y The y-coordinate of the pixel to query
     *
     * @return The <code>Color</code> of the pixel at <code>(x,y)</code>.
     *
     * @since 1.3
     */
    public Color get(int x, int y) {
        int i = x + (y * width);
        return pixels[i];
    }

    /**
     * Saves the <code>Img</code> object to a png file with file path <code>nm</code>.
     * <code>nm</code> must be a valid absolute file path without a file extension.
     * Prints "Failed to save Image" to the console if the IO operation fails.
     *
     * @param nm The absolute file-path without extension to save the PNG at
     *
     * @since 1.3.1
     */
    public void save(String nm) {
        BufferedImage out = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        int[] outPix = new int[width * height];

        Log.print("Saving image with " + outPix.length + " pixels to "+nm+".png...");

        for (int i = 0; i < pixels.length; i++) {
            try {
                outPix[i] = pixels[i].getRGB();
            } catch (NullPointerException e) {
                outPix[i] = Color.black.getRGB();
                Log.print("Pixel Error");
            }
        }

        out.setRGB(0, 0, width, height, outPix, 0, width);

        File o = new File(nm + ".png");
        try {
            ImageIO.write(out, "png", o);
        } catch (IOException e) {
            Log.print("Failed to save Image");
            e.printStackTrace();
        }

    }

    /**
     * Generates an <code>Img</code> object representing a section of this object, between
     * the corners defined by their pixel coordinates.
     *
     * @param x1 The x-coordinate of the upper-left corner
     * @param y1 The y-coordinate of the upper-left corner
     * @param x2 The x-coordinate of the lower-right corner
     * @param y2 The y-coordinate of the lower-right corner
     *
     * @return The subimage
     *
     * @since 1.3.2
     */
    public Img subimage(int x1, int y1, int x2, int y2) {
        int w = x2 - x1;
        int h = y2 - y1;

        Color[] convPix = new Color[h * w];

        for (int y = y1; y < y2; y++) {
            for (int x = x1; x < x2; x++) {
                int i = (x - x1) + ((y - y1) * w);
                convPix[i] = get(x, y);
            }
        }

        return new Img(convPix, w);
    }

    /**
     * Draws and array of <code>Pixel</code>s onto the <code>Img</code>
     *
     * @param in The array of <code>Pixel</code>s
     *
     * @since 1.7.5
     */
    public void drawPixels(Pixel[] in) {
        for (Pixel p : in) {
            try {
                drawPoint(p.loc(), p.col());
            } catch (ArrayIndexOutOfBoundsException e) {
                Err.log("Invalid Point:\n\tx: " + p.loc().x() + "\n\ty: " + p.loc().y());
            }
        }
    }

    public ArrayList<Pixel> asPixels() {
        ArrayList<Pixel> out = new ArrayList<>();

        for (int y=0; y<height; y++) {
            for (int x=0; x<width; x++) {
                Color cap = this.pixels[(y*width)+x];

                out.add(new Pixel(x, y, cap));
            }
        }

        return out;
    }
}
