package leetcode.daily;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import leetcode.tree.Serialize;
import leetcode.tree.TreeNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * 337. 打家劫舍 III  中等
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 * <p>
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,3,null,3,null,1]
 * <p>
 * 3
 * / \
 * 2   3
 * \   \
 * 3   1
 * <p>
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 * 示例 2:
 * <p>
 * 输入: [3,4,5,1,3,null,1]
 * <p>
 *      3
 * / \
 * 4   5
 * / \   \
 * 1   3   1
 * <p>
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 08/05/20 9:20
 */
public class Rob {

    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        List<List<Integer>> dp = new ArrayList<>();
        List<Integer> nullItem = new ArrayList<>(2);
        nullItem.add(0);
        nullItem.add(0);
        dp.add(nullItem);

        Map<TreeNode, Integer> indexMap = new HashMap<>();
        indexMap.put(null, 0);

        List<Integer> rob = rob(root, dp, indexMap);
        return Math.max(rob.get(0), rob.get(1));
    }

    private List<Integer> rob(TreeNode node, List<List<Integer>> dp, Map<TreeNode, Integer> indexMap) {
        List<Integer> dpItem = new ArrayList<>(2);
        Integer index = indexMap.get(node);
        if (index != null) {
            return dp.get(index);
        }
        List<Integer> left = node.left != null ? rob(node.left, dp, indexMap) : dp.get(0);
        List<Integer> right = node.right != null ? rob(node.right, dp, indexMap) : dp.get(0);
        dpItem.add(node.val + left.get(1) + right.get(1));
        dpItem.add(Math.max(left.get(0), left.get(1)) + Math.max(right.get(0), right.get(1)));
        dp.add(dpItem);
        indexMap.put(node, dp.size() - 1);
        return dpItem;
    }

    @Test
    public void test() {
        Assert.assertEquals(rob(null), 0);
    }

    @Test
    public void test1() {
        Serialize serialize = new Serialize();
        TreeNode root = serialize.deserialize("3,2,3,null,3,null,1");
        Assert.assertEquals(rob(root), 7);
    }

    @Test
    public void test2() {
        Serialize serialize = new Serialize();
        TreeNode root = serialize.deserialize("3,4,5,1,3,null,1");
        Assert.assertEquals(rob(root), 9);
    }

}
