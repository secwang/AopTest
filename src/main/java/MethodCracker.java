import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.io.FileOutputStream;


/**
 * Created by secwang on 8/12/16.
 */

@Aspect
public class MethodCracker {

    @Around("execution(* *(..)) && @annotation(TX)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        System.out.println(MethodSignature.class.cast(point.getSignature()).getMethod().getName());
        System.out.println(point.getArgs());
        System.out.println(MethodSignature.class.cast(point.getSignature()).getParameterTypes());



        Object result = point.proceed();
        Kryo kryo = new Kryo();
//        Output output = new Output(new FileOutputStream("file.bin"));
        Output output2 = new Output(new FileOutputStream("types.bin"));
        Object[] vars = point.getArgs();
        Class[] types =MethodSignature.class.cast(point.getSignature()).getParameterTypes();
//        kryo.writeObject(output, vars);
        kryo.writeClassAndObject(output2,types);
//        output.close();
        output2.close();
        return result;
    }

}
