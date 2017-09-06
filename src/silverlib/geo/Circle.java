package silverlib.geo;

import java.util.ArrayList;
import silverlib.math.*;

/** <h1>Circle Class </h1>
Contains a list of points that define the edges of a circle*/
public class Circle extends Shape{
  private int radius;
  
  /** Initializes a <code>Circle</code> object from a <code>Point</code> and a radius.
  
  @param c The <code>Point</code> which marks the center of the <code>Circle</code>
  @param r The radius of the <code>Circle</code>
  @since 1.4.2.1
  */
  public Circle(Point c,int r){
    super(c);
    radius = r;
    
    int xMin = c.x()-r-2;
    int xMax = c.x()+r+2;
    int yMin = c.y()-r-2;
    int yMax = c.y()+r+2;
    
    for(int i=yMin;i<yMax;i++){
      for(int j=xMin;j<xMax;j++){
        if(Num.within(Num.square(i-c.y())+Num.square(j-c.x()),Num.square(r),20)){
          Point p = new Point(j,i);
          pts().add(p);
        }
      }
    }
  }
  
  /**@return A <code>String</code> representation of the <code>Circle</code> object, in 
  the form <code>{(x,y),(x2,y2),...}</code>
  @since 1.5.1
  */
  public String toString(){
    String out = "{";
    
    for(Point i:pts()){
      out += i.toString() + ", "; 
    }
    
    out = out.substring(0,out.length()-2);
    out += "}"; 
    
    return out;
  }
  
  /**@return The radius of the Circle
  @since 1.5.1*/
  public int radius(){return radius;}
}
