package leetcode.sort;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * 315. 计算右侧小于当前元素的个数
 * 给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
 * <p>
 * 示例:
 * <p>
 * 输入: [5,2,6,1]
 * 输出: [2,1,1,0]
 * 解释:
 * 5 的右侧有 2 个更小的元素 (2 和 1).
 * 2 的右侧仅有 1 个更小的元素 (1).
 * 6 的右侧有 1 个更小的元素 (1).
 * 1 的右侧有 0 个更小的元素.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CountSmaller {
    public List<Integer> countSmaller(int[] nums) {
        int[] index = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            index[i] = i;
        }
        int[] ans = new int[nums.length];
        insertSort(nums, index, ans, 0, nums.length - 1);
        return null;
    }

    private void insertSort(int[] nums, int[] index, int[] ans, int start, int end) {
        if (start == end) {
            return;
        }
        insertSort(nums, index, ans, start + 1, end);
        int value = nums[start];
        int i = start + 1;
        int j = end;
        while (i <= j) {
            int mid = i + (j - i) / 2;
            if (value > nums[mid]) {
                j = mid - 1;
            } else if (value < nums[mid]) {
                i = mid + 1;
            } else {
                break;
            }
        }
        for (int k = start; k < j; k++) {
            nums[k] = nums[k - 1];
        }
        nums[j] = value;
    }

    // private void merge(int[] nums, int[] index, int[] ans, int start, int end) {
    //     if (start == end) {
    //         return;
    //     }
    //     int mid = start + (end - start) / 2;
    //     merge(nums, index, ans, start, mid);
    //     merge(nums, index, ans, mid + 1, end);
    //     int i = start;
    //     int j = mid + 1;
    //     while (i <= mid && j <= end) {
    //         if (nums[index[i]] < nums[index[j]]) {
    //             i++;
    //         } else {
    //             j++;
    //         }
    //     }
    //     while (i < mid) {
    //         i++;
    //     }
    //     while (j < end) {
    //         j++;
    //     }
    // }

    @Test
    public void test() {
        Assert.assertEquals(countSmaller(new int[] {5, 2, 6, 1}), Arrays.asList(2, 1, 1, 0));
    }
}
