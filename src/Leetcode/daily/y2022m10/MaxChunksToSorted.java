package leetcode.daily.y2022m10;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 */
public class MaxChunksToSorted {

    public int maxChunksToSorted(int[] arr) {
        int ans = 0;
        int tempL = 0;
        int tempMax = Integer.MIN_VALUE;
        int tempMin = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (i == arr[i] && tempL == 0) {
                ans++;
            } else {
                tempMax = Math.max(tempMax, arr[i]);
                tempMin = Math.min(tempMin, arr[i]);
                tempL++;
                if (tempMax == i && tempL == tempMax - tempMin + 1) {
                    tempMax = Integer.MIN_VALUE;
                    tempMin = Integer.MAX_VALUE;
                    tempL = 0;
                    ans++;
                }
            }
        }

        return ans;
    }

    @Test
    public void test() {
        Assert.assertEquals(1, maxChunksToSorted(new int[]{0}));
        Assert.assertEquals(1, maxChunksToSorted(new int[]{4, 3, 2, 1, 0}));
        Assert.assertEquals(4, maxChunksToSorted(new int[]{1, 0, 2, 3, 4}));
    }

}
