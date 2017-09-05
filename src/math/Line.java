package silverlib.math;
import java.util.ArrayList;

public class Line{
  ArrayList<Point> pts;
  Point p1;
  Point p2;
  
  public Line(Point a, Point b){
    pts = new ArrayList<Point>();
    
    if(a.x()<=b.x()){
      p1=a;
      p2=b;
    }else{
      p1=b;
      p2=a;
    }
    
    pts.add(p1);
    
    Fraction slope = new Fraction((p1.y()-p2.y()),(p1.x()-p2.x()));
    slope.simplify();
    
    int x = p1.x();
    int y = p1.y();
    int rise = slope.num();
    int run = slope.den();
    
    while(x<p2.x() && y<p2.y()){
      for(int i=0;i<Math.abs(rise);i++){
        if(rise<0){
          y--;
        }else{
          y++;
        }
        
        pts.add(new Point(x,y));
      }
      
      for(int i=0;i<Math.abs(run);i++){
        if(run<0){
          x--;
        }else{
          x++;
        }
        
        pts.add(new Point(x,y));
      }
    }
  }
  
  public Point start(){
    return p1;
  }
  public Point end(){
    return p2;
  }
  
  public Point[] pts(){
    return (Point[]) pts.toArray();
  }
  
  public String toString(){
    String out = "[";
    
    for(Point i:pts){
      out += i.toString() + ", "; 
    }
    
    out = out.substring(0,out.length()-2);
    out += "]"; 
    
    return out;
  }
}
