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
    int imgW = in.width();

    double endWidth = Math.PI * 2 * (double)imgW;

    Color[] pix = in.pixels();

    Pixel[] outPix = new Pixel[pix.length];
    
    int xc = cent.x();
    int yc = cent.y();

    for(int i=0;i<pix.length;i++){
      int x = Pixel.coordinate(i,imgW)[0];
      int y = Pixel.coordinate(i,imgW)[1];

      int rise = y-yc;
      int run = x-xc;

      double r = (double)Math.sqrt(Num.square(rise) + Num.square(run));

      double c = 2*Math.PI*r;
      
      double ys;
      if(x<xc){ys=(double)yc-r;}else{ys=(double)yc+r;}
      double xs = (double)xc;
      
      double d = Math.sqrt(Num.square(x-xs)+ Num.square(y-ys));
      
      double theta = Math.acos(1-(Num.square(d)/(2*Num.square(r))));
      
      if(x>xc){theta+=Math.PI;}
      
      double p = (r*theta)/c;
      
      int xF = (int)Math.rint(p*endWidth);
      int yF = (int)Math.rint(r);
      
      // if(x==390&&y==140){
      //   Log.print("Pole X: "+x);
      //   Log.print("Pole Y: "+y);
      //   Log.print("Map X: "+xF);
      //   Log.print("Map Y: "+yF);
      // }

      outPix[i] = new Pixel(xF,yF,pix[i]);
    }

    Img out = new Img((int)Math.rint(endWidth)+2,in.height());

    out.drawPixels(outPix);

    return out;
  }
  
  public static double[] relocate(int x,int y,Point cent,int w){
    double xc = (double)cent.x();
    double yc = (double)cent.y();

    double rise = (double)y-yc;
    double run = (double)x-xc;

    double r = (double)Math.sqrt(Num.square(rise) + Num.square(run));

    double c = 2*Math.PI*r;
      
    double ys;
    if(x<xc){ys=(double)yc-r;}else{ys=(double)yc+r;}
    double xs = (double)xc;
      
    double d = Math.sqrt(Num.square(x-xs)+ Num.square(y-ys));
      
    double theta = Math.acos(1-(Num.square(d)/(2*Num.square(r))));
      
    if(x>xc){theta+=Math.PI;}
      
    double p = (r*theta)/c;
    
    double width = Math.rint(w*Math.PI*2)+2;
    
    double xF = p*width;
    double yF = r;
    
    xF = Math.rint(xF);
    yF = Math.rint(yF);
    
    // Log.print("Width: "+width);
    // Log.print("Radius: "+r);
    // Log.print("Circumference: "+c);
    // Log.print("y_s: "+ys);
    // Log.print("d: "+d);
    // Log.print("Theta: "+theta);
    // Log.print("Ratio: "+p);
    
    // xF/=100;
    // yF/=100;
    
    double[] ret = new double[]{xF,yF};
    
    return ret;
  }
}
