package leetcode.list;
import java.util.HashMap;
import java.util.Map;

public class HasCycle {
    Map<ListNode, Integer> map = new HashMap<>();
    int index = 0;

    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        if (map.containsKey(head)) {
            return true;
        } else {
            map.put(head, index++);
        }
        return hasCycle(head.next);
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

}
