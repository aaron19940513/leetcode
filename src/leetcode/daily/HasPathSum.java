package leetcode.daily;
import leetcode.tree.Serialize;
import leetcode.tree.TreeNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * 112. 路径总和
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例: 
 * 给定如下二叉树，以及目标和 sum = 22，
 * <p>
 * 5
 * / \
 * 4   8
 * /   / \
 * 11  13  4
 * /  \      \
 * 7    2      1
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 07/09/20 8:49
 */
public class HasPathSum {

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left != null || root.right != null) {
            if (root.left != null) {
                if (hasPathSum(root.left, sum - root.val)) {
                    return true;
                }
            }
            if (root.right != null) {
                return hasPathSum(root.right, sum - root.val);
            }
            return false;
        } else {
            return sum == root.val;
        }
    }

    @Test
    public void test() {
        Serialize serialize = new Serialize();
        TreeNode deserialize = serialize.deserialize("5,4,8,11,null,13,4,7,2,null,null,null,1");
        Assert.assertTrue(hasPathSum(deserialize, 22));
        Assert.assertFalse(hasPathSum(deserialize, 122));
        Assert.assertTrue(hasPathSum(deserialize, 26));
    }
}
