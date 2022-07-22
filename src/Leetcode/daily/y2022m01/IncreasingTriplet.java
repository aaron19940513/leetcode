package leetcode.daily.y2022m01;

import org.junit.Assert;
import org.junit.Test;

/**
 * 334. 递增的三元子序列
 * 给你一个整数数组 nums ，判断这个数组中是否存在长度为 3 的递增子序列。
 * <p>
 * 如果存在这样的三元组下标 (i, j, k) 且满足 i < j < k ，使得 nums[i] < nums[j] < nums[k] ，返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3,4,5]
 * 输出：true
 * 解释：任何 i < j < k 的三元组都满足题意
 * 示例 2：
 * <p>
 * 输入：nums = [5,4,3,2,1]
 * 输出：false
 * 解释：不存在满足题意的三元组
 * 示例 3：
 * <p>
 * 输入：nums = [2,1,5,0,4,6]
 * 输出：true
 * 解释：三元组 (3, 4, 5) 满足题意，因为 nums[3] == 0 < nums[4] == 4 < nums[5] == 6
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 5 * 105
 * -231 <= nums[i] <= 231 - 1
 * <p>
 * <p>
 * 进阶：你能实现时间复杂度为 O(n) ，空间复杂度为 O(1) 的解决方案吗？
 */
public class IncreasingTriplet {
    public boolean increasingTriplet(int[] nums) {
        int a = nums[0];
        Integer b = null;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= a) {
                a = nums[i];
            } else if (b == null || nums[i] <= b) {
                b = nums[i];
            } else {
                return true;
            }
        }

        return false;
    }

    @Test
    public void test() {
        Assert.assertTrue(increasingTriplet(new int[]{1, 2, 3, 4, 5}));
        Assert.assertFalse(increasingTriplet(new int[]{5, 4, 3, 2, 1}));
        Assert.assertTrue(increasingTriplet(new int[]{2, 1, 5, 0, 4, 6}));
    }

    @Test
    public void assertError() {
        Assert.assertFalse(increasingTriplet(new int[]{1, 1, 1}));
        Assert.assertTrue(increasingTriplet(new int[]{20, 100, 10, 12, 5, 13}));
    }
}
