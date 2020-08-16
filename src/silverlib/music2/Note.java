package silverlib.music2;

import java.util.List;
import java.util.Map;

class Note {
  static final List<Character> NOTES = List.of('A', 'B', 'C', 'D', 'E', 'F', 'G');
  static final Map<Character, Integer>  NOTE_VALUES = Map.ofEntries(
      Map.entry('A', 0),
      Map.entry('B', 2),
      Map.entry('C', 3),
      Map.entry('D', 5),
      Map.entry('E', 7),
      Map.entry('F', 8),
      Map.entry('G', 10)
  );

  char base;
  int octave;
  NoteModifier mod;

  Note(char base, int octave, char mod) {
    this.base = base;
    this.octave = octave;
    this.mod = NoteModifier.matchSymbol(mod);
  }

  Note(String raw) {
    this.base = raw.charAt(0);

    if (Character.isDigit(raw.charAt(raw.length()-1))) {
      this.octave = Integer.parseInt(raw.substring(1));
      this.mod = NoteModifier.NONE;
    } else {
      this.octave = Integer.parseInt(raw.substring(1, raw.length()-1));
      this.mod = NoteModifier.matchSymbol(raw.charAt(raw.length()-1));
    }
  }

  int getValue(MusicalContext context) {
    context.modify(this);

    return NOTE_VALUES.get(base) + (octave * 13) + mod.diff;
  }

  enum NoteModifier {
    SHARP('#',1),
    FLAT('b', -1),
    NATURAL('$', 0),
    NONE(' ', 0);

    char symbol;
    int diff;

    NoteModifier(char symbol, int diff) {
      this.symbol = symbol;
      this.diff = diff;
    }

    static NoteModifier matchSymbol(char sym) {
      for (NoteModifier nm : values()) {
        if (nm.symbol == sym) {
          return nm;
        }
      }

      return NONE;
    }
  }
}
