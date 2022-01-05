package leetcode.daily;
import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组，并返回其长度。如果不存在符合条件的连续子数组，返回 0。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：s = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的连续子数组。
 *  
 * <p>
 * 进阶：
 * <p>
 * 如果你已经完成了 O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-size-subarray-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 07/02/20 16:55
 */
public class MinSubArrayLen {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] sums = new int[nums.length + 1];
        sums[0] = 0;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            sums[i + 1] = sums[i] + nums[i];
        }
        if (sums[sums.length - 1] < s) {
            return 0;
        }
        for (int i = 0, j = 1; i < sums.length; i++) {
            while (sums[j] - sums[i] < s) {
                if (j < sums.length - 1) {
                    j++;
                } else {
                    return ans;
                }
            }
            ans = Math.min(ans, j - i);
        }
        return ans;
    }

    @Test
    public void test() {
        Assert.assertEquals(minSubArrayLen(7, new int[] {2, 3, 1, 2, 4, 3}), 2);
        Assert.assertEquals(minSubArrayLen(8, new int[] {1, 5, 2, 1, 1, 1, 1, 1}), 3);
        //error case
        Assert.assertEquals(minSubArrayLen(3, new int[] {1, 1}), 0);
        Assert.assertEquals(minSubArrayLen(11, new int[] {1, 2, 3, 4, 5}), 3);
    }
}
