package silverlib.err;

/**
 * Thrown when a <code>Point</code> is initialized using a <code>String</code> with an improper format
 *
 * @author Jake Trimble
 * @since 1.7.4
 */
public class PointFormatException extends ArgFormatException {
    String format;

    /**
     * Initializes a <code>PointFormatException</code>
     *
     * @param sn The name of the property
     *
     * @since 1.7.4
     */
    public PointFormatException(String sn) {
        super(sn, "(x,y)");
    }
} 
