package silverlib.geo;

import java.util.ArrayList;
import silverlib.math.*;
import silverlib.img.*;
import java.awt.Color;

/** <h1>Circle Class </h1>
Contains a list of points that define the edges of a circle*/
public class Circle extends Shape implements Fillable{
  private int radius;
  private int tol;
  private Color fill;
  //private int lineWidth;
  
  /** Initializes a <code>Circle</code> object from a <code>Point</code> and a radius.
  
  @param c The <code>Point</code> which marks the center of the <code>Circle</code>
  @param r The radius of the <code>Circle</code>
  @since 1.4.2.1
  */
  public Circle(Point c,int r){
    super(c);
    radius = r;
    //lineWidth = 2;
    tol = (int)((double)r/1.09);
    
    properties.put("radius",r);
    properties.put("fillR",fill.getRed());
    properties.put("fillG",fill.getGreen());
    properties.put("fillB",fill.getBlue());
    
    int xMin = c.x()-r-2;
    int xMax = c.x()+r+2;
    int yMin = c.y()-r-2;
    int yMax = c.y()+r+2;
    
    for(int i=yMin;i<yMax;i++){
      for(int j=xMin;j<xMax;j++){
        if(Num.within(Num.square(i-c.y())+Num.square(j-c.x()),Num.square(r),tol)){
          Point p = new Point(j,i);
          pts.add(p);
        }
      }
    }
    
    fill = CLEAR;
  }
  
  /**@return A <code>String</code> representation of the <code>Circle</code> object, in 
  the form <code>{(x,y),(x2,y2),...}</code>
  @since 1.5.1
  */
  public String toString(){
    String out = "{";
    
    for(Point i:pts){
      out += i.toString() + ", "; 
    }
    
    out = out.substring(0,out.length()-2);
    out += "}"; 
    
    return out;
  }
  
  /**@return The radius of the Circle
  @since 1.5.1*/
  public int radius(){return radius;}
  /**@return The fill <code>Color</code> of this circle
  @since 1.6.2
  */
  public Color fill(){return fill;}
  
  /**Sets the fill Color
  @param c The <code>Color</code> to setFill
  @since 1.6.2
  */
  public void setFill(Color c){
    fill = c;
  }
  
  /**Sets a property and updates class fields, also redraws the circle if 
  necessary.
  @param n The key of the property to modify
  @param v The value to set the property to
  @since 1.7.1
  */
  @Override
  public void setProp(String n,int v){
    super.setProp(n,v);
    
    if(n.equals("radius") || n.equals("x") || n.equals("y")){
      radius = getProp("radius");
      remake();
    }else if(n.equals("fillR") || n.equals("fillG") || n.equals("fillB")){
      fill = new Color(getProp("fillR"),getProp("fillG"),getProp("fillB"));
    }
  }
  
  /** Redraws the circle
  @since 1.7.1
  */
  public void remake(){
    int xMin = loc.x()-radius-2;
    int xMax = loc.x()+radius+2;
    int yMin = loc.y()-radius-2;
    int yMax = loc.y()+radius+2;
    
    for(int i=yMin;i<yMax;i++){
      for(int j=xMin;j<xMax;j++){
        if(Num.within(Num.square(i-loc.y())+Num.square(j-loc.x()),Num.square(radius),tol)){
          Point p = new Point(j,i);
          pts.add(p);
        }
      }
    }
  }
  
  /**Draws the circle into an <code>Img</code> object, filling it with color
  if the fill color has been set
  @param im The <code>Img</code> to draw to
  @since 1.6.2
  */
  @Override
  public void draw(Img im){
    if(fill.equals(CLEAR)){
      super.draw(im);
    }else{
      drawFill(im);
    }
  }
  
  /**Draws the circle into an <code>Img</code> object, filling it with color
  @param im The <code>Img</code> object to draw to
  @since 1.6.2
  */
  public void drawFill(Img im){
    int xMin = loc.x()-radius-2;
    int xMax = loc.x()+radius+2;
    int yMin = loc.y()-radius-2;
    int yMax = loc.y()+radius+2;
    
    for(int i=yMin;i<yMax;i++){
      for(int j=xMin;j<xMax;j++){
        if((Num.square(i-loc.y())+Num.square(j-loc.x())) < Num.square(radius)){
          Point d = new Point(j,i);
          im.drawPoint(d,fill);
        }
      }
    }
    
    super.draw(im);
  }
}
