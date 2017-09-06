package silverlib.log;

import java.io.*;

public class Log {
  /**
  Prints a message to the console in a log format, specifying the object that sent the message.
  
  @param o The object sending a message
  @param msg The message to log
  @since 1.1
  */
  public static void log(Object o, String msg) {
    System.out.println("<<<"+o.toString()+" : "+msg+">>>");
  }
  
  /**
  Prints a message to the console in a log format.
  
  @param msg The message to log
  @since 1.1
  */
  public static void log(String msg) {
    System.out.println("<<<main : "+msg+">>>");
  }
  
  /**
  Prints a message to the console.
  
  @param msg The message to print
  @since 1.1
  */
  public static void print(String msg) {
    System.out.println(msg);
  }
  
  /**
  Indicates that a line nuber has been passed in some code. Useful for debugging.
  
  @param ln The line number
  @since 1.1.1
  */
  public static void mark(int ln) {
    System.out.println("<<<Passed marker on line "+ln+">>>");
  }
  
  /**
  Indicates that a line nuber has been passed in the code of some object. Useful for debugging.
  
  @param o The object in which the instruction occured
  @param msg The line number
  @since 1.1.1
  */
  public static void mark(Object o, int ln) {
    System.out.println("<<<Passed marker on line "+ln+" in class "+o.toString()+">>>");
  }

  /**
  Prints a message to a file with given <code>filename</code>.
  
  @param out The message to print
  @param filename The file name, including the .txt extension
  @since 1.2.1
  */
  public static void printToFile(String out,String filename){
    File curLoc = new File(Log.class.getProtectionDomain().getCodeSource().getLocation().getPath());
    String loc = curLoc.getPath();
    loc = loc.replaceAll("%20", " ");
    loc = loc.substring(0,loc.length()-5);
    try{
      PrintWriter o = new PrintWriter(loc+"data/"+filename);
      o.println(out);
      o.flush();
      o.close();
      //print("Debug Success");
    }catch(IOException e){
      print("Log Faliure");
      return;
    }
  }
}
