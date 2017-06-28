package silverlib.log;

public class Err extends Log{
  
  public static void log(Object o, Exception e) {
    System.out.println("!! >>Error in class "+o.toString()+":\n");
    e.printStackTrace();
    System.out.print("  << !!");
  }

  public static void log(Object o, Exception e, int ln) {
    System.out.println("!! >>Error in class "+o.toString()+" on line "+ln+":\n");
    e.printStackTrace();
    System.out.print("  << !!");
  }

  public static void log(Exception e) {
    System.out.println("!! >>Error in main:\n");
    e.printStackTrace();
    System.out.print("  << !!");
  }

  public static void log(Exception e, int ln) {
    System.out.println("!! >>Error in main on line "+ln+":\n");
    e.printStackTrace();
    System.out.print("  << !!");
  }

  public static void log(String msg) {
    System.out.println("!! >>Error in main:\n ");
    System.out.print(msg);
    System.out.print("  << !!");
  }

  public static void log(String msg,Exception e) {
    System.out.print("\n!! >>Error in main: "+msg+":\n");
    e.printStackTrace();
    System.out.println("  << !!");
  }

  public static void log(int ln, String msg, Object o) {
    System.out.println("!! >>Error in class "+o.toString() + " on line "+ln+":\n ");
    System.out.print(msg);
    System.out.print("  << !!");
  }

  public static void log(String msg, Object o) {
    System.out.println("!! >>Error in class "+o.toString() + ":\n ");
    System.out.print(msg);
    System.out.print("  << !!");
  }

  public static void log(int ln, String msg) {
    System.out.println("!! >>Error in main on line "+ln+":\n ");
    System.out.print(msg);
    System.out.print("  << !!");
  }
}
