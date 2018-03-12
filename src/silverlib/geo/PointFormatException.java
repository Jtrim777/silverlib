package silverlib.geo;

/**
 * Thrown when a <code>Point</code> is initialized using a <code>String</code> with an improper format
 *
 * @author Jake Trimble
 * @since 1.7.4
 */
public class PointFormatException extends Exception {
    String format;

    /**
     * Initializes a <code>PointFormatException</code>
     *
     * @param sn The name of the property
     *
     * @since 1.7.4
     */
    PointFormatException(String sn) {
        format = sn;
    }
} 
