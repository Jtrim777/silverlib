package silverlib.img;
import java.io.*;
import silverlib.log.*;

/**Thrown when a <code>Shape</code> that a <code>Scene</code> or <code>Movie</code> does not possess is accessed
  @since 1.7.4
  @author Jake Trimble
*/
class NoSuchShapeException extends Exception{
  String shapeName;
  
  /**Initializes a <code>NoSuchShapeException</code>
    @param sn The name of the <code>Shape</code>
    @since 1.7.4
  */
  NoSuchShapeException(String sn){
    shapeName = sn;
  }
} 
