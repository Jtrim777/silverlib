package silverlib.math;

public class Num{
  public static float random(float floor,float cel){
    return ((cel-floor)*(float)Math.random())+floor;
  }
  public static float random(float cel){
    return cel*(float)Math.random();
  }
  
  public static double random(double floor,double cel){
    return ((cel-floor)*(double)Math.random())+floor;
  }
  public static double random(double cel){
    return cel*(double)Math.random();
  }
  
  public static int random(int floor,int cel){
    return (int)((double)(cel-floor)*(double)Math.random()+(double)floor);
  }
  public static int random(int cel){
    return (int)((double)cel*(double)Math.random());
  }
  
  
  public static long exp(double base,int pow){
    return (long)Math.pow((double)base,(double)pow);
  }
  
  public static long sci(double base,int ttt){
    return (long)(base) * exp(10,ttt);
  }
  
  public static int GCD(int a, int b) { return b==0 ? a : GCD(b, a%b); }
  
  public static int square(int base){return base*base;}
}
