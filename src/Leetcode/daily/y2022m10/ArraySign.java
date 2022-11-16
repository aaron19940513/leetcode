package leetcode.daily.y2022m10;

import org.junit.Assert;
import org.junit.Test;

public class ArraySign {

    public int arraySign(int[] nums) {
        int negative = 0;
        for (int num : nums) {
            if (num == 0) {
                return 0;
            } else if (num < 0) {
                negative++;
            }
        }
        return negative % 2 == 0 ? 1 : -1;
    }

    @Test
    public void test() {
        Assert.assertEquals(1, arraySign(new int[]{-1, -2, -3, -4, 3, 2, 1}));
        Assert.assertEquals(0, arraySign(new int[]{1, 5, 0, 2, -3}));
        Assert.assertEquals(-1, arraySign(new int[]{-1, 1, -1, 1, -1}));
    }
}
