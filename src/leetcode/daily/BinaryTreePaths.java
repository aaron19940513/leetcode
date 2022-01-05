package leetcode.daily;
import java.util.ArrayList;
import java.util.List;

import leetcode.tree.Serialize;
import leetcode.tree.TreeNode;
import org.junit.Test;

/**
 * 257. 二叉树的所有路径 简单
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * @date 09/04/20 8:33
 */
public class BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<String> ans = new ArrayList<>();
        dfs(ans, root, "");
        return ans;
    }

    public void dfs(List<String> ans, TreeNode node, String preString) {
        if (node.left == null && node.right == null) {
            ans.add(preString + node.val);
        }
        if (node.left != null) {
            dfs(ans, node.left, preString + node.val + "->");
        }
        if (node.right != null) {
            dfs(ans, node.right, preString + node.val + "->");
        }
    }

    @Test
    public void test() {
        Serialize serialize = new Serialize();
        TreeNode root = serialize.deserialize("1,2,3,null,5");
        for (String binaryTreePath : binaryTreePaths(root)) {
            System.out.println(binaryTreePath);
        }
    }

}
