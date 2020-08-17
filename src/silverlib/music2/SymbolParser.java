package silverlib.music2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static silverlib.music2.MusicProcessingError.*;

public class SymbolParser {
// ([=~!]*)([ABCDEFG]\d(?:\|[ABCDEFG]\d)*)((?:[$\#b])*)([.*])((?:\d|\.|\/)*)((?:[+&>^])*)

  public static SongEvent parseSymbol(String raw, MusicalContext context) {
    if (raw.contains("?")) {
      return parseMeta(raw, context);
    } else if (raw.contains("_")) {
      return parseRest(raw, context);
    } else {
      return parseNote(raw, context);
    }
  }

  private static MetaSongEvent parseMeta(String raw, MusicalContext context) {
    if (!raw.startsWith("?")) {
      throw new SymbolParsingError("Meta sequences must start with a '?'", raw);
    }

    String body = raw.substring(1);


    return null;
  }

  private static SoundEvent parseNote(String raw, MusicalContext context) {
    Pattern basePattern = Pattern.compile("([=~^]*)([ABCDEFG]\\d[$#b]?(?:\\|[ABCDEFG]\\d[$#b]?)*)" +
        "([.*])((?:\\d|\\.|/)*)((?:[+&>!])*)");

    Matcher match = basePattern.matcher(raw);

    if (!match.matches() || !match.group().equals(raw)) {
      throw new NoteFormatException(raw);
    }

    String prefixes = match.group(1);
    String body = match.group(2);
    String delimiter = match.group(3);
    String duration = match.group(4);
    String suffixes = match.group(5);

    List<Note> baseNotes = Arrays.stream(body.split("\\|"))
        .map(Note::new).collect(Collectors.toList());

    double relDur = parseDuration(delimiter, duration, raw);
    int dur = (int) (relDur * context.getWholeNoteDur());

    SoundEvent rawEvent = new SoundEvent(context.getTime(), context.getChannel(),
        context.getVolume());

    for (Note note : baseNotes) {
      rawEvent.addOnEvent(0, note.getValue(context));
      rawEvent.addOffEvent(dur, note.getValue(context));
    }

    List<Modifier> mods = new ArrayList<>();
    prefixes.chars().forEach(c -> mods.add(Modifier.matchSymbol((char) c)));
    suffixes.chars().forEach(c -> mods.add(Modifier.matchSymbol((char) c)));

    mods.sort((m1, m2) -> m2.precedence - m1.precedence);

    for (Modifier m1 : mods) {
      for (Modifier m2 : mods) {
        if (m1 != m2 && (m1.mask & m2.mask) != 0) {
          throw new NoteModificationError(m1, m2, raw);
        }
      }
    }

    SoundEvent result = rawEvent;
    for (Modifier mod : mods) {
      result = mod.operation.apply(result, context);
    }

    return result;
  }

  private static RestEvent parseRest(String raw, MusicalContext context) {
    return null;
  }

  private static double parseDuration(String delimiter, String duration, String raw) {
    if (delimiter.equals(".")) {
      try {
        return 1d / (double) Integer.parseInt(duration);
      } catch (NumberFormatException e) {
        throw new NoteParsingError("Divsive durations must be inetegrs", raw);
      }
    } else {
      if (duration.contains("/")) {
        String[] fracParts = duration.split("/");
        if (fracParts.length != 2) {
          throw new NoteParsingError("Fractional durations must be proper fractions", raw);
        }

        try {
          return (double) Integer.parseInt(fracParts[0]) / (double) Integer.parseInt(fracParts[1]);
        } catch (NumberFormatException e) {
          throw new NoteParsingError("Fractional durations must be proper fractions", raw);
        }
      } else {
        try {
          return Double.parseDouble(duration);
        } catch (NumberFormatException e) {
          throw new NoteParsingError("Multiplicative durations must be doubles", raw);
        }
      }
    }
  }

//  public static void main(String[] args) {
//    String testValue = "~=C4.4";
//    MusicalContext ctxt = new MusicalContext(4, 4, 120, List.of(), List.of());
//
//    SoundEvent out = (SoundEvent) SymbolParser.parseSymbol(testValue, ctxt);
//
//    System.out.println(out.toString());
//  }

