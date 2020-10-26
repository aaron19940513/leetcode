package Leetcode.thead;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author Sam Gao
 * @date 08/31/20 16:32
 */
public class SemaphoreSample {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(1);//定义1个许可证，也就是说服务器只允许1个人在里面玩
        for (int i = 1; i <= 3; i++) {
            final int index = i;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();//获取一个许可证
                        play();
                        semaphore.release();//执行完成后释放这个许可证
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            });
        }
        threadPool.shutdown();
    }

    public static void play() {

        try {
            System.out.println(new Date() + " " + Thread.currentThread().getName() + ":获得服务器进入资格");
            Thread.sleep(2000);
            System.out.println(new Date() + " " + Thread.currentThread().getName() + ":退出服务器");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
