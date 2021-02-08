package Leetcode.daily.y2021m01;
import org.junit.Assert;
import org.junit.Test;

/**
 * 643. 子数组最大平均数 I
 * 给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：[1,12,-5,-6,50,3], k = 4
 * 输出：12.75
 * 解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= k <= n <= 30,000。
 * 所给数据范围 [-10,000，10,000]。
 *
 * @date 02/04/21 8:48
 */
public class FindMaxAverage {
    public double findMaxAverage(int[] nums, int k) {
        int ans = 0;
        int sum = 0;
        for (int i = 0, j = 0; j < nums.length; j++) {
            sum += nums[j];
            if (j >= k) {
                sum -= nums[i];
                i++;
                ans = Math.max(ans, sum);
            } else {
                ans = sum;
            }
        }
        return (double) ans / k;
    }

    @Test
    public void test() {
        Assert.assertEquals(12.75D, findMaxAverage(new int[] {1, 12, -5, -6, 50, 3}, 4), 0.001);
    }
}
