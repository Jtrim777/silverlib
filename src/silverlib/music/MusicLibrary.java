package silverlib.music;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class MusicLibrary implements Serializable{
    private ArrayList<Song> songs;
    private String libraryName;
    private String[] songNames;

    /**
     * Initializes a <code>MusicLibrary</code> from a list of file paths for .sng files that conform to the format
     * described at https://silordoflight.github.io/silverlib/javaDoc/SongFormat.html
     * @param paths A list of absolute file paths for the song sources
     * @param name The name of this library
     * @throws IOException If loading fails
     * @throws InvalidMidiDataException If sequencing fails
     *
     * @since 1.10.4
     */
    public MusicLibrary(String[] paths, String name) throws IOException, InvalidMidiDataException {
        songs = new ArrayList<>();
        libraryName = name;

        for (String path : paths){
            Song thisSong = new Song(path, false);
            songs.add(thisSong);
        }

        songNames = new String[songs.size()];

        for (int i = 0; i < songs.size(); i++) {
            songNames[i] = songs.get(i).getName();
        }
    }

    /**
     * Initializes a <code>MusicLibrary</code> from a list of MIDI files
     * @param midiFiles A list of absolute file paths for the song sources
     * @param songNames A list of song names
     * @param name The name of this library
     * @throws IOException If loading fails
     * @throws InvalidMidiDataException If sequencing fails
     *
     * @since 1.10.4
     */
    public MusicLibrary(File[] midiFiles, String[] songNames, String name) throws IOException, InvalidMidiDataException {
        songs = new ArrayList<>();
        libraryName = name;

        for (int i=0; i<midiFiles.length; i++){
            Song thisSong = Song.load(midiFiles[i], songNames[i]);
            songs.add(thisSong);
        }

        this.songNames = songNames;
    }

    /**
     * @return The library's name
     *
     * @since 1.10.4
     */
    public String getName(){
        return libraryName;
    }

    /**
     * @return The number of songs in the library
     *
     * @since 1.10.4
     */
    public int getSize(){
        return songs.size();
    }

    /**
     * @return The names of the songs in the library
     *
     * @since 1.10.4
     */
    public String[] getSongNames() {
        return songNames;
    }

    /**
     * @return The total amount of time of all the songs in the library, in seconds
     *
     * @since 1.10.4
     */
    public int getTotalTime(){
        long total = 0;

        for(Song a : songs){
            total += a.getSong().getMicrosecondLength();
        }

        return (int)(total/1000000);
    }


    /**
     * Plays a <code>Song</code> given its name
     * @param songName The name of the song, exactly how it was give on initialization
     * @throws MidiUnavailableException
     * @throws InvalidMidiDataException
     * @throws InterruptedException
     *
     * @since 1.10.4
     */
    public void play(String songName) throws MidiUnavailableException, InvalidMidiDataException, InterruptedException {
        songs.get(Arrays.asList(songNames).indexOf(songName)).play();
    }

    /**
     * Plays a <code>Song</code> given its position inn the playlist
     * @param index The index of the song in this playlist
     * @throws MidiUnavailableException
     * @throws InvalidMidiDataException
     * @throws InterruptedException
     *
     * @since 1.10.4
     */
    public void play(int index) throws MidiUnavailableException, InvalidMidiDataException, InterruptedException {
        songs.get(index).play();
    }

    /**
     * Plays a random <code>Song</code> from the playlist
     * @throws MidiUnavailableException
     * @throws InvalidMidiDataException
     * @throws InterruptedException
     *
     * @since 1.10.4
     */
    public void playRandom() throws MidiUnavailableException, InvalidMidiDataException, InterruptedException {
        int index = (int)(Math.random() * getSize());

        songs.get(index).play();
    }

    /**
     * Plays all the <code>Song</code>s in the playlist
     * @throws MidiUnavailableException
     * @throws InvalidMidiDataException
     * @throws InterruptedException
     *
     * @since 1.10.4
     */
    public void playAll() throws MidiUnavailableException, InvalidMidiDataException, InterruptedException {
        for (Song a : songs){
            a.play();
        }
    }

    /**
     * @return A <code>String</code> representation of this playlist
     */
    @Override
    public String toString() {
        String out = "================\n"+libraryName+"\n\n";

        for (String a : songNames){
            out += "- " + a + "\n";
        }

        out += "\n================";

        return out;
    }
}