  enum Modifier {
    TREMOLO('=', 5, 0b00001001, (base, context) -> {
      if (base.getNoteOns().size() > 1) {
        throw new NoteParsingError("Tremolo cannot be applied to chords", "[]");
      }

      int nVal = base.getNoteOns().get(0).noteValue;

      int tTime = base.getDuration();
      int stepTime = (int) ((double) context.getWholeNoteDur() / 32d);

      int steps = tTime / stepTime;
      int endExtra = tTime - (steps * stepTime);

      SoundEvent newEvent = base.copyCore();

      int t = 0;
      for (int i = 0; i < steps; i++) {
        newEvent.addOnEvent(t, i % 2 == 0 ? nVal : nVal - 1);
        t += stepTime;
        if (i == steps - 1) {
          t += endExtra;
        }
        newEvent.addOffEvent(t, i % 2 == 0 ? nVal : nVal - 1);
      }

      return newEvent;
    }),
    ARPEGGIO('~', 5, 0b00000001, (base, context) -> {
      if (base.getNoteOns().size() < 2) {
        throw new NoteParsingError("Arpeggio cannot be applied to non-chords", "[]");
      }

      int tTime = base.getDuration();
      int sDiff = (tTime / 2) / base.getNoteOns().size();

      SoundEvent newEvent = base.copyCore();
      for (SoundEvent.SubEvent se : base.getNoteOffs()) {
        newEvent.addOffEvent(se.relTime, se.noteValue);
      }

      int rt = 0;
      for (SoundEvent.SubEvent note : base.getNoteOns()) {
        newEvent.addOnEvent(rt, note.noteValue);

        rt += sDiff;
      }

      newEvent.setDuration(tTime);

      return newEvent;
    }),
    SFORZA('^', 3, 0, (base, context) -> {
      int nv = base.getVolume() < 107 ? base.getVolume() + 20 : 127;

      base.setVolume(nv);

      return base;
    }),
    DOT('+', -1, 0, (base, context) -> {
      int od = base.getDuration();
      int nd = (int) ((double) od * 1.5);

      SoundEvent ne = base.copyCore();
      for (SoundEvent.SubEvent se : base.getNoteOns()) {
        ne.addOnEvent(se.relTime, se.noteValue);
      }

      for (SoundEvent.SubEvent off : base.getNoteOffs()) {
        int nt = (off.relTime - od) + nd;
        ne.addOffEvent(nt, off.noteValue);
      }

      return ne;
    }),
    FERMATA('&', 0, 0b00000010, (base, context) -> {
      int od = base.getDuration();
      int nd = (int) ((double) od * 2);

      SoundEvent ne = base.copyCore();
      for (SoundEvent.SubEvent se : base.getNoteOns()) {
        ne.addOnEvent(se.relTime, se.noteValue);
      }

      for (SoundEvent.SubEvent off : base.getNoteOffs()) {
        int nt = (off.relTime - od) + nd;
        ne.addOffEvent(nt, off.noteValue);
      }

      return ne;
    }),
    STACATTO('!', 0, 0b00000011, (base, context) -> {
      int od = base.getDuration();
      int nd = (int) ((double) od / 2);

      SoundEvent ne = base.copyCore();
      for (SoundEvent.SubEvent se : base.getNoteOns()) {
        ne.addOnEvent(se.relTime, se.noteValue);
      }

      for (SoundEvent.SubEvent off : base.getNoteOffs()) {
        int nt = (off.relTime - od) + nd;
        ne.addOffEvent(nt, off.noteValue);
      }

      ne.setDuration(od);

      return ne;
    }),
    LEGATO('>', 0, 0b00001010, (base, context) -> {
      base.setDuration(base.getDuration() - (int) ((double) context.getWholeNoteDur() / 64d));

      return base;
    });

    char symbol;
    int precedence;
    int mask;
    BiFunction<SoundEvent, MusicalContext, SoundEvent> operation;

