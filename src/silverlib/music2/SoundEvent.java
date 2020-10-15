package silverlib.music2;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class SoundEvent implements SongEvent {
  private List<SubEvent> noteOns;
  private List<SubEvent> noteOffs;
  private int duration;
  private int channel;
  private int volume;
  private int startTime;

  public SoundEvent(int startTime, int channel, int vol) {
    noteOns = new ArrayList<>();
    noteOffs = new ArrayList<>();
    duration = 0;
    this.channel = channel;
    this.volume = vol;
    this.startTime = startTime;
  }

  void addOnEvent(int relTime, int noteVal) {
    this.noteOns.add(new SubEvent(relTime, noteVal, 1));
  }

  void addOffEvent(int relTime, int noteVal) {
    this.noteOffs.add(new SubEvent(relTime, noteVal, 0));

    if (relTime > duration) {
      duration = relTime;
    }
  }

  public int getDuration() {
    return duration;
  }

  public int getTrueDuration() {
    int maxEnd = 0;
    int minStart = Integer.MAX_VALUE;

    for (SubEvent se : noteOns) {
      if (se.relTime < minStart) {
        minStart = se.relTime;
      }
    }

    for (SubEvent se : noteOffs) {
      if (se.relTime > maxEnd) {
        maxEnd = se.relTime;
      }
    }

    return maxEnd - minStart;
  }

  public void addToTrack(Track track, int baseTime) throws InvalidMidiDataException {
    for (SubEvent noe : noteOns) {
      ShortMessage sm = new ShortMessage(ShortMessage.NOTE_ON, channel, noe.noteValue, volume);
      MidiEvent me = new MidiEvent(sm, baseTime + noe.relTime);

      track.add(me);
    }

    for (SubEvent noe : noteOffs) {
      ShortMessage sm = new ShortMessage(ShortMessage.NOTE_OFF, channel, noe.noteValue, volume);
      MidiEvent me = new MidiEvent(sm, baseTime + noe.relTime);

      track.add(me);
    }
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public List<SubEvent> getNoteOns() {
    return noteOns;
  }

  public List<SubEvent> getNoteOffs() {
    return noteOffs;
  }

  public SoundEvent copyCore() {
    return new SoundEvent(startTime, channel, volume);
  }

  public int getVolume() {
    return volume;
  }

  public void setVolume(int volume) {
    this.volume = volume;
  }

  @Override
  public String toString() {
    String out = "SoundEvent: {\n";

    List<SubEvent> all = new ArrayList<>(noteOns);
    all.addAll(noteOffs);

    all.sort(Comparator.comparingInt(se -> se.relTime));

    for (SubEvent event : all) {
      out += event.toString() + "\n";
    }

    out += "}";
    return out;
  }

  static class SubEvent {
    int type;
    int relTime;
    int noteValue;

    SubEvent(int relTime, int noteValue, int type) {
      this.relTime = relTime;
      this.noteValue = noteValue;
      this.type = type;
    }

    @Override
    public String toString() {
      return String.format("[%s %d @ %d]", type == 0 ? "OFF" : "ON", noteValue, relTime);
    }
  }
}
