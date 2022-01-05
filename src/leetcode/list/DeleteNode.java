package leetcode.list;
public class DeleteNode {
    public void deleteNode(ListNode node) {
        ListNode pre = null;
        ListNode current = node;
        ListNode next = node.next;
        while (next != null) {
            current.val = next.val;
            pre = current;
            current = next;
            next = next.next;
        }
        pre.next = null;
    }

    public class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
