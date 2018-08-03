package silverlib.img;

import silverlib.err.NoSuchPropertyException;
import silverlib.log.*;
import silverlib.geo.*;

import java.util.*;
import java.awt.Color;

/**
 * A series of <code>Scene</code>s where the contained <code>Shape</code>s are
 * modified between scenes. Outputs a .mov file
 *
 * @author Jake Trimble
 * @since 1.7
 */
//public class Movie {
//    private Scene curScene;
//    private ArrayList<Img> frames;
//    private ArrayList<Shape> shapes;
//    private ArrayList<Shape> upShapes;
//    private HashMap<String, Integer> shapeInds;
//    private int numScenes;
//    private String name;
//    private int width, height;
//    private boolean initialized;
//
//    /**
//     * Initializes a <code>Movie</code> object
//     *
//     * @param w The width of all of the <code>Movie</code>'s frames
//     * @param h The width of all of the <code>Movie</code>'s frames
//     *
//     * @since 1.7.3
//     */
//    public Movie(String n, int w, int h) {
//        frames = new ArrayList<Img>();
//        shapes = new ArrayList<Shape>();
//        upShapes = new ArrayList<Shape>();
//        shapeInds = new HashMap<String, Integer>();
//        numScenes = 0;
//        name = n;
//        width = w;
//        height = h;
//        initialized = false;
//    }
//
//    /**
//     * Creates the first <code>Scene</code> in the <code>Movie</code>, sets the
//     * <code>initialized</code> field to <code>true</code>, and initializes the
//     * <code>curScene</code> and <code>upShapes</code> fields.
//     *
//     * @param c The background color of the first <code>Scene</code>
//     *
//     * @since 1.7.3
//     */
//    public void init(Color c) {
//        shapes = upShapes;
//        initialized = true;
//        Scene s1 = new Scene(width, height, c, shapes);
//        Img f1 = s1.getImg();
//        frames.add(f1);
//        curScene = s1;
//        numScenes++;
//    }
//
//    /**
//     * Adds a <code>Shape</code> to the <code>Movie</code> display
//     *
//     * @param n The name of the <code>Shape</code>, to use when editing it
//     * @param s The <code>Shape</code>
//     *
//     * @since 1.7.4
//     */
//    public void addShape(String n, Shape s) {
//        upShapes.add(s);
//        shapeInds.put(n, upShapes.size() - 1);
//    }
//
//    /**
//     * Moves a specified <code>Shape</code> around the coordinate plane by a certain amount
//     *
//     * @param shape The name of the <code>Shape</code> to move
//     * @param chngX The amount to move the <code>Shape</code> by on the x-axis
//     * @param chngY The amount to move the <code>Shape</code> by on the y-axis
//     *
//     * @throws NoSuchShapeException If <code>shape</code> is not a valid key
//     * @since 1.7.4
//     */
//    public void moveShape(String shape, int chngX, int chngY) throws NoSuchShapeException {
//        if (!shapeInds.containsKey(shape)) {
//            throw new NoSuchShapeException(shape);
//        }
//
//        int curX = upShapes.get(shapeInds.get(shape)).x();
//        int curY = upShapes.get(shapeInds.get(shape)).y();
//        int finX = curX + chngX;
//        int finY = curY + chngY;
//        try {
//            upShapes.get(shapeInds.get(shape)).setProp("x", finX);
//            upShapes.get(shapeInds.get(shape)).setProp("y", finY);
//        } catch (Exception e) {
//            Log.print("Something is really wrong here");
//        }
//    }
//
//    /**
//     * Moves a specified <code>Shape</code> to a new loaction
//     *
//     * @param shape The name of the <code>Shape</code> to move
//     * @param finX  The x-coordinate to move the <code>Shape</code> to
//     * @param finY  The y-coordinate to move the <code>Shape</code> to
//     *
//     * @throws NoSuchShapeException If <code>shape</code> is not a valid key
//     * @since 1.7.4
//     */
//    public void moveShapeTo(String shape, int finX, int finY) throws NoSuchShapeException {
//        if (!shapeInds.containsKey(shape)) {
//            throw new NoSuchShapeException(shape);
//        }
//
//        try {
//            upShapes.get(shapeInds.get(shape)).setProp("x", finX);
//            upShapes.get(shapeInds.get(shape)).setProp("y", finY);
//        } catch (Exception e) {
//            Log.print("Something is really wrong here");
//        }
//    }
//
//    /**
//     * Changes the <code>Color</code> of a specified <code>Shape</code>
//     *
//     * @param shape The name of the <code>Shape</code> to re-color
//     * @param f     The <code>Color</code> the shape will be set to
//     *
//     * @throws NoSuchShapeException If <code>shape</code> is not a valid key
//     * @since 1.7.4
//     */
//    public void changeShapeColor(String shape, Color f) throws NoSuchShapeException {
//        if (!shapeInds.containsKey(shape)) {
//            throw new NoSuchShapeException(shape);
//        }
//
//        try {
//            upShapes.get(shapeInds.get(shape)).setProp("red", f.getRed());
//            upShapes.get(shapeInds.get(shape)).setProp("green", f.getGreen());
//            upShapes.get(shapeInds.get(shape)).setProp("blue", f.getBlue());
//        } catch (Exception e) {
//            Log.print("Something is really wrong here");
//        }
//
//    }
//
//    /**
//     * Changes a specific property of a specified <code>Shape</code>
//     *
//     * @param shape The name of the <code>Shape</code> to edit
//     * @param prop  The key of the property to modify
//     * @param val   The value to set the property to
//     *
//     * @throws NoSuchShapeException    If <code>shape</code> is not a valid key
//     * @throws NoSuchPropertyException If <code>prop</code> is not a valid key
//     * @since 1.7.4
//     */
//    public void editShape(String shape, String prop, int val) throws NoSuchShapeException, NoSuchPropertyException {
//        if (!shapeInds.containsKey(shape)) {
//            throw new NoSuchShapeException(shape);
//        }
//
//        upShapes.get(shapeInds.get(shape)).setProp(prop, val);
//    }
//
//    /**
//     * Adds frames to the <code>Movie</code> for all the changes since the last time
//     * <code>commitChanges()</code> was called
//     *
//     * @param dur The number of frames in which to commit changes
//     *
//     * @since 1.7.4
//     */
//    public void commitChanges(int dur) throws NoSuchPropertyException {
//        if (dur <= 0) {
//            Err.log("Invalid animation duration : Must be graeter than 0");
//            return;
//        }
//
//        curScene = curScene.next(upShapes);
//        frames.add(curScene.getImg());
//        numScenes++;
//
//        String shapesToEdit = "";
//        String[] edits = new String[shapes.size()];
//        for (String e : edits) {
//            e = "";
//        }
//
//        for (int s = 0; s < shapes.size(); s++) {
//            Shape sN = shapes.get(s);
//            Shape sL = upShapes.get(s);
//
//            for (String p : sN.props()) {
//                if (sN.getProp(p) != sL.getProp(p)) {
//                    int init = sN.getProp(p);
//                    int fin = sL.getProp(p);
//                    int chng = (fin - init) / dur;
//                    edits[s] += p + "," + chng + "/";
//                }
//            }
//            if (edits[s].contains("/")) {
//                edits[s] = edits[s].substring(0, edits[s].length() - 1);
//            }
//
//            if (!edits[s].equals("")) {
//                shapesToEdit += s + ",";
//            }
//        }
//
//        if (shapesToEdit.length() > 0) {
//            shapesToEdit = shapesToEdit.substring(0, shapesToEdit.length() - 1);
//        }
//
//        String toMod = "";
//
//        if (shapesToEdit.length() > 0) {
//            toMod += shapesToEdit + ":";
//            for (String i : edits) {
//                if (!i.equals("")) {
//                    toMod += i + "`";
//                }
//            }
//            if (toMod.charAt(toMod.length() - 1) == '`') {
//                toMod = toMod.substring(0, toMod.length() - 1);
//            }
//        }
//        else {
//            toMod = null;
//        }
//
//        for (int i = 0; i < dur; i++) {
//            Scene nxt = curScene.next(toMod);
//            frames.add(nxt.getImg());
//            curScene = nxt;
//            numScenes++;
//        }
//
//    }
//}
