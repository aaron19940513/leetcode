package leetcode;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author Sam Gao
 * @date 12/02/20 17:02
 */
public class Test {
    private static ThreadLocal<String> name = new ThreadLocal<>();

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(5);//定义1个许可证，也就是说服务器只允许1个人在里面玩
        for (int i = 1; i <=5; i++) {
            final int index = i;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();//获取一个许可证
                        name = new ThreadLocal<>();
                        name.set(String.valueOf(index));
                        System.out.println(Thread.currentThread().getName() + "set    " + index);
                        System.out.println(Thread.currentThread().getName() + "get    " + name.get());
                        semaphore.release();//执行完成后释放这个许可证
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            });
        }
        threadPool.shutdown();
    }
}
