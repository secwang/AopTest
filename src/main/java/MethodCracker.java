import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;


/**
 * Created by secwang on 8/12/16.
 */

@Aspect
public class MethodCracker {

    @Around("execution(* *(..)) && @annotation(TX)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        System.out.println(MethodSignature.class.cast(point.getSignature()).getMethod().getName());
        System.out.println(point.getArgs());
        Object result = point.proceed();
        return result;
    }

}
