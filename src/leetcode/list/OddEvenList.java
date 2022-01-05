package leetcode.list;

/**
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 * <p>
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 1->3->5->2->4->NULL
 * 示例 2:
 * <p>
 * 输入: 2->1->3->5->6->4->7->NULL
 * 输出: 2->3->6->7->1->5->4->NULL
 */
public class OddEvenList {
    public ListNode oddEvenList(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }
        ListNode evenHeader = head.next;

        ListNode odd = head;
        ListNode even = head.next;
        ListNode current = evenHeader.next;
        int i = 1;
        while (current != null) {
            if (i % 2 != 0) {
                odd.next = current;
                odd = current;
            } else {
                even.next = current;
                even = current;
            }
            current = current.next;
            i++;
        }
        even.next = null;
        odd.next = evenHeader;
        return head;
    }

    public class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static void main(String[] args) {
        OddEvenList oddEvenList = new OddEvenList();
        OddEvenList.ListNode node = new OddEvenList().new ListNode(1);
        OddEvenList.ListNode node1 = new OddEvenList().new ListNode(2);
        OddEvenList.ListNode node2 = new OddEvenList().new ListNode(3);
        OddEvenList.ListNode node3 = new OddEvenList().new ListNode(4);
        OddEvenList.ListNode node4 = new OddEvenList().new ListNode(5);
        node.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        System.out.println(oddEvenList.oddEvenList(node));
    }
}
