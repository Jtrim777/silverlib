package silverlib.music2;

class MusicProcessingError extends IllegalArgumentException {
  MusicProcessingError(String message) {
    super(message);
  }

  static class SongGenerationError extends MusicProcessingError {
    SongGenerationError(String msg) {
      super(msg);
    }
  }

  static class BadMetadataException extends MusicProcessingError {
    BadMetadataException() {
      super("Metadata line should be provided in the format: " +
          "\"tempo time_sig_num time_sig_den sharps flats\"; where tempo, time_sig_num, and " +
          "time_sig_den are integers, while sharps and flats are colon-separated characters");
    }
  }

  static class NoteFormatException extends MusicProcessingError {
    NoteFormatException(String note) {
      super("Invalid note format: \""+note+"\" Expected format is: \n" +
          "ABCDEF where: \n" +
          "- A is any prefix modifying the form of a note, one of [~, =, !]\n" +
          "- B is the note body, consisting of a capital note letter followed by the octave.\n" +
          "    Optionally, the note body may contain several notes seperated by '|'\n" +
          "- C is and infix modifier, either '$' for natural or 'b' for flat\n" +
          "- D is the duration delimiter, either '.' for divisive or '*' for multiplicative\n" +
          "- E is the duration, a whole number for division or a decimal/fraction for "+
             "multiplication" +
          "- F is any suffix modifying the duration of the note, one of [+, &, >, ^]");
    }
  }

  static class SymbolParsingError extends MusicProcessingError {
    SymbolParsingError(String message, String note) {
      super("Error parsing symbol "+note+"; "+message);
    }
  }

  static class MetaParsingError extends SymbolParsingError {
    MetaParsingError(SymbolParser.MetaSequence meta, String symbol) {
      super("Meta Sequence "+meta.name()+" expects arguments\n"+meta.argDesc, symbol);
    }
  }

  static class NoteParsingError extends SymbolParsingError {
    NoteParsingError(String message, String note) {
      super(message, note);
    }
  }

  static class NoteModificationError extends NoteParsingError {
    NoteModificationError(SymbolParser.Modifier mod, SymbolParser.Modifier conflict, String note) {
      super("Modification "+mod.name()+" ["+mod.symbol+"] is not compatible with " +
              conflict.name() +" ["+conflict.symbol+"]", note);
    }
  }
}
