package Leetcode.sort;
public class FindPeakElement {

    public int findPeakElement(int[] nums) {
        int current = 1;
        int ans = 0;
        if (nums.length <= 1) {
            return 0;
        } else if (nums[0] > nums[1]) {
            return 0;
        } else if (nums[nums.length - 1] > nums[nums.length - 2]) {
            return nums.length - 1;
        }
        while (current < nums.length - 1) {
            if (nums[current - 1] < nums[current] && nums[current] > nums[current + 1]) {
                ans = current;
                break;
            }
            current++;
        }
        return ans;
    }
}
