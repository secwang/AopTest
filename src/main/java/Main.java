import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import org.omg.CORBA.Object;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by secwang on 8/12/16.
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        testAOP();


    }

    private static void testAOP() throws FileNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        java.lang.Object[] vars = getObjects();

        Class[] types = getClasses();


        callAddTwo(vars, types);


        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.addTwo(1,2);
        System.out.println("In main");
    }

    private static void callAddTwo(java.lang.Object[] vars, Class[] types) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        ServiceProvider serviceProvider = new ServiceProvider();
        Method m = serviceProvider.getClass().getMethod("addTwo", types);
        System.out.println(m);
        m.invoke(serviceProvider,vars);
    }

    private static Class[] getClasses() throws FileNotFoundException {
        Kryo kryo1 = new Kryo();

        Input input1 = new Input(new FileInputStream("types.bin"));

        Class[] types = (Class[]) kryo1.readClassAndObject(input1);
        input1.close();
        System.out.println(types);
        return types;
    }

    private static java.lang.Object[] getObjects() throws FileNotFoundException {
        Kryo kryo = new Kryo();
        Input input = new Input(new FileInputStream("file.bin"));

        java.lang.Object[] vars= kryo.readObject(input, java.lang.Object[].class);
        input.close();
        System.out.println(vars);
        return vars;
    }

}
