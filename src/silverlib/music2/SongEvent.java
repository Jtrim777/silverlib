package silverlib.music2;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Track;

interface SongEvent {
  void addToTrack(Track track, int baseTime) throws InvalidMidiDataException;

  int getDuration();
}
