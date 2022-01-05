package leetcode;
/**
 * @author sam
 * @date 11/06/19 17:40
 */
public class ArrayMerge {
    public static void main(String[] args) {
        int[] nums1 = new int[] {1, 2, 3, 0, 0, 0};
        int[] nums2 = new int[] {2, 5, 6};
        merge(nums1, 3, nums2, nums2.length);
        for (int i : nums1) {
            System.out.printf(i + " ");
        }

    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        if (m + n < nums1.length) {
            return;
        }
        int index1 = m - 1;
        int index2 = n - 1;
        for (int index = m + n - 1; index >= 0; index--) {
            if (index2 < 0) {
                return;
            } else if (index1 < 0) {
                nums1[index] = nums2[index2];
                index2--;
            } else if (nums1[index1] > nums2[index2]) {
                nums1[index] = nums1[index1];
                index1--;
            } else {
                nums1[index] = nums2[index2];
                index2--;
            }
        }
    }
}
