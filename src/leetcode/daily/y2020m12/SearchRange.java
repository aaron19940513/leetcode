package leetcode.daily.y2020m12;
import org.junit.Assert;
import org.junit.Test;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * <p>
 * 进阶：
 * <p>
 * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 示例 2：
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 示例 3：
 * <p>
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums 是一个非递减数组
 * -109 <= target <= 109
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 12/01/20 13:40
 */
public class SearchRange {
    public int[] searchRange(int[] nums, int target) {
        int left = -1;
        int right = -1;
        if (nums.length == 0) {
            return new int[] {left, right};
        }
        int start = 0;
        int end = nums.length - 1;
        if (nums[start] > target) {
            return new int[] {left, right};
        } else if (nums[start] == target) {
            left = start;
        } else {
            while (start <= end) {
                int mid = (start + end) / 2;
                if (nums[mid] > target) {
                    end = mid - 1;
                } else if (nums[mid] < target) {
                    start = mid + 1;
                } else {
                    if (mid - 1 >= 0 && nums[mid - 1] != target) {
                        left = mid;
                        break;
                    }
                    end = mid - 1;
                }
            }
        }
        if (left == -1) {
            return new int[] {left, right};
        }
        start = left;
        end = nums.length - 1;
        if (nums[end] == target) {
            right = end;
        } else {
            while (start <= end) {
                int mid = (start + end) / 2;
                if (nums[mid] > target) {
                    end = mid - 1;
                } else if (nums[mid] < target) {
                    start = mid + 1;
                } else {
                    if (mid + 1 < nums.length && nums[mid + 1] != target) {
                        right = mid;
                        break;
                    }
                    start = mid + 1;
                }
            }
        }
        return new int[] {left, right};
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[] {-1, -1}, searchRange(new int[] {}, 5));
        Assert.assertArrayEquals(new int[] {-1, -1}, searchRange(new int[] {1, 3, 5}, 10));
        Assert.assertArrayEquals(new int[] {-1, -1}, searchRange(new int[] {192, 234, 555}, 100));
        Assert.assertArrayEquals(new int[] {5, 5}, searchRange(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 6));
        Assert.assertArrayEquals(new int[] {3, 6}, searchRange(new int[] {1, 2, 3, 5, 5, 5, 5, 8, 9, 10}, 5));
    }

    @Test
    public void errorCase() {
        Assert.assertArrayEquals(new int[] {0, 0}, searchRange(new int[] {1}, 1));
    }

}
