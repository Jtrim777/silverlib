package silverlib.game;

import silverlib.geo.Point;
import silverlib.log.Log;

import java.awt.event.WindowListener;

public abstract class Game {
  GameCanvas canvas;
  private transient boolean worldExists = false;
  transient GameTimer tickTimer;
  transient boolean stopTimer = false;
  private transient GameKeyAdapter ka;
  private transient GameMouseAdapter ma;
  private transient WindowListener windowClosing;
  private transient SceneImg blankScene = new SceneImg(0, 0);
  private int width;
  private int height;

  public void activate(int w, int h, double speed) {
    if (this.worldExists) {
      System.out.println("Only one world can run at a time");
    } else {
      this.width = w;
      this.height = h;
      this.canvas = new GameCanvas(w, h);
      this.blankScene = new SceneImg(w, h);
      this.canvas.frame.setDefaultCloseOperation(2);
      this.windowClosing = new WindowCloseListener(this);
      this.canvas.frame.addWindowListener(this.windowClosing);
      long start = System.currentTimeMillis();

      long tmp;
      for(tmp = System.currentTimeMillis(); tmp - start < 1000L; tmp = System.currentTimeMillis()) {
      }

      this.ka = new GameKeyAdapter(this);
      this.canvas.frame.addKeyListener(this.ka);
      this.canvas.frame.setFocusTraversalKeysEnabled(false);
      this.ma = new GameMouseAdapter(this);
      this.canvas.frame.addMouseListener(this.ma);
      this.canvas.frame.addMouseMotionListener(this.ma);
      this.canvas.frame.setFocusable(true);
      this.canvas.show();
      this.worldExists = true;
      this.tickTimer = new GameTimer(this, speed);
      this.drawGame();
      start = System.currentTimeMillis();

      for(tmp = System.currentTimeMillis(); tmp - start < 1000L; tmp = System.currentTimeMillis()) {
      }

      if (speed > 0.0D) {
        this.tickTimer.timer.start();
      }

      Log.print("Game Initialized");

      this.drawGame();
    }
  }

  void stopWorld() {
    if (this.worldExists) {
      this.tickTimer.timer.stop();
      this.worldExists = false;
      this.tickTimer.stopTimer();
      this.canvas.frame.removeKeyListener(this.ka);
      this.canvas.frame.removeMouseListener(this.ma);
      this.canvas.clear();
      this.drawGame();

      Log.print("Game Ended");
    }
  }

  public void processTick() {
    try {
      if (this.worldExists && !this.stopTimer) {
        if (this.worldShouldEnd()) {
          this.stopWorld();
        } else {
          this.onTick();
          this.drawGame();
        }
      }
    } catch (RuntimeException var2) {
      var2.printStackTrace();
      this.drawGame();
      Runtime.getRuntime().halt(1);
    }
  }
  public abstract void onTick();

  public abstract boolean worldShouldEnd();

  public void drawGame() {
    if (this.worldExists) {
      SceneImg scene = this.render();
      this.canvas.clear();
      this.canvas.drawScene(scene);
    } else {
      SceneImg scene = this.renderFinal();
      this.canvas.clear();
      this.canvas.drawScene(scene);
    }
  }
  public abstract SceneImg render();
  public SceneImg renderFinal() {
    return render();
  }

  synchronized void processKeyEvent(GameKeyAdapter.KEventType type, String key) {
    try {
      if (this.worldExists) {
        this.onKeyEvent(type, key);
        this.drawGame();
      }
    } catch (RuntimeException var3) {
      var3.printStackTrace();
      this.drawGame();
      Runtime.getRuntime().halt(1);
    }
  }
  public abstract void onKeyEvent(GameKeyAdapter.KEventType type, String key);

  synchronized void processMouseEvent(GameMouseAdapter.MEventType type,
                                         GameMouseAdapter.MButton button, Point mPos) {
    try {
      if (this.worldExists) {
        this.onMouseEvent(type, button, mPos);
        this.drawGame();
      }
    } catch (RuntimeException var3) {
      var3.printStackTrace();
      this.drawGame();
      Runtime.getRuntime().halt(1);
    }
  }
  public abstract void onMouseEvent(GameMouseAdapter.MEventType type,
                                    GameMouseAdapter.MButton button, Point mPos);

  public SceneImg getEmptyScene() {
    return new SceneImg(this.width, this.height);
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }
}
