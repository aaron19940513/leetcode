package leetcode.daily;
import leetcode.tree.Serialize;
import leetcode.tree.TreeNode;
import org.junit.Test;

/**
 * 617. 合并二叉树 简单
 * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
 * <p>
 * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-binary-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 09/23/20 13:37
 */
public class MergeTrees {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null || t2 == null) {
            return t1 == null ? t2 : t1;
        }
        merge(t1, t2);
        return t1;
    }

    private void merge(TreeNode t1, TreeNode t2) {
        t1.val = t1.val + t2.val;
        if (t1.left == null) {
            t1.left = t2.left;
        } else if (t2.left != null) {
            merge(t1.left, t2.left);
        }

        if (t1.right == null) {
            t1.right = t2.right;
        } else if (t2.right != null) {
            merge(t1.right, t2.right);
        }
    }

    @Test
    public void test() {
        Serialize serialize = new Serialize();
        TreeNode treeNode = mergeTrees(serialize.deserialize("1,3,2,5"), serialize.deserialize("2,1,3,null,4,null,7"));
    }
}
