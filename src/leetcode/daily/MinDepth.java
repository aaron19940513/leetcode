package leetcode.daily;
import leetcode.tree.Serialize;
import leetcode.tree.TreeNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * 111. 二叉树的最小深度 简单
 * 给定一个二叉树，找出其最小深度。
 * <p>
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * <p>
 * 给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回它的最小深度  2.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-depth-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 08/21/20 9:36
 */
public class MinDepth {
    //dfs
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        if (root.left == null) {
            return minDepth(root.right) + 1;
        } else if (root.right == null) {
            return minDepth(root.left) + 1;
        }

        return Math.min(minDepth(root.left) + 1, minDepth(root.right) + 1);
    }


    @Test
    public void test() {
        Serialize serialize = new Serialize();
        TreeNode deserialize = serialize.deserialize("3,9,20,null,null,15,7");
        Assert.assertEquals(minDepth(deserialize), 2);
    }


    @Test
    public void test1() {
        Serialize serialize = new Serialize();
        TreeNode deserialize = serialize.deserialize("1,2");
        Assert.assertEquals(minDepth(deserialize), 2);
    }
}
