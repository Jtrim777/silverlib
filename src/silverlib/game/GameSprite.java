package silverlib.game;

import silverlib.geo.Point;
import silverlib.geo.Shape;
import silverlib.img.Img;
import silverlib.img.Pixel;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

public class GameSprite {
  private ArrayList<Pixel> pixels;

  public GameSprite() {
    this.pixels = new ArrayList<>();
  }

  public GameSprite(String filepath) throws IOException {
    Img imgForm = new Img(filepath);

    this.pixels = imgForm.asPixels();
  }

  public void addShape(Shape inp, int x, int y) {
    Color c = inp.col();

    for (Point p : inp.pts()) {
      Point tp = new Point(p.x() + x, p.y() + y);

      Pixel px = new Pixel(tp, c);
      pixels.add(px);
    }
  }

  public ArrayList<Pixel> getPixels() {
    return pixels;
  }

  public void drawOn(Img img, int x, int y) {
    for (Pixel px : this.pixels) {
      int nx = px.loc().x() + x;
      int ny = px.loc().y() + y;

      if (nx >= 0 && nx < img.width() && ny >= 0 && ny < img.height()) {
        img.set(nx, ny, px.col());
      }
    }
  }
}
