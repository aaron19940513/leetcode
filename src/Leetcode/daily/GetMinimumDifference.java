package Leetcode.daily;
import Leetcode.tree.Serialize;
import Leetcode.tree.TreeNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * 530. 二叉搜索树的最小绝对差 简单
 * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
 *
 * @date 10/12/20 8:58
 */
public class GetMinimumDifference {
    int ans = Integer.MAX_VALUE;
    int pre = -1;

    public int getMinimumDifference(TreeNode root) {
        if (root.left != null) {
            getMinimumDifference(root.left);
        }
        if (pre != -1) {
            ans = Math.min(ans, root.val - pre);
        }
        pre = root.val;
        if (root.right != null) {
            getMinimumDifference(root.right);
        }
        return ans;
    }

    @Test
    public void test() {
        Serialize serialize = new Serialize();
        Assert.assertEquals(getMinimumDifference(serialize.deserialize("1,null,3,2")), 1);
    }
}
