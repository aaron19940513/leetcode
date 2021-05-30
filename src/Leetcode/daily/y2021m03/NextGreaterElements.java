package Leetcode.daily.y2021m03;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * 503. 下一个更大元素 II mid
 * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,1]
 * 输出: [2,-1,2]
 * 解释: 第一个 1 的下一个更大的数是 2；
 * 数字 2 找不到下一个更大的数；
 * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
 * 注意: 输入数组的长度不会超过 10000。
 *
 * @date 03/16/21 10:58
 */
public class NextGreaterElements {
    public int[] nextGreaterElements(int[] nums) {
        int[] ans = new int[nums.length];
        int[] dp = new int[nums.length];
        int[] visit = new int[nums.length];
        Arrays.fill(dp, -2);
        for (int i = 0; i < nums.length; i++) {
            ans[i] = nexGreaterIndex(nums, i, dp, visit) == -1 ? -1 : nums[dp[i]];
        }
        return ans;
    }

    private int nexGreaterIndex(int[] nums, int index, int[] dp, int[] visit) {
        if (dp[index] != -2) {
            return dp[index];
        }
        dp[index] = -3;
        int nextIndex = (index + 1) % nums.length;
        while (nextIndex != -1 && nextIndex != -3 && nums[index] >= nums[nextIndex]) {
            nextIndex = nexGreaterIndex(nums, nextIndex, dp, visit);
        }

        if (nextIndex == -1 || nextIndex == -3) {
            dp[index] = -1;
        } else {
            dp[index] = nextIndex;
        }

        return dp[index];
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[] {2, -1, 2}, nextGreaterElements(new int[] {1, 2, 1}));
        Assert.assertArrayEquals(new int[] {4, 5, 5, -1}, nextGreaterElements(new int[] {2, 4, 3, 5}));
        Assert.assertArrayEquals(new int[] {5, 5, -1, 4}, nextGreaterElements(new int[] {4, 2, 5, 3}));
        Assert.assertArrayEquals(new int[] {-1, 5, 5, 5, 5}, nextGreaterElements(new int[] {5, 4, 3, 2, 1}));
        Assert.assertArrayEquals(new int[] {}, nextGreaterElements(new int[] {}));
        Assert.assertArrayEquals(new int[] {-1}, nextGreaterElements(new int[] {1}));
        Assert.assertArrayEquals(new int[] {-1, -1, -1}, nextGreaterElements(new int[] {2, 2, 2}));
    }


}