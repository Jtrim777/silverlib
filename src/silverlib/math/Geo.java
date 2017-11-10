package silverlib.math;

public final class Geo{
  public static long ellipseCirc(long smaj,long smin){
    long h = Num.square(smaj-smin)/Num.square(smaj+smin);
    
    long c = (long)((3*h)/(10+Math.sqrt(4-(3*h))) + 1);
    
    return (long)Math.PI * (smaj+smin) * c;
  }
}
