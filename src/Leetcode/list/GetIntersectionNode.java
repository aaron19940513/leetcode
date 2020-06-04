package Leetcode.list;
public class GetIntersectionNode {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;

    }

    // public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    //     ListNode reverseNodeA = reverse(headA);
    //     ListNode reverseNodeB = reverse(headB);
    //     ListNode ans = null;
    //     ListNode nodeA = reverseNodeA;
    //     ListNode nodeB = reverseNodeB;
    //     while (nodeA != null && nodeB != null && nodeA == nodeB) {
    //         ans = nodeA;
    //         nodeA = nodeA.next;
    //         nodeB = nodeB.next;
    //     }
    //
    //     reverse(reverseNodeA);
    //     reverse(reverseNodeB);
    //     return ans;
    // }

    private ListNode reverse(ListNode head) {
        ListNode pre = head;
        ListNode next = pre.next;
        pre.next = null;
        while (next != null) {
            ListNode tmp = next.next;
            next.next = pre;
            pre = next;
            next = tmp;
        }
        return pre;
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
        GetIntersectionNode getIntersectionNode = new GetIntersectionNode();
        GetIntersectionNode.ListNode node = new GetIntersectionNode().new ListNode(4);
        GetIntersectionNode.ListNode node1 = new GetIntersectionNode().new ListNode(1);
        GetIntersectionNode.ListNode node2 = new GetIntersectionNode().new ListNode(8);
        GetIntersectionNode.ListNode node3 = new GetIntersectionNode().new ListNode(4);
        GetIntersectionNode.ListNode node4 = new GetIntersectionNode().new ListNode(5);
        node.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node2 = node3;


        GetIntersectionNode.ListNode node21 = new GetIntersectionNode().new ListNode(5);
        GetIntersectionNode.ListNode node22 = new GetIntersectionNode().new ListNode(0);
        GetIntersectionNode.ListNode node23 = new GetIntersectionNode().new ListNode(1);
        node21.next = node22;
        node22.next = node23;
        node23.next = node2;

        GetIntersectionNode.ListNode ans = getIntersectionNode.getIntersectionNode(node, node21);
    }
}
