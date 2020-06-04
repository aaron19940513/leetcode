package Leetcode.tree;
import java.util.Stack;

public class LowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int index_p = index(root, p, 0);
        int index_q = index(root, q, 0);
        while (index_p != index_q) {
            if (index_p > index_q) {
                index_p = (index_p - 1) / 2;
            } else {
                index_q = (index_q - 1) / 2;
            }
        }
        return find(index_p, root);
    }


    private int index(TreeNode node, TreeNode target, int i) {
        if (node == null) {
            return -1;
        }
        if (target == node) {
            return i;
        }
        int index = index(node.left, target, 2 * i + 1);
        if (index != -1) {
            return index;
        }
        index = index(node.right, target, 2 * i + 2);
        if (index != -1) {
            return index;
        }
        return -1;
    }


    private TreeNode find(int index, TreeNode root) {
        Stack<Integer> stack = new Stack<>();
        while (index != 0) {
            stack.push(index % 2);
            index = (index - 1) / 2;
        }
        TreeNode node = root;
        while (stack.size() > 0) {
            if (stack.pop() == 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return node;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode first = new TreeNode(5);
        TreeNode second = new TreeNode(1);
        TreeNode third = new TreeNode(6);
        TreeNode fourth = new TreeNode(2);
        TreeNode fifth = new TreeNode(0);
        TreeNode sixth = new TreeNode(8);
        TreeNode ninth = new TreeNode(7);
        TreeNode ten = new TreeNode(4);
        root.left = first;
        root.right = second;
        first.left = third;
        first.right = fourth;
        second.left = fifth;
        second.right = sixth;
        fourth.left = ninth;
        fourth.right = ten;
        LowestCommonAncestor lowestCommonAncestor = new LowestCommonAncestor();
        System.out.println(lowestCommonAncestor.lowestCommonAncestor(root, first, second).val);
        System.out.println(lowestCommonAncestor.lowestCommonAncestor(root, first, ten).val);
        System.out.println(lowestCommonAncestor.lowestCommonAncestor(root, ninth, ten).val);
    }
}
