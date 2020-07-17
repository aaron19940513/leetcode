package Leetcode.daily;
import org.junit.Assert;
import org.junit.Test;
import sun.plugin2.gluegen.runtime.StructAccessor;

/**
 * 309. 最佳买卖股票时机含冷冻期
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
 * <p>
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * <p>
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 示例:
 * <p>
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 07/10/20 9:52
 */
public class MaxProfit {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int length = prices.length;
        int[] dp = new int[length + 2];
        int[] trend = new int[length + 2];
        trend[0] = 0;
        trend[1] = 0;
        trend[2] = 0;
        for (int i = 3; i < length + 2; i++) {
            trend[i] = prices[i - 2] - prices[i - 3];
        }
        dp[0] = 0;
        dp[1] = 0;
        dp[2] = 0;
        for (int i = 3; i < length + 2; i++) {
            if (trend[i] > 0) {
                if (trend[i - 1] > 0) {
                    dp[i] = dp[i - 1] + trend[i];
                } else {
                    dp[i] = Math.max(dp[i - 3] + trend[i], dp[i - 2] + trend[i - 1] + trend[i]);
                    dp[i] = Math.max(dp[i], dp[i - 2]);
                }
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[length + 1];
    }

    @Test
    public void test() {
        //3 0 2 大降小增
        Assert.assertEquals(3, maxProfit(new int[] {1, 2, 3, 0, 2}));
        //40 20 60 小降大增
        Assert.assertEquals(maxProfit(new int[] {25, 30, 40, 20, 60}), 45);

        Assert.assertEquals(maxProfit(new int[] {20, 50, 40, 70}), 50);

        Assert.assertEquals(maxProfit(new int[] {10}), 0);
        Assert.assertEquals(maxProfit(new int[] {10, 20}), 10);
        //error case
        Assert.assertEquals(maxProfit(new int[] {}), 0);
        Assert.assertEquals(maxProfit(new int[] {1, 7, 2, 4}), 6);
    }
}
