package silverlib.test;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TestRunner {
    private final Class targetTestContainer;

    private static final Set<Class<?>> SIMPLE_WRAPPER_TYPES = getSimpleWrapperTypes();

    public TestRunner(Class targetTestContainer) {
        this.targetTestContainer = targetTestContainer;
    }

    public static boolean isSimple(Class<?> clazz) {
        return SIMPLE_WRAPPER_TYPES.contains(clazz);
    }

    private static Set<Class<?>> getSimpleWrapperTypes() {
        Set<Class<?>> ret = new HashSet<Class<?>>();
        ret.add(Boolean.class);
        ret.add(Character.class);
        ret.add(Byte.class);
        ret.add(Short.class);
        ret.add(Integer.class);
        ret.add(Long.class);
        ret.add(Float.class);
        ret.add(Double.class);
        ret.add(Void.class);
        ret.add(String.class);
        return ret;
    }

    @SuppressWarnings("unchecked")
    public void runTests() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Object instance = targetTestContainer.getDeclaredConstructor().newInstance();
        Field[] allFields = targetTestContainer.getDeclaredFields();
        Method[] allMethods = targetTestContainer.getDeclaredMethods();

        ArrayList<Method> allTestMethods = new ArrayList<>();

        for (Method m : allMethods) {
            if (m.getName().startsWith("test")) {
                allTestMethods.add(m);
                this.log("Found testing method: "+m.getName(), 0);
            }else {
                this.log("Discarding extraneous method: " + m.getName(), 0);
            }
        }

        this.log("Running Tests on "+targetTestContainer.getName()+" {",0);

        this.log("Enumerating Fields {", 1);

        this.enumerateFields(targetTestContainer, instance, 0);

        this.log("}\n",1);

        this.log("Running Test Methods {", 1);

        int totalFailiures = 0;

        for (Method m : allTestMethods) {
            String testName = m.getName().substring(4);

            Tester mTester = new Tester(testName);

            m.invoke(instance, mTester);

            this.log(TestRunner.formatToLevel(mTester.resultBreakdown(), 2),2);
            this.log("------------\n",2);

            totalFailiures += mTester.failCount();
        }

        this.log("} ["+totalFailiures+" Tests Failed]", 1);

        this.log("} [Testing Complete]", 0);
    }

    private void enumerateFields(Class cls, Object ins, int round) throws IllegalAccessException {
        Field[] allFields = cls.getDeclaredFields();
        this.log("["+allFields.length+" Fields]",2+round);

        for (Field f : allFields) {
            f.setAccessible(true);
            String fnm = f.getName();
            Object value = f.get(ins);

            if (value == null) {
                this.log(fnm+" : null",2+round);
            } else if (TestRunner.isSimple(value.getClass())) {
                this.log(fnm+" : "+value.toString(),2+round);
            } else {
                this.log(fnm+" : "+value.getClass().getName()+" {",2+round);

                if (round < 4) {
                    this.enumerateFields(value.getClass(), value, round+1);
                } else {
                    this.log("[Abbreviated] "+value.toString(),3+round);
                }

                this.log("}",2+round);
            }
        }
    }

    private void log(String message, int level) {
        String prefix = "";
        for (int i = 0; i < level; i++) {
            prefix += "\t";
        }

        System.out.println(prefix+message);
    }

    private static String formatToLevel(String inp, int level) {
        String prefix = "";
        for (int i = 0; i < level; i++) {
            prefix += "\t";
        }

        return inp.replaceAll("\n", "\n"+prefix);
    }
}
