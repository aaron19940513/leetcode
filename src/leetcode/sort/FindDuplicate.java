package leetcode.sort;
public class FindDuplicate {
    public int findDuplicate(int[] nums) {
        int sentry = nums[0];
        while (true) {
            if (nums[sentry] != sentry) {
                int temp = nums[sentry];
                nums[sentry] = sentry;
                sentry = temp;
            } else {
                return sentry;
            }
        }
    }


}
