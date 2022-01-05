package leetcode.thead;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.junit.Test;

/**
 * 我们提供了一个类：
 * <p>
 * public class Foo {
 *   public void one() { print("one"); }
 *   public void two() { print("two"); }
 *   public void three() { print("three"); }
 * }
 * 三个不同的线程将会共用一个 Foo 实例。
 * <p>
 * 线程 A 将会调用 one() 方法
 * 线程 B 将会调用 two() 方法
 * 线程 C 将会调用 three() 方法
 * 请设计修改程序，以确保 two() 方法在 one() 方法之后被执行，three() 方法在 two() 方法之后被执行。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3]
 * 输出: "onetwothree"
 * 解释:
 * 有三个线程会被异步启动。
 * 输入 [1,2,3] 表示线程 A 将会调用 one() 方法，线程 B 将会调用 two() 方法，线程 C 将会调用 three() 方法。
 * 正确的输出是 "onetwothree"。
 * 示例 2:
 * <p>
 * 输入: [1,3,2]
 * 输出: "onetwothree"
 * 解释:
 * 输入 [1,3,2] 表示线程 A 将会调用 one() 方法，线程 B 将会调用 three() 方法，线程 C 将会调用 two() 方法。
 * 正确的输出是 "onetwothree"。
 *  
 * <p>
 * 注意:
 * <p>
 * 尽管输入中的数字似乎暗示了顺序，但是我们并不保证线程在操作系统中的调度顺序。
 * <p>
 * 你看到的输入格式主要是为了确保测试的全面性。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/print-in-order
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Foo {
    private volatile int flag = 1;

    public Foo() {

    }

    public void first(Runnable printFirst) {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        flag++;
    }

    public void second(Runnable printSecond) {
        while (flag != 2) {

        }
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        flag++;
    }

    public void third(Runnable printThird) {
        while (flag != 3) {

        }
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
        flag++;
    }

    @Test
    public void test() {
        List<CompletableFuture> futureList = new ArrayList<>();
        CompletableFuture first = CompletableFuture.runAsync(() -> {
            first(() -> System.out.println("first"));

        });
        futureList.add(first);
        CompletableFuture second = CompletableFuture.runAsync(() -> {
            third(() -> System.out.println("three"));

        });
        futureList.add(second);
        CompletableFuture third = CompletableFuture.runAsync(() -> {
            second(() -> System.out.println("second"));
        });
        futureList.add(third);

        CompletableFuture combinedFuture = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[] {}));
        combinedFuture.join();


    }

}



