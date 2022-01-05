package leetcode.daily.y2021m04;
import leetcode.tree.Serialize;
import leetcode.tree.TreeNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * 783. 二叉搜索树节点最小距离 easy
 * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 * <p>
 * 注意：本题与 530：https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/ 相同
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [4,2,6,1,3]
 * 输出：1
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [1,0,48,null,null,12,49]
 * 输出：1
 *
 * @date 04/13/21 9:29
 */
public class MinDiffInBST {
    int ans = Integer.MAX_VALUE;
    Integer preValue = null;
    //方法1：递归解法
    public int minDiffInBST(TreeNode root) {
        recursive(root);
        return ans;
    }

    public void recursive(TreeNode root) {
        if (root.left != null) {
            recursive(root.left);
        }
        if (preValue != null) {
            ans = Math.min(ans, root.val - preValue);
        }

        preValue = root.val;
        if (root.right != null) {
            recursive(root.right);
        }
    }

    @Test
    public void test() {
        Serialize serialize = new Serialize();
        Assert.assertEquals(1, minDiffInBST(serialize.deserialize("4,2,6,1,3")));

    }

    @Test
    public void test1() {
        Serialize serialize = new Serialize();
        Assert.assertEquals(1, minDiffInBST(serialize.deserialize("1,0,48,null,null,12,49")));
    }
}
