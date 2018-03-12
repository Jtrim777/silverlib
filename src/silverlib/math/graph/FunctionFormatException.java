package silverlib.math.graph;

/**
 * Thrown when a <code>Point</code> is initialized using a <code>String</code> with an improper format
 *
 * @author Jake Trimble
 * @since 1.9.1
 */
public class FunctionFormatException extends Exception {

    /**
     * Initializes a <code>FunctionFormatException</code> given an invalid function and the proper format
     *
     * @param fn     The invalid function <code>String</code>
     * @param format The proper function format
     *
     * @since 1.9.1
     */
    FunctionFormatException(String fn, String format) {
        super("The given arguments \"" + fn + "\" do not match the format \"" + format + "\"");
    }
}
