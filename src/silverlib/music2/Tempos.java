package silverlib.music2;

enum Tempos {
  GRAVE(45),
  LARGO(60),
  ADAGIO(75),
  ANDANTE(100),
  MODERATO(120),
  ALLEGRO(150),
  VIVACE(175),
  PRESTO(200);

  int bpm;

  Tempos(int bpm) {
    this.bpm = bpm;
  }


}
