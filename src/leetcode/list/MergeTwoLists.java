package leetcode.list;
import org.junit.Assert;
import org.junit.Test;

/**
 * 21. 合并两个有序链表 简单
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 08/19/20 13:51
 */
public class MergeTwoLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        ListNode head = l1.val < l2.val ? l1 : l2;
        ListNode pre = null;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                while (l1 != null && l1.val < l2.val) {
                    pre = l1;
                    l1 = l1.next;
                }
                pre.next = l2;
            } else {
                while (l2 != null && l1.val >= l2.val) {
                    pre = l2;
                    l2 = l2.next;
                }
                pre.next = l1;
            }
        }
        return head;
    }

    @Test
    public void test() {
        int[] array = new int[] {1, 3, 5, 7, 9};
        ListNode head = getListNode(array);

        int[] array1 = new int[] {2, 4, 6, 8, 10};
        ListNode head1 = getListNode(array1);
        ListNode listNode2 = mergeTwoLists(head, head1);
    }

    @Test
    public void test1() {
        int[] array = new int[] {1, 2, 3, 4, 8};
        ListNode head = getListNode(array);

        int[] array1 = new int[] {5, 6, 7, 9};
        ListNode head1 = getListNode(array1);
        ListNode listNode2 = mergeTwoLists(head, head1);
    }

    @Test
    public void test2() {
        int[] array = new int[] {1, 3, 5, 7, 9};
        ListNode head = getListNode(array);
        Assert.assertNull(mergeTwoLists(null, null));
        Assert.assertEquals(head, mergeTwoLists(head, null));
        Assert.assertEquals(head, mergeTwoLists(null, head));
    }

    private ListNode getListNode(int[] array) {
        ListNode head = new ListNode(array[0]);
        ListNode listNode = head;
        for (int i = 1; i < array.length; i++) {
            ListNode temp = new ListNode(array[i]);
            listNode.next = temp;
            listNode = temp;
        }
        return head;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}