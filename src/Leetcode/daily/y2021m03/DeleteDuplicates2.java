package Leetcode.daily.y2021m03;
/**
 * @author Sam Gao
 * @date 03/26/21 8:43
 */
public class DeleteDuplicates2 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode curNode = head;
        ListNode nextNode;
        while (curNode != null) {
            nextNode = curNode.next;
            if (nextNode != null && curNode.val == nextNode.val) {
                curNode.next = nextNode.next;
            }else{
                curNode = nextNode;
            }
        }
        return head;
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
