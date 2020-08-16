package silverlib.music2;

public enum Dynamics {
  FFF(127),
  FF(127 * 6/7),
  F(127 * 5/7),
  MF(127 * 4/7),
  MP(127 * 3/7),
  P( 127 * 2/7),
  PP( 127/7 + 8),
  PPP(15);

  int value;

  Dynamics(int value) {
    this.value = value;
  }
}
