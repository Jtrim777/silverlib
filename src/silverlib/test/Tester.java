package silverlib.test;

import java.util.ArrayList;

public class Tester {
    private ArrayList<Tester.Test> results = new ArrayList<>();
    private String testName;

    public Tester(String testName) {
        this.testName = testName;
    }

    @SuppressWarnings("unchecked")
    public <E extends Comparable> boolean assertTrue(E statement, E expResult) {
        boolean testResult = statement.equals(expResult);
        results.add(new Test(testResult, statement, expResult));

        return testResult;
    }

    public boolean finalResult() {
        boolean rez = true;
        for (Test b : results) {
            if (!b.result) {rez = false;}
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
            if (!cr.result) {
                out += "> [Test "+i+"] Failed: Expected "+cr.expected.toString()+
                        "; Found "+cr.actual.toString()+"\n";
            }
        }

        out += "\n------------\n"+failCount()+" TESTS FAILED";

        return out;
    }

    public int failCount() {
        int rez = 0;
        for (Test t : results) {
            if (!t.result) {
                rez++;
            }
        }

        return rez;
    }

    private class Test<E extends Comparable> {
        boolean result;
        E actual;
        E expected;

        Test(boolean result, E actual, E expected) {
            this.result = result;
            this.actual = actual;
            this.expected = expected;
        }
    }
}
