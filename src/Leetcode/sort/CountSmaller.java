package Leetcode.sort;
import java.util.List;

public class CountSmaller {
    public List<Integer> countSmaller(int[] nums) {
        int[] index = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            index[i] = i;
        }
        int[] ans = new int[nums.length];
        merge(nums, index, ans, 0, nums.length - 1);
        return null;
    }

    private void merge(int[] nums, int[] index, int[] ans, int start, int end) {
        if (start == end) {
            return;
        }
        int mid = start + (end - start) / 2;
        merge(nums, index, ans, start, mid);
        merge(nums, index, ans, mid + 1, end);
        int i = start;
        int j = mid + 1;
        while (i <= mid && j <= end) {
            if (nums[index[i]] < nums[index[j]]) {
                i++;
            } else {
                j++;
            }
        }
        while (i < mid) {

            i++;
        }
        while (j < end) {
            j++;
        }


    }
}
