package silverlib.img;

import silverlib.log.*;
import silverlib.geo.*;
import java.util.*;
import java.awt.Color;


/**A scene in a <code>Movie</code> that represents an <code>Img</code> object
with multiple layers of <code>Shape</code> objects.
@since 1.7
@author Jake Trimble
*/
public class Scene extends Img{
  private ArrayList<Shape> layers;
  private Color mainCol;
  
  /**Creates a <code>Scene</code> object with a given width, height, and
  background color.
  @param w The width of the <code>Scene</code>
  @param h The height of the <code>Scene</code>
  @param back The background <code>Color</code> of the <code>Scene</code>
  @since 1.7
  */
  public Scene(int w, int h,Color back){
    super(w,h,back);
    mainCol = back;
    
    layers = new ArrayList<Shape>();
  }
  
  // public Scene next(String mods){
  //   if(mods==null){return this;}
  //   else{
  //     
  //   }
  // }
}
