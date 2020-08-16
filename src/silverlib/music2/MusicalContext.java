package silverlib.music2;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class MusicalContext {
  private int beatsPerBar;
  private int beatSize;
  private int tempo;
  private int wholeNoteDur;
  private int measureDur;

  private List<Character> sharps;
  private List<Character> flats;

  private int volume;
  private int channel;
  private int time;

  private Map<MInstruments, Integer> instMap;
  private int maxChannel = 0;

  private ChangeOverBeats accelerando;
  private ChangeOverBeats crescendo;

  public MusicalContext(int beatsPerBar, int beatSize, int tempo, List<Character> sharps,
                        List<Character> flats) {
    this.beatsPerBar = beatsPerBar;
    this.beatSize = beatSize;
    this.tempo = tempo;
    this.sharps = sharps;
    this.flats = flats;

    this.time = 0;
    this.volume = Dynamics.MF.value;
    this.channel = 0;
    this.instMap = new HashMap<>();
    instMap.put(MInstruments.GRAND_PIANO, 0);
    updateTimes();

    this.accelerando = null;
    this.crescendo = null;
  }

  void updateTimes() {
    this.wholeNoteDur = (int)((1d / (double)tempo) * 60000d * (double)beatSize);
    this.measureDur = wholeNoteDur / beatSize * beatsPerBar;
  }

  int getTimeForBeats(int beats) {
    return (wholeNoteDur / beatSize) * beats;
  }

  void setTimeSignature(int num, int den) {
    this.beatsPerBar = num;
    this.beatSize = den;
    updateTimes();
  }

  void setTempo(int t) {
    this.tempo = t;
    updateTimes();
  }

  void setKeySignature(List<Character> sharps, List<Character> flats) {
    this.sharps = sharps;
    this.flats = flats;
  }

  void setInstrument(MInstruments inst) {
    if (instMap.containsKey(inst)) {
      this.channel = instMap.get(inst);
    } else {
      this.channel = maxChannel + 1;
      maxChannel++;
      this.instMap.put(inst, this.channel);
    }
  }

  public int getTempo() {
    if (accelerando != null) {
      setTempo(accelerando.getCurrentValue(time));

      if (accelerando.isDone(time)) {
        this.accelerando = null;
      }
    }

    return tempo;
  }

  public int getWholeNoteDur() {
    return wholeNoteDur;
  }

  public int getMeasureDur() {
    return measureDur;
  }

  public int getVolume() {
    if (crescendo != null) {
      this.volume = crescendo.getCurrentValue(time);

      if (crescendo.isDone(time)) {
        this.crescendo = null;
      }
    }

    return volume;
  }

  public void setVolume(int volume) {
    this.volume = volume;
  }

  public int getChannel() {
    return channel;
  }

  public int getTime() {
    return time;
  }

  public void startCrescendo(int target, int beats) {
    this.crescendo = new ChangeOverBeats(this.volume, target, time, getTimeForBeats(beats));
  }

  public void startAccelerando(int target, int beats) {
    this.accelerando = new ChangeOverBeats(this.tempo, target, time, getTimeForBeats(beats));
  }

  public MusicalContext copy() {
    List<Character> nFlats = new ArrayList<>(flats);
    List<Character> nSharps = new ArrayList<>(sharps);

    return new MusicalContext(beatsPerBar, beatSize, tempo, nSharps, nFlats);
  }

  public SoundEvent generateBaseNote() {
    return new SoundEvent(time, channel, volume);
  }

  public void modify(Note inp) {
    if (inp.mod == Note.NoteModifier.NATURAL) {
      return;
    }

    if (sharps.contains(inp.base)) {
      inp.mod = Note.NoteModifier.SHARP;
    } else if (flats.contains(inp.base)) {
      inp.mod = Note.NoteModifier.FLAT;
    }
  }

  private static class ChangeOverBeats {
    int startValue;
    int endValue;
    int startTime;
    int length;

    ChangeOverBeats(int startValue, int endValue, int startTime, int length) {
      this.startValue = startValue;
      this.endValue = endValue;
      this.startTime = startTime;
      this.length = length;
    }

    int getCurrentValue(int ctime) {
      int step = (startValue - endValue) / length;
      return startValue + ((ctime - startTime) * step);
    }

    boolean isDone(int ctime) {
      return ctime >= (startTime + length);
    }
  }
}
