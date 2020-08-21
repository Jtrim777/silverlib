package silverlib.music2;

import silverlib.io.IO;
import silverlib.log.Log;

import javax.sound.midi.*;
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
          .filter(bit -> !bit.isEmpty()).collect(Collectors.toList());

      tracks.add(generateTrackEvents(trackBits, initContext.copy()));
    }

    this.midiData = new Sequence(Sequence.PPQ,500,tracks.size());

    Track[] midiTracks = midiData.getTracks();

    for (int i = 0; i < tracks.size(); i++) {
      Track thisTrack = midiTracks[i];

      populateMidiTrack(i, thisTrack, tracks.get(i));
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

    int tsNum, tsDen;
    String sharps, flats, tempo;

    Scanner metaScanner = new Scanner(metadata);

    try {
      tempo = metaScanner.next();
      tsNum = metaScanner.nextInt();
      tsDen = metaScanner.nextInt();
      sharps = metaScanner.next();
      flats = metaScanner.next();
    } catch (NoSuchElementException e) {
      throw new MusicProcessingError.BadMetadataException();
    }

    int itempo;
    try {
      itempo = Integer.parseInt(tempo);
    } catch (NumberFormatException e) {
      try {
        itempo = Tempos.valueOf(tempo).bpm;
      } catch (IllegalArgumentException e2) {
        throw new MusicProcessingError.BadMetadataException("Could not identify tempo");
      }
    }

    List<Character> sharpsO = extractCharacters(sharps);

    List<Character> flatsO = extractCharacters(flats);


    return new MusicalContext(tsNum, tsDen, itempo, sharpsO, flatsO);
  }

  private static List<Character> extractCharacters(String raw) {
    return Arrays.stream(raw.split(":"))
        .map(letter -> {
          char c = letter.charAt(0);
          if (!Note.NOTES.contains(c) && c != 'X') {
            throw new MusicProcessingError.BadMetadataException("Unknown symbol for key " +
                "signature: "+c);
          }

          return c;
        }).filter(c -> c != 'X').collect(Collectors.toList());
  }

  private void populateMidiTrack(int number, Track track, List<SongEvent> source)
      throws InvalidMidiDataException {
    int head = 0;
    int time = 0;

    int voltaIndex = 1;
    int voltaHead = -1;

    int measureCount = 1;
    int measureLength = 0;

    while (head < source.size()) {
      SongEvent event = source.get(head);

      event.addToTrack(track, time);
      head++;
      time += event.getDuration();
      measureLength += event.getDuration();

      if (event instanceof MetaSongEvent) {
        MetaSongEvent metaEvent = (MetaSongEvent)event;

        if (metaEvent.getType().isEmpty()) {
          head++;
        }
        else if (metaEvent.getType().equals("GOTO")) {
          source.remove(head);

          head = findMarkIndex(metaEvent.getTitle(), source);
        }
        else if (metaEvent.getType().equals("VOLTA")) {
          String title = metaEvent.getTitle();
          String key = title.substring(0, title.length()-1);
          String idx = title.substring(title.length()-1);

          if (idx.equals("!")) {
            voltaHead = head;
            voltaIndex = 1;
          } else {
            int tidx = Integer.parseInt(idx);

            if (voltaIndex > tidx) {
              // Skip to next
              head = findMeta(key + (tidx + 1), "VOLTA", source, head);
            } else if (voltaIndex < tidx) {
              // Return to first
              head = voltaHead + 1;
              voltaIndex += 1;
            } else {
              head ++;
            }
          }
        }
        else if (metaEvent.getType().equals("MSR")) {
          onEndMeasure(measureCount, measureLength, number);
          measureCount += 1;
          measureLength = 0;
        }
      }
    }
  }

  private void onEndMeasure(int measure, int length, int trackIndex) {

  }

  private static int findMarkIndex(String mark, List<SongEvent> source) {
    for (int i = 0; i < source.size(); i++) {
      if (source.get(i) instanceof MetaSongEvent) {
        if (((MetaSongEvent)source.get(i)).getTitle().equals(mark)) {
          return i;
        }
      }
    }

    throw new MusicProcessingError.SongGenerationError("Could not find MARK with key "
        + mark + " in this track");
  }

  private static int findMeta(String name, String type, List<SongEvent> source, int start) {
    for (int i=start; i<source.size(); i++) {
      SongEvent event = source.get(i);

      if (event instanceof MetaSongEvent) {
        MetaSongEvent mevent = (MetaSongEvent)event;
        if (mevent.getType().equals(type) && mevent.getTitle().equals(name)) {
          return i;
        }
      }
    }

    throw new MusicProcessingError.SongGenerationError("Could not find VOLTA with key "
        + name + " in this track");
  }

  /**
   *
   * @return The <code>javax.midi.Sequence</code> that this class represents
   *
   * @since 1.12.9
   */
  public Sequence getSequence() {
    return midiData;
  }

  /**
   * Saves this song to a midi sequence file
   * @param path The path of the file to write to
   * @throws IOException If saving fails
   *
   * @since 1.12.9
   */
  public void writeToFile(File path) throws IOException {
    MidiSystem.write(midiData, MidiSystem.getMidiFileTypes(midiData)[0], path);
  }

  /**
   * Plays the song through the devices current audio output, sleeps process until finished, then stops player
   * @throws MidiUnavailableException If Midi is currently unavailable
   * @throws InvalidMidiDataException If the <code>javax.midi.Sequence</code> is corrupted
   * @throws InterruptedException If the Thread is interrupted
   *
   * @since 1.12.9
   */
  public void play() throws MidiUnavailableException, InvalidMidiDataException, InterruptedException {
    Sequencer player = MidiSystem.getSequencer();
    player.setSequence(midiData);

    player.open();
    player.start();

    Thread.sleep(midiData.getMicrosecondLength()/1000);

    player.stop();
  }

  /**
   * Plays the song from the specified position, sleeps process until finished, then stops player
   * @throws MidiUnavailableException If Midi is currently unavailable
   * @throws InvalidMidiDataException If the <code>javax.midi.Sequence</code> is corrupted
   * @throws InterruptedException If the Thread is interrupted
   *
   * @since 1.12.9
   */
  public void playAt(int pos) throws MidiUnavailableException, InvalidMidiDataException, InterruptedException {
    Sequencer player = MidiSystem.getSequencer();
    player.setSequence(midiData);

    player.open();

    player.setMicrosecondPosition((long)pos * 1000);
    Log.print(player.toString());

    player.start();

    Thread.sleep((midiData.getMicrosecondLength()/1000)-pos+10);

    player.stop();
  }
}
