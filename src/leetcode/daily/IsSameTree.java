package leetcode.daily;

import leetcode.tree.Serialize;
import leetcode.tree.TreeNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * 100. 相同的树 简单
 * 给定两个二叉树，编写一个函数来检验它们是否相同。
 * <p>
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 * <p>
 * 示例 1:
 * <p>
 * 输入:       1         1
 * / \       / \
 * 2   3     2   3
 * <p>
 * [1,2,3],   [1,2,3]
 * <p>
 * 输出: true
 * 示例 2:
 * <p>
 * 输入:      1          1
 * /           \
 * 2             2
 * <p>
 * [1,2],     [1,null,2]
 * <p>
 * 输出: false
 * 示例 3:
 * <p>
 * 输入:       1         1
 * / \       / \
 * 2   1     1   2
 * <p>
 * [1,2,1],   [1,1,2]
 * <p>
 * 输出: false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/same-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 08/07/20 8:33
 */
public class IsSameTree {
    //递归
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p != null && q != null && p.val == q.val) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        } else {
            return false;
        }
    }

    //循环
    // public boolean isSameTree2(TreeNode p, TreeNode q) {
    //     if (p == null && q == null) {
    //         return true;
    //     }
    //     Stack<TreeNode> rightChild=new Stack<>();
    //     while(!rightChild.isEmpty())
    //     if (p != null && q != null && p.val == q.val) {
    //
    //
    //         return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    //     } else {
    //         return false;
    //     }
    // }

    @Test
    public void test() {
        Serialize serialize = new Serialize();
        Assert.assertTrue(isSameTree(serialize.deserialize("1,2,3"), serialize.deserialize("1,2,3")));
        Assert.assertTrue(isSameTree(serialize.deserialize("1,null,3"), serialize.deserialize("1,null,3")));
        Assert.assertFalse(isSameTree(serialize.deserialize("1,2,3"), serialize.deserialize("1,null,3")));
    }
}
