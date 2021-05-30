package Leetcode.sort;
import org.junit.Assert;
import org.junit.Test;

/**
 * 33. 搜索旋转排序数组 mid
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * <p>
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,
 * 1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 * <p>
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 * 示例 2：
 * <p>
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 * 示例 3：
 * <p>
 * 输入：nums = [1], target = 0
 * 输出：-1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 5000
 * -10^4 <= nums[i] <= 10^4
 * nums 中的每个值都 独一无二
 * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
 * -10^4 <= target <= 10^4
 *
 * @date 04/07/21 9:02
 */
public class Search {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid;
        if (target <= nums[right]) {
            while (left <= right) {
                mid = (left + right) / 2;
                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] < target || nums[mid] > nums[nums.length - 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        } else {
            while (left <= right) {
                mid = (left + right) / 2;
                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] > target || nums[mid] < nums[0]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }

    @Test
    public void test() {
        Assert.assertEquals(1, search(new int[] {7, 0, 1, 2, 3, 4, 5}, 0));
        Assert.assertEquals(4, search(new int[] {4, 5, 6, 7, 0, 1, 2}, 0));
        Assert.assertEquals(-1, search(new int[] {4, 5, 6, 7, 0, 1, 3}, 2));
        Assert.assertEquals(2, search(new int[] {4, 5, 6, 7, 0, 1, 3}, 6));
        Assert.assertEquals(2, search(new int[] {0, 1, 2, 3, 4, 5}, 2));
        Assert.assertEquals(2, search(new int[] {5, 4, 3, 2, 1, 0}, 3));
    }

    @Test
    public void errorCase() {
        Assert.assertEquals(1, search(new int[] {3, 1}, 1));
    }
}
