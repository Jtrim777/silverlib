package silverlib.geo;

/** <h1> Basic Point Class </h1>
A class that represents a point in a 2D coordinate plane.
*/
public class Point{
  int x;
  int y;
  
  /** Initializes a <code>Point</code> object from two integer x and y values.
  
  @param a The x-value
  @param b The y-value
  @since 1.4
  */
  public Point(int a, int b){
    x=a;
    y=b;
  }
  
  /** Returns the x-coordinate of the <code>Point</code>
  @return <code>x</code>
  @since 1.4
  */
  public int x(){
    return x;
  }
  /** Returns the y-coordinate of the <code>Point</code>
  @return <code>y</code>
  @since 1.4
  */
  public int y(){
    return y;
  }
  
  /** Returns the a String representation of the <code>Point</code>, in the 
  form <code>(x,y)</code>.
  @return String rep of this <code>Point</code>
  @since 1.4.2
  */
  public String toString(){
    return "("+x+","+y+")";
  }
}
