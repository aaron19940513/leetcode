package Leetcode.daily;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import Leetcode.tree.Serialize;
import Leetcode.tree.TreeNode;
import org.junit.Test;

/**
 * 145. 二叉树的后序遍历 中等
 * 给定一个二叉树，返回它的 后序 遍历。
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 * @date 09/29/20 9:06
 */
public class PostorderTraversal {
    List<Integer> ans = new ArrayList<>();

    public List<Integer> postorderTraversal(TreeNode root) {
        //recursion(root);
        iteration(root);
        return ans;
    }

    public void iteration(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            ans.add(root.val);
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> flagStack = new Stack<>();
        TreeNode cur = root;
        int flag = 0;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                flagStack.push(1);
                while (cur.left != null) {
                    cur = cur.left;
                    stack.push(cur);
                    flagStack.push(1);
                }
                cur = null;
            } else {
                cur = stack.peek();
                flag = flagStack.peek();
                if (cur.right == null || flag == 2) {
                    ans.add(cur.val);
                    stack.pop();
                    flagStack.pop();
                    cur = null;
                } else {
                    flagStack.pop();
                    flagStack.push(2);
                    cur = cur.right;
                }
            }
        }
    }

    /**
     * 递归方法
     */
    public void recursion(TreeNode root) {
        if (root == null) {
            return;
        }
        recursion(root.left);
        recursion(root.right);
        ans.add(root.val);
    }

    @Test
    public void test() {
        Serialize serialize = new Serialize();
        TreeNode root = serialize.deserialize("1,null,2,3");
        List<Integer> integers = postorderTraversal(root);
        for (Integer integer : integers) {
            System.out.print(integer);
        }
    }

    @Test
    public void test1() {
        Serialize serialize = new Serialize();
        TreeNode root = serialize.deserialize("1,2,3,4,5,6,7");
        List<Integer> integers = postorderTraversal(root);
        for (Integer integer : integers) {
            System.out.print(integer);
        }
    }
}
