package Leetcode.week;
import org.junit.Assert;
import org.junit.Test;

/**
 * 268. 缺失数字  简单
 * 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,0,1]
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: [9,6,4,2,3,5,7,0,1]
 * 输出: 8
 * <p>
 * <p>
 * 说明:
 * 你的算法应具有线性时间复杂度。你能否仅使用额外常数空间来实现?
 *
 * @date 07/15/20 11:25
 */
public class MissingNumber {
    public int missingNumber(int[] nums) {
        int sum = nums.length * (nums.length + 1) / 2;
        for (int num : nums) {
            sum -= num;
        }
        return sum;
    }

    @Test
    public void test() {
        Assert.assertEquals(missingNumber(new int[] {3, 0, 1}), 2);
        Assert.assertEquals(missingNumber(new int[] {9, 6, 4, 2, 3, 5, 7, 0, 1}), 8);
    }
}
