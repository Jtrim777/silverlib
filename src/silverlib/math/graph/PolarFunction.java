package silverlib.math.graph;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A representation of a polar function in a 2D polar coordinate plane
 *
 * @author Jake Trimble
 * @since 1.9.1.3
 */
public class PolarFunction extends Function {
    /**
     * The Regular Expression to match for creating a function from a <code>String</code>
     * {@value "y=(\\d*)x([\\+-])?(\\d*)"}
     */
    public static final String POLAR_REGEXP = "y=(\\d*)x([\\+-])?(\\d*)";

    /**
     * Initializes a <code>PolarFunc</code> given numerical values
     *
     * @param m The slope of the linear function
     * @param h The horizontal translation of the linear function
     * @param b The y-intercept of the linear function
     *
     * @since 1.9.1.3
     */
    public PolarFunction(double m, double h, double b) {
        super(m, h, b);
    }

    /**
     * Creates a <code>LinearFunc</code> from a textual representation
     *
     * @param f The function as text conforming to RegExp POLAR_REGEXP
     *
     * @return An initialized <code>LinearFunc</code>
     *
     * @throws FunctionFormatException If <code>f</code> does not match the proper RegExp
     * @since 1.9.1.3
     */
    public static PolarFunction parseFunction(String f) throws FunctionFormatException {
        if (!f.matches(POLAR_REGEXP)) {
            throw new FunctionFormatException(f, "y=mx+b");
        }

        Pattern linearFunctionPattern = Pattern.compile(POLAR_REGEXP);
        Matcher matcher = linearFunctionPattern.matcher(f);

        double m = Double.parseDouble(matcher.group(1));

        double sign = matcher.group(2).equals("-") ? -1 : 1;

        double b = Double.parseDouble(matcher.group(3)) * sign;

        PolarFunction out = new PolarFunction(m, 0, b);

        return out;
    }

    @Override
    public double fx(double x) {
        return (double) fx((float) x);
    }

    @Override
    public int fx(int x) {
        return (int) fx((float) x);
    }

    @Override
    public float fx(float x) {
        return (float) (slope * (x - xTrans) + yTrans);
    }

    /**
     * Get a <code>String</code> representation of the linear function
     *
     * @return A string representation of the function in the form y = m(x-h) + b
     *
     * @since 1.9.1.3
     */
    @Override
    public String toString() {
        char signX = xTrans > 0 ? '-' : '+';
        String transX = Double.toString(Math.abs(xTrans));

        char signY = yTrans > 0 ? '+' : '-';
        String transY = Double.toString(Math.abs(yTrans));

        return String.format("y = %s(x %s %s) %s %s", slope, signX, transX, signY, transY);
    }


}