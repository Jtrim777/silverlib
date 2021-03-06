package silverlib.game;

import silverlib.geo.Point;
import silverlib.geo.Shape;
import silverlib.geo.Fillable;
import silverlib.img.Img;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class SceneImg extends Img {
  private List<ScreenString> strings = new ArrayList<>();

  public SceneImg(String fpth) throws IOException {
    super(fpth);
  }
  public SceneImg(File file) throws IOException {
    super(file);
  }
  public SceneImg(int width, int height) {
    super(width, height, Color.WHITE);
  }
  public SceneImg(int width, int height, Color bg) {
    super(width, height, bg);
  }
  public SceneImg(SceneImg from){
    super(from);
  }

  public void add(Shape obj) {
    if (obj instanceof Fillable) {
      ((Fillable)obj).drawFill(this);
    } else {
      obj.draw(this);
    }
  }

  public void placeSprite(GameSprite sprite, int x, int y) {
    sprite.drawOn(this, x, y);
  }

  public void addString(String text, int drawX, int drawY) {
    this.strings.add(new ScreenString(text, drawX, drawY));
  }

  public void addString(ScreenString str) {
    this.strings.add(str);
  }

  public List<ScreenString> getStrings() {
    return strings;
  }

  public void overlayImg(Img img) {
    Point center = new Point(this.width()/2, this.height()/2);

    Point no = new Point(center.x()-(img.width()/2), center.y()-(img.height()/2));

    for (Point p : Point.iterPoints(0,0, img.width(), img.height())) {
      Point np = Point.adjustForOrigin(p, no);

      this.set(np.x(), np.y(), img.get(p.x(), p.y()));
    }
  }
}
