package Leetcode.weekcompetition.week238;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * 5739. 最高频元素的频数 显示英文描述
 * <p>
 * 题目难度Medium
 * 元素的 频数 是该元素在一个数组中出现的次数。
 * <p>
 * 给你一个整数数组 nums 和一个整数 k 。在一步操作中，你可以选择 nums 的一个下标，并将该下标对应元素的值增加 1 。
 * <p>
 * 执行最多 k 次操作后，返回数组中最高频元素的 最大可能频数 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,4], k = 5
 * 输出：3
 * 解释：对第一个元素执行 3 次递增操作，对第二个元素执 2 次递增操作，此时 nums = [4,4,4] 。
 * 4 是数组中最高频元素，频数是 3 。
 * 示例 2：
 * <p>
 * 输入：nums = [1,4,8,13], k = 5
 * 输出：2
 * 解释：存在多种最优解决方案：
 * - 对第一个元素执行 3 次递增操作，此时 nums = [4,4,8,13] 。4 是数组中最高频元素，频数是 2 。
 * - 对第二个元素执行 4 次递增操作，此时 nums = [1,8,8,13] 。8 是数组中最高频元素，频数是 2 。
 * - 对第三个元素执行 5 次递增操作，此时 nums = [1,4,13,13] 。13 是数组中最高频元素，频数是 2 。
 * 示例 3：
 * <p>
 * 输入：nums = [3,9,6], k = 2
 * 输出：1
 *
 * @date 2021/4/25 10:47
 */
public class MaxFrequency {
    public int maxFrequency(int[] nums, int k) {
        int ans = 1;
        Arrays.sort(nums);
        long[] preSum = new long[nums.length + 1];
        for (int i = 1; i < nums.length + 1; i++) {
            preSum[i] = nums[i - 1] + preSum[i - 1];
        }
        for (int i = 0, j = 1; j < nums.length; j++) {
            if (nums[j] * (j - i + 1) > (preSum[j + 1] - preSum[i] + k)) {
                i++;
            }
            ans = Math.max(ans, j - i + 1);
        }
        return ans;
    }

    @Test
    public void test() {
        Assert.assertEquals(3, maxFrequency(new int[] {1, 2, 4}, 5));
        Assert.assertEquals(2, maxFrequency(new int[] {1, 4, 8, 13}, 5));
        Assert.assertEquals(1, maxFrequency(new int[] {3, 9, 6}, 2));
    }
}
