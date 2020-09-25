package Leetcode.daily;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import Leetcode.tree.Serialize;
import Leetcode.tree.TreeNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * 94. 二叉树的中序遍历 中等
 * 给定一个二叉树，返回它的中序 遍历。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 09/14/20 9:01
 */
public class InorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        //recursion(list, root);
        list = iteration(root);

        return list;
    }



    //进阶 迭代算法

    /**
     * 用两个堆栈来存储迭代中的信息，treeNodeStack中存储的树中的节点
     * nodeTypeStack中存储的是节点的类型，
     * 1：左子树没有遍历的父节点
     * 2: 左子树已经被遍历的父节点
     */
    public List<Integer> iteration(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> treeNodeStack = new Stack<>();
        Stack<Integer> nodeTypeStack = new Stack<>();
        treeNodeStack.push(root);
        nodeTypeStack.push(1);
        while (!treeNodeStack.isEmpty()) {
            if (nodeTypeStack.peek() == 1 && treeNodeStack.peek().left != null) {
                nodeTypeStack.pop();
                nodeTypeStack.push(2);

                treeNodeStack.push(treeNodeStack.peek().left);
                nodeTypeStack.push(1);
            } else {
                nodeTypeStack.pop();
                TreeNode node = treeNodeStack.pop();
                list.add(node.val);
                if (node.right != null) {
                    treeNodeStack.push(node.right);
                    nodeTypeStack.push(1);
                }
            }
        }
        return list;
    }

    //递归算法
    public void recursion(List<Integer> list, TreeNode root) {
        if (root.left != null) {
            recursion(list, root.left);
        }
        list.add(root.val);
        if (root.right != null) {
            recursion(list, root.right);
        }
    }

    @Test
    public void test() {
        Serialize serialize = new Serialize();
        TreeNode deserialize = serialize.deserialize("1,null,2,3");
        List<Integer> integers = inorderTraversal(deserialize);
        Assert.assertEquals(integers, Arrays.asList(1, 3, 2));
    }
}
