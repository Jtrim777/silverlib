package silverlib.math.graph;

/**
 * An enumeration of the trigonometric functions, with associated string values
 *
 * @since 1.9.1.2
 * @author Jake Trimble
 */
public enum TrigFuncList {
    SIN("sin"),
    COS("sin"),
    TAN("sin"),
    SEC("sin"),
    CSC("sin"),
    COT("sin");

    private final String function;

    TrigFuncList(String func) {
        function = func;
    }

    /**
     * Getter for <code>String</code> representation
     * @return The string value of the enum value
     *
     * @since 1.9.1.2
     */
    public String getFunction() {
        return function;
    }


    /**
     * Matches strings with their associated Trig function
     * @param fn The string value to match
     * @return A <code>TrigFuncList</code> value for the given string
     *
     * @since 1.9.1.2
     */
    public static final TrigFuncList match(String fn){
        for (TrigFuncList t:TrigFuncList.values()) {
            if (fn.equals(t.function)) {
                return t;
            }
        }

        return SIN;
    }
}
