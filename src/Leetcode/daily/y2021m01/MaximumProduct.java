package Leetcode.daily.y2021m01;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * 628. 三个数的最大乘积 easy
 * 给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3]
 * 输出: 6
 * 示例 2:
 * <p>
 * 输入: [1,2,3,4]
 * 输出: 24
 * 注意:
 * <p>
 * 给定的整型数组长度范围是[3,10 4次方]，数组中所有的元素范围是[-1000, 1000]。
 * 输入的数组中任意三个数的乘积不会超出32位有符号整数的范围。
 *
 * @date 01/20/21 11:04
 */
public class MaximumProduct {
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int multi1 = nums[0]*nums[1];
        int multi2 = nums[nums.length - 1];
        int multi3 = nums[nums.length - 1]*nums[nums.length - 2]*nums[nums.length - 3];


        return Math.max(multi1 * multi2, multi3);
    }

    @Test
    public void test() {
        Assert.assertEquals(6, maximumProduct(new int[] {1, 2, 3}));
        Assert.assertEquals(24, maximumProduct(new int[] {1, 2, 3, 4}));
    }
}
