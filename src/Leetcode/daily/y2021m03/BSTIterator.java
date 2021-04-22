package Leetcode.daily.y2021m03;
import java.util.Stack;

import Leetcode.tree.Serialize;
import Leetcode.tree.TreeNode;
import org.junit.Assert;

/**
 * 173. 二叉搜索树迭代器 mid
 * 实现一个二叉搜索树迭代器类BSTIterator ，表示一个按中序遍历二叉搜索树（BST）的迭代器：
 * BSTIterator(TreeNode root) 初始化 BSTIterator 类的一个对象。BST 的根节点 root 会作为构造函数的一部分给出。指针应初始化为一个不存在于 BST 中的数字，且该数字小于 BST 中的任何元素。
 * boolean hasNext() 如果向指针右侧遍历存在数字，则返回 true ；否则返回 false 。
 * int next()将指针向右移动，然后返回指针处的数字。
 * 注意，指针初始化为一个不存在于 BST 中的数字，所以对 next() 的首次调用将返回 BST 中的最小元素。
 * <p>
 * 你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 的中序遍历中至少存在一个下一个数字。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * <p>
 * 输入
 * ["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
 * [[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
 * 输出
 * [null, 3, 7, true, 9, true, 15, true, 20, false]
 * <p>
 * 解释
 * BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
 * bSTIterator.next();    // 返回 3
 * bSTIterator.next();    // 返回 7
 * bSTIterator.hasNext(); // 返回 True
 * bSTIterator.next();    // 返回 9
 * bSTIterator.hasNext(); // 返回 True
 * bSTIterator.next();    // 返回 15
 * bSTIterator.hasNext(); // 返回 True
 * bSTIterator.next();    // 返回 20
 * bSTIterator.hasNext(); // 返回 False
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中节点的数目在范围 [1, 105] 内
 * 0 <= Node.val <= 106
 * 最多调用 105 次 hasNext 和 next 操作
 * <p>
 * <p>
 * 进阶：
 * <p>
 * 你可以设计一个满足下述条件的解决方案吗？next() 和 hasNext() 操作均摊时间复杂度为 O(1) ，并使用 O(h) 内存。其中 h 是树的高度。
 *
 * @date 03/29/21 10:47
 */
public class BSTIterator {

    private Stack<TreeNode> treeNodes = new Stack<>();

    public BSTIterator(TreeNode root) {
        recursive(root);
    }

    public int next() {
        TreeNode treeNode = treeNodes.pop();
        if (treeNode.right != null) {
            recursive(treeNode.right);
        }
        return treeNode.val;
    }

    public boolean hasNext() {
        if (treeNodes.isEmpty()) {
            return false;
        }
        return true;
    }

    private void recursive(TreeNode node) {
        while (node != null) {
            treeNodes.push(node);
            node = node.left;
        }
    }

    public static void main(String[] args) {
        Serialize serialize = new Serialize();
        TreeNode treeNode = serialize.deserialize("7,3,15,null,null,9,20");
        BSTIterator bSTIterator = new BSTIterator(treeNode);
        Assert.assertEquals(bSTIterator.next(), 3);    // 返回 3
        Assert.assertEquals(bSTIterator.next(), 7);    // 返回 7
        Assert.assertTrue(bSTIterator.hasNext()); // 返回 True
        Assert.assertEquals(bSTIterator.next(), 9);    // 返回 9
        Assert.assertTrue(bSTIterator.hasNext()); // 返回 True
        Assert.assertEquals(bSTIterator.next(), 15);    // 返回 15
        Assert.assertTrue(bSTIterator.hasNext()); // 返回 True
        Assert.assertEquals(bSTIterator.next(), 20);    // 返回 20
        Assert.assertFalse(bSTIterator.hasNext()); // 返回 False


        TreeNode treeNode1 = serialize.deserialize("7,3,15,null,null,9,20,8,10,null,null,null,null,null,12");
        BSTIterator bSTIterator1 = new BSTIterator(treeNode1);
        Assert.assertEquals(3, bSTIterator1.next());    // 返回 3
        Assert.assertEquals(7, bSTIterator1.next());    // 返回 7

        Assert.assertEquals(8, bSTIterator1.next());    // 返回 8

        Assert.assertEquals(9, bSTIterator1.next());    // 返回 9

        Assert.assertEquals(10, bSTIterator1.next());    // 返回 10

        Assert.assertEquals(12, bSTIterator1.next());    // 返回 12
        Assert.assertEquals(15, bSTIterator1.next());    // 返回 15
        Assert.assertEquals(20, bSTIterator1.next());    // 返回 20
        Assert.assertFalse(bSTIterator1.hasNext());
    }

}
