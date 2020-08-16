package silverlib.music2;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Track;

public class MetaSongEvent implements SongEvent {
  private String title;

  public MetaSongEvent(String title) {
    this.title = title;
  }

  @Override
  public void addToTrack(Track track) throws InvalidMidiDataException {

  }

  @Override
  public int getDuration() {
    return 0;
  }

  public String getTitle() {
    return title;
  }
}
