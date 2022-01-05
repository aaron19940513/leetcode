package leetcode.daily;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import leetcode.tree.Serialize;
import leetcode.tree.TreeNode;

/**
 * 637. 二叉树的层平均值 简单
 * 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 输出：[3, 14.5, 11]
 * 解释：
 * 第 0 层的平均值是 3 ,  第1层是 14.5 , 第2层是 11 。因此返回 [3, 14.5, 11] 。
 *  
 * <p>
 * 提示：
 * <p>
 * 节点值的范围在32位有符号整数范围内。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/average-of-levels-in-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Sam Gao
 * @date 09/14/20 17:32
 */
public class AverageOfLevels {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        while (!list.isEmpty()) {
            List<TreeNode> tempList = new ArrayList<>();
            double temp = 0;
            int count = 0;
            for (TreeNode treeNode : list) {
                count++;
                temp += treeNode.val;
                if (treeNode.left != null) {
                    tempList.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    tempList.add(treeNode.right);
                }
            }
            ans.add((temp / count));
            list = tempList;
        }
        return ans;
    }

    @Test
    public void test() {
        Serialize serialize = new Serialize();
        TreeNode node = serialize.deserialize("3,9,20,null,null,15,7");
        List<Double> doubles = averageOfLevels(node);
        System.out.println(doubles);
    }


}
