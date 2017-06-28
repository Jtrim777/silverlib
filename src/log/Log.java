package silverlib.log;

import java.io.*;

public class Log {
  public static void log(Object o, String msg) {
    System.out.println("<<<"+o.toString()+" : "+msg+">>>");
  }
  public static void log(String msg) {
    System.out.println("<<<main : "+msg+">>>");
  }

  public static void print(String msg) {
    System.out.println(msg);
  }
  
  public static void mark(int ln) {
    System.out.println("<<<Passed marker on line "+ln+">>>");
  }
  public static void mark(Object o, int ln) {
    System.out.println("<<<Passed marker on line "+ln+" in class "+o.toString()+">>>");
  }

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
      //print("Log Faliure");
      return;
    }
  }
}
