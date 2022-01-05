package leetcode.daily;
import leetcode.tree.Serialize;
import leetcode.tree.TreeNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * 700. 二叉搜索树中的搜索 简单
 * 给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。
 *
 * @date 09/01/20 8:50
 */
public class SearchBST {
    public TreeNode searchBST(TreeNode root, int val) {
        TreeNode node = root;
        while (node != null) {
            if (node.val == val) {
                return node;
            } else if (val > node.val) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return null;
    }

    @Test
    public void test(){
        Serialize serialize =new Serialize();
        TreeNode treeNode = serialize.deserialize("4,2,7,1,3");
        Assert.assertEquals(searchBST(treeNode,2),serialize.deserialize("2,1,3"));
    }
}
