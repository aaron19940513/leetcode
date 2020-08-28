package Leetcode.thead;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

/**
 * @author Sam Gao
 * @date 08/14/20 11:03
 */
public class TheadPoolExecutorTest {
    public static void main(String[] args) throws InterruptedException, IOException {
        int corePoolSize = 2;
        int maximumPoolSize = 4;
        long keepAliveTime = 10;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
        ThreadFactory threadFactory = new NameTreadFactory();
        RejectedExecutionHandler handler = new MyIgnorePolicy();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
                                                             workQueue, threadFactory, handler);
        //executor.prestartAllCoreThreads(); // 预启动所有核心线程

        for (int i = 1; i <= 10; i++) {
            MyTask task = new MyTask(String.valueOf(i));
            executor.execute(task);
        }

        System.in.read(); //阻塞主线程
    }

    static class NameTreadFactory implements ThreadFactory {

        private final AtomicInteger mThreadNum = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "my-thread-" + mThreadNum.getAndIncrement());
            System.out.println(t.getName() + " has been created");
            return t;
        }
    }

    public static class MyIgnorePolicy implements RejectedExecutionHandler {

        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            doLog(r, e);
        }

        private void doLog(Runnable r, ThreadPoolExecutor e) {
            // 可做日志记录等
            System.err.println(r.toString() + " rejected");
            //          System.out.println("completedTaskCount: " + e.getCompletedTaskCount());
        }
    }

    static class MyTask implements Runnable {
        private String name;

        public MyTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                System.out.println(this.toString() + " is running!");
                Thread.sleep(3000); //让任务执行慢点
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "MyTask [name=" + name + "]";
        }
    }

    @Test
    public void test() {
        System.out.println(Integer.toBinaryString(-1 << (Integer.SIZE - 3)));
    }


    @Test
    public void testFinally() {
        try {
            while (true) {
                try {
                    beforeExecute();
                    Throwable thrown = null;
                    try {
                        System.out.println("do something");
                        int a = 1 / 0;
                    } catch (RuntimeException x) {
                        thrown = x;
                        //throw x;
                    } catch (Error x) {
                        thrown = x;
                        //throw x;
                    } catch (Throwable x) {
                        thrown = x;
                        throw x;
                    } finally {
                        afterExecute(thrown);
                    }
                } finally {
                    System.out.println("f");
                }
            }

        } finally {
            System.out.println("exit loop");
        }
    }

    private void afterExecute(Throwable thrown) {
        System.out.println("afterExecute");
    }

    private void beforeExecute() {
        System.out.println("beforeExecute");

    }

}
