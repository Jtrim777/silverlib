package silverlib.music;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

/** Represents a piano note or chord
 *
 * @since 1.10.0
 * @author Jake Trimble
 */
abstract class SlvSound {
   protected int duration;
   protected Notes[] notes;

   private String flats;
   private String sharps;

   boolean natural;

    /**
     * Creates an SlvSound
     * @param d The note/chord's duration in milliseconds
     * @param f The <code>Song</code>'s flatted keys
     * @param s The <code>Song</code>'s sharped keys
     * @param nts The note/notes that comprise the sound
     *
     * @since 1.10.0
     */
   SlvSound(int d, String f, String s, Notes... nts){
       notes = nts;
       duration = d;
       flats = f;
       sharps = s;
       natural = false;
   }

    /**
     * Converts the note to a string
     * @return String in the format Note1 Note2 Note3| duration
     *
     * @since 1.10.0
     */
    public String toString(){

        StringBuilder outBuilder = new StringBuilder();
        for (Notes n:notes){
           outBuilder.append(n.getKey()).append(" ");
       }
        String out = outBuilder.toString();

        out += "| ";

       out += duration + "ms";

       return out;
    }


    /**
     * Generates a Note or a Chord
     * @param inp The string information of the sound
     * @param f The song's flats
     * @param s The song's sharps
     * @return A <code>Note</code> or a <code>Chord</code>
     *
     * @since 1.10.0
     */
    public static SlvSound process(String inp, String f, String s){
        if (inp.contains("|")){
            return new Chord(inp,f,s);
        } else {
            return new Note(inp,f,s);
        }
    }

    /**
     * Add's this note as a <code>javax.midi.MidiEvent</code> to the given <code>javax.midi.Track</code>
     * @param track The track to add the note to
     * @param timeLoc The position (in milliseconds) to add the note at
     * @param volume The volume at which to play the note (0-127)
     * @throws InvalidMidiDataException If the <code>MidiEvent</code> fails to initialize
     *
     * @since 1.10.0
     */
    public void genEvents(Track track, int timeLoc, int volume) throws InvalidMidiDataException {
        for (Notes n : notes){
            int nNum = n.getKeyNum();

            if(!natural) {
                if (flats.contains(n.getNote())) {
                    nNum--;
                }
                else if (sharps.contains(n.getNote())) {
                    nNum--;
                }
            }

            ShortMessage sm = new ShortMessage(ShortMessage.NOTE_ON,0,nNum, volume);
            MidiEvent cEvent = new MidiEvent(sm,timeLoc);

            track.add(cEvent);
        }

        timeLoc += duration;

        for (Notes n : notes){
            int nNum = n.getKeyNum();

            if (!natural) {
                if (flats.contains(n.getNote())) {
                    nNum--;
                }
                else if (sharps.contains(n.getNote())) {
                    nNum--;
                }
            }

            ShortMessage sm = new ShortMessage(ShortMessage.NOTE_OFF,0,nNum, volume);
            MidiEvent cEvent = new MidiEvent(sm,timeLoc);
            track.add(cEvent);
        }
    }

    /**
     * Recalculates the duration of the note based on the song's tempo
     * @param tempo The song's tempo
     * @return The duration of the note in this song (in milliseconds)
     *
     * @since 1.10.0
     */
    public int getDuration(String tempo, int whole){
        int aTempo = Notes.match(tempo).getKeyNum();

        double timeRatio = (double)whole/(double)Note.WHOLE;

        return (int)((120.0/(double)aTempo) * (double)duration * timeRatio);
    }
}
