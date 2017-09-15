package silverlib.io;

import java.io.*;
import silverlib.log.*;
//import java.reflect.*;

public class IO {
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

  public static String readFile(String f) throws IOException{

			FileInputStream in = new FileInputStream(f);
			String contents = "";

			while(in.available() > 0){
				contents += (char) in.read();
			}
			in.close();

			return contents;

  }

  public static String[] readLines(String f) throws IOException{

			FileInputStream in = new FileInputStream(f);
			String contents = "";

			while(in.available() > 0){
				contents += (char) in.read();
			}
			in.close();

			return contents.split("\n");

  }
  
  public static String runScript(File script,String... args) throws IOException{
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
