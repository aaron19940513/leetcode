package Leetcode.daily;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * <p>
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || nums[i] > nums[i - 1]) {
                int j = i + 1;
                int k = nums.length - 1;
                while (j < k) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> item = new ArrayList<>();
                        item.add(nums[i]);
                        item.add(nums[j]);
                        item.add(nums[k]);
                        ans.add(item);
                        while (nums[j + 1] == nums[j] && j + 1 < k) {
                            j++;
                        }
                        j++;
                    } else if (nums[i] + nums[j] + nums[k] < 0) {
                        while (nums[j + 1] == nums[j] && j + 1 < k) {
                            j++;
                        }
                        j++;
                    } else {
                        while (nums[k - 1] == nums[k] && j < k - 1) {
                            k--;
                        }
                        k--;
                    }
                }
            }
        }
        return ans;
    }



    @Test
    public void test() {
        List<List<Integer>> lists = threeSum(new int[] {-2, 0, 1, 1, 2});
        //List<List<Integer>> lists = threeSum(new int[] {-1, 0, 1, 2, -1, -4});
        List<List<Integer>> lists1 = threeSum(new int[] {0, 0, 0, 0, 0, 0});
        System.out.println(lists.toString());
        System.out.println(lists1.toString());
    }
}
