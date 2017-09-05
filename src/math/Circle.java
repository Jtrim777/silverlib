package silverlib.math;
import java.util.ArrayList;

public class Circle {
  private ArrayList<Point> pts;
  private Point center;
  private int radius;
  
  public Circle(Point c,int r){
    center = c;
    radius = r;
    
    int xMin = c.x()-r-1;
    int xMax = c.x()+r+1;
    int yMin = c.y()-r-1;
    int yMax = c.y()+r+1;
    
    pts = new ArrayList<Point>();
    
    for(int i=yMin;i<yMax;i++){
      for(int j=xMin;j<xMax;j++){
        if(Num.square(i)+Num.square(j) == Num.square(r)){
          Point p = new Point(j,i);
          pts.add(p);
        }
      }
    }
  }
  
  public String toString(){
    String out = "{";
    
    for(Point i:pts){
      out += i.toString() + ", "; 
    }
    
    out = out.substring(0,out.length()-2);
    out += "}"; 
    
    return out;
  }
  
  public int radius(){return radius;}
  public Point center(){return center;}
  public ArrayList<Point> pts(){return pts;}
}
