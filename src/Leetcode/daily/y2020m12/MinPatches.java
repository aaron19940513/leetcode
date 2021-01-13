package Leetcode.daily.y2020m12;
import org.junit.Assert;
import org.junit.Test;

/**
 * 330. 按要求补齐数组 hard
 * 给定一个已排序的正整数数组 nums，和一个正整数 n 。从 [1, n] 区间内选取任意个数字补充到 nums 中，使得 [1, n] 区间内的任何数字都可以用 nums 中某几个数字的和来表示。请输出满足上述要求的最少需要补充的数字个数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,3], n = 6
 * 输出: 1
 * 解释:
 * 根据 nums 里现有的组合 [1], [3], [1,3]，可以得出 1, 3, 4。
 * 现在如果我们将 2 添加到 nums 中， 组合变为: [1], [2], [3], [1,3], [2,3], [1,2,3]。
 * 其和可以表示数字 1, 2, 3, 4, 5, 6，能够覆盖 [1, 6] 区间里所有的数。
 * 所以我们最少需要添加一个数字。
 * 示例 2:
 * <p>
 * 输入: nums = [1,5,10], n = 20
 * 输出: 2
 * 解释: 我们需要添加 [2, 4]。
 * 示例 3:
 * <p>
 * 输入: nums = [1,2,2], n = 5
 * 输出: 0
 *
 * @date 12/29/20 10:50
 */
public class MinPatches {
    private int ans = 0;
    private int sum = 1;

    public int minPatches(int[] nums, int n) {
        if (nums.length == 0) {
            while (sum < n && sum > 0) {
                ans++;
                sum = 2 * sum + 1;
            }
            return ans + 1;
        }
        int i = 0;
        if (nums[0] != 1) {
            ans++;
        } else {
            i++;
        }
        for (; i <= nums.length; i++) {
            if (sum >= n) {
                return ans;
            }
            if (i == nums.length) {
                while (sum < n && sum > 0) {
                    ans++;
                    sum = 2 * sum + 1;
                }
            } else {
                minPatches(nums[i], n);
            }
        }
        return ans;
    }

    private void minPatches(int full, int n) {
        while (sum + 1 < full && sum < n && sum > 0) {
            ans++;
            sum = 2 * sum + 1;
        }
        sum += full;
    }

    @Test
    public void test() {
        Assert.assertEquals(1, minPatches(new int[] {1, 3}, 6));
    }

    @Test
    public void test1() {
        Assert.assertEquals(2, minPatches(new int[] {1, 5, 10}, 20));
    }

    @Test
    public void test2() {
        Assert.assertEquals(0, minPatches(new int[] {1, 2, 2}, 5));
    }

    @Test
    public void test3() {
        Assert.assertEquals(1, minPatches(new int[] {1, 2, 4, 5}, 13));
    }

    @Test
    public void test4() {
        Assert.assertEquals(4, minPatches(new int[] {}, 15));
    }

    @Test
    public void test5() {
        Assert.assertEquals(1, minPatches(new int[] {1, 1, 1, 4, 5}, 15));
    }

    @Test
    public void errorCase() {
        Assert.assertEquals(28, minPatches(new int[] {1, 2, 31, 33}, 2147483647));
    }
}
