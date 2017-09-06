package silverlib.log;
import java.util.*;
import java.io.*;

/**
*An instantiable class to create a log file for debug or recording purposes. 
*/
public class Logger{
  /**
  *The name of the log file
  */
  private String name;
  /**
  *The location of the log file
  */
  private String loc;
  /**
  *A brief description of the log
  */
  private String logPurpose;
  /**
  *The time at each log
  */
  private String timeStamp;
  /**
  *The number of lines in the file
  */
  private int lineCount;
  /**
  *The output
  */
  private PrintWriter out;
  
  /**
  *Main class constructor. 
  *@param n The name of the log file
  *@param l The file location
  @since 1.2
  @throws IOException
  */
  public Logger(String n,String l) throws IOException{
    name = n;
    loc = l;
    logPurpose = "";
    lineCount = 1;
    timeStamp = timeStampLoadL();
    try{
      out = new PrintWriter(loc+name+"-"+timeStamp+".txt");
      out.println("^^Log init:"+timeStamp+">>");
    }catch(IOException e){
      out = null;
    }
    timeStamp = timeStampLoad();
  }
  
  /**
  *Class constructor with purpose. 
  *@param n The name of the log file
  *@param l The file location
  *@param p The purpose of the log file
  @since 1.2
  */
  public Logger(String n,String l,String p){
    name = n;
    loc = l;
    logPurpose = p;
    lineCount = 1;
    timeStamp = timeStampLoadL();
    try{
      out = new PrintWriter(loc+name+"-"+timeStamp+".txt");
      out.println(name+"\n"+logPurpose+"\n");
      out.println("^^Log init:"+timeStamp+">>");
    }catch(IOException e){
      out = null;
    }
    timeStamp = timeStampLoad();
  }
  
  /**
  *Generates an hr:min:sec timestamp. 
  *@return A string representaion of the local time
  @since 1.2
  */
  public String timeStampLoad(){
    Date temp = new Date();
    return temp.toString();
  }
  /**
  *Generates a timestamp with date and time 
  *@return A string representaion of the local time and date
  @since 1.2
  */
  public String timeStampLoadL(){
    Date temp = new Date();
    return temp.toString();
  }
  
  /**
  *Logs a message to the file with a timestamp. 
  @param message The message to log
  @since 1.2
  */
  public void log(String message){
    timeStamp = timeStampLoad();
    out.println(timeStamp+":<"+message+">");
  }
  
  /**
  *Logs an error to the file with a timestamp. 
  @param e The exception to report
  @since 1.2
  */
  public void error(Exception e){
    timeStamp = timeStampLoad();
    StackTraceElement[] stacks = e.getStackTrace();
    String errorOut = "";
    for(StackTraceElement i:stacks){
      errorOut += "  "+i.toString()+"\n";
    }
    
    out.println(timeStamp+":!Error!"+errorOut);
  }

  /**
  *Prints final log info and closes the PrintWriter. 
  @since 1.2
  */
  public void close(){
    out.println("<<< Log closed at "+timeStampLoad()+" with "+lineCount+" lines of output >>>");
    
    out.flush();
    out.close();
    Log.print("<<Log "+name+" saved>>");
  }
}
