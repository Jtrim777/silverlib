package silverlib.log;

public class Err extends Log {

    /**
     * Prints an exception to the console in an error format, specifying the object that sent the message.
     *
     * @param o The object sending a message
     * @param e The exception to report
     *
     * @since 1.1.3
     */
    public static void log(Object o, Exception e) {
        System.out.println("!! >>Error in class " + o.toString() + ":\n");
        e.printStackTrace();
        System.out.print("  << !!");
    }

    /**
     * Prints an exception to the console in an error format, specifying the object that sent the message, and
     * the line on which the error occurred.
     *
     * @param o  The object sending a message
     * @param e  The exception to report
     * @param ln The line on which the error occurred
     *
     * @since 1.1.3
     */
    public static void log(Object o, Exception e, int ln) {
        System.out.println("!! >>Error in class " + o.toString() + " on line " + ln + ":\n");
        e.printStackTrace();
        System.out.print("  << !!");
    }

    /**
     * Prints an exception to the console in an error format.
     *
     * @param e The exception to report
     *
     * @since 1.1.3
     */
    public static void log(Exception e) {
        System.out.println("!! >>Error in main:\n");
        e.printStackTrace();
        System.out.print("  << !!");
    }

    /**
     * Prints an exception to the console in an error format, and the line on which
     * the error occurred.
     *
     * @param e  The exception to report
     * @param ln The line number on which the error occurred
     *
     * @since 1.1.3
     */
    public static void log(Exception e, int ln) {
        System.out.println("!! >>Error in main on line " + ln + ":\n");
        e.printStackTrace();
        System.out.print("  << !!");
    }

    /**
     * Prints a mesage to the console in an error format.
     *
     * @param msg The message to print
     *
     * @since 1.1.3
     */
    public static void log(String msg) {
        System.out.println("!! >>Error in main:\n ");
        System.out.print(msg);
        System.out.print("  << !!");
    }

    /**
     * Prints an exception to the console in an error format, along with an accompanying message.
     *
     * @param e   The exception to report
     * @param msg The message to print
     *
     * @since 1.1.3
     */
    public static void log(String msg, Exception e) {
        System.out.print("\n!! >>Error in main: " + msg + ":\n");
        e.printStackTrace();
        System.out.println("  << !!");
    }

    /**
     * Prints a message to the console in error format, along with the object and line in/on
     * which the error occurred.
     *
     * @param ln  The line number on which the error occurred.
     * @param msg The message to print
     * @param o   The object where the error occurred
     *
     * @since 1.1.3
     */
    public static void log(int ln, String msg, Object o) {
        System.out.println("!! >>Error in class " + o.toString() + " on line " + ln + ":\n ");
        System.out.print(msg);
        System.out.print("  << !!");
    }

    /**
     * Prints a message to the console in an error format, along with the object where the
     * error occurred.
     *
     * @param msg The message to print
     * @param o   The object where the error occurred
     *
     * @since 1.1.3
     */
    public static void log(String msg, Object o) {
        System.out.println("!! >>Error in class " + o.toString() + ":\n ");
        System.out.print(msg);
        System.out.print("  << !!");
    }

    /**
     * Prints a message to the console in an error format, and the line number on which
     * the error occurred.
     *
     * @param msg The message to print
     * @param ln  The line number on which the error occurred
     *
     * @since 1.1.3
     */
    public static void log(int ln, String msg) {
        System.out.println("!! >>Error in main on line " + ln + ":\n ");
        System.out.print(msg);
        System.out.print("  << !!");
    }
}
