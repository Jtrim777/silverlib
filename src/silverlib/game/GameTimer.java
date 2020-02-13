package silverlib.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

final class GameTimer {
  Game currentWorld;
  Timer timer;
  public boolean running = true;
  int speed;
  ActionListener timerTasks = evt -> {
    if (GameTimer.this.running) {
      GameTimer.this.currentWorld.processTick();
    }

  };

  GameTimer(Game currentWorld, double speed) {
    this.currentWorld = currentWorld;
    this.timer = new Timer((int)(speed * 1000.0D), this.timerTasks);
    this.speed = (int)(speed * 1000.0D);
  }

  void setSpeed() {
    this.timer.setDelay(this.speed);
  }

  void stopTimer() {
    this.running = false;
  }
}

