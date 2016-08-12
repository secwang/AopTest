/**
 * Created by secwang on 8/12/16.
 */
public class ServiceProvider {
    @TX
    public int addTwo(int num1, int num2){
      return num1 + num2;
    }
}
