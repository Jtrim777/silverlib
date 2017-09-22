package silverlib.img;

import silverlib.log.*;
import silverlib.geo.*;
import java.util.*;
import java.awt.Color;

/**A series of <code>Scene</code>s where the contained <code>Shape</code>s are
modified between scenes. Outputs a .mov file
@since 1.7
@author Jake Trimble
*/
public class Movie{
  /**The current <code>Scene</code> in the <code>Movie</code> generation*/
  private Scene curScene;
  /**All of the frames in the <code>Movie</code>*/
  private ArrayList<Img> frames;
  /**All of the <code>Shape</code>s added to the <code>Movie</code>*/
  private ArrayList<Shape> shapes;
  /**The indexes for all of the names of the <code>Shape</code>s in the <code>Movie</code>*/
  private HashMap<String,Integer> shapeInds;
  /**The number of frames in the <code>Movie</code>*/
  private int numScenes;
  /**The name of the <code>Movie</code>*/
  private String name;
  /**The dimensions of the <code>Movie</code>'s frames*/
  private int width, height;
  /**Indicates if the init() method has been called yet*/
  private boolean initialized;
  
  /**Initializes a <code>Movie</code> object
    @param w The width of all of the <code>Movie</code>'s frames
    @param h The width of all of the <code>Movie</code>'s frames
    @since 1.7.3
  */
  public Movie(String n,int w, int h){
    frames = new ArrayList<Img>();
    shapes = new ArrayList<Shape>();
    shapeInds = new HashMap<String,Integer>();
    numScenes = 0;
    name = n;
    width = w;
    height = h;
    initialized = false;
  }
  
  /**Creates the first <code>Scene</code> in the <code>Movie</code>, sets the 
    <code>initialized</code> field to <code>true</code>, and initializes the <code>curScene</code> field.
    @param c The background color of the first <code>Scene</code>
    @since 1.7.3
  */
  public void init(Color c){
    initialized = true;
    Scene s1 = new Scene(width,height,c,shapes);
    Img f1 = s1.getImg();
    frames.add(f1);
    curScene = s1;
    numScenes ++;
  }
  
}
