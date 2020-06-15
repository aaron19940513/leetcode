package Leetcode.daily;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * @author Sam Gao
 * @date 06/12/20 15:24
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || nums[i] > nums[i - 1]) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (j == i + 1 || nums[j] > nums[j - 1]) {
                        for (int k = j + 1; k < nums.length; k++) {
                            if (k == j + 1 || nums[k] > nums[k - 1]) {
                                if (nums[i] + nums[j] + nums[k] == 0) {
                                    List<Integer> item = new ArrayList<>();
                                    item.add(nums[i]);
                                    item.add(nums[j]);
                                    item.add(nums[k]);
                                    ans.add(item);
                                }
                            }
                        }
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
