package Leetcode.array;

import java.util.Random;

public class DisruptArrays {
    private int[] nums;

    Random random = new Random();

    public DisruptArrays(int[] nums) {
        this.nums = nums;
    }

    /**
     * Resets the array to its original configuration and return it.
     */
    public int[] reset() {
        return nums;
    }

    /**
     * Returns a random shuffling of the array.
     */
    public int[] shuffle() {
        int[] a = nums.clone();
        for (int index = 0; index < a.length; index++) {
            int randomIndex = random.nextInt(a.length  - index) + index;
            if (randomIndex != index) {
                int temp = a[index];
                a[index] = a[randomIndex];
                a[randomIndex] = temp;
            }
        }
        return a;
    }


}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */

