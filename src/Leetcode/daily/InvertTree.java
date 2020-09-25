package Leetcode.daily;
import Leetcode.tree.Serialize;
import Leetcode.tree.TreeNode;
import org.junit.Test;

/**
 * @author Sam Gao
 * @date 09/16/20 8:45
 */
public class InvertTree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode temp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(temp);
        return root;
    }

    @Test
    public void test() {
        Serialize serialize = new Serialize();
        TreeNode root = serialize.deserialize("4,2,7,1,3,6,9");
        TreeNode treeNode = invertTree(root);
        System.out.println(serialize.serialize(treeNode));
    }

}
