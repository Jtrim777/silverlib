package silverlib.math;

/**<h1>Math Calculations Class</h1>*/
public class Num{
  /**
  @param floor The base of the random range, inclusive
  @param cel The top of the random range, exclusive
  @return A random <code>float</code> between <code>floor</code> and <code>cel</code>, exclusive.
  @since 1.4
  */
  public static float random(float floor,float cel){
    return ((cel-floor)*(float)Math.random())+floor;
  }
  /**
  @param cel The top of the random range, exclusive
  @return A random <code>float</code> between 0 and <code>cel</code>, exclusive.
  @since 1.4
  */
  public static float random(float cel){
    return cel*(float)Math.random();
  }
  
  /**
  @param floor The base of the random range, inclusive
  @param cel The top of the random range, exclusive
  @return A random <code>double</code> between <code>floor</code> and <code>cel</code>, exclusive.
  @since 1.4
  */
  public static double random(double floor,double cel){
    return ((cel-floor)*(double)Math.random())+floor;
  }
  /**
  @param cel The top of the random range, exclusive
  @return A random <code>double</code> between 0 and <code>cel</code>, exclusive.
  @since 1.4
  */
  public static double random(double cel){
    return cel*(double)Math.random();
  }
  
  /**
  @param floor The base of the random range, inclusive
  @param cel The top of the random range, exclusive
  @return A random <code>int</code> between <code>floor</code> and <code>cel</code>, exclusive.
  @since 1.4
  */
  public static int random(int floor,int cel){
    return (int)((double)(cel-floor)*(double)Math.random()+(double)floor);
  }
  /**
  @param cel The top of the random range, exclusive
  @return A random <code>int</code> between 0 and <code>cel</code>, exclusive.
  @since 1.4
  */
  public static int random(int cel){
    return (int)((double)cel*(double)Math.random());
  }
  
  /**An extension of <code>Math.pow</code>
  @param base The numer to raise to an exponent
  @param pow The exponent
  @return <code>base</code> to the power of <code>pow</code>
  @since 1.4.0.1
  */
  public static long exp(double base,int pow){
    return (long)Math.pow((double)base,(double)pow);
  }
  
  /**Returns a <code>long</code> representation of Scientific Notation
  @param base The numer to multiply by ten to an exponent
  @param ttt The exponent
  @return <code>base</code> times ten to the power of <code>ttt</code>
  @since 1.4.0.1
  */
  public static long sci(double base,int ttt){
    return (long)(base) * exp(10,ttt);
  }
  
  /**Finds the greatest common denominator of two numbers
  @param a One of two numbers
  @param b One of two numbers
  @return The GCD of <code>a</code> and <code>b</code>
  @since 1.4.1.1
  */
  public static int GCD(int a, int b) { return b==0 ? a : GCD(b, a%b); }
  
  /**Squares a numbers
  @param base The number to Squares
  @return <code>base</code> squared
  @since 1.4.1.1
  */
  public static int square(int base){return base*base;}
  
  /** Decides whether two numbers are within a tolerable range of each other
  @param base The lower numbers
  @param target The higher numbers
  @param tol The tolerance
  @return <code>true</code> if <code>target</code> and <code>base</code> are within <code>tol</code> of each other, <code>false</code> otherwise.
  @since 1.4.2.1
  */
  public static boolean within(int base,int target,int tol){
    return Math.abs(target-base) <= tol;
  }
}
