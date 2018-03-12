package silverlib.math;

/** <h1>Fraction Representation</h1> */
public class Fraction {
    int num;
    int den;
    double dec;

    /**
     * Creates an instance of the <code>Fraction</code> class, given a numerator and
     * a denominator.
     *
     * @param n The numerator
     * @param d The denominator
     *
     * @since 1.4.1.1
     */
    public Fraction(int n, int d) {
        num = n;
        den = d;
        dec = (double) num / (double) den;
    }

    /**
     * @return The numerator
     *
     * @since 1.4.1.1
     */
    public int num() {
        return num;
    }

    /**
     * @return The denominator
     *
     * @since 1.4.1.1
     */
    public int den() {
        return den;
    }

    /**
     * @return A decimal representation of the fraction
     *
     * @since 1.4.1.1
     */
    public double decimal() {
        return dec;
    }

    /**
     * Simplifies the Fraction
     *
     * @since 1.4.1.1
     */
    public void simplify() {
        int gcd = Num.GCD(num, den);
        num /= gcd;
        den /= gcd;
    }

    /**
     * @return A <code>String</code> representation of the fraction in the form numerator/denominator.
     *
     * @since 1.4.1.1
     */
    public String toString() {
        return num + "/" + den;
    }
}
