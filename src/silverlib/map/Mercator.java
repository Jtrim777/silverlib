package silverlib.map;

import silverlib.img.Img;
import silverlib.img.Pixel;
import silverlib.geo.*;
import silverlib.math.*;
import silverlib.log.*;
import java.io.*;
import java.awt.Color;

/** Creator of Mercator Projections
  @since 1.8
  @author Jake Trimble
*/
public class Mercator{
  /** Creates a Mercator Projection
    @param in The source <code>Img</code>
    @param cent The pole to center the Projection on
    @return A Mercator Projection of <code>in</code>
    @throws IOException If errors occur in the <code>Img</code> editing
    @since 1.8
  */
  public static Img map(Img in,Point cent) throws IOException{
    int longDim = in.width();

    int endWidth = (int)Math.rint(Math.PI * 2 * longDim);

    int imgW = in.width();
    Color[] pix = in.pixels();

    Pixel[] outPix = new Pixel[pix.length];

    for(int i=0;i<pix.length;i++){
      int x = Pixel.coordinate(i,imgW)[0];
      int y = Pixel.coordinate(i,imgW)[1];

      int rise = y-cent.y();
      int run = x-cent.x();

      int yF = (int)Math.rint(Math.sqrt(Num.square(rise) + Num.square(run)));

      double tan = (double)run/(double)rise;
      double angle = Math.toDegrees(Math.atan(tan));
      if(angle < 0){
        angle = angle + 360;
      }
      double ratio = angle/360;

      int xF = (int)(ratio * (double)endWidth);

      if(Quadrant.match(run,rise) == Quadrant.I){
        xF += endWidth/2;
      }else if(Quadrant.match(run,rise) == Quadrant.II){
        xF += endWidth/2;
      }

      outPix[i] = new Pixel(xF,yF,pix[i]);
    }

    Img out = new Img(endWidth,in.height());

    out.drawPixels(outPix);

    return out;
  }
  
  public static int[] relocate(int x,int y,Point cent,int w){
    int endWidth = (int)Math.rint(Math.PI * 2 * w);
    
    int rise = y-cent.y();
    int run = x-cent.x();

    int yF = (int)Math.rint(Math.sqrt(Num.square(rise) + Num.square(run)));

    double tan = (double)run/(double)rise;
    double angle = Math.toDegrees(Math.atan(tan));
    if(angle < 0){
      angle = angle + 360;
    }
    double ratio = angle/360;

    int xF = (int)(ratio * (double)endWidth);

    if(Quadrant.match(run,rise) == Quadrant.I){
      xF += endWidth/2;
    }else if(Quadrant.match(run,rise) == Quadrant.II){
      xF += endWidth/2;
    }
    
    int[] ret = new int[]{xF,yF};
    
    return ret;
  }
}
