package silverlib.music2;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Track;

public class RestEvent implements SongEvent {
  private int duration;

  public RestEvent(int duration) {
    this.duration = duration;
  }

  @Override
  public void addToTrack(Track track) throws InvalidMidiDataException {

  }

  @Override
  public int getDuration() {
    return duration;
  }
}
