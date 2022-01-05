package leetcode.daily;
import org.junit.Test;

/**
 * 143. 重排链表  中
 * <p>
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 示例 1:
 * <p>
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * 示例 2:
 * <p>
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 *
 * @date 10/20/20 8:58
 */
public class ReorderList {
    public void reorderList(ListNode head) {
        //get revertIndex
        int size = 0;
        ListNode cur = head;
        while (cur != null) {
            cur = cur.next;
            size++;
        }
        if (size < 3) {
            return;
        }

        // revert List from revertIndex
        int revertIndex = (size + 1) / 2;
        cur = head;
        ListNode pre = null;
        ListNode temp;
        for (int i = 0; i < size; i++) {
            if (i <= revertIndex) {
                pre = cur;
                cur = cur.next;
                if (i == revertIndex - 1 || i == revertIndex) {
                    pre.next = null;
                }
                continue;
            }
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        // connect two list
        cur = head;
        ListNode temp1;
        for (int i = 0; i < revertIndex; i++) {
            if (pre == null || cur == null) {
                break;
            }
            temp = cur.next;
            temp1 = pre.next;
            cur.next = pre;
            pre.next = temp;
            cur = temp;
            pre = temp1;
        }
    }

    @Test
    public void test() {
        ListNode listNode = new ListNode(0);
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        ListNode listNode6 = new ListNode(6);
        ListNode listNode7 = new ListNode(7);
        listNode.next = listNode1;
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        listNode6.next = listNode7;
        reorderList(listNode);
    }


    @Test
    public void test1() {
        ListNode listNode = new ListNode(0);
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        listNode.next = listNode1;
        listNode1.next = listNode2;
        reorderList(listNode);
    }
}
