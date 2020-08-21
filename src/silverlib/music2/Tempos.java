package silverlib.music2;

enum Tempos {
  GRAVE(45),
  LARGO(60),
  ADAGIO(76),
  ANDANTE(108),
  MODERATO(120),
  ALLEGRO(156),
  VIVACE(176),
  PRESTO(200);

  int bpm;

  Tempos(int bpm) {
    this.bpm = bpm;
  }


}
