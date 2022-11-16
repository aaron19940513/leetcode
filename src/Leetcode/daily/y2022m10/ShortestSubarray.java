package leetcode.daily.y2022m10;

import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.Assert;
import org.junit.Test;

/**
 * 862. 和至少为 K 的最短子数组 给你一个整数数组 nums 和一个整数 k ，找出 nums 中和至少为 k 的 最短非空子数组 ，并返回该子数组的长度。如果不存在这样的 子数组 ，返回 -1 。
 *
 * 子数组 是数组中 连续 的一部分。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1], k = 1 输出：1 示例 2：
 *
 * 输入：nums = [1,2], k = 4 输出：-1 示例 3：
 *
 * 输入：nums = [2,-1,2], k = 3 输出：3
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 10^5 -10^5 <= nums[i] <= 10^5 1 <= k <= 10^9
 */
public class ShortestSubarray {

    public int shortestSubarray(int[] nums, int k) {
        long[] preSum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        Deque<Integer> deque = new ArrayDeque<>();
        int result = Integer.MAX_VALUE;

        for (int i = 0; i < preSum.length; i++) {
            //处理左端点
            while (!deque.isEmpty() && k <= preSum[i] - preSum[deque.peekFirst()]) {
                Integer left = deque.pollFirst();
                result = Math.min(result, i - left);
            }
            //处理右端点
            while (!deque.isEmpty() && preSum[i] <= preSum[deque.peekLast()]) {
                Integer right = deque.pollLast();
            }
            deque.offerLast(i);
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }

    @Test
    public void test() {
        Assert.assertEquals(1, shortestSubarray(new int[]{1}, 1));
        Assert.assertEquals(-1, shortestSubarray(new int[]{1, 2}, 4));
        Assert.assertEquals(3, shortestSubarray(new int[]{2, -1, 2}, 3));
    }

    @Test
    public void testErrorCase(){
        Assert.assertEquals(1, shortestSubarray(new int[]{100000000, 100000000,100000000, 100000000,100000000, 100000000,
        100000000, 100000000,100000000, 100000000}, 1000000000));
    }

    @Test
    public void test1() {
        long a = 100000L * 100000;
        int maxValue = Integer.MAX_VALUE;
    }

}
