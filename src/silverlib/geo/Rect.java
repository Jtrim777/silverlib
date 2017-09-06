package silverlib.geo;

import silverlib.math.*;

/** <h1>Rectangle class</h1>
 Contains a series of 4 <code>Line</code>s*/
public class Rect extends Shape{
  private Line[] lines;
  private int width;
  private int height;
  
  /** Initializes a <code>Rect</code> object from an x and y coordinate, and the width
  and height of the <code>Shape</code>.
  
  @param x The x coordinate
  @param y The y coordinate
  @param w The width of the rectangle
  @param h The height of the rectangle 
  @since 1.5.1
  */
  public Rect(int x,int y,int w,int h){
    super(x,y);
    width=w;
    height=h;
    
    lines = new Line[4];
    
    /*
           0
       ---------
    2  |        | 3
       |        |
       ---------
           1
    */
    Point p1 = new Point(x+(w-1),y);
    Point p2 = new Point(x+(w-1),y+(h-1));
    Point p3 = new Point(x,y+(h-1));
    
    lines[0] = new Line(loc(),p1);
    lines[1] = new Line(p3,p2);
    lines[2] = new Line(loc(),p3);
    lines[3] = new Line(p1,p2);
    
    for(Line line:lines){
      for(Point pt:line.pts()){
        pts().add(pt);
      }
    }
  }
  /** Initializes a square <code>Rect</code> object from an x and y coordinate, and size
  of the <code>Shape</code>.
  
  @param x The x coordinate
  @param y The y coordinate
  @param s The width and height of the square 
  @since 1.5.1
  */
  public Rect(int x,int y,int s){
    super(x,y);
    width=s;
    height=s;
    
    lines = new Line[4];
    
    Point p1 = new Point(x+(s-1),y);
    Point p2 = new Point(x+(s-1),y+(s-1));
    Point p3 = new Point(x,y+(s-1));
    
    lines[0] = new Line(loc(),p1);
    lines[1] = new Line(p3,p2);
    lines[2] = new Line(loc(),p3);
    lines[3] = new Line(p1,p2);
    
    for(Line line:lines){
      for(Point pt:line.pts()){
        pts().add(pt);
      }
    }
  }
  
  /** Initializes a <code>Rect</code> object from a <code>Point</code>, and the width
  and height of the <code>Shape</code>.
  
  @param p The location of the shape
  @param w The width of the rectangle
  @param h The height of the rectangle 
  @since 1.5.1
  */
  public Rect(Point p,int w,int h){
    super(p);
    width=w;
    height=h;
    
    lines = new Line[4];
    
    Point p1 = new Point(p.x()+(w-1),p.y());
    Point p2 = new Point(p.x()+(w-1),p.y()+(h-1));
    Point p3 = new Point(p.x(),p.y()+(h-1));
    
    lines[0] = new Line(loc(),p1);
    lines[1] = new Line(p3,p2);
    lines[2] = new Line(loc(),p3);
    lines[3] = new Line(p1,p2);
    
    for(Line line:lines){
      for(Point pt:line.pts()){
        pts().add(pt);
      }
    }
  }
  /** Initializes a square <code>Rect</code> object from a <code>Point</code>, and size
  of the <code>Shape</code>.
  
  @param p The location of the shape
  @param s The width and height of the square 
  @since 1.5.1
  */
  public Rect(Point p,int s){
    super(p);
    width=s;
    height=s;
    
    lines = new Line[4];
    
    Point p1 = new Point(p.x()+(s-1),p.y());
    Point p2 = new Point(p.x()+(s-1),p.y()+(s-1));
    Point p3 = new Point(p.x(),p.y()+(s-1));
    
    lines[0] = new Line(loc(),p1);
    lines[1] = new Line(p3,p2);
    lines[2] = new Line(loc(),p3);
    lines[3] = new Line(p1,p2);
    
    for(Line line:lines){
      for(Point pt:line.pts()){
        pts().add(pt);
      }
    }
  }
  
  /**@return The <code>Line</code>s that make up this rectangle
  @since 1.5.1
  */
  public Line[] lines(){return lines;}
  /**@return The width of this rectangle
  @since 1.5.1
  */
  public int width(){return width;}
  /**@return The height of this rectangle
  @since 1.5.1
  */
  public int height(){return height;}
  
  /**@return A <code>String</code> representation of the <code>Rect</code> object, in 
  the form <code>{[(x,y),(x2,y2),...],[(x,y),(x2,y2),...],...}</code>
  @since 1.5.1
  */
  public String toString(){
    String out = "{";
    
    for(Line i:lines){
      out += i.toString() + ", "; 
    }
    
    out = out.substring(0,out.length()-2);
    out += "}"; 
    
    return out;
  }
}
