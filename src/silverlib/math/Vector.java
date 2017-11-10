package silverlib.math;

import silverlib.geo.PinPoint;

public class Vector{
  private float xPow, yPow;
  private double angle;
  private double magnitude;
  
  public Vector(float x,float y,double p){
    xPow = x;
    yPow = y;
    magnitude = Math.abs(p);
    
    double d = Math.sqrt(Num.square(x)+Num.square(1-y));
    angle = Math.acos(1-(Num.square(d)/2));
    if(x<0){
      angle = 180-angle + 180;
    }
  }
  
  public float x(){return xPow;}
  public float y(){return yPow;}
  public double mag(){return magnitude;}
  public double angle(){return angle;}
  
  public PinPoint tick(PinPoint in){
    double x = in.x();
    double y = in.y();
    
    x += magnitude*xPow;
    y += magnitude*yPow;
    
    PinPoint out = new PinPoint(x,y);
    
    return out;
  }
  
  public void add(Vector v){
    angle += v.angle();
    if(angle>=360){
      angle -= 360;
    }
    
    magnitude += v.mag();
  }
}
