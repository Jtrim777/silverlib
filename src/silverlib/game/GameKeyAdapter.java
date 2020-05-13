package silverlib.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.util.HashMap;

final class GameKeyAdapter extends KeyAdapter {
  private static final HashMap<Integer, String> KEY_MAPPING = generateKeyMapping();

  private GameKeyAdapter.OnKey onKey;
  private GameKeyAdapter.OnReleased onReleased;

  GameKeyAdapter(Game currentWorld) {
    onKey = new GameKeyAdapter.OnKey(currentWorld);
    onReleased = new GameKeyAdapter.OnReleased(currentWorld);
  }

  @Override
  public void keyTyped(KeyEvent e) { }

  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() != 0) {
      this.onKey.apply(constructKeyName(e));
    }

  }

  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() != 0) {
      this.onReleased.apply(constructKeyName(e));
    }
  }

  void resetGame(Game current) {
    this.onKey = new GameKeyAdapter.OnKey(current);
    this.onReleased = new GameKeyAdapter.OnReleased(current);
  }

  static class OnReleased {
    final Game currentGame;

    OnReleased(Game w) {
      this.currentGame = w;
    }

    public void apply(String data) {
      this.currentGame.processKeyEvent(GameUtils.KEventType.RELEASED, data);
    }
  }

  static class OnKey {
    final Game currentGame;

    OnKey(Game w) {
      this.currentGame = w;
    }

    public void apply(String data) {
      this.currentGame.processKeyEvent(GameUtils.KEventType.PRESSED, data);
    }
  }

  private static HashMap<Integer, String> generateKeyMapping() {
    HashMap<Integer, String> rez = new HashMap<>();

    for (Field f : KeyEvent.class.getFields()) {
      String fname = f.getName();
      if (!fname.startsWith("VK_")) {
        continue;
      }

      fname = fname.substring(3);
      try {
        rez.put((Integer)f.get(null), fname);
      } catch (IllegalAccessException e) {
        throw new IllegalStateException("Couldn't get value of key constant "+fname);
      }
    }

    return rez;
  }

  private static String constructKeyName(KeyEvent e) {
    String prefix = "";
    if (e.getKeyLocation() == 2) {
      prefix = "LEFT-";
    } else if (e.getKeyLocation() == 3) {
      prefix = "RIGHT-";
    } else if (e.getKeyLocation() == 4) {
      prefix = "NUMPAD-";
    }

    return prefix + KEY_MAPPING.get(e.getKeyCode());
  }
}
