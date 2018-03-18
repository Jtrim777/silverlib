package silverlib.math.graph;

import silverlib.err.FunctionFormatException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A representation of a trigonometric function in a 2D coordinate plane
 *
 * @author Jake Trimble
 * @since 1.9.1.2
 */
public class TrigFunc extends Function {
    private TrigFuncList tFunc;
    private double periodK;

    /**
     * The Regular Expression to match for creating a function from a <code>String</code>
     * {@value "y=(\\d*)(sin|cos|tan|sec|csc|cot)\\((\\d*)x([\\+-])?(\\d*)\\)([\\+-])?(\\d*)"}
     */
    public static String TRIG_REGEXP = "y=(\\d*)(sin|cos|tan|sec|csc|cot)\\((\\d*)x([\\+-])?(\\d*)\\)([\\+-])?(\\d*)";


    /**
     * Initializes a <code>TrigFunc</code> given numerical values
     *
     * @param a The amplitude of the trigonometric function
     * @param t The trigonometric function to use
     * @param k 2π over the period of the trigonometric function
     * @param h The horizontal translation of the trigonometric function
     * @param v The vertical translation of the trigonometric function
     *
     * @since 1.9.1.2
     */
    public TrigFunc(double a, String t, double k, double h, double v) {
        super(a, h, v);

        periodK = k;

        tFunc = TrigFuncList.match(t);
    }

    /**
     * Creates a <code>TrigFunc</code> from a trigonometric representation
     *
     * @param f The function as text conforming to RegExp TRIG_REGEXP
     *
     * @return An initialized <code>TrigFunc</code>
     *
     * @throws FunctionFormatException If <code>f</code> does not match the proper RegExp
     * @since 1.9.1.2
     */
    public static TrigFunc parseFunction(String f) throws FunctionFormatException {
        if (!f.matches(TRIG_REGEXP)) {
            throw new FunctionFormatException(f, "y=asin(kx-h)+k");
        }

        Pattern trigonometricFunctionPattern = Pattern.compile(TRIG_REGEXP);
        Matcher matcher = trigonometricFunctionPattern.matcher(f);

        double a = Double.parseDouble(matcher.group(1));

        TrigFuncList fn = TrigFuncList.match(matcher.group(2));

        double k = Double.parseDouble(matcher.group(3));

        double h_sign = matcher.group(4).equals("-") ? -1 : 1;

        double h = Double.parseDouble(matcher.group(5)) * h_sign;

        double v_sign = matcher.group(6).equals("-") ? -1 : 1;

        double v = Double.parseDouble(matcher.group(7)) * v_sign;

        return new TrigFunc(a,fn.getFunction(),k,h,v);
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
        switch (tFunc){
            case SIN:
                return (float) (slope * Math.sin(periodK*x-xTrans)+yTrans);

            case COS:
                return (float) (slope * Math.cos(periodK*x-xTrans)+yTrans);

            case TAN:
                return (float) (slope * Math.tan(periodK*x-xTrans)+yTrans);

            case SEC:
                return (float) (slope * (1/Math.cos(periodK*x-xTrans))+yTrans);

            case CSC:
                return (float) (slope * (1/Math.sin(periodK*x-xTrans))+yTrans);

            case COT:
                return (float) (slope * (1/Math.tan(periodK*x-xTrans))+yTrans);

             default:
                 return 0;
        }
    }

    /**
     * Get a <code>String</code> representation of the trigonometric function
     *
     * @return A string representation of the function in the form y = a sin(kx-h) + v
     *
     * @since 1.9.1.2
     */
    @Override
    public String toString() {
        char signX = xTrans > 0 ? '-' : '+';
        String transX = Double.toString(Math.abs(xTrans));

        char signY = yTrans > 0 ? '+' : '-';
        String transY = Double.toString(Math.abs(yTrans));

        return String.format("y = %s %s(x %s %s) %s %s", slope, tFunc.getFunction(), signX, transX, signY, transY);
    }
}

