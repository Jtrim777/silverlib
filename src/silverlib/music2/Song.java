package silverlib.music2;

import silverlib.io.IO;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Sequence;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Song {
  private List<List<SongEvent>> tracks;
  private String title;
  private Sequence midiData;

  public Song(String title, String filePath) throws IOException, InvalidMidiDataException {
    this.title = title;
    this.midiData = new Sequence(Sequence.PPQ,500,6);
    this.tracks = new ArrayList<>();

    //Load song data
    File srcFile = new File(filePath);
    String rawData = IO.readFile(srcFile.getAbsolutePath());

    int metaEnd = rawData.indexOf('\n');

    String metaLine = rawData.substring(0, metaEnd);
    String rawBody = rawData.substring(metaEnd + 1);

    MusicalContext initContext = generateContext(metaLine);

    rawBody = rawBody.replaceAll("\n", " ?MSR ");

    String[] rawTracks = rawBody.split("%");
    if (rawTracks.length > 6) {
      throw new MusicProcessingError("The maximum number of tracks is 6");
    }

    for (String rawTrack : rawTracks) {
      List<String> trackBits = Arrays.stream(rawTrack.split(" "))
          .filter(bit -> bit.length() > 1).collect(Collectors.toList());

      tracks.add(generateTrackEvents(trackBits, initContext.copy()));
    }

  }

  private List<SongEvent> generateTrackEvents(List<String> input, MusicalContext context) {
    List<SongEvent> output = new ArrayList<>();

    for (String rawSymbol : input) {
      output.add(SymbolParser.parseSymbol(rawSymbol, context));
    }

    return output;
  }

  private MusicalContext generateContext(String metadata) {
    if (metadata.split(" ").length != 5) {
      throw new MusicProcessingError.BadMetadataException();
    }

    int tempo, tsNum, tsDen;
    String sharps, flats;

    Scanner metaScanner = new Scanner(metadata);

    try {
      tempo = metaScanner.nextInt();
      tsNum = metaScanner.nextInt();
      tsDen = metaScanner.nextInt();
      sharps = metaScanner.next();
      flats = metaScanner.next();
    } catch (NoSuchElementException e) {
      throw new MusicProcessingError.BadMetadataException();
    }

    List<Character> sharpsO = Arrays.stream(sharps.split(":"))
        .map(letter -> {
          Character c = letter.charAt(0);
          if (!Note.NOTES.contains(c)) {
            throw new MusicProcessingError.BadMetadataException();
          }

          return c;
        }).collect(Collectors.toList());

    List<Character> flatsO = Arrays.stream(flats.split(":"))
        .map(letter -> {
          Character c = letter.charAt(0);
          if (!Note.NOTES.contains(c)) {
            throw new MusicProcessingError.BadMetadataException();
          }

          return c;
        }).collect(Collectors.toList());

    return new MusicalContext(tsNum, tsDen, tempo, sharpsO, flatsO);
  }
}
