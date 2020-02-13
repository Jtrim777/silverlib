package silverlib.game;

import silverlib.geo.Circle;
import silverlib.geo.Point;
import silverlib.log.Log;
import silverlib.test.Tester;

import java.awt.*;

public class TestGame extends Game {
  double ballX;

  TestGame() {
    this.ballX = 0.0;
  }


  @Override
  public void onTick() {
//    Log.print("Ticked");
    ballX += 0.25;
  }

  @Override
  public boolean worldShouldEnd() {
    return ballX > this.getWidth();
  }

  @Override
  public SceneImg render() {
    SceneImg blank = getEmptyScene();
    Circle ball = new Circle(new Point((int)ballX,getHeight()/2), 30);

    ball.setFill(Color.RED);

    blank.add(ball);

    return blank;
  }

  @Override
  public void onKeyEvent(GameKeyAdapter.KEventType type, String key) {

  }

  @Override
  public void onMouseEvent(GameMouseAdapter.MEventType type, GameMouseAdapter.MButton button, Point mPos) {

  }
}

class TestTestGame {
  public static void main(String[] args) {
    TestGame game = new TestGame();

    game.activate(500, 400, 0.01);
  }
}
