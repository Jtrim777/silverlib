package silverlib.game;

import javalib.utils.AbstractKeyAdapter;
import javalib.utils.AbstractKeyAdapter.Consumer;

final class GameKeyAdapter extends AbstractKeyAdapter {
  GameKeyAdapter(Game currentWorld) {
    super(new GameKeyAdapter.OnKey(currentWorld), new GameKeyAdapter.OnReleased(currentWorld));
  }

  void resetGame(Game current) {
    this.onKey = new GameKeyAdapter.OnKey(current);
    this.onReleased = new GameKeyAdapter.OnReleased(current);
  }

  static class OnReleased implements Consumer<String> {
    final Game currentGame;

    OnReleased(Game w) {
      this.currentGame = w;
    }

    public void apply(String data) {
      this.currentGame.processKeyEvent(GameUtils.KEventType.RELEASED, data);
    }
  }

  static class OnKey implements Consumer<String> {
    final Game currentGame;

    OnKey(Game w) {
      this.currentGame = w;
    }

    public void apply(String data) {
      this.currentGame.processKeyEvent(GameUtils.KEventType.PRESSED, data);
    }
  }


}
