package silverlib.img;

/**
 * A description of the changes that occur to a <code>Shape</code> between two
 * <code>Scene</code>s
 *
 * @author Jake Trimble
 * @since 1.7.4
 */
class ShapeMod {
    protected int shapeInd;
    protected String[] props;
    protected int[] vals;

    /**
     * Initializes a <code>ShapeMod</code>
     *
     * @param ind The numerical index of the modified <code>Shape</code>
     * @param pv  A string of the changes that occur to the <code>Shape</code>
     *
     * @since 1.7.4
     */
    ShapeMod(String ind, String pv) {
        shapeInd = Integer.parseInt(ind);

        String[] pairs = pv.split("/");
        props = new String[pairs.length];
        vals = new int[pairs.length];

        for (Integer i = 0; i < pairs.length; i++) {
            String[] nix = pairs[i].split(",");
            props[i] = nix[0];
            vals[i] = Integer.parseInt(nix[1]);
        }
    }
}
