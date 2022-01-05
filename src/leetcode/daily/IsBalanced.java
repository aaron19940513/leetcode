package leetcode.daily;
import leetcode.tree.Serialize;
import leetcode.tree.TreeNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * 110. 平衡二叉树 简单
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * <p>
 * 本题中，一棵高度平衡二叉树定义为：
 * <p>
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 * <p>
 * 示例 1:
 * <p>
 * 给定二叉树 [3,9,20,null,null,15,7]
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回 true 。
 * <p>
 * 示例 2:
 * <p>
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 * <p>
 * 1
 * / \
 * 2   2
 * / \
 * 3   3
 * / \
 * 4   4
 * 返回 false 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/balanced-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 08/17/20 9:03
 */
public class IsBalanced {

    private boolean isBalanced = true;

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        high(root);
        return isBalanced;
    }

    public int high(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftHigh = isBalanced ? high(node.left) + 1 : 0;
        int rightHigh = isBalanced ? high(node.right) + 1 : 0;
        if (Math.abs(leftHigh - rightHigh) > 1) {
            isBalanced = false;
        }
        return Math.max(leftHigh, rightHigh);
    }

    @Test
    public void test(){
        Serialize serialize =new Serialize();
        Assert.assertTrue(isBalanced(serialize.deserialize("3,9,20,null,null,15,7")));
    }

    @Test
    public void test1(){
        Serialize serialize =new Serialize();
        Assert.assertFalse(isBalanced(serialize.deserialize("1,2,2,3,3,null,null,4,4")));
    }
}
