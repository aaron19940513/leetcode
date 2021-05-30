package Leetcode.test;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class Main {

    static void reorderList(ListNode head) {
        Deque<ListNode> queue = new ArrayDeque<>();
        ListNode cur = head;
        while (cur != null) {
            queue.offer(cur);
            cur = cur.next;
        }
        ListNode temp = queue.removeFirst();
        int count = 1;
        while (!queue.isEmpty()) {
            if (count % 2 == 0) {
                temp.next = queue.removeFirst();
            } else {
                temp.next = queue.removeLast();
            }
            temp = temp.next;
            count++;
        }
        temp.next = null;
    }

    static int[] twoPeopleWalkAlon(int[] intArray1, int[] intArray2) {
        Set<Integer> set = new HashSet<>();
        int sum = 0;
        for (int i : intArray1) {
            sum += i;
        }
        for (int i : intArray2) {
            set.add(i);
            sum -= i;
        }
        if (sum % 2 != 0) {
            return new int[] {};
        }

        for (int i : intArray1) {
            if (set.contains(i - sum / 2)) {
                return new int[] {i, i - sum / 2};
            }
        }
        return new int[] {};
    }
    @Test
    public void test1() {
        // Assert.assertArrayEquals(new int[] {4, 3}, twoPeopleWalkAlon(new int[] { 3, 3, 4, 5, 6}, new int[] { 6, 5, 4, 3, 1}));
        // Assert.assertArrayEquals(new int[] {}, twoPeopleWalkAlon(new int[] { 3}, new int[] {4}));
        Assert.assertArrayEquals(new int[] {5,6}, twoPeopleWalkAlon(new int[] { 6, 5, 4, 3, 1}, new int[] {3, 3, 4, 5, 6}));
    }

    @Test
    public void test() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        //ListNode n5 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        //n4.next = n5;
        reorderList(n1);

    }




    public static void main(String[] args) {
        ListNode listNode;
        Scanner in = new Scanner(System.in);
        int treeNodeSize = Integer.parseInt(in.next());
        List<ListNode> listNodeList = new ArrayList<>();
        for (int i = 0; i < treeNodeSize; i++) {
            int val = Integer.parseInt(in.next());
            listNode = new ListNode();
            listNode.val = val;
            if (listNodeList.size() > 0) {
                listNodeList.get(i - 1).next = listNode;
            }
            listNodeList.add(listNode);
        }
        reorderList(listNodeList.get(0));
        ListNode node = listNodeList.get(0);
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
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


