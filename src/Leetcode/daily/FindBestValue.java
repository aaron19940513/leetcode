package Leetcode.daily;
import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给你一个整数数组 arr 和一个目标值 target ，请你返回一个整数 value ，使得将数组中所有大于 value 的值变成 value 后，数组的和最接近  target （最接近表示两者之差的绝对值最小）。
 * <p>
 * 如果有多种使得和最接近 target 的方案，请你返回这些整数中的最小值。
 * <p>
 * 请注意，答案不一定是 arr 中的数字。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [4,9,3], target = 10
 * 输出：3
 * 解释：当选择 value 为 3 时，数组会变成 [3, 3, 3]，和为 9 ，这是最接近 target 的方案。
 * 示例 2：
 * <p>
 * 输入：arr = [2,3,5], target = 10
 * 输出：5
 * 示例 3：
 * <p>
 * 输入：arr = [60864,25176,27249,21296,20204], target = 56803
 * 输出：11361
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 10^4
 * 1 <= arr[i], target <= 10^5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-of-mutated-array-closest-to-target
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindBestValue {
    public int findBestValue(int[] arr, int target) {
        Arrays.sort(arr);
        int length = arr.length;
        int[] sum = new int[arr.length];
        int boundary = 0;
        int boundaryIndex = 0;
        for (; boundaryIndex < arr.length; boundaryIndex++) {
            boundary += arr[boundaryIndex];
            sum[boundaryIndex] = boundary;
            if (boundary > target || boundary == target) {
                break;
            }
        }

        int start = 0;
        int end = boundaryIndex;
        while (start < end) {
            int half = (start + end) / 2;
            if (sum[half] + arr[half] * (length - half - 1) > target) {
                end = half - 1;
            } else if (sum[half] + arr[half] * (length - half - 1) < target) {
                start = half + 1;
            } else {
                return arr[half];
            }
        }
        if (start == 0) {
            return new BigDecimal((double) target / length).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
        } else if (start == length) {
            return arr[length - 1];
        } else {
            int a = Math.abs(sum[start] - target);
            int b = Math.abs(sum[start + 1] - target);
            return a > b ? start + 1 : start;
        }

    }

    @Test
    public void test() {
        //数组中的所有数据加起来都比target小
        Assert.assertEquals(findBestValue(new int[] {9}, 10), 9);
        Assert.assertEquals(findBestValue(new int[] {6, 7, 8}, 30), 8);
        //数组中的每个数据都比target/length大
        Assert.assertEquals(findBestValue(new int[] {100}, 80), 80);
        Assert.assertEquals(findBestValue(new int[] {100, 80, 90}, 210), 70);
        Assert.assertEquals(findBestValue(new int[] {60864, 25176, 27249, 21296, 20204}, 56803), 11361);
        //数组中有一些数据比target/length大
        Assert.assertEquals(findBestValue(new int[] {2, 3, 5}, 10), 5);
        Assert.assertEquals(findBestValue(new int[] {4, 9, 3}, 10), 3);
    }
}
