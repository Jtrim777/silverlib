package silverlib.music;

import silverlib.io.IO;

import javax.sound.midi.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Represents a musical piece on the piano, with treble and bass tracks, loaded from a text file formatted according to
 * https://silordoflight.github.io/silverlib/javaDoc/SongFormat.html
 *
 * @since 1.10
 * @author Jake Trimble
 */
public class Song implements Serializable{
    private Sequence song;

    private static final String[] TRACK_NAMES = new String[]{"Treble Cleff", "Bass Cleff", "Treble Acc.", "Bass Acc.", "Other Acc. #1", "Other Acc. #2"};

    private int totalLength;

    private String name;

    /**
     * Initializes a <code>Song</code> object using the string file path of the source file
     * @param filePath The string file path of the source file
     * @throws IOException If source file loading fails
     * @throws InvalidMidiDataException If <code>Sequence</code> fails to load its <code>Track</code>'s
     *
     * @since 1.10.4
     */
    public Song(String filePath, boolean debug) throws IOException, InvalidMidiDataException {
        song = new Sequence(Sequence.PPQ,500,6);

        //Load song data
        File srcFile = new File(filePath);
        String rawData = IO.readFile(srcFile.getAbsolutePath());

        //Process raw string
        String[] songInfo = rawData.split("\n")[0].split(",");
        String modTxt = rawData.substring(rawData.indexOf('\n')+1);
        String[] rawTxtTracks = modTxt.split("\n%\n");

        //Process song information
        name = songInfo[0];
        int beatsPerBar = Integer.parseInt(songInfo[1]);
        int beatLength = Note.WHOLE / Integer.parseInt(songInfo[2]);
        String tempo = songInfo[3];
        String flats = songInfo[4];
        String sharps = songInfo[5];

        System.out.println("> Generating song \""+name+"\" from raw text data...");

        for (int i = 0; i < rawTxtTracks.length; i++) {
            ArrayList<String> rawList = new ArrayList<>(Arrays.asList(rawTxtTracks[i].replace("\n"," - ").split(" ")));
            rawList.remove("");

            this.fillTrack(song.getTracks()[i], rawList, tempo, flats, sharps, TRACK_NAMES[i], debug);
        }

        ShortMessage stop = new ShortMessage(ShortMessage.STOP);
        MidiEvent stopi = new MidiEvent(stop,song.getTracks()[1].ticks()+500);

        for (Track th : song.getTracks()) {
            th.add(stopi);
        }

        System.out.println("\t> Complete!");
    }

    private Song(Sequence wholeSequence, String name){
        song = wholeSequence;

        this.name = name;
    }

    private void fillTrack(Track out, ArrayList<String> input, String tempo, String flats, String sharps, String trackName, boolean debugMode) throws InvalidMidiDataException {
        //Set variables for beginning
        int volume = Notes.MF.getKeyNum();
        int playhead = 0;
        int timeLoc = 2000;
        int volInc = 0;
        int stopInc = 0;
        int measureCount = 1;
        int curMesLength = 0;

        System.out.println("\t> Loading "+trackName+"...");

        while (playhead < input.size()) {
            String curElem = input.get(playhead);
            char cmd = getFunction(curElem);

            //Handle Crescendos/Decrescendos
            if (playhead == stopInc){
                volInc = 0;
            }

            volume += volInc;

            switch (cmd){
                case 'G':
                    String[] pts = curElem.split(":");
                    input.set(playhead,"-");
                    playhead = input.indexOf("MARK:"+pts[1]);
                    break;

                case 'M':
                    break;

                case 'V':
                    String[] pts2 = curElem.split(":");
                    volume = Notes.match(pts2[1]).getKeyNum();
                    break;

                case 'C':
                    String[] pts3 = curElem.split(":");
                    int targetV = Notes.match(pts3[1]).getKeyNum();
                    volInc = (targetV - volume)/Integer.parseInt(pts3[2]);
                    stopInc = playhead + Integer.parseInt(pts3[2]);
                    break;

                case 'R':
                    if (debugMode) {
                        System.out.println("\t\t> Treble Cleff :: Measure #" + measureCount + " @ " + curMesLength + " beats");
                    }

                    curMesLength = 0;
                    measureCount++;
                    break;

                default:
                    SlvSound nn = SlvSound.process(curElem, flats, sharps);
                    nn.genEvents(out,timeLoc,volume);
                    timeLoc += nn.getDuration(tempo);
                    curMesLength += nn.getDuration(tempo);
            }

            playhead++;
        }
    }

    private char getFunction(String cmd){
        if(cmd.contains("GOTO")){
            return 'G';
        } else if (cmd.contains("MARK")) {
            return 'M';
        } else if (cmd.contains("VOL")) {
            return 'V';
        } else if (cmd.contains("CRESC")) {
            return 'C';
        } else if (cmd.contains("-")) {
            return 'R';
        } else {
            return 'N';
        }
    }

    /**
     *
     * @return The <code>javax.midi.Sequence</code> that this class represents
     *
     * @since 1.10.0
     */
    public Sequence getSong() {
        return song;
    }

    /**
     * Saves this song to a midi sequence file
     * @param path The path of the file to write to
     * @throws IOException If saving fails
     *
     * @since 1.10.4
     */
    public void writeToFile(File path) throws IOException {
        MidiSystem.write(song, MidiSystem.getMidiFileTypes(song)[0], path);
    }

    /**
     * Plays the song through the devices current audio output, sleeps process until finished, then stops player
     * @throws MidiUnavailableException If Midi is currently unavailable
     * @throws InvalidMidiDataException If the <code>javax.midi.Sequence</code> is corrupted
     * @throws InterruptedException If the Thread is interrupted
     *
     * @since 1.10.4
     */
    public void play() throws MidiUnavailableException, InvalidMidiDataException, InterruptedException {
        Sequencer player = MidiSystem.getSequencer();
        player.setSequence(getSong());

        player.open();
        player.start();

        Thread.sleep(song.getMicrosecondLength()/1000);

        player.stop();
    }

    /**
     * Loads a <code>Song</code> object from a MIDI file
     * @param path The file from which to load
     * @param nm The name of the Song
     * @return The <code>Song</code>
     * @throws IOException If load fails
     * @throws InvalidMidiDataException If the MIDI file is invalid
     *
     * @since 1.10.4
     */
    public static Song load(File path, String nm) throws IOException, InvalidMidiDataException {
        Sequence seq = MidiSystem.getSequence(path);

        return new Song(seq, nm);
    }

    public String getName() {
        return name;
    }
}
