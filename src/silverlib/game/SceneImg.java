package silverlib.game;

import silverlib.geo.Shape;
import silverlib.geo.Fillable;
import silverlib.img.Img;

import java.awt.Color;

public class SceneImg extends Img {
  public SceneImg(int width, int height) {
    super(width, height, Color.WHITE);
  }

  public void add(Shape obj) {
    if (obj instanceof Fillable) {
      ((Fillable)obj).drawFill(this);
    } else {
      obj.draw(this);
    }
  }
}
