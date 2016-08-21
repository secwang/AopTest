/**
 * Created by secwang on 8/12/16.
 */
public class ServiceProvider {
    @TX
    public int addTwo(int num1, int num2) {

        int num = num1 + num2;
        System.out.println(num);
        System.out.println(TransactionManager.getTransactionId());
        return num;

    }
}
