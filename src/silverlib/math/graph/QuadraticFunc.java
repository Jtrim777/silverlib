package silverlib.math.graph;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A representation of a quadratic function in a 2D coordinate plane
 *
 * @author Jake Trimble
 * @since 1.9.1.2
 */
public class QuadraticFunc extends Function {
    /**
     * The Regular Expression to match for creating a function from a <code>String</code>
     * {@value "y=(\\d*)\\(x([\\+-])?(\\d*)\\)\\^(\\d*)([\\+-])?(\\d*)"}
     */
    public static final String QUADRATIC_REGEXP = "y=(\\d*)\\(x([\\+-])?(\\d*)\\)\\^(\\d*)([\\+-])?(\\d*)";

    private int power;

    /**
     * Initializes a <code>QuadraticFunc</code> given numerical values
     *
     * @param a The slope of the quadratic function
     * @param h The horizontal translation of the quadratic function
     * @param p The power to raise the function to
     * @param k The y-intercept of the quadratic function
     *
     * @since 1.9.1.2
     */
    public QuadraticFunc(double a, double h, int p, double k) {
        super(a, h, k);

        power = p;
    }

    /**
     * Creates a <code>QuadraticFunc</code> from a textual representation
     *
     * @param f The function as text conforming to RegExp QUADRATIC_REGEXP
     *
     * @return An initialized <code>QuadraticFunc</code>
     *
     * @throws FunctionFormatException If <code>f</code> does not match the proper RegExp
     * @since 1.9.1.2
     */
    public static QuadraticFunc parseFunction(String f) throws FunctionFormatException {
        if (!f.matches(QUADRATIC_REGEXP)) {
            throw new FunctionFormatException(f, "y=a(x-h)^p+b");
        }

        Pattern quadraticFunctionPattern = Pattern.compile(QUADRATIC_REGEXP);
        Matcher matcher = quadraticFunctionPattern.matcher(f);

        double a = Double.parseDouble(matcher.group(1));

        double h_sign = matcher.group(2).equals("-") ? -1 : 1;

        double h = Double.parseDouble(matcher.group(3)) * h_sign;

        int p = Integer.parseInt(matcher.group(4));

        double k_sign = matcher.group(5).equals("-") ? -1 : 1;

        double k = Double.parseDouble(matcher.group(6)) * k_sign;

        return new QuadraticFunc(a, h, p, k);
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
        return (float) (slope * Math.pow(x - xTrans,power) + yTrans);
    }

    /**
     * Get a <code>String</code> representation of the quadratic function
     *
     * @return A string representation of the function in the form "y = a(x - h)^p + b"
     *
     * @since 1.9.1.2
     */
    @Override
    public String toString() {
        char signX = xTrans > 0 ? '-' : '+';
        String transX = Double.toString(Math.abs(xTrans));

        char signY = yTrans > 0 ? '+' : '-';
        String transY = Double.toString(Math.abs(yTrans));

        return String.format("y = %s(x %s %s)^%s %s %s", slope, signX, transX, power, signY, transY);
    }


}