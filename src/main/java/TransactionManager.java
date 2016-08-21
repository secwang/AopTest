import java.util.UUID;

/**
 * Created by secwang on 8/17/16.
 */
public class TransactionManager {
    private static ThreadLocal<String> context = new ThreadLocal<String>();

    public static void startTransaction() {
//logic to start a transaction
//...

        System.out.println("aop works");
        context.set(UUID.randomUUID().toString());
        System.out.println(context.get());
    }

    public static String getTransactionId() {
        return context.get();
    }

    public static void endTransaction() {
//logic to end a transaction
//…
        context.remove();
    }
}
