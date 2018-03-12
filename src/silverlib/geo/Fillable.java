package silverlib.geo;

import silverlib.img.*;

import java.awt.Color;

/**
 * Implemented by all <code>Shape</code>s that can be filled with a <code>Color</code>
 *
 * @author Jake Trimble
 * @since 1.6.2
 */
interface Fillable {
    /**
     * A <code>Color</code> that is completely transparent :
     *
     */
    static Color CLEAR = new Color(0, 0, 0, 0);

    /**
     * Draws an <code>Shape</code> into an <code>Img</code>, filling it with a
     * <code>Color</code>
     *
     * @param im The <code>Img</code> to draw onto
     *
     * @since 1.6.2
     */
    public void drawFill(Img im);

    /**
     * Sets the fill <code>Color</code> for a <code>Shape</code> class
     *
     * @param c The <code>Color</code> to setFill
     *
     * @since 1.6.2
     */
    public void setFill(Color c);

    /**
     * @return The fill <code>Color</code> of this <code>Shape</code>
     *
     * @since 1.6.2
     */
    public Color fill();
}
