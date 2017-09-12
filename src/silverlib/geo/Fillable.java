package silverlib.geo;

import silverlib.img.*;
import java.awt.Color;

interface Fillable{
  static final Color CLEAR = new Color(0,0,0,0);
  
  public void drawFill(Img im);
  
  public void setFill(Color c);
  
  public Color fill();
}
