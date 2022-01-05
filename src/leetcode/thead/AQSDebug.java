package leetcode.thead;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Sam Gao
 * @date 07/27/20 15:08
 */
public class AQSDebug {
    private Lock lock = new ReentrantLock();

    private void sayHello() {
        lock.lock();
        System.out.println("lock"+Thread.currentThread().getName());
        lock.unlock();
        System.out.println("unlock"+Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        AQSDebug aqsDebug = new AQSDebug();
        new Thread(aqsDebug::sayHello, "first").start();
        new Thread(aqsDebug::sayHello, "second").start();
        new Thread(aqsDebug::sayHello, "third").start();
    }

}
