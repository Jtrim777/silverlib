package silverlib.img; 

import java.awt.image.*;
import javax.imageio.*;
import java.awt.Color;
import java.io.*;
import silverlib.log.*;
import java.util.*;

public class Img{
  private Color[] pixels;
  private int height;
  private int width;
  
  public Img(String nm) throws IOException{
    File f = new File(nm);
    BufferedImage rawImg = ImageIO.read(f);
    
    width = rawImg.getWidth();
    height = rawImg.getHeight();
    
    int[] rawPix = rawImg.getRGB(0, 0, width, height, null, 0, width);
    
    //Log.print(rawPix.length + "");
    
    pixels = new Color[width*height];
    
    for(int i=0;i<pixels.length;i++){
      pixels[i] = new Color(rawPix[i]);
    }
  }
  
  public Img(Color[] pix,int w){
    height = pix.length/w;
    width = pix.length/height;
    pixels = pix;
  }
  
  public int width(){return width;}
  public int height(){return height;}
  public Color[] pixels(){return pixels;}
  
  public void set(int x, int y, Color n){
    int i = x + (y*width);
    pixels[i] = n;
  }
  
  public Color get(int x, int y){
    int i = x + (y*width);
    return pixels[i];
  }
  
  public void save(String nm){
    BufferedImage out = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
    
    int[] outPix = new int[width*height];
    
    Log.print(outPix.length + "");
    
    for(int i=0;i<pixels.length;i++){
      outPix[i] = pixels[i].getRGB();
    }
    
    out.setRGB(0, 0, width, height, outPix, 0, width);
    
    File o = new File(nm+".png");
    try{
      ImageIO.write(out,"png",o);
    }catch(IOException e){
      Log.print("Failed to save Image");
      e.printStackTrace();
    }
    
  }

  public Img subimage(int x1, int y1, int x2, int y2){
    int w = x2-x1;
    int h = y2-y1;
    
    Color[] convPix = new Color[h*w];
    
    for(int y=y1;y<y2;y++){
      for(int x=x1;x<x2;x++){
        int i = (x-x1) + ((y-y1)*w);
        convPix[i] = get(x,y);
      }
    }
    
    return new Img(convPix,w);
  }
}
