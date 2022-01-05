package leetcode.daily;
import leetcode.tree.TreeNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个二叉树，计算整个树的坡度。
 * <p>
 * 一个树的节点的坡度定义即为，该节点左子树的结点之和和右子树结点之和的差的绝对值。空结点的的坡度是0。
 * <p>
 * 整个树的坡度就是其所有节点的坡度之和。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * 1
 * /   \
 * 2     3
 * 输出: 1
 * 解释:
 * 结点的坡度 2 : 0
 * 结点的坡度 3 : 0
 * 结点的坡度 1 : |2-3| = 1
 * 树的坡度 : 0 + 0 + 1 = 1
 * 注意:
 * <p>
 * 任何子树的结点的和不会超过32位整数的范围。
 * 坡度的值不会超过32位整数的范围。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-tilt
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindTilt {
    public int findTilt(TreeNode root) {
        if (root == null) {
            return 0;
        }
        reBuildTree(root);
        return sumNodeTilt(root);
    }

    private int reBuildTree(TreeNode node) {
        if (node == null) {
            return 0;
        }
        node.val = reBuildTree(node.left) + reBuildTree(node.right) + node.val;
        return node.val;
    }

    private int sumNodeTilt(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int tilt = Math.abs((node.left == null ? 0 : node.left.val) - (node.right == null ? 0 : node.right.val));
        return sumNodeTilt(node.left) + sumNodeTilt(node.right) + tilt;
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        TreeNode first = new TreeNode(2);
        TreeNode second = new TreeNode(3);
        root.left = first;
        root.right = second;
        Assert.assertEquals(findTilt(root), 1);

        TreeNode root1 = new TreeNode(1);
        TreeNode left1 = new TreeNode(2);
        TreeNode right1 = new TreeNode(3);
        TreeNode left11 = new TreeNode(4);
        TreeNode right22= new TreeNode(5);
        root1.left = left1;
        root1.right = right1;
        left1.left = left11;
        right1.right = right22;
        Assert.assertEquals(findTilt(root1), 11);
    }
}
