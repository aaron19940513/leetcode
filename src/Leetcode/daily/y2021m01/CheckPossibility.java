package Leetcode.daily.y2021m01;
import org.junit.Assert;
import org.junit.Test;

/**
 * 665. 非递减数列
 * 给你一个长度为 n 的整数数组，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。
 * <p>
 * 我们是这样定义一个非递减数列的： 对于数组中所有的 i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [4,2,3]
 * 输出: true
 * 解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
 * 示例 2:
 * <p>
 * 输入: nums = [4,2,1]
 * 输出: false
 * 解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
 * <p>
 * <p>
 * 说明：
 * <p>
 * 1 <= n <= 10 ^ 4
 * - 10 ^ 5 <= nums[i] <= 10 ^ 5
 *
 * @date 2021/2/7 8:38
 */
public class CheckPossibility {
    public boolean checkPossibility(int[] nums) {
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                if (count == 0) {
                    int left = i - 2 == -1 ? Integer.MIN_VALUE : nums[i - 2];
                    if (left > nums[i]) {
                        nums[i] = nums[i - 1];
                    }
                    count++;
                } else {
                    return false;
                }
            }
        }

        return true;
    }


    @Test
    public void test() {
        Assert.assertTrue(checkPossibility(new int[] {4, 2, 3}));
        Assert.assertFalse(checkPossibility(new int[] {4, 2, 1}));
        Assert.assertTrue(checkPossibility(new int[] {2, 5, 2, 3}));
        Assert.assertFalse(checkPossibility(new int[] {4, 5, 2, 3}));
        Assert.assertFalse(checkPossibility(new int[] {4, 5, 4, 3}));
    }

    @Test
    public void errorCase() {

        Assert.assertFalse(checkPossibility(new int[] {3, 4, 2, 3}));
    }
}
