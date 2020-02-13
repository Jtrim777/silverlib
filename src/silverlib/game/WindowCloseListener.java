package silverlib.game;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class WindowCloseListener extends WindowAdapter {
  Game g;

  WindowCloseListener(Game g) {
    this.g = g;
  }

  public void windowClosing(WindowEvent we) {
    if (this.g != null && this.g.tickTimer != null) {
      this.g.tickTimer.stopTimer();
    }

  }
}
