package leetcode.daily.y2021m04;
import org.junit.Assert;
import org.junit.Test;

/**
 * 154. 寻找旋转排序数组中的最小值 II hard
 * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,4,4,5,6,7] 在变化后可能得到：
 * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,4]
 * 若旋转 7 次，则可以得到 [0,1,4,4,5,6,7]
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 * <p>
 * 给你一个可能存在 重复 元素值的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,3,5]
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：nums = [2,2,2,0,1]
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == nums.length
 * 1 <= n <= 5000
 * -5000 <= nums[i] <= 5000
 * nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
 * <p>
 * <p>
 * 进阶：
 * <p>
 * 这道题是 寻找旋转排序数组中的最小值 的延伸题目。
 * 允许重复会影响算法的时间复杂度吗？会如何影响，为什么？
 *
 * @date 04/09/21 9:19
 */
public class FindMin2 {
    public int findMin(int[] nums) {
        if (nums[0] < nums[nums.length - 1]) {
            return nums[0];
        }
        int left = 0;
        int right = nums.length - 1;
        int mid;
        boolean flag = (nums[left] == nums[right]);
        if (flag) {
            int value = nums[left];
            //if left+1==right
            //if left+2==right
            //if left+n==right
            while (left < right && nums[left] == value && nums[right] == value) {
                left++;
                right--;
            }
            // 313 0110
            if (nums[left] > value && nums[right] > value) {
                return value;
            }
        }
        while (left < right) {
            if (nums[left] == nums[right]) {
                left++;
                right--;
            }
            mid = (left + right) / 2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }


    @Test
    public void test() {
        Assert.assertEquals(0, findMin(new int[] {0, 0, 0, 0}));
        Assert.assertEquals(0, findMin(new int[] {7, 0, 1, 2, 3, 4, 5}));
        Assert.assertEquals(0, findMin(new int[] {4, 5, 6, 7, 0, 1, 2}));
        Assert.assertEquals(0, findMin(new int[] {4, 5, 6, 7, 0, 1, 3}));
        Assert.assertEquals(0, findMin(new int[] {4, 5, 6, 7, 0, 1, 3}));
        Assert.assertEquals(0, findMin(new int[] {0, 1, 2, 3, 4, 5}));
        Assert.assertEquals(0, findMin(new int[] {5, 4, 3, 2, 1, 0}));
    }

    @Test
    public void test1() {
        Assert.assertEquals(1, findMin(new int[] {3, 1}));
        Assert.assertEquals(0, findMin(new int[] {0, 1, 1, 0}));
        Assert.assertEquals(0, findMin(new int[] {0, 1, 2, 1, 0}));
        Assert.assertEquals(0, findMin(new int[] {2, 2, 2, 0, 2, 2}));
        Assert.assertEquals(0, findMin(new int[] {1, 0, 1, 1, 1}));
        Assert.assertEquals(2, findMin(new int[] {2, 2, 2, 3, 2, 2, 2}));
        Assert.assertEquals(1, findMin(new int[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 13, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}));
        Assert.assertEquals(1, findMin(new int[] {1}));
    }

    @Test
    public void errorCase() {
        Assert.assertEquals(1, findMin(new int[] {3,1,3}));

    }
}
