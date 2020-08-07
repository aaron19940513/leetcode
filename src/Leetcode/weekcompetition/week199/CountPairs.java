package Leetcode.weekcompetition.week199;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import Leetcode.tree.Serialize;
import Leetcode.tree.TreeNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * 给你二叉树的根节点 root 和一个整数 distance 。
 * <p>
 * 如果二叉树中两个 叶 节点之间的 最短路径长度 小于或者等于 distance ，那它们就可以构成一组 好叶子节点对 。
 * <p>
 * 返回树中 好叶子节点对的数量 。
 * <p>
 * 示例 1：
 * 输入：root = [1,2,3,null,4], distance = 3
 * 输出：1
 * 解释：树的叶节点是 3 和 4 ，它们之间的最短路径的长度是 3 。这是唯一的好叶子节点对。
 * <p>
 * 示例 2：
 * 输入：root = [1,2,3,4,5,6,7], distance = 3
 * 输出：2
 * 解释：好叶子节点对为 [4,5] 和 [6,7] ，最短路径长度都是 2 。但是叶子节点对 [4,6] 不满足要求，因为它们之间的最短路径长度为 4 。
 * <p>
 * 示例 3：
 * 输入：root = [7,1,4,6,null,5,3,null,null,null,null,null,2], distance = 3
 * 输出：1
 * 解释：唯一的好叶子节点对是 [2,5] 。
 * <p>
 * 示例 4：
 * 输入：root = [100], distance = 1
 * 输出：0
 * <p>
 * 示例 5：
 * 输入：root = [1,1,1], distance = 2
 * 输出：1
 *  
 * <p>
 * 提示：
 * <p>
 * tree 的节点数在 [1, 2^10] 范围内。
 * 每个节点的值都在 [1, 100] 之间。
 * 1 <= distance <= 10
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-good-leaf-nodes-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 07/28/20 17:11
 */
public class CountPairs {
    private int ans = 0;

    public int countPairs(TreeNode root, int distance) {
        ans=0;
        distance(root, distance, 0);
        return ans;
    }

    private List<Integer> distance(TreeNode node, int distance, int depth) {
        if (node.left == null && node.right == null) {
            return Collections.singletonList(depth);
        }
        List<Integer> leftDepths = null;
        if (node.left != null) {
            leftDepths = distance(node.left, distance, depth + 1);
        }
        List<Integer> rightDepths = null;
        if (node.right != null) {
            rightDepths = distance(node.right, distance, depth + 1);
        }

        if (leftDepths != null && rightDepths != null) {
            for (Integer leftDepth : leftDepths) {
                for (Integer rightDepth : rightDepths) {
                    if (leftDepth + rightDepth - 2 * depth <= distance) {
                        ans++;
                    }
                }
            }
        }
        List<Integer> list = new ArrayList<>();
        if (leftDepths != null) {
            list.addAll(leftDepths);
        }
        if (rightDepths != null) {
            list.addAll(rightDepths);
        }
        return list;
    }
    public int countPairs1(TreeNode root, int distance) {
        if (root == null) {
            return 0;
        }
        return countPairs(root, distance, new int[distance]);
    }

    public int countPairs(TreeNode root, int distance, int[] distanceList) {
        if (root.left == null && root.right == null) {
            if (distance > 1) {
                distanceList[1]++;
            }
            return 0;
        } else if (root.left == null) {
            int res = countPairs(root.right, distance, distanceList);
            if (distance - 1 >= 0) {
                System.arraycopy(distanceList, 0, distanceList, 1, distance - 1);
            }
            return res;
        } else if (root.right == null) {
            int res = countPairs(root.left, distance, distanceList);
            if (distance - 1 >= 0) {
                System.arraycopy(distanceList, 0, distanceList, 1, distance - 1);
            }
            return res;
        } else {
            int res = 0;
            int[] distancesLeft = new int[distance];
            int[] distancesRight = new int[distance];
            res += countPairs(root.left, distance, distancesLeft);
            res += countPairs(root.right, distance, distancesRight);
            for (int i = distance - 1; i > 0; i--) {
                distanceList[i] = distancesLeft[i - 1] + distancesRight[i - 1];
            }
            for (int i = 1; i < distance; i++) {
                distancesRight[i] += distancesRight[i - 1];
                res += distancesLeft[distance - i] * distancesRight[i];
            }
            return res;
        }
    }

    @Test
    public void test() {
        Serialize serialize = new Serialize();
        // Assert.assertEquals(countPairs(serialize.deserialize("1,2,3,4,5,6,7"), 3), 2);
        // Assert.assertEquals(countPairs(serialize.deserialize("1"), 1), 0);
        // Assert.assertEquals(countPairs(serialize.deserialize("1,2,3,null,4"), 3), 1);
        // Assert.assertEquals(countPairs(serialize.deserialize("7,1,4,6,null,5,3,null,null,null,null,null,2"), 3), 1);
        //error case
        Assert.assertEquals(countPairs1(serialize.deserialize("15,66,55,97,60,12,56,null,54,null,49,null,9,null,null,null,null,null,90"), 5), 3);
    }
}