    Modifier(char symbol, int precedence, int mask,
             BiFunction<SoundEvent, MusicalContext, SoundEvent> operation) {
      this.symbol = symbol;
      this.precedence = precedence;
      this.mask = mask;
      this.operation = operation;
    }

    static Modifier matchSymbol(char sym) {
      for (Modifier m : values()) {
        if (m.symbol == sym) {
          return m;
        }
      }

      throw new NoteParsingError("Unknown modifier symbol: " + sym, "[]");
    }
  }

  enum MetaSequence {
    VOL("vol - The dynamic to switch to or the integer value", (context, args) -> {
      if (args.length != 2) {
        throw new MetaParsingError(null, "");
      }

      int target;
      try {
        target = Dynamics.valueOf(args[1]).value;
      } catch (IllegalArgumentException c) {
        try {
          target = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
          throw new MetaParsingError(null, "");
        }
      }

      context.setVolume(target);

      return null;
    }),
    CRESC("vol - The dynamic/integer volume to change to; " +
        "beats - The length of the change in beats", (context, args) -> {

      if (args.length != 3) {
        throw new MetaParsingError(null, "");
      }

      int target;
      try {
        target = Dynamics.valueOf(args[1]).value;
      } catch (IllegalArgumentException c) {
        try {
          target = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
          throw new MetaParsingError(null, "");
        }
      }

      int beats;
      try {
        beats = Integer.parseInt(args[2]);
      } catch (NumberFormatException e) {
        throw new MetaParsingError(null, "");
      }

      context.startCrescendo(target, beats);

      return null;
    }),
    TEMPO("tempo - The tempo name or value (in bpm) to switch to", (context, args) -> {
      if (args.length != 2) {
        throw new MetaParsingError(null, "");
      }

      int target;
      try {
        target = Tempos.valueOf(args[1]).bpm;
      } catch (IllegalArgumentException c) {
        try {
          target = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
          throw new MetaParsingError(null, "");
        }
      }

      context.setTempo(target);

      return null;
    }),
    ACCEL("tempo - The tempo name or value (in bpm) to switch to; " +
        "beats - The length of the change in beats", (context, args) -> {
      if (args.length != 3) {
        throw new MetaParsingError(null, "");
      }

      int target;
      try {
        target = Tempos.valueOf(args[1]).bpm;
      } catch (IllegalArgumentException c) {
        try {
          target = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
          throw new MetaParsingError(null, "");
        }
      }

      int beats;
      try {
        beats = Integer.parseInt(args[2]);
      } catch (NumberFormatException e) {
        throw new MetaParsingError(null, "");
      }

      context.startAccelerando(target, beats);

      return null;
    }),
    TIME("num - Beats per measure sig; den - Beat type", (context, args) -> {
      if (args.length != 3) {
        throw new MetaParsingError(null, "");
      }

      int num;
      try {
        num = Integer.parseInt(args[1]);
      } catch (NumberFormatException e) {
        throw new MetaParsingError(null, "");
      }

      int den;
      try {
        den = Integer.parseInt(args[2]);
      } catch (NumberFormatException e) {
        throw new MetaParsingError(null, "");
      }

      context.setTimeSignature(num, den);

      return null;
    }),
    KEY("sharps - The keys to sharp, separated by commas; " +
        "flats - The keys to flat, separated by commas", (context, args) -> {
      if (args.length != 3) {
        throw new MetaParsingError(null, "");
      }

      List<Character> sharps = Arrays.stream(args[1].split(",")).map(s -> s.charAt(0))
          .collect(Collectors.toList());

      List<Character> flats = Arrays.stream(args[2].split(",")).map(s -> s.charAt(0))
          .collect(Collectors.toList());

      context.setKeySignature(sharps, flats);

      return null;
    })/*,
    MARK("key - The name of this mark", (context, args) -> {
      if (args.length != 2) {
        throw new MetaParsingError(null, "");
      }


    }),
    GOTO(),
    MSR()*/;

    String argDesc;
    BiFunction<MusicalContext, String[], MetaSongEvent> factory;

    MetaSequence(String argDesc, BiFunction<MusicalContext, String[], MetaSongEvent> factory) {
      this.argDesc = argDesc;
      this.factory = factory;
    }
  }
}
