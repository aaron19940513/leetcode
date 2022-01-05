package y2021m03;
import org.junit.Test;

/**
 * 82. 删除排序链表中的重复元素 II mid
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 * 示例 2:
 * <p>
 * 输入: 1->1->1->2->3
 * 输出: 2->3
 *
 * @date 03/25/21 8:27
 */
public class DeleteDuplicates {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode dumb = new ListNode();
        dumb.next = head;
        ListNode preNode = dumb;
        ListNode curNode = head;
        ListNode nextNode;
        while (curNode != null) {
            nextNode = curNode.next;
            if (nextNode != null && curNode.val == nextNode.val) {
                while (nextNode != null && nextNode.val == curNode.val) {
                    nextNode = nextNode.next;
                }
                preNode.next = nextNode;
            } else {
                preNode = curNode;
            }
            curNode = nextNode;
        }
        return dumb.next;
    }

    ListNode listNode = new ListNode(0);
    ListNode listNode1 = new ListNode(1);
    ListNode listNode2 = new ListNode(2);
    ListNode listNode21 = new ListNode(2);
    ListNode listNode3 = new ListNode(3);
    ListNode listNode31 = new ListNode(3);
    ListNode listNode4 = new ListNode(4);
    ListNode listNode5 = new ListNode(5);
    ListNode listNode51 = new ListNode(5);

    @Test
    public void test() {
        listNode.next = listNode1;
        listNode1.next = listNode2;
        listNode2.next = listNode21;
        listNode21.next = listNode3;
        listNode3.next = listNode31;
        listNode31.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode51;
        ListNode listNode = deleteDuplicates(this.listNode);
    }

    @Test
    public void test1() {
        listNode2.next = listNode21;
        listNode21.next = listNode3;
        listNode3.next = listNode31;
        listNode31.next = null;
        ListNode listNode = deleteDuplicates(listNode2);
    }

    public class ListNode {
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

}


