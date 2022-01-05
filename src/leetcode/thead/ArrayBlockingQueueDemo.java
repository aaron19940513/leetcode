package leetcode.thead;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Sam Gao
 * @date 2021/4/17 8:06
 */
public class ArrayBlockingQueueDemo {

    private ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(2,true);

    private AtomicInteger i = new AtomicInteger();

    private void product() {
        int andIncrement = i.getAndIncrement();
        blockingQueue.offer(andIncrement);
        System.out.println("product " + andIncrement + Thread.currentThread().getName());
    }

    private void consumer() {
        try {
            Integer take = blockingQueue.take();
            System.out.println("consumer " + take + Thread.currentThread().getName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    public static void main(String[] args) {
        ArrayBlockingQueueDemo arrayBlockingQueueDemo = new ArrayBlockingQueueDemo();
        new Thread(arrayBlockingQueueDemo::consumer, "take first").start();
        new Thread(arrayBlockingQueueDemo::consumer, "take second").start();
        new Thread(arrayBlockingQueueDemo::consumer, "take third").start();
        new Thread(arrayBlockingQueueDemo::product, "offer first").start();
        new Thread(arrayBlockingQueueDemo::product, "offer second").start();
        new Thread(arrayBlockingQueueDemo::product, "offer third").start();
    }
}
