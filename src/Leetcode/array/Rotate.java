package Leetcode.array;

public class Rotate {
    public void solution(int[] nums, int k) {
        if (nums.length == 1) {
            return;
        }
        k = k % nums.length;
        swap(nums, 0, nums.length - k - 1);
        swap(nums, nums.length - k, nums.length - 1);
        swap(nums, 0, nums.length - 1);

    }

    private void swap(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
