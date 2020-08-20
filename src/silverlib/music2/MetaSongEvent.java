package silverlib.music2;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Track;

public class MetaSongEvent implements SongEvent {
  private String title;
  private String type;

  public MetaSongEvent(String title) {
    this.title = title;
  }

  public MetaSongEvent(String title, String type) {
    this.title = title;
    this.type = type;
  }

  @Override
  public void addToTrack(Track track, int bt) throws InvalidMidiDataException {

  }

  @Override
  public int getDuration() {
    return 0;
  }

  public String getTitle() {
    return title;
  }

  public String getType() {
    return type;
  }
}
