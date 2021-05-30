package Leetcode.weekcompetition.week240;
import org.junit.Assert;
import org.junit.Test;

/**
 * 5752. 子数组最小乘积的最大值 显示英文描述
 * 通过的用户数303
 * 尝试过的用户数809
 * 用户总通过次数308
 * 用户总提交次数1493
 * 题目难度Medium
 * 一个数组的 最小乘积 定义为这个数组中 最小值 乘以 数组的 和 。
 * <p>
 * 比方说，数组 [3,2,5] （最小值是 2）的最小乘积为 2 * (3+2+5) = 2 * 10 = 20 。
 * 给你一个正整数数组 nums ，请你返回 nums 任意 非空子数组 的最小乘积 的 最大值 。由于答案可能很大，请你返回答案对  109 + 7 取余 的结果。
 * <p>
 * 请注意，最小乘积的最大值考虑的是取余操作 之前 的结果。题目保证最小乘积的最大值在 不取余 的情况下可以用 64 位有符号整数 保存。
 * <p>
 * 子数组 定义为一个数组的 连续 部分。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3,2]
 * 输出：14
 * 解释：最小乘积的最大值由子数组 [2,3,2] （最小值是 2）得到。
 * 2 * (2+3+2) = 2 * 7 = 14 。
 * 示例 2：
 * <p>
 * 输入：nums = [2,3,3,1,2]
 * 输出：18
 * 解释：最小乘积的最大值由子数组 [3,3] （最小值是 3）得到。
 * 3 * (3+3) = 3 * 6 = 18 。
 * 示例 3：
 * <p>
 * 输入：nums = [3,1,5,6,4,2]
 * 输出：60
 * 解释：最小乘积的最大值由子数组 [5,6,4] （最小值是 4）得到。
 * 4 * (5+6+4) = 4 * 15 = 60 。
 *
 * @date 2021/5/9 11:28
 */
public class MaxSumMinProduct {
    public int maxSumMinProduct(int[] nums) {
        int n = nums.length;
        long[] preSum = new long[n + 1];
        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = nums[i] + preSum[i];
        }
        long[][] dp = new long[n][n];
        long ans = product(dp, 0, n - 1, preSum, nums);
        return (int) (ans % (Math.pow(10, 9) + 7));
    }

    private long product(long[][] dp, int start, int endIndex, long[] preSum, int[] nums) {
        if (dp[start][endIndex] != 0) {
            return dp[start][endIndex];
        }
        dp[start][endIndex] = (preSum[endIndex + 1] - preSum[start]) * findMin(start, endIndex, nums);
        for (int i = start; i < endIndex; i++) {
            dp[start][endIndex] = Math.max(dp[start][endIndex], Math.max(product(dp, start, i, preSum, nums), product(dp, i + 1, endIndex, preSum, nums)));
        }


        return dp[start][endIndex];
    }

    private int findMin(int start, int end, int[] nums) {
        int value = Integer.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            value = Math.min(value, nums[i]);
        }
        return value;
    }

    @Test
    public void test() {
        Assert.assertEquals(14, maxSumMinProduct(new int[] {1, 2, 3, 2}));
        Assert.assertEquals(18, maxSumMinProduct(new int[] {2, 3, 3, 1, 2}));
        Assert.assertEquals(60, maxSumMinProduct(new int[] {3, 1, 5, 6, 4, 2}));
    }
}
