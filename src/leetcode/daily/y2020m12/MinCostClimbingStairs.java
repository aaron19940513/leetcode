package leetcode.daily.y2020m12;
import org.junit.Assert;
import org.junit.Test;

/**
 * 746. 使用最小花费爬楼梯  easy
 * 数组的每个索引作为一个阶梯，第 i个阶梯对应着一个非负数的体力花费值 cost[i](索引从0开始)。
 * <p>
 * 每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择继续爬一个阶梯或者爬两个阶梯。
 * <p>
 * 您需要找到达到楼层顶部的最低花费。在开始时，你可以选择从索引为 0 或 1 的元素作为初始阶梯。
 * <p>
 * 示例 1:
 * <p>
 * 输入: cost = [10, 15, 20]
 * 输出: 15
 * 解释: 最低花费是从cost[1]开始，然后走两步即可到阶梯顶，一共花费15。
 *  示例 2:
 * <p>
 * 输入: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
 * 输出: 6
 * 解释: 最低花费方式是从cost[0]开始，逐个经过那些1，跳过cost[3]，一共花费6。
 * 注意：
 * <p>
 * cost 的长度将会在 [2, 1000]。
 * 每一个 cost[i] 将会是一个Integer类型，范围为 [0, 999]。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/min-cost-climbing-stairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 12/21/20 8:41
 */
public class MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length];
        return Math.min(minCost(cost, dp, cost.length - 1), minCost(cost, dp, cost.length - 2));
    }

    private int minCost(int[] cost, int[] dp, int endIndex) {
        if (endIndex == 1 || endIndex == 0) {
            return cost[endIndex];
        }
        if (dp[endIndex] == 0) {
            dp[endIndex] = Math.min(minCost(cost, dp, endIndex - 1), minCost(cost, dp, endIndex - 2)) + cost[endIndex];
        }
        return dp[endIndex];
    }

    @Test
    public void test() {
        Assert.assertEquals(minCostClimbingStairs(new int[] {10, 15, 20}), 15);
        Assert.assertEquals(minCostClimbingStairs(new int[] {1, 100, 1, 1, 1, 100, 1, 1, 100, 1}), 6);
    }
}
