package leetcode.daily.y2022m09;

import org.junit.Assert;
import org.junit.Test;

public class GetKthMagicNumber {


    public int getKthMagicNumber(int k) {
        int k3 = 1;
        int k5 = 1;
        int k7 = 1;

        int[] road = new int[k + 1];
        road[0] = 0;
        road[1] = 1;

        for (int i = 2; i <= k; i++) {
            int n3 = road[k3] * 3;
            int n5 = road[k5] * 5;
            int n7 = road[k7] * 7;
            int min = Math.min(n3, Math.min(n5, n7));
            if (min == n3) {
                k3++;
            }
            if (min == n5) {
                k5++;
            }
            if (min == n7) {
                k7++;
            }
            road[i] = min;
        }

        return road[k];
    }


    @Test
    public void test() {
        Assert.assertEquals(getKthMagicNumber(2), 3);
        Assert.assertEquals(getKthMagicNumber(5), 9);
        Assert.assertEquals(getKthMagicNumber(10), 35);
        Assert.assertEquals(getKthMagicNumber(20), 175);
    }
}
