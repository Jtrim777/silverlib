import java.io.*;
import java.reflect.*;

public class IO {
  public static String getPath(Object obj){
    Class c = obj.getClass();

    File curLoc = new File(Language.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		String loc = curLoc.getPath();

  }
}
