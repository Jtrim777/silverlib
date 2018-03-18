package silverlib.geo;

import silverlib.err.NoSuchPropertyException;

/** <h1>Point class in a graphical 2D plane</h1> */
public class Dot extends Shape {

    /**
     * Initializes a <code>Dot</code> object from a <code>Point</code>.
     *
     * @param a The <code>Point</code>
     *
     * @since 1.5.2
     */
    public Dot(Point a) {
        super(a);
        pts().add(a);
    }

    /**
     * @return A <code>String</code> representation of the <code>Dot</code> object, in
     * the form <code>(x,y)</code>
     *
     * @since 1.5.2
     */
    public String toString() {
        return "(" + loc().x() + "," + loc().y() + ")";
    }

    public void setProp(String n, int v) throws NoSuchPropertyException {
        super.setProp(n, v);

        pts.clear();
        pts.add(loc);
    }
}
