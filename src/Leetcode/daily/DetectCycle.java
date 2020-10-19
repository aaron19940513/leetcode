package Leetcode.daily;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 * 142. 环形链表 II
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * <p>
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
 * <p>
 * 说明：不允许修改给定的链表。
 * <p>
 * 进阶：
 * <p>
 * 你是否可以使用 O(1) 空间解决此题？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/linked-list-cycle-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 10/15/20 9:13
 */
public class DetectCycle {
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        ListNode listNode = head;
        while (listNode != null) {
            if (set.contains(listNode)) {
                return listNode;
            } else {
                set.add(listNode);
            }
            listNode = listNode.next;
        }
        return null;
    }

    // head节点到环的起始节点长度为a，环的长度为b
    // 第一次重合，fast比slow多跑了N个环的长度。fast = 2slow; fast = slow + nb, 两式相减得 slow=nb
    // 已知a+nb就是环的起点，让slow再走a步就可以找到环的起点，所以此时让fast回到head节点，和slow都一步一步向前走，第二次重合就是环的起点。
    public ListNode detectCycle1(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        //第一次重合
        while (true) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                break;
            }
        }
        fast = head;
        while (true) {
            if (fast == slow) {
                return slow;
            }
            fast = fast.next;
            slow = slow.next;
        }
    }

    @Test
    public void test() {
        ListNode listNode = new ListNode(0);
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        listNode.next = listNode1;
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode1;
        Assert.assertEquals(detectCycle(listNode), listNode1);
    }

    @Test
    public void test1() {
        ListNode listNode = new ListNode(0);
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        listNode.next = listNode1;
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode1;
        Assert.assertEquals(detectCycle1(listNode), listNode1);
    }
}
