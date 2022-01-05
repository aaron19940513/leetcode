package leetcode.daily;

import leetcode.tree.TreeNode;
import org.junit.Test;

/**
 * 109. 有序链表转换二叉搜索树
 *
 * @date 08/18/20 13:35
 */
public class sortedListToBST {
    public TreeNode sortedListToBST(ListNode head) {
        int size = 0;
        ListNode listNode = head;
        while (listNode != null) {
            size++;
            listNode = listNode.next;
        }
        return sortedListToBST(head, size);
    }

    private TreeNode sortedListToBST(ListNode listNode, int size) {
        if (size == 0) {
            return null;
        } else if (size == 1) {
            return new TreeNode(listNode.val);
        }
        int leftSize = size % 2 == 0 ? (size / 2 - 1) : (size / 2);
        int rightSize = size - leftSize - 1;
        TreeNode leftChild = sortedListToBST(listNode, leftSize);
        for (int i = 0; i < leftSize; i++) {
            listNode = listNode.next;
        }
        TreeNode root = new TreeNode(listNode.val);
        listNode = listNode.next;
        TreeNode rightChild = sortedListToBST(listNode, rightSize);
        root.left = leftChild;
        root.right = rightChild;
        return root;
    }

    @Test
    public void test() {
        int[] array = new int[] {-10, -3, 0, 5, 9};
        ListNode head = new ListNode(-10);
        ListNode listNode = head;
        for (int i = 1; i < array.length; i++) {
            ListNode temp = new ListNode(array[i]);
            listNode.next = temp;
            listNode = temp;
        }
        TreeNode treeNode = sortedListToBST(head);
    }

    @Test
    public void test1() {
        int[] array = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        ListNode head = new ListNode(1);
        ListNode listNode = head;
        for (int i = 1; i < array.length; i++) {
            ListNode temp = new ListNode(array[i]);
            listNode.next = temp;
            listNode = temp;
        }
        TreeNode treeNode = sortedListToBST(head);
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

