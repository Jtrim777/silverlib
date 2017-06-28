package silverlib.log;

public class LogErr extends Log{
  
  public static void err(Object o, Exception e) {
    System.out.println("!! >>Error in class "+o.toString()+":\n");
    e.printStackTrace();
    System.out.print("  << !!");
  }

  public static void err(Object o, Exception e, int ln) {
    System.out.println("!! >>Error in class "+o.toString()+" on line "+ln+":\n");
    e.printStackTrace();
    System.out.print("  << !!");
  }

  public static void err(Exception e) {
    System.out.println("!! >>Error in main:\n");
    e.printStackTrace();
    System.out.print("  << !!");
  }

  public static void err(Exception e, int ln) {
    System.out.println("!! >>Error in main on line "+ln+":\n");
    e.printStackTrace();
    System.out.print("  << !!");
  }

  public static void err(String msg) {
    System.out.println("!! >>Error in main:\n ");
    System.out.print(msg);
    System.out.print("  << !!");
  }

  public static void err(String msg,Exception e) {
    System.out.print("\n!! >>Error in main: "+msg+":\n");
    e.printStackTrace();
    System.out.println("  << !!");
  }

  public static void err(int ln, String msg, Object o) {
    System.out.println("!! >>Error in class "+o.toString() + " on line "+ln+":\n ");
    System.out.print(msg);
    System.out.print("  << !!");
  }

  public static void err(String msg, Object o) {
    System.out.println("!! >>Error in class "+o.toString() + ":\n ");
    System.out.print(msg);
    System.out.print("  << !!");
  }

  public static void err(int ln, String msg) {
    System.out.println("!! >>Error in main on line "+ln+":\n ");
    System.out.print(msg);
    System.out.print("  << !!");
  }
}
