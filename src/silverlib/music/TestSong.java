package silverlib.music;

import silverlib.music.MIntruments;
import silverlib.music.Song;

import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;

public class TestSong {
    public static void main(String[] args) throws IOException, InvalidMidiDataException, InterruptedException, MidiUnavailableException {
        Song testSong = new Song(new File("src/silverlib/music/TremoloTest.txt").getAbsolutePath(), MIntruments.GRAND_PIANO, true);

//        Synthesizer synth = MidiSystem.getSynthesizer();
//        Instrument[] instruments = synth.getAvailableInstruments();
//        for (Instrument ins : instruments){
//            System.out.println(ins.toString());
//        }

        testSong.play();

        System.out.println("Done");
        System.exit(0);
    }
}