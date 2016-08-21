import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;


/**
 * Created by secwang on 8/12/16.
 */

@Aspect
public class MethodCracker {

    @Around("execution(* *(..)) && @annotation(TX)")
    public Object around(ProceedingJoinPoint point) throws Throwable {


        System.out.println("aop work");
        TransactionManager.startTransaction();

        Object result = point.proceed();

        TransactionManager.endTransaction();


        saveArgs(point);

        saveParamsType(point);

        return result;
    }

    private void saveParamsType(ProceedingJoinPoint point) throws FileNotFoundException {
        Kryo kryo = new Kryo();
        Output output2 = new Output(new FileOutputStream("types.bin"));
        Class[] types = MethodSignature.class.cast(point.getSignature()).getParameterTypes();
        kryo.writeClassAndObject(output2, types);
        output2.close();
    }

    private void saveArgs(ProceedingJoinPoint point) throws FileNotFoundException {
        Kryo kryo = new Kryo();
        Output output = new Output(new FileOutputStream("file.bin"));
        Object[] vars = point.getArgs();
        kryo.writeObject(output, vars);
        output.close();
    }

}
