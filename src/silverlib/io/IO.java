package silverlib.io;

import java.io.*;
import silverlib.log.*;
//import java.reflect.*;

/** Class for performing IO functions
@since 1.6
@author Jake Trimble
*/
public class IO {
  
  /** Gets the file path of a file given a source directory from a bash script
  @param fnm The name of the file, including any relative path differences from
   the source directory.
  @param script A bash script to run that echoes the source directory path
  @return The file path of <code>fnm</code>
  @since 1.6.3
  
  */ 
  public static String getPath(String fnm,File script) throws IOException{
    String[] command = {script.getAbsolutePath()};
    Process process = Runtime.getRuntime().exec(command);
    BufferedReader reader = new BufferedReader(new InputStreamReader(
        process.getInputStream()));
    String s;
    while ((s = reader.readLine()) != null) {
      return s+"/"+fnm;
    }
    return null;
  }
  
  /** Reads the <code>String</code> contents of a <code>File</code>.
  @param f The <code>File</code> to read from
  @return The <code>String</code> contents of <code>f</code>
  @since 1.6.3
  
  */ 
  public static String readFile(String f) throws IOException{

			FileInputStream in = new FileInputStream(f);
			String contents = "";

			while(in.available() > 0){
				contents += (char) in.read();
			}
			in.close();

			return contents;

  }

  /** Reads the <code>String</code> contents of a <code>File</code> and returns
  them as an array of lines.
  @param f The <code>File</code> to read from
  @return A <code>String[]</code> array of the lines of text in <code>File f</code>
  @since 1.6.3
  
  */ 
  public static String[] readLines(String f) throws IOException{

			FileInputStream in = new FileInputStream(f);
			String contents = "";

			while(in.available() > 0){
				contents += (char) in.read();
			}
			in.close();

			return contents.split("\n");

  }
  
  /** Runs a bash script and returns the result.
  @param script The <code>File</code> which represents the script to run
  @param args Arguments to pass to the bash script
  @return The result of running the bash script
  @since 1.6.4
  
  */ 
  public static String runScript(File script,String... args)throws IOException{
    String[] command = new String[args.length + 1];
    
    command[0] = script.getAbsolutePath();
    
    for(int i=1;i<command.length;i++){
      command[i] = args[i-1];
    }
    
    Process process = Runtime.getRuntime().exec(command);
    BufferedReader reader = new BufferedReader(new InputStreamReader(
        process.getInputStream()));
    String s;
    while ((s = reader.readLine()) != null) {
      return s;
    }
    return null;
  }
}
