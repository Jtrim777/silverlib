package silverlib.music;

/**
 * Represents a chord on the piano
 *
 * @since 1.10.0
 * @author Jake Trimble
 */
public class Chord extends SlvSound {

    /**
     * Initializes the Chord from a text representation
     * @param sym The text source
     * @param f The song's flats
     * @param s The song's sharps
     *
     * @since 1.10.0
     */
    public Chord(String sym, String f, String s){
        super(0,f,s,Notes.REST);

        String src = sym.replace('|','P');
        src = src.replace('.','Y');
        src = src.replace('*','Z');

        if (sym.contains("$")){
            natural = true;
            src = src.replace("$","");
        }

        if (src.contains("Z")){
            String[] pts = src.split("Z");

            String[] kys = pts[0].split("P");
            notes = new Notes[kys.length];

            for (int i = 0; i < kys.length; i++) {
                notes[i] = Notes.match(kys[i]);
            }

            duration =(int)((double)Note.WHOLE*Double.parseDouble(pts[1].replace('Y','.')));
        }
        else if (src.contains("Y")){
            String[] pts = src.split("Y");

            String[] kys = pts[0].split("P");
            notes = new Notes[kys.length];

            for (int i = 0; i < kys.length; i++) {
                notes[i] = Notes.match(kys[i]);
            }

            duration = Note.WHOLE/Integer.parseInt(pts[1]);
        }
        else {
            String[] kys = src.split("P");
            notes = new Notes[kys.length];

            for (int i = 0; i < kys.length; i++) {
                notes[i] = Notes.match(kys[i]);
            }

            duration = Note.QUARTER;
        }

        //System.out.println(sym + " -> "+src+" -> "+this.toString());
    }

    public Chord(int d, String f, String s, Notes ... k){
        super(d,f,s,k);
    }

    public Chord(String f, String s, Note ... n){
        super(n[0].duration, f, s, Notes.REST);

        notes = new Notes[n.length];

        for (int i = 0; i < n.length; i++) {
            notes[i] = n[i].notes[0];
        }
    }
}
