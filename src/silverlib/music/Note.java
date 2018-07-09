package silverlib.music;

/**
 * Represents a single note on the piano
 *
 * @since 1.10.0
 * @author Jake Trimble
 */
public class Note extends SlvSound {

    /**
     * The duration of a whole note (in milliseconds) at Moderato tempo
     */
    public static int WHOLE = 2000;
    public static int HALF = WHOLE/2;
    public static int QUARTER = HALF/2;
    public static int EIGHTH = QUARTER/2;
    public static int SIXTEENTH = EIGHTH/2;

    /**
     * Initializes the Note from a text representation
     * @param sym The text source
     * @param f The song's flats
     * @param s The song's sharps
     *
     * @since 1.10.0
     */
    public Note(String sym, String f, String s){
        super(0,f,s,Notes.REST);

        notes = new Notes[1];

        String src = sym.replace('.','Y');
        src = src.replace('*','Z');

        if (sym.contains("$")){
            natural = true;
            src = src.replace("$","");
        }

        if (src.contains("Z")){
            String[] pts = src.split("Z");

            notes[0] = Notes.match(pts[0]);

            duration =(int)((double)WHOLE*Double.parseDouble(pts[1].replace('Y','.')));

            //System.out.println("Note "+notes[0].getKey()+" with duration "+duration);
        }
        else if(src.contains("Y")){
            String[] pts = src.split("Y");

            notes[0] = Notes.match(pts[0]);

            duration = WHOLE/Integer.parseInt(pts[1]);
        }  else {
            notes[0] = Notes.match(src);

            duration = QUARTER;
        }

        //System.out.println(sym + " -> "+src+" -> "+this.toString());
    }

    public Note(Notes k, int d, String f, String s){
        super(d,f,s,k);
    }


}