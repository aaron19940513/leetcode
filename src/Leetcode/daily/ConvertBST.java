package Leetcode.daily;
import Leetcode.tree.Serialize;
import Leetcode.tree.TreeNode;
import org.junit.Test;

/**
 * 538. 把二叉搜索树转换为累加树  简单
 * <p>
 * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
 * <p>
 *  
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/convert-bst-to-greater-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 09/21/20 8:48
 */
public class ConvertBST {
    private int greater = 0;

    /**
     * 利用BST的特性，节点左边的所有元素都比节点小，节点右边的元素都比节点大
     */
    public TreeNode convertBST(TreeNode root) {
        greater(root);
        return root;
    }

    public void greater(TreeNode node) {
        if (node == null) {
            return;
        }
        greater(node.right);
        greater += node.val;
        node.val = greater;
        greater(node.left);
    }

    @Test
    public void test() {
        Serialize serialize = new Serialize();
        TreeNode root = serialize.deserialize("5,2,7,1,3,6,8");
        TreeNode treeNode = convertBST(root);
    }

}