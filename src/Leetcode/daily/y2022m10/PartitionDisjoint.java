package leetcode.daily.y2022m10;

import org.junit.Assert;
import org.junit.Test;

public class PartitionDisjoint {

    public int partitionDisjoint(int[] nums) {
        int[] max = new int[nums.length];
        int[] min = new int[nums.length];

        int maxValue = -1;
        for (int i = 0; i < nums.length; i++) {

            max[i] = maxValue = Math.max(maxValue, nums[i]);
        }

        int minValue = Integer.MAX_VALUE;
        for (int i = nums.length - 1; i >= 0; i--) {
            min[i] = minValue = Math.min(minValue, nums[i]);
        }

        for (int i = 0; i < nums.length - 1; i++) {
            if (max[i] <= min[i + 1]) {
                return i + 1;
            }
        }

        return nums.length;
    }

    @Test
    public void test() {
        Assert.assertEquals(partitionDisjoint(new int[]{5, 0, 3, 8, 6}), 3);
        Assert.assertEquals(partitionDisjoint(new int[]{1, 1, 1, 0, 6, 12}), 4);
    }


    @Test
    public void errorCase(){
        Assert.assertEquals(partitionDisjoint(new int[] {1,1}), 1);
    }
}
