package silverlib.test;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class Tester {
    private ArrayList<Tester.Test> results = new ArrayList<>();
    private String testName;

    public Tester(String testName) {
        this.testName = testName;
    }

    @SuppressWarnings("unchecked")
    public <E extends Comparable> boolean assertEquals(E statement, E expResult) {
        boolean testResult = statement.equals(expResult);
        results.add(new EqualityTest(testResult, statement, expResult));

        return testResult;
    }

    @SuppressWarnings("unchecked")
    public <E extends Comparable> boolean assertWithin(E statement, E expResult,
                                                              double tol) {

        if (!(statement instanceof Number)) {
            results.add(new FormatFailiure("`assertWithin` expects a number; given "+
                    statement.getClass().getName()));
            return false;
        }

        double st = (double)statement;
        double exp = (double)expResult;

        boolean testResult = Math.abs(st-exp) <= tol;

        results.add(new EqualityTest(testResult, statement, expResult));

        return testResult;
    }

    public boolean assertNull(Object statement) {
        boolean testResult = statement == null;

        results.add(new NullTest(testResult, statement));

        return testResult;
    }

    public <T> boolean assertPasses(Object caller, Method m, Object... args) {
        try {
            Object ignore = m.invoke(caller, args);
        } catch (Exception e) {
            Test rez = new SuccessTest(false, e);
            results.add(rez);
            return false;
        }

        Test rez = new SuccessTest(true, null);
        results.add(rez);
        return true;
    }

    public boolean finalResult() {
        boolean rez = true;
        for (Test b : results) {
            if (!b.result()) {rez = false;}
        }

        return rez;
    }

    public String resultBreakdown() {
        String out = this.testName + " Tests:\n";

        if (finalResult()) {
            out += "All tests passed!";
            return out;
        }

        for (int i = 0; i < results.size(); i++) {
            Test cr = results.get(i);
            if (!cr.result()) {
                if (cr instanceof EqualityTest) {
                    EqualityTest crcd = (EqualityTest)cr;
                    out += "> [Test "+i+"] Failed: Expected "+crcd.expected.toString()+
                            "; Found "+crcd.actual.toString()+"\n";
                } else if (cr instanceof SuccessTest) {
                    SuccessTest crcd = (SuccessTest)cr;
                    out += "> [Test "+i+"] Failed: Exception {"+
                            crcd.error.getLocalizedMessage()+
                            "} given on method call\n";
                } else if (cr instanceof NullTest) {
                    NullTest crcd = (NullTest) cr;
                    out += "> [Test "+i+"] Failed: Expected null; Found"+
                            crcd.actual.toString()+"\n";
                } else if (cr instanceof FormatFailiure) {
                    FormatFailiure crcd = (FormatFailiure)cr;
                    out += "> [Test "+i+"] Failed: "+crcd.message+"\n";
                }
            }
        }

        out += "\n------------\n"+failCount()+" TESTS FAILED";

        return out;
    }

    public int failCount() {
        int rez = 0;
        for (Test t : results) {
            if (!t.result()) {
                rez++;
            }
        }

        return rez;
    }

    private interface Test {
        boolean result();
    }

    private class EqualityTest<E extends Comparable> implements Test {
        boolean result;
        E actual;
        E expected;

        EqualityTest(boolean result, E actual, E expected) {
            this.result = result;
            this.actual = actual;
            this.expected = expected;
        }

        @Override
        public boolean result() {
            return result;
        }
    }

    private class SuccessTest implements Test {
        boolean result;
        Exception error;

        public SuccessTest(boolean result, Exception error) {
            this.result = result;
            this.error = error;
        }

        @Override
        public boolean result() {
            return result;
        }
    }

    private class FormatFailiure implements Test {
        String message;

        public FormatFailiure(String message) {
            this.message = message;
        }

        @Override
        public boolean result() {
            return false;
        }
    }

    private class NullTest implements Test {
        boolean result;
        Object actual;

        public NullTest(boolean result, Object actual) {
            this.result = result;
            this.actual = actual;
        }

        @Override
        public boolean result() {
            return result;
        }
    }
}
