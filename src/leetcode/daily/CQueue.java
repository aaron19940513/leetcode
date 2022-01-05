package leetcode.daily;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

/**
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * ["CQueue","appendTail","deleteHead","deleteHead"]
 * [[],[3],[],[]]
 * 输出：[null,null,3,-1]
 * 示例 2：
 * <p>
 * 输入：
 * ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
 * [[],[],[5],[2],[],[]]
 * 输出：[null,-1,null,null,5,2]
 * 提示：
 * <p>
 * 1 <= values <= 10000
 * 最多会对 appendTail、deleteHead 进行 10000 次调用
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 06/30/20 13:38
 */
public class CQueue {
    Stack<Integer> tail;
    Stack<Integer> head;

    public CQueue() {
        tail = new Stack<>();
        head = new Stack<>();
    }

    public void appendTail(int value) {
        tail.push(value);
    }

    public int deleteHead() {
        if (head.isEmpty() && tail.isEmpty()) {
            return -1;
        }
        if (!head.isEmpty()) {
            return head.pop();
        } else {
            while (!tail.isEmpty()) {
                head.push(tail.pop());
            }
            return head.pop();
        }
    }

    @Test
    public void test() {
        CQueue obj = new CQueue();
        Assert.assertEquals(-1, obj.deleteHead());
        obj.appendTail(1);
        obj.appendTail(2);
        obj.appendTail(3);
        Assert.assertEquals(1, obj.deleteHead());
        Assert.assertEquals(2, obj.deleteHead());
        obj.appendTail(4);
        Assert.assertEquals(3, obj.deleteHead());
        Assert.assertEquals(4, obj.deleteHead());
        Assert.assertEquals(-1, obj.deleteHead());
    }
}
