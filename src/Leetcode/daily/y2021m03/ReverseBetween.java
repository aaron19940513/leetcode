package Leetcode.daily.y2021m03;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * 92. 反转链表 II mid
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * <p>
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 *
 * @date 03/18/21 8:48
 */
public class ReverseBetween {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right) {
            return head;
        }
        ListNode preLeftNode = null;
        ListNode leftNode = null;
        ListNode preNode = null;
        ListNode curNode = head;
        ListNode nextNode = head.next;
        int index = 1;
        while (index <= right) {
            if (left < index) {
                curNode.next = preNode;
            } else if (left == index) {
                preLeftNode = preNode;
                leftNode = curNode;
            }
            preNode = curNode;
            curNode = nextNode;
            if (nextNode != null) {
                nextNode = nextNode.next;
            }
            index++;
        }
        if (preLeftNode != null) {
            preLeftNode.next = preNode;
        }

        leftNode.next = curNode;
        if(left!=1){
            return head;
        }else{
            return preNode;
        }

    }

    @Test
    public void test() {
        Map<String,String> map =new HashMap<>();
        map.put("1","1");
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            stringStringEntry.getKey();
        }

        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        //reverseBetween(n1, 2, 4);
        reverseBetween(n1, 1, 5);
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

        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }
}

