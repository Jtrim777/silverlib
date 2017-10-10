package silverlib.geo;
import java.io.*;
import silverlib.log.*;

/**Thrown when a <code>Point</code> is initialized using a <code>String</code> with an improper format
  @since 1.7.4
  @author Jake Trimble
*/
public class ImproperFormatException extends Exception{
  String format;
  
  /**Initializes a <code>ImproperFormatException</code>
    @param sn The name of the property
    @since 1.7.4
  */
  ImproperFormatException(String sn){
    format = sn;
  }
} 
