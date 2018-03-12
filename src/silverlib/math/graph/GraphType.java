package silverlib.math.graph;

/**
 * An enumeration of the various forms of coordinate systems that may be used by the <code>Graph</code> class and
 * its subclasses
 *
 * @author Jake Trimble
 * @since 1.9.1
 */
public enum GraphType {
    /**
     * (0,0) at origin point; y increases upward
     */
    MATH,
    /**
     * (0,0) at top, left corner; y increases down
     */
    CODE,
    /**
     * (0,0) at origin point; x is radius, y is theta
     */
    POLAR
}
