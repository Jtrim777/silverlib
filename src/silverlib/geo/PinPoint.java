package silverlib.geo;

/** <h1> Basic PinPoint Class </h1>
A class that represents a PinPoint in a 2D coordinate plane.
*/
public class PinPoint{
  double x;
  double y;
  
  /** Initializes a <code>PinPoint</code> object from two integer x and y values.
  
  @param a The x-value
  @param b The y-value
  @since 1.4
  */
  public PinPoint(double a, double b){
    x=a;
    y=b;
  }
  
  /** Initializes a <code>PinPoint</code> from a <code>String</code> in the format
      <code>(x,y)</code>
      @param s A <code>String</code> representation of the <code>PinPoint</code> in the form <code>(x,y)</code>
      @since 1.8.2
      @throws ImproperFormatException If <code>s</code> does not match <code>(x,y)</code>
  */
  public PinPoint(String s) throws ImproperFormatException{
    if(s.matches("^[(]\\d+[,]\\d+[)]")){
      String in = s.substring(1,s.length()-1);
      String[] pts = in.split(",");
      x = Double.parseDouble(pts[0]);
      y = Double.parseDouble(pts[1]);
    }else{
      throw new ImproperFormatException(s);
    }
  }
  
  /** Returns the x-coordinate of the <code>PinPoint</code>
  @return <code>x</code>
  @since 1.4
  */
  public double x(){
    return x;
  }
  /** Returns the y-coordinate of the <code>PinPoint</code>
  @return <code>y</code>
  @since 1.4
  */
  public double y(){
    return y;
  }
  
  /** Returns the a String representation of the <code>PinPoint</code>, in the 
  form <code>(x,y)</code>.
  @return String rep of this <code>PinPoint</code>
  @since 1.4.2
  */
  public String toString(){
    return "("+x+","+y+")";
  }
}
