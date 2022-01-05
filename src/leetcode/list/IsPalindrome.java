package leetcode.list;
public class IsPalindrome {

    int size = 0;
    int half = 0;

    public boolean isPalindrome(ListNode head) {
        ListNode tail = reverseHalf(head);
        while (half > 0) {
            if (head.val != tail.val) {
                return false;
            }
            head = head.next;
            tail = tail.next;
            half--;
        }
        return true;
    }

    private ListNode reverseHalf(ListNode head) {
        size++;
        if (head == null || head.next == null) {
            half = size / 2;
            return head;
        }
        ListNode ans = reverseHalf(head.next);
        size--;
        if (size > half) {
            head.next.next = head;
            head.next = null;
        }
        return ans;
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
        IsPalindrome IsPalindrome = new IsPalindrome();
        IsPalindrome.ListNode node = new IsPalindrome().new ListNode(1);
        IsPalindrome.ListNode node1 = new IsPalindrome().new ListNode(2);
        IsPalindrome.ListNode node2 = new IsPalindrome().new ListNode(3);
        IsPalindrome.ListNode node3 = new IsPalindrome().new ListNode(2);
        IsPalindrome.ListNode node4 = new IsPalindrome().new ListNode(1);
        node.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        System.out.println(IsPalindrome.isPalindrome(node));
    }
}
