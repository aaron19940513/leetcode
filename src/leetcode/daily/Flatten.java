package leetcode.daily;
import leetcode.tree.Serialize;
import leetcode.tree.TreeNode;
import org.junit.Test;

/**
 * 114. 二叉树展开为链表  中等
 * 给定一个二叉树，原地将它展开为一个单链表。
 * <p>
 *  
 * <p>
 * 例如，给定二叉树
 * <p>
 * 1
 * / \
 * 2   5
 * / \   \
 * 3   4   6
 * 将其展开为：
 * <p>
 * 1
 * \
 * 2
 * \
 * 3
 * \
 * 4
 * \
 * 5
 * \
 * 6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 08/03/20 10:13
 */
public class Flatten {
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root);
    }

    private TreeNode dfs(TreeNode node) {
        if (node.left != null && node.right != null) {
            TreeNode tail = dfs(node.left);
            TreeNode temp = node.right;
            node.right = node.left;
            node.left = null;
            tail.right = temp;
            return dfs(temp);
        } else if (node.left != null) {
            TreeNode temp = node.left;
            node.left = null;
            node.right = temp;
            return dfs(temp);
        } else if (node.right != null) {
            return dfs(node.right);
        } else {
            return node;
        }
    }

    @Test
    public void test() {
        Serialize serialize = new Serialize();
        TreeNode root = serialize.deserialize("1,2,3,4,5,6,7");
        flatten(root);
        System.out.println(serialize.serialize(root));
    }

    @Test
    public void test1() {
        Serialize serialize = new Serialize();
        TreeNode root = serialize.deserialize("1,2,null,3,null,4,null,5,null,6,null,7");
        flatten(root);
        System.out.println(serialize.serialize(root));
    }

    @Test
    public void test2() {
        flatten(null);
    }

}
