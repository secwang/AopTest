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
        Kryo kryo = new Kryo();
        Kryo kryo1 = new Kryo();

        Input input = new Input(new FileInputStream("file.bin"));
        Input input1 = new Input(new FileInputStream("types.bin"));

        java.lang.Object[] vars= kryo.readObject(input, java.lang.Object[].class);
        input.close();
        Class[] types = (Class[]) kryo1.readClassAndObject(input1);
        input1.close();

        System.out.println(vars);
        System.out.println(types);
        ServiceProvider serviceProvider = new ServiceProvider();
        Method m = serviceProvider.getClass().getMethod("addTwo", types);
        System.out.println(m);
        m.invoke(serviceProvider,vars);

//
//        Method method = ServiceProvider.class.getMethod("addTwo");

//        String methodName = "addTwo";
//        String valueObject = "ServiceProvider";
//
//        Method m = valueObject.getClass().getMethod(methodName, new Class[] {});
//        m.invoke(valueObject,vars);

//      Method m = "a".getClass().getMethod("b", new Class[] {});

//        ServiceProvider serviceProvider = new ServiceProvider();
//        serviceProvider.addTwo(1,2);
//        System.out.println("In main");


    }

}
