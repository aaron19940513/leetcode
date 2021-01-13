package Leetcode.daily;
import Leetcode.tree.Serialize;
import Leetcode.tree.TreeNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * 129. 求根到叶子节点数字之和 中等
 * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
 * <p>
 * 例如，从根到叶子节点路径 1->2->3 代表数字 123。
 * <p>
 * 计算从根到叶子节点生成的所有数字之和。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3]
 * 1
 * / \
 * 2   3
 * 输出: 25
 * 解释:
 * 从根到叶子节点路径 1->2 代表数字 12.
 * 从根到叶子节点路径 1->3 代表数字 13.
 * 因此，数字总和 = 12 + 13 = 25.
 * 示例 2:
 * <p>
 * 输入: [4,9,0,5,1]
 * 4
 * / \
 * 9   0
 *  / \
 * 5   1
 * 输出: 1026
 * 解释:
 * 从根到叶子节点路径 4->9->5 代表数字 495.
 * 从根到叶子节点路径 4->9->1 代表数字 491.
 * 从根到叶子节点路径 4->0 代表数字 40.
 * 因此，数字总和 = 495 + 491 + 40 = 1026.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-root-to-leaf-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 10/29/20 10:08
 */
public class SumNumbers {
    private int ans = 0;
    private int temp = 0;

    public int sumNumbers(TreeNode root) {
        if(root!=null){
            dfs(root);
        }
        return ans;
    }

    private void dfs(TreeNode node) {
        temp = temp * 10 + node.val;
        if (node.left == null && node.right == null) {
            ans += temp;
        }
        if (node.left != null) {
            dfs(node.left);
        }
        if (node.right != null) {
            dfs(node.right);
        }

        temp = (temp - node.val) / 10;
    }

    @Test
    public void test(){
        Serialize serialize = new Serialize();
        Assert.assertEquals(sumNumbers(serialize.deserialize("1,2,3")),25);
    }

    @Test
    public void test1(){
        Serialize serialize = new Serialize();
        Assert.assertEquals(sumNumbers(serialize.deserialize("4,9,0,5,1")),1026);
    }
}
