package Leetcode.daily;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 *  
 * <p>
 * 提示：
 * <p>
 * 3 <= nums.length <= 10^3
 * -10^3 <= nums[i] <= 10^3
 * -10^4 <= target <= 10^4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum-closest
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 06/24/20 16:29
 */
public class ThreeSumClosest {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int distance = Integer.MAX_VALUE;
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int start = 0;
            int end = nums.length - 1;
            while (start < end) {
                if (start == i) {
                    start++;
                    continue;
                }
                if (end == i) {
                    end--;
                    continue;
                }
                int sum = nums[i] + nums[start] + nums[end];
                if (Math.abs(target - sum) < distance) {
                    distance = Math.abs(target - sum);
                    ans = sum;
                }
                if (sum > target) {
                    end--;
                } else {
                    start++;
                }
            }
        }
        return ans;
    }

    @Test
    public void test() {
        Assert.assertEquals(threeSumClosest(new int[] {-1, 2, 1, -4}, 1), 2);
        Assert.assertEquals(threeSumClosest(new int[] {10, 20, 30, 120, 400, 500, 700}, 480), 450);
        // error case
        Assert.assertEquals(threeSumClosest(new int[] {0, 1, 2}, 0), 3);
    }
}
