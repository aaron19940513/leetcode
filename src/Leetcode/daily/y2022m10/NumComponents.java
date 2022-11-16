package leetcode.daily.y2022m10;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class NumComponents {

    // Definition for singly-linked list.
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

    public int numComponents(ListNode head, int[] nums) {
        Set<Integer> numsSet = new HashSet<>();
        for (int num : nums) {
            numsSet.add(num);
        }

        List<Boolean> flag = new ArrayList<>();
        ListNode current = head;
        while (current != null) {
            if (numsSet.contains(current.val)) {
                flag.add(true);
            } else {
                flag.add(false);
            }
            current = current.next;
        }

        int ans = flag.get(0) ? 1 : 0;


        for (int i = 1; i < flag.size(); i++) {
            if (flag.get(i) && !flag.get(i - 1)) {
                ans++;
            }
        }
        return ans;
    }

    @Test
    public void test() {
        ListNode node1 = new ListNode(0);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        Assert.assertEquals(2, numComponents(node1, new int[]{0, 3, 1}));

        Assert.assertEquals(2, numComponents(node1, new int[]{0, 3, 1, 4}));
    }


}
