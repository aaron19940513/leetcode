package Leetcode.daily;
import java.util.ArrayList;
import java.util.List;

import Leetcode.tree.TreeNode;
import org.junit.Test;

/**
 * 95. 不同的二叉搜索树 II  中等
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：3
 * 输出：
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * 解释：
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 * <p>
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= n <= 8
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 07/21/20 13:37
 */
public class GenerateTrees {
    public List<TreeNode> generateTrees(int n) {
        List<List<TreeNode>> dp = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            dp.add(new ArrayList<>());
        }
        dp.get(0).add(null);
        generateDp(n, dp);
        return dp.get(n);
    }

    private void generateDp(int n, List<List<TreeNode>> dp) {
        List<TreeNode> treeNodes = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i - 1 != 0 && dp.get(i - 1).isEmpty()) {
                generateDp(i - 1, dp);
            }
            if (n - i != 0 && dp.get(n - i).isEmpty()) {
                generateDp(n - i, dp);
            }
            List<TreeNode> leftList = dp.get(i - 1);
            List<TreeNode> rightList = copyTree(dp.get(n - i), i);
            for (TreeNode left : leftList) {
                for (TreeNode right : rightList) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    treeNodes.add(root);
                }
            }
        }
        dp.set(n, treeNodes);
    }


    private List<TreeNode> copyTree(List<TreeNode> treeNode, int start) {
        List<TreeNode> copyTree = new ArrayList<>();
        for (TreeNode node : treeNode) {
            copyTree.add(traverse(node, start));
        }
        return copyTree;
    }

    private TreeNode traverse(TreeNode node, int start) {
        if (node == null) {
            return null;
        }
        TreeNode nodeNew = new TreeNode(node.val + start);
        nodeNew.left = traverse(node.left, start);
        nodeNew.right = traverse(node.right, start);
        return nodeNew;
    }

    @Test
    public void test() {
        List<TreeNode> treeNodes = generateTrees(3);
        List<TreeNode> treeNodes1 = generateTrees(4);
        List<TreeNode> treeNodes2 = generateTrees(5);

    }
}
