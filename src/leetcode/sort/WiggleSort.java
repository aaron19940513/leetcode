package leetcode.sort;
import java.util.Arrays;

/**
 * 小大中
 */
public class WiggleSort {
    public void wiggleSort(int[] nums) {
        int length = nums.length;
        if (length == 0 || length == 1) {
            return;
        }
        quickSelect(nums, 0, length, length / 2);
        int mid = nums[length / 2];
        int pre = 0;
        int current = 0;
        int end = length - 1;
        while (current < end) {
            if (nums[current] < mid) {
                swap(nums, current++, pre++);
            } else if (nums[current] > mid) {
                swap(nums, current, end--);
            } else {
                current++;
            }
        }
        int mid_index = (length - 1) / 2;
        int[] nums_copy = Arrays.copyOfRange(nums, 0, nums.length);
        for (int i = 0; i <= mid_index; i++) {
            nums[i * 2] = nums_copy[mid_index - i];
        }
        for (int i = 0; i < length - 1 - mid_index; i++) {
            nums[i * 2 + 1] = nums_copy[length - 1 - i];
        }
    }

    private void quickSelect(int[] nums, int begin, int end, int n) {
        //last element of array
        int sentry = nums[end - 1];
        int i = begin;
        int j = begin;
        while (j < end) {
            if (nums[j] <= sentry) {
                swap(nums, i++, j++);
            } else {
                j++;
            }
        }
        if (i - 1 > n) {
            quickSelect(nums, begin, i - 1, n);
        } else if (i <= n) {
            quickSelect(nums, i, end, n);
        }
    }

    private void swap(int[] nums, int i, int j) {
        if (i != j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    public static void main(String[] args) {
        WiggleSort wiggleSort = new WiggleSort();
        //wiggleSort.quickSelect(new int[] {1, 4, 5, 9, 6, 8, 7}, 0, 7, 3);
        int[] a = new int[] {1, 5, 1, 1, 6, 4};
        wiggleSort.wiggleSort(a);
        for (int i : a) {
            System.out.printf(i + " ");
        }
        System.out.println("------------------------------------");
        int[] b = new int[] {8, 6, 6, 6, 6, 6, 6, 1, 1, 1, 1, 1, 1};
        wiggleSort.wiggleSort(b);
        for (int i : b) {
            System.out.printf(i + " ");
        }
    }
}
