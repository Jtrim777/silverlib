import silverlib.geo.*;
import silverlib.img.Img;
import java.io.File;
import java.awt.Color;

public class Graph extends Image{
  private Function fx;
  private Point origin;
  private int xScale;
  private int yScale;

  public Graph(Function f, Point o, int w, int h){
    super(w,h);

    fxTem = f.translateY(o.y);
    fx = fxTem.reflectX();

    origin = o;
  }
}