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

  /**Creates a <code>Scene</code> object with a given width, height,
  background color, and predefined list of <code>Shape</code>s.
  @param w The width of the <code>Scene</code>
  @param h The height of the <code>Scene</code>
  @param back The background <code>Color</code> of the <code>Scene</code>
  @param l An <code>ArrayList</code> of <code>Shape</code> objects
  @since 1.7.2
  */
  public Scene(int w, int h,Color back,ArrayList<Shape> l){
    super(w,h,back);
    mainCol = back;

    layers = l;
  }

  public Scene next(String mods){
    if(mods==null){return this;}
    else{
      String[] parts = mods.split(":");
      String[] toEditS = parts[0].split(",");
      int[] toEdit = new int[toEditS.length];
      for(int i=0;i<toEdit.length;i++){
        toEdit[i] = Integer.parseInt(toEditS[i]);
      }
      String[] modParts = parts[1].split("/");
      String[] props = new String[modParts.length];
      int[] vals = new int[modParts.length];

      for(int i=0;i<modParts.length;i++){
        String[] pp = modParts[i].split(",");
        props[i] = pp[0];
        vals[i] = Integer.parseInt(pp[1]);
      }

      for(int i=0;i<toEdit.length;i++){
        layers.get(toEdit[i]).setProp(props[i],vals[i]);
      }

      return this;
    }
  }

  public Img getImg(){
    for(Shape s:layers){
      s.draw(this);
    }

    return (Img)this;
  }
}
