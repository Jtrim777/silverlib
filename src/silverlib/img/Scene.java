package silverlib.img;

import silverlib.err.NoSuchPropertyException;
import silverlib.geo.*;

import java.util.*;
import java.awt.Color;


/**
 * A scene in a <code>Movie</code> that represents an <code>Img</code> object
 * with multiple layers of <code>Shape</code> objects.
 *
 * @author Jake Trimble
 * @since 1.7
 */
//public class Scene extends Img {
//    private ArrayList<Shape> layers;
//    private Color mainCol;
//
//    /**
//     * Creates a <code>Scene</code> object with a given width, height, and
//     * background color.
//     *
//     * @param w    The width of the <code>Scene</code>
//     * @param h    The height of the <code>Scene</code>
//     * @param back The background <code>Color</code> of the <code>Scene</code>
//     *
//     * @since 1.7
//     */
//    public Scene(int w, int h, Color back) {
//        super(w, h, back);
//        mainCol = back;
//
//        layers = new ArrayList<Shape>();
//    }
//
//    /**
//     * Creates a <code>Scene</code> object with a given width, height,
//     * background color, and predefined list of <code>Shape</code>s.
//     *
//     * @param w    The width of the <code>Scene</code>
//     * @param h    The height of the <code>Scene</code>
//     * @param back The background <code>Color</code> of the <code>Scene</code>
//     * @param l    An <code>ArrayList</code> of <code>Shape</code> objects
//     *
//     * @since 1.7.2
//     */
//    public Scene(int w, int h, Color back, ArrayList<Shape> l) {
//        super(w, h, back);
//        mainCol = back;
//
//        layers = l;
//    }
//
//    /**
//     * @param l The shapes that exist in this new scene
//     *
//     * @return A new <code>Scene</code> given a new set of <code>Shape</code>s
//     *
//     * @since 1.7.4
//     */
//    public Scene next(ArrayList<Shape> l) {
//        layers = l;
//        return this;
//    }
//
//    /**
//     * Gives a new <code>Scene</code> given a description of the changes that occur
//     * between this <code<Scene</code> and the next
//     *
//     * @param mods A description of the changes that occur between this <code<Scene</code> and the next
//     *
//     * @return A new scene given <code>mods</code>
//     *
//     * @since 1.7.4
//     */
//    public Scene next(String mods) throws NoSuchPropertyException {
//        if (mods == null) {
//            return this;
//        }
//        else {
//            String[] parts = mods.split(":");
//
//            String[] toEdit = parts[0].split(",");
//            String[] chngs = parts[1].split("`");
//
//            ShapeMod[] tMods = new ShapeMod[toEdit.length];
//            for (Integer i = 0; i < toEdit.length; i++) {
//                tMods[i] = new ShapeMod(toEdit[i], chngs[i]);
//            }
//
//            for (ShapeMod s : tMods) {
//                for (Integer i = 0; i < s.props.length; i++) {
//                    layers.get(s.shapeInd).setProp(s.props[i], s.vals[i]);
//                }
//            }
//
//            return this;
//        }
//    }
//
//    /**
//     * @return This <code>Scene</code> as an <code>Img</code>
//     *
//     * @since 1.7.3
//     */
//    public Img getImg() {
//        for (Shape s : layers) {
//            s.draw(this);
//        }
//
//        return (Img) this;
//    }
//}
