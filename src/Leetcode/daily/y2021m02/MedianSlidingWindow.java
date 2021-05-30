package Leetcode.daily.y2021m02;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * 480. 滑动窗口中位数
 * 中位数是有序序列最中间的那个数。如果序列的大小是偶数，则没有最中间的数；此时中位数是最中间的两个数的平均数。
 * <p>
 * 例如：
 * <p>
 * [2,3,4]，中位数是 3
 * [2,3]，中位数是 (2 + 3) / 2 = 2.5
 * 给你一个数组 nums，有一个大小为 k 的窗口从最左端滑动到最右端。窗口中有 k 个数，每次窗口向右移动 1 位。你的任务是找出每次窗口移动后得到的新窗口中元素的中位数，并输出由它们组成的数组。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 给出 nums = [1,3,-1,-3,5,3,6,7]，以及 k = 3。
 * <p>
 * 窗口位置                      中位数
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       1
 * 1 [3  -1  -3] 5  3  6  7      -1
 * 1  3 [-1  -3  5] 3  6  7      -1
 * 1  3  -1 [-3  5  3] 6  7       3
 * 1  3  -1  -3 [5  3  6] 7       5
 * 1  3  -1  -3  5 [3  6  7]      6
 * 因此，返回该滑动窗口的中位数数组 [1,-1,-1,3,5,6]。
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * 你可以假设 k 始终有效，即：k 始终小于输入的非空数组的元素个数。
 * 与真实值误差在 10 ^ -5 以内的答案将被视作正确答案。
 *
 * @date 02/03/21 8:42
 */
public class MedianSlidingWindow {
    public double[] medianSlidingWindow(int[] nums, int k) {
        int[] arr = new int[k];
        double[] ans = new double[nums.length - k + 1];
        for (int i = 0; i < k; i++) {
            arr[i] = nums[i];
        }
        Arrays.sort(arr);
        ans[0] = median(arr);
        int head = 0;
        for (int i = k; i < nums.length; i++) {
            int j;
            for (j = 0; j < arr.length; j++) {
                if (arr[j] == nums[head]) {
                    arr[j] = nums[i];
                    break;
                }
            }
            insertSort(arr, j);
            head++;
            ans[i - k + 1] = median(arr);
        }
        return ans;

    }

    private void insertSort(int[] arr, int j) {
        int flag = arr[j];
        if (j + 1 < arr.length && arr[j + 1] < arr[j]) {
            for (int i = j + 1; i < arr.length; i++) {
                if (flag > arr[i]) {
                    arr[i - 1] = arr[i];
                } else {
                    arr[i - 1] = flag;
                    break;
                }
                if (i == arr.length - 1) {
                    arr[i] = flag;
                }
            }
        } else if (j - 1 >= 0 && arr[j - 1] > arr[j]) {
            for (int i = j - 1; i >= 0; i--) {
                if (flag < arr[i]) {
                    arr[i + 1] = arr[i];
                } else {
                    arr[i + 1] = flag;
                    break;
                }
                if (i == 0) {
                    arr[i] = flag;
                }
            }
        }
    }

    private double median(int[] arr) {
        if (arr.length % 2 == 0) {
            return ((double) arr[(arr.length) / 2] + (double) arr[(arr.length) / 2 - 1]) / (double) 2;
        } else {
            return arr[(arr.length - 1) / 2];
        }
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(new double[] {1, -1, -1, 3, 5, 6}, medianSlidingWindow(new int[] {1, 3, -1, -3, 5, 3, 6, 7}, 3), 0.001);
        Assert.assertArrayEquals(new double[] {2, 3, 3, 3, 4, 4.5}, medianSlidingWindow(new int[] {1, 3, -1, 3, 5, 3, -5, 6, 7}, 4), 0.001);

    }

    @Test
    public void errorCase() {
        Assert.assertArrayEquals(new double[] {2147483647.00000}, medianSlidingWindow(new int[] {2147483647, 2147483647}, 2), 0.001);

    }
}
