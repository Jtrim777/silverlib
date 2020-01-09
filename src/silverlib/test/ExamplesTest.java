//package silverlib.test;
//
//import silverlib.geo.Point;
//
//import java.lang.reflect.InvocationTargetException;
//
//public class ExamplesTest {
//    int tryNum = 37;
//    double tryNum2 = 38.7;
//    String simpleString = "Hello World";
//    boolean tbool = false;
//    Point p = new Point(34, 78);
//
//    public ExamplesTest() { }
//
//    public void testSimple(Tester t) {
//        t.assertTrue(8+4, 11);
//        t.assertTrue("abc"+"efg", "abcefg");
//        t.assertTrue((false && false) || true, true);
//    }
//
//    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException,
//            InstantiationException, IllegalAccessException {
//        TestRunner tester = new TestRunner(ExamplesTest.class);
//
//        tester.runTests();
//    }
//}
