package silverlib.map;

import silverlib.img.Img;
import silverlib.img.Pixel;
import silverlib.geo.*;
import silverlib.math.*;
import silverlib.log.*;
import java.io.*;
import java.awt.Color;

public class Mercator{
  public static Img map(Img in,Point cent) throws IOException{
    int longDim = in.width();
    if(in.height()>longDim){
      longDim = in.height();
    }

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
        xF += 3220;
      }else if(Quadrant.match(run,rise) == Quadrant.II){
        xF += 3220;
      }

      outPix[i] = new Pixel(xF,yF,pix[i]);
    }

    Img out = new Img(endWidth,in.height());

    out.drawPixels(outPix);

    return out;
  }
}
