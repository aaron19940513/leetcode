package leetcode.daily.y2021m04;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * 213. 打家劫舍 II mid
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，能够偷窃到的最高金额。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,3,2]
 * 输出：3
 * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,3,1]
 * 输出：4
 * 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 3：
 * <p>
 * 输入：nums = [0]
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 1000
 *
 * @date 04/15/21 14:07
 */
public class Rob {
    public int rob(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        int nums1 = nums[0] + dfs(nums, dp, nums.length - 1, 2);
        Arrays.fill(dp, -1);
        int nums2 = dfs(nums, dp, nums.length, 1);
        return Math.max(nums1, nums2);
    }

    public int dfs(int[] nums, int[] dp, int endIndex, int index) {
        if (index >= endIndex) {
            return 0;
        }
        if (dp[index] != -1) {
            return dp[index];
        }
        dp[index] = Math.max(nums[index] + dfs(nums, dp, endIndex, index + 2), dfs(nums, dp, endIndex, index + 1));
        return dp[index];
    }

    @Test
    public void test() {
        Assert.assertEquals(3, rob(new int[] {2, 3, 2}));
        Assert.assertEquals(4, rob(new int[] {1, 2, 3, 1}));
        Assert.assertEquals(0, rob(new int[] {0}));
    }
}
