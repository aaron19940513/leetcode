package Leetcode.daily;
import org.junit.Assert;
import org.junit.Test;

/**
 * 24. 两两交换链表中的节点 中等
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 10/13/20 9:35
 */
public class SwapPairs {
    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode pre = head;
        ListNode post = pre.next;
        head = post != null ? post : head;
        ListNode postNext;
        ListNode postNextNext;
        while (pre != null && post != null) {
            postNext = post.next;
            postNextNext = postNext != null ? postNext.next : null;
            pre.next = postNextNext != null ? postNextNext : postNext;
            post.next = pre;
            pre = postNext;
            post = postNextNext;
        }
        return head;
    }

    @Test
    public void test() {
        Assert.assertNull(swapPairs(null));
    }

    @Test
    public void test1() {
        ListNode listNode = new ListNode(1);
        Assert.assertEquals(listNode, swapPairs(listNode));
    }

    @Test
    public void test2() {
        ListNode listNode = new ListNode(1);
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(3);
        ListNode listNode3 = new ListNode(4);
        listNode.next = listNode1;
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        ListNode listNode4 = swapPairs(listNode);
    }

    @Test
    public void test3() {
        ListNode listNode = new ListNode(1);
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(3);
        ListNode listNode3 = new ListNode(4);
        ListNode listNode4 = new ListNode(5);
        ListNode listNode5 = new ListNode(6);
        ListNode listNode6 = new ListNode(7);
        listNode.next = listNode1;
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        ListNode ans = swapPairs(listNode);
    }
}
