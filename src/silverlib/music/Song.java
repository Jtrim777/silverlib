package silverlib.music;

import silverlib.geo.Point;
import silverlib.io.IO;
import silverlib.log.Log;
import silverlib.img.Img;
import silverlib.geo.Line;

import java.awt.Color;
import javax.sound.midi.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


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

    private NoteObjList[] repTracks;

    private HashMap<MIntruments, Integer> instChannelMap = new HashMap<>();
    private int maxChannel = 0;

    /**
     * Initializes a <code>Song</code> object using the string file path of the source file
     * @param filePath The string file path of the source file
     * @param debug Prints debug statements during compilation if true
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

        repTracks = new NoteObjList[rawTxtTracks.length];
        for(int i=0;i < rawTxtTracks.length; i++) {
            repTracks[i] = new NoteObjList();
        }

        //Process song information
        name = songInfo[0];
        int beatsPerBar = Integer.parseInt(songInfo[1]);
        int beatLength = Integer.parseInt(songInfo[2]);
        //int wholeNote = beatLength * beatsPerBar;
        String tempo = songInfo[3];
        String flats = songInfo[4];
        String sharps = songInfo[5];

        System.out.println("> Generating song \""+name+"\" from raw text data...");

        int wholeNote = Notes.match(tempo).getKeyNum()/beatLength * beatsPerBar;

        for (int i = 0; i < rawTxtTracks.length; i++) {
            ArrayList<String> rawList = new ArrayList<>(Arrays.asList(rawTxtTracks[i].replace("\n"," - ").split(" ")));
            rawList.remove("");

            this.fillTrack(song.getTracks()[i], i, rawList, tempo, flats, sharps, TRACK_NAMES[i], wholeNote, debug);
        }

        ShortMessage stop = new ShortMessage(ShortMessage.STOP);
        MidiEvent stopi = new MidiEvent(stop,song.getTracks()[1].ticks()+500);

        for (Track th : song.getTracks()) {
            th.add(stopi);
        }

        System.out.println("\t> Complete!");
    }

    /**
     * Initializes a <code>Song</code> object using the string file path of the source file and a specific instrument
     * @param filePath The string file path of the source file
     * @param instrument The main instrument to play the song on
     * @param debug Prints debug statements during compilation if true
     * @throws IOException If source file loading fails
     * @throws InvalidMidiDataException If <code>Sequence</code> fails to load its <code>Track</code>'s
     *
     * @since 1.10.4
     */
    public Song(String filePath, MIntruments instrument, boolean debug) throws IOException, InvalidMidiDataException {
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
        int beatLength = Integer.parseInt(songInfo[2]);

        repTracks = new NoteObjList[rawTxtTracks.length];
        for(int i=0;i < rawTxtTracks.length; i++) {
            repTracks[i] = new NoteObjList();
        }

        String tempo = songInfo[3];
        String flats = songInfo[4];
        String sharps = songInfo[5];

        int wholeNote = Notes.match(tempo).getKeyNum()/beatLength * beatsPerBar;
        System.out.println("> Whole note for tempo "+tempo+" is "+Notes.match(tempo).getKeyNum()+"ms; With a beat of 1/"+beatLength+" and "+beatsPerBar+" beats per bar, the duration of a whole note is "+wholeNote+"ms");

        System.out.println("> Generating song \""+name+"\" from raw text data...");

        for (int i = 0; i < rawTxtTracks.length; i++) {
            ShortMessage instChangeMsg = new ShortMessage();
            instChangeMsg.setMessage(ShortMessage.PROGRAM_CHANGE, 0, instrument.getId(), 0);
            song.getTracks()[i].add(new MidiEvent(instChangeMsg, 0));

            ArrayList<String> rawList = new ArrayList<>(Arrays.asList(rawTxtTracks[i].replace("\n"," - ").split(" ")));
            rawList.remove("");

            this.fillTrack(song.getTracks()[i], i, rawList, tempo, flats, sharps, TRACK_NAMES[i], wholeNote, debug);
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

    public Song(File jsonFile) throws IOException, InvalidMidiDataException {
        String fileInputTxt = IO.readFile(jsonFile.getAbsolutePath());

        int numberOfTracks = (int) fileInputTxt.chars().filter(ch -> ch == 'e').count() + 1;

        song = new Sequence(Sequence.PPQ,500,numberOfTracks);

        String[] fileInputLines = fileInputTxt.split("\n");

        name = fileInputLines[0];

        int trackI = 0;
        for (int i=1;i<fileInputLines.length;i++){
            if (fileInputLines[i].equals("%")){
                trackI ++;
                continue;
            }

            String[] pts = fileInputLines[i].split(":");

            if (pts[0].equals("ON")) {
                int noteNumber = Integer.parseInt(pts[1]);
                int volume = Integer.parseInt(pts[2]);
                int time = Integer.parseInt(pts[3]);

                ShortMessage sm = new ShortMessage(ShortMessage.NOTE_ON,0,noteNumber, volume);
                MidiEvent cEvent = new MidiEvent(sm,time);
                song.getTracks()[trackI].add(cEvent);
            } else {
                int noteNumber = Integer.parseInt(pts[1]);
                int volume = Integer.parseInt(pts[2]);
                int time = Integer.parseInt(pts[3]);

                ShortMessage sm = new ShortMessage(ShortMessage.NOTE_OFF,0,noteNumber, volume);
                MidiEvent cEvent = new MidiEvent(sm,time);
                song.getTracks()[trackI].add(cEvent);
            }
        }
    }

    private void fillTrack(Track out, int trackNum, ArrayList<String> input, String tempo, String flats, String sharps, String trackName, int wholeNote, boolean debugMode) throws InvalidMidiDataException {
        //Set variables for beginning
        int volume = Notes.MF.getKeyNum();
        int playhead = 0;
        int timeLoc = 2000;
        int volInc = 0;
        int stopInc = 0;
        int measureCount = 1;
        int curMesLength = 0;
        MIntruments instrument = MIntruments.GRAND_PIANO;
        instChannelMap.put(instrument, 0);

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

                    if (debugMode) {
                        System.out.println("\t\t> "+trackName+" :: Measure #" + measureCount + " :: Returning to Mark " + pts[1]);
                    }
                    break;

                case 'M':
                    if (debugMode) {
                        String[] pts8 = curElem.split(":");
                        System.out.println("\t\t> "+trackName+" :: Measure #" + measureCount + " :: Setting Mark " + pts8[1]);
                    }
                    break;

                case 'V':
                    String[] pts2 = curElem.split(":");
                    volume = Notes.match(pts2[1]).getKeyNum();

                    if (debugMode) {
                        System.out.println("\t\t> "+trackName+" :: Measure #" + measureCount + " :: Volume set to " + pts2[1]);
                    }
                    break;

                case 'C':
                    String[] pts3 = curElem.split(":");
                    int targetV = Notes.match(pts3[1]).getKeyNum();
                    volInc = (targetV - volume)/Integer.parseInt(pts3[2]);
                    stopInc = playhead + Integer.parseInt(pts3[2]);

                    if (debugMode) {
                        System.out.println("\t\t> "+trackName+" :: Measure #" + measureCount + " :: Beginning Crescendo to " + pts3[1]);
                    }
                    break;

                case 'R':
                    if (debugMode && curMesLength > 0) {
                        System.out.println("\t\t> "+trackName+" :: Measure #" + measureCount + " @ " + curMesLength + " beats [t="+timeLoc+"ms]");
                    }

                    if (curMesLength > 0){
                        measureCount++;
                    }

                    curMesLength = 0;

                    repTracks[trackNum].add(new NoteObj(-1,0));

                    break;

                case 'I':
                    instrument = MIntruments.valueOf(curElem.split(":")[1]);
                    if (!instChannelMap.containsKey(instrument)) {
                        maxChannel ++;
                        instChannelMap.put(instrument, maxChannel);
                        if (maxChannel > 15) {
                            throw new InvalidMidiDataException("Too many instruments were " +
                                "added; Channel "+maxChannel+" is out of range [0,16)");
                        }
                    }

                    ShortMessage instChangeMsg = new ShortMessage();
                    instChangeMsg.setMessage(ShortMessage.PROGRAM_CHANGE, instChannelMap.get(instrument),
                        instrument.getId(),
                        0);

                    out.add(new MidiEvent(instChangeMsg, timeLoc));

                    if (debugMode) {
                        System.out.println("\t\t> "+trackName+" :: Measure #" + measureCount + " :: Changing Instrument to " + instrument.name());
                    }
                    break;

                case 'T':

                    String[] comp2;
                    int occurences;

                    //Log.log(curElem);

                    if (curElem.contains(".") && !curElem.contains("*")) {
                        String s1 = curElem.replace('.', 'Y');
                        String[] comp1 = s1.split("Y");
                        comp2 = comp1[0].split("=");
                        occurences = (16 / Integer.parseInt(comp1[1])) / 2;
                    } else {
                        String s1 = curElem.replace('*', 'Y');
                        String[] comp1 = s1.split("Y");
                        comp2 = comp1[0].split("=");
                        double interim = (Double.parseDouble(comp1[1]) / (1.0/8.0));

                        //System.out.println(interim);

                        occurences = (int)interim;
                    }

                    //Log.log(occurences+"");

                    SlvSound n1 = SlvSound.process(comp2[0] + ".16", flats, sharps);
                    SlvSound n2 = SlvSound.process(comp2[1] + ".16", flats, sharps);

                    for (int i = 0; i < occurences; i++) {
                        n1.genEvents(out,trackNum,timeLoc,tempo,volume, instChannelMap.get(instrument));
                        timeLoc += n1.getDuration(tempo);
                        curMesLength += n1.getDuration(tempo);

                        n2.genEvents(out,trackNum,timeLoc,tempo,volume, instChannelMap.get(instrument));
                        timeLoc += n2.getDuration(tempo);
                        curMesLength += n2.getDuration(tempo);
                    }
                    break;
                case 'S':
                    SlvSound rest = SlvSound.process(curElem, flats, sharps);

                    timeLoc += rest.getDuration(tempo);
                    curMesLength += rest.getDuration(tempo);
                    break;
                default:
                    SlvSound nn = SlvSound.process(curElem, flats, sharps);
                    nn.genEvents(out, trackNum, timeLoc, tempo, volume, instChannelMap.get(instrument));
                    timeLoc += nn.getDuration(tempo);
                    curMesLength += nn.getDuration(tempo);
                    for (Notes n : nn.notes) {
                        repTracks[trackNum].add(new NoteObj(n.getKeyNum(),(int)(nn.duration/(1.0/32.0))));
                    }
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
        } else if (cmd.contains("INST")) {
            return 'I';
        } else if (cmd.contains("=")) {
            return 'T';
        } else if (cmd.contains("_")) {
            return 'S';
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
     * Plays the song from the specified position, sleeps process until finished, then stops player
     * @throws MidiUnavailableException If Midi is currently unavailable
     * @throws InvalidMidiDataException If the <code>javax.midi.Sequence</code> is corrupted
     * @throws InterruptedException If the Thread is interrupted
     *
     * @since 1.10.6-b002
     */
    public void playAt(int pos) throws MidiUnavailableException, InvalidMidiDataException, InterruptedException {
        Sequencer player = MidiSystem.getSequencer();
        player.setSequence(getSong());

        player.open();

        player.setMicrosecondPosition((long)pos * 1000);
        Log.print(player.toString());

        player.start();

        Thread.sleep((song.getMicrosecondLength()/1000)-pos+10);

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

    public Img getSheet(int showTracks){
        Img sheet = new Img(500,1000);

        int st = showTracks <= repTracks.length ? showTracks : repTracks.length;

        for (int tn = 0; tn < st; tn++) {
            int xPos = 10; //spacing is 3px
            int yPos = 10; //Note level is yPos + 107 - noteNumber

            for (NoteObj n : repTracks[tn]) {
                try {
                    Log.print("> " + n.mag + " @ " + n.len);
                    if (n.mag == 0) {
                        xPos += n.len + 3;
                    }
                    else if (n.mag == -1) {
                        xPos += 5;
                    }
                    else {
                        Point sp = new Point(xPos, yPos + 107 - n.mag);

                        Point ep;

                        if (xPos < 497 - n.len) {
                            ep = new Point(xPos + n.len, yPos + 107 - n.mag);
                            xPos += n.len + 3;
                        }
                        else {
                            ep = new Point(500, yPos + 107 - n.mag);
                            xPos = 500;
                        }

                        Line barLine = new Line(sp, ep);
                        barLine.setColor(Color.WHITE);
                        barLine.draw(sheet);

                    }

                    if (xPos > 480) {
                        xPos = 10;
                        yPos += 120;
                    }

                } catch (ArrayIndexOutOfBoundsException e) {
                    Log.print("!>>Ended sheet generation due to exceeded length<<!");
                    break;
                }
            }
        }

        return sheet;

    }

    private class NoteObj {
        private int mag;
        private int len;

        public NoteObj(int m, int l){
            mag = m;
            len = l;
        }

        public int getLen() {
            return len;
        }

        public int getMag() {
            return mag;
        }
    }

    private class NoteObjList extends ArrayList<NoteObj> { }
}
