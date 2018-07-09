package silverlib.music;

import silverlib.io.IO;

import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Represents a musical piece on the piano, with treble and bass tracks, loaded from a text file formatted according to
 * https://silordoflight.github.io/silverlib/javaDoc/SongFormat.html
 *
 * @since 1.10
 * @author Jake Trimble
 */
public class Song {
    private Sequence song;

    private int totalLength;

    /**
     * Initializes a <code>Song</code> object using the string file path of the source file
     * @param filePath The string file path of the source file
     * @throws IOException If source file loading fails
     * @throws InvalidMidiDataException If <code>Sequence</code> fails to load its <code>Track</code>'s
     *
     * @since 1.10.0
     */
    public Song(String filePath) throws IOException, InvalidMidiDataException {
        song = new Sequence(Sequence.PPQ,500,3);

        Track trebleTrack = song.getTracks()[1];
        Track bassTrack = song.getTracks()[2];

        //Load song data
        File srcFile = new File(filePath);
        String rawData = IO.readFile(srcFile.getAbsolutePath());

        //Process raw string
        String[] songInfo = rawData.split("\n")[0].split(",");
        String modTxt = rawData.substring(rawData.indexOf('\n')+1);
        String[] rawTxtTracks = modTxt.split("\n%\n");

        //Process song information
        String name = songInfo[0];
        int beatsPerBar = Integer.parseInt(songInfo[1]);
        int beatLength = Note.WHOLE / Integer.parseInt(songInfo[2]);
        String tempo = songInfo[3];
        String flats = songInfo[4];
        String sharps = songInfo[5];

        ArrayList<String> rawTreble = new ArrayList<>(Arrays.asList(rawTxtTracks[0].replace("\n"," - ").split(" ")));
        rawTreble.remove("");

        ArrayList<String> rawBase = new ArrayList<>(Arrays.asList(rawTxtTracks[1].replace("\n"," - ").split(" ")));
        rawBase.remove("");

        //Set variables for beginning
        int volume = Notes.MF.getKeyNum();
        int playhead = 0;
        int timeLoc = 2000;
        int volInc = 0;
        int stopInc = 0;
        int measureCount = 1;
        int curMesLength = 0;

        while (playhead < rawTreble.size()) {
            String curElem = rawTreble.get(playhead);

            //Handle Crescendos/Decrescendos
            if (playhead == stopInc){
                volInc = 0;
                //System.out.println(volume);
            }

            volume += volInc;

            //If element is GOTO:#, go to MARK:#
            if (curElem.contains("GOTO")){
                String[] pts = curElem.split(":");

                rawTreble.set(playhead,"_*0");

                playhead = rawTreble.indexOf("MARK:"+pts[1]);


            }

            //If element is MARK:#, do nothing
//            else if (curElem.contains("MARK")) {
//
//            }

            //If measure break, print statement
            else if (curElem.contains("-")){
                if(curMesLength != Note.WHOLE/ beatLength * beatsPerBar){
                    System.out.println("Measure "+measureCount+" is not full/overfull ("+curMesLength+")");
                }

                System.out.println();

                curMesLength = 0;
                measureCount++;
            }

            //If element is VOL:X, set volume to X
            else if (curElem.contains("VOL")){
                String[] pts = curElem.split(":");

                volume = Notes.match(pts[1]).getKeyNum();

                //System.out.println("Volume set to "+pts[0]+" ("+ volume +")");
            }

            //If element is CRESC:X:#, take # notes to change to volume X
            else if (curElem.contains("CRESC")){
                String[] pts = curElem.split(":");

                int targetV = Notes.match(pts[1]).getKeyNum();

                volInc = (targetV- volume)/Integer.parseInt(pts[2]);
                stopInc = playhead + Integer.parseInt(pts[2]);
            }

            //Otherwise, element is a note, so process it
            else{
                SlvSound nn = SlvSound.process(curElem, flats, sharps);

                //System.out.println(nn.toString()+" @ "+timeLoc+"ms ("+playhead+")");

                nn.genEvents(trebleTrack,timeLoc,volume);
                timeLoc += nn.getDuration(tempo);
                curMesLength += nn.getDuration(tempo);

                //System.out.println(nn.toString()+" @ "+timeLoc+"ms ("+playhead+")");
            }

            playhead++;
        }

        //System.out.println(name +"'s treble track is "+timeLoc+" ticks long."+"\n\n");

        //Reset variables for bass track
         volume = Notes.MF.getKeyNum();
         playhead = 0;
         timeLoc = 2000;
         volInc = 0;
         stopInc = 0;
         measureCount = 1;
         curMesLength = 0;

        while (playhead < rawBase.size()) {
            String curElem = rawBase.get(playhead);

            //Handle Crescendos/Decrescendos
            if (playhead == stopInc){
                volInc = 0;
                //System.out.println(volume);
            }

            volume += volInc;

            //If element is GOTO:#, go to MARK:#
            if (curElem.contains("GOTO")){
                String[] pts = curElem.split(":");

                rawBase.set(playhead,"_*0");

                playhead = rawBase.indexOf("MARK:"+pts[1]);
            }

            //If element is MARK:#, do nothing
//            else if (curElem.contains("MARK")) {
//
//            }

            //If measure break, print statement
            else if (curElem.contains("-")){
                if(curMesLength != 2000){
                    //System.out.println("Measure "+measureCount+" is not full/overfull ("+curMesLength+")");
                }

                System.out.println();

                curMesLength = 0;
                measureCount++;
            }

            //If element is VOL:X, set volume to X
            else if (curElem.contains("VOL")){
                String[] pts = curElem.split(":");

                volume = Notes.match(pts[1]).getKeyNum();

                //System.out.println("Volume set to "+pts[0]+" ("+ volume +")");
            }

            //If element is CRESC:X:#, take # notes to change to volume X
            else if (curElem.contains("CRESC")){
                String[] pts = curElem.split(":");

                int targetV = Notes.match(pts[1]).getKeyNum();

                volInc = (targetV- volume)/Integer.parseInt(pts[2]);
                stopInc = playhead + Integer.parseInt(pts[2]);
            }

            //Otherwise, element is a note, so process it
            else {
                SlvSound nn = SlvSound.process(curElem, flats, sharps);

                //System.out.println(nn.toString()+" @ "+timeLoc+"ms ("+playhead+")");

                nn.genEvents(bassTrack,timeLoc,volume);
                timeLoc += nn.getDuration(tempo);
                curMesLength += nn.getDuration(tempo);
            }

            playhead++;
        }

        //System.out.println(name +"'s bass track is "+timeLoc+" ticks long.");

        ShortMessage stop = new ShortMessage(ShortMessage.STOP);
        MidiEvent stopi = new MidiEvent(stop,timeLoc+500);

        trebleTrack.add(stopi);
        bassTrack.add(stopi);
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
     * Saves this song to a midi audio file
     * @param path The path of the file to write to
     * @throws IOException If saving fails
     *
     * @since 1.10.0
     */
    public void writeToFile(File path) throws IOException {
        MidiSystem.write(song,1,path);
    }

    /**
     * Plays the song through the devices current audio output
     * @throws MidiUnavailableException If Midi is currently unavailable
     * @throws InvalidMidiDataException If the <code>javax.midi.Sequence</code> is corrupted
     *
     * @since 1.10.1
     */
    public void play() throws MidiUnavailableException, InvalidMidiDataException {
        Sequencer player = MidiSystem.getSequencer();
        player.setSequence(getSong());

        player.open();
        player.start();
    }
}
