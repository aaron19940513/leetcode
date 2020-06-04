package Leetcode.stack;
import java.util.Random;

public class FindKthLargest {
    public int findKthLargest(int[] nums, int k) {
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        buildMaxHeap(nums);
        for (int i = 0; i < k; i++) {
            swap(nums, 0, length - 1 - i);
            adjustHeap(nums, 0, length - 1 - i);
        }
        return nums[length - k];
    }

    private static void buildMaxHeap(int[] array) {
        int length = array.length;
        for (int index = length / 2 - 1; index >= 0; index--) {
            adjustHeap(array, index, length);
        }
    }

    private static void adjustHeap(int[] array, int index, int length) {
        int maxIndex = index;
        if (index * 2 + 1 < length && array[index * 2 + 1] > array[maxIndex]) {
            maxIndex = index * 2 + 1;
        }
        if (index * 2 + 2 < length && array[index * 2 + 2] > array[maxIndex]) {
            maxIndex = index * 2 + 2;
        }
        if (maxIndex != index) {
            swap(array, index, maxIndex);
            adjustHeap(array, maxIndex, length);

        }
    }

    private static void swap(int[] array, int index, int maxIndex) {
        int temp = array[index];
        array[index] = array[maxIndex];
        array[maxIndex] = temp;
    }

    class Solution {
        int[] nums;

        public void swap(int a, int b) {
            int tmp = this.nums[a];
            this.nums[a] = this.nums[b];
            this.nums[b] = tmp;
        }


        public int partition(int left, int right, int pivot_index) {
            int pivot = this.nums[pivot_index];
            // 1. move pivot to end
            swap(pivot_index, right);
            int store_index = left;

            // 2. move all smaller elements to the left
            for (int i = left; i <= right; i++) {
                if (this.nums[i] < pivot) {
                    swap(store_index, i);
                    store_index++;
                }
            }

            // 3. move pivot to its final place
            swap(store_index, right);

            return store_index;
        }

        public int quickselect(int left, int right, int k_smallest) {
            /*
            Returns the k-th smallest element of list within left..right.
            */

            if (left == right) // If the list contains only one element,
            {
                return this.nums[left];  // return that element
            }

            // select a random pivot_index
            Random random_num = new Random();
            int pivot_index = left + random_num.nextInt(right - left);

            pivot_index = partition(left, right, pivot_index);

            // the pivot is on (N - k)th smallest position
            if (k_smallest == pivot_index) {
                return this.nums[k_smallest];
            }
            // go left side
            else if (k_smallest < pivot_index) {
                return quickselect(left, pivot_index - 1, k_smallest);
            }
            // go right side
            return quickselect(pivot_index + 1, right, k_smallest);
        }

        public int findKthLargest(int[] nums, int k) {
            this.nums = nums;
            int size = nums.length;
            // kth largest is (N - k)th smallest
            return quickselect(0, size - 1, size - k);
        }
    }

}
