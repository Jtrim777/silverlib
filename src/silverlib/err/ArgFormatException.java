package silverlib.err;

/**
 * An exception template for an improper argument format passed to a constructor/method
 *
 * @since 1.9.2
 * @author Jake Trimble
 */
public class ArgFormatException extends Exception {
    private String passedArgument;
    private String properFormat;

    /**
     * Creates a <code>ArgFormatException</code> from a proper format and the improper passed format
     *
     * @param given The improper passed format
     * @param proper The proper format
     *
     * @since 1.9.2
     */
    public ArgFormatException(String given, String proper){
        super("The given argument \"" + given + "\" does not match the format \"" + proper + "\"");

        passedArgument = given;
        properFormat = proper;
    }

    public String getImproperPassedArgument(){
        return passedArgument;
    }

    public String getProperFormat() {
        return properFormat;
    }
}
