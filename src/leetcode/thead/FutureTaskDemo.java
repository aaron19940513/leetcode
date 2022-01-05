package leetcode.thead;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Sam Gao
 * @date 03/22/21 14:53
 */
public class FutureTaskDemo {
    FutureTask<Integer> t1 = new FutureTask<Integer>(() -> {
        System.out.println(1123);
        return 2;
    });

    private void sayHello() {
        try {

            t1.get(1000, TimeUnit.MILLISECONDS);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {

        FutureTaskDemo futureTaskDemo = new FutureTaskDemo();
        new Thread(futureTaskDemo::sayHello, "first").start();
        new Thread(futureTaskDemo::sayHello, "second").start();
        new Thread(futureTaskDemo::sayHello, "third").start();
    }
}
