public class Rect{
  private Line[] lines;
  private Point loc;
  private int width;
  private int height;
  
  public Rect(int x,int y,int w,int h){
    loc = new Point(x,y);
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
    
    lines[0] = new Line(loc,p1);
    lines[1] = new Line(p3,p2);
    lines[2] = new Line(loc,p3);
    lines[3] = new Line(p1,p2);
  }
  
  public Rect(int x,int y,int s){
    loc = new Point(x,y);
    width=s;
    height=s;
    
    lines = new Line[4];
    
    /*
           0
       ---------
    2  |        | 3
       |        |
       ---------
           1
    */
    
    Point p1 = new Point(x+(s-1),y);
    Point p2 = new Point(x+(s-1),y+(s-1));
    Point p3 = new Point(x,y+(s-1));
    
    lines[0] = new Line(loc,p1);
    lines[1] = new Line(p3,p2);
    lines[2] = new Line(loc,p3);
    lines[3] = new Line(p1,p2);
  }
  
  public Line[] lines(){return lines;}
  public Point loc(){return loc;}
  public int width(){return width;}
  public int height(){return height;}
  
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
