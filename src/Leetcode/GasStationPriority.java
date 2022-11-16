package leetcode;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Sets;

public class GasStationPriority {

    public int[] gasStationPriority(int[] origin, int[] spec, Set<Integer> originSpec) {
        int i = 0;
        int j = 0;
        int k = 0;
        for (; j < origin.length && i < origin.length && k <spec.length; i++, j++) {
            while (i < origin.length && i != spec[k]) {
                i++;
            }
            k++;
            while (j < origin.length && !originSpec.contains(j)) {
                j++;
            }
            if (i == origin.length || j == origin.length) {
                break;
            }
            // 品牌油站本来就在前面
            if (j <= i) {
                continue;
            } else {
                int temp = origin[j];
                int m = j;
                while (m > i) {
                    origin[m] = origin[m - 1];
                    m--;
                }
                origin[i] = temp;
            }
        }
        return origin;

    }

    @Test
    public void test() {

        // no originSpec
        Assert.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, gasStationPriority(new int[]{1, 2, 3, 4, 5},
            new int[]{0, 1, 2}, new HashSet<>()));

        // originSpec < spec
        Assert.assertArrayEquals(new int[]{2, 4, 1, 3, 5}, gasStationPriority(new int[]{1, 2, 3, 4, 5},
            new int[]{0, 1, 2}, Sets.newHashSet(1, 3)));

        // originSpec == spec
        Assert.assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 9, 7, 8, 10}, gasStationPriority(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
            new int[]{2, 4, 6}, Sets.newHashSet(0, 4, 8)));

        // originSpec > spec
        Assert.assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 9, 7, 8, 10}, gasStationPriority(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
            new int[]{2, 4, 6}, Sets.newHashSet(0, 4, 8, 9)));

        Assert.assertArrayEquals(new int[]{1, 2, 3, 6, 7, 4, 5}, gasStationPriority(new int[]{1, 2, 3, 4, 5, 6, 7},
            new int[]{2, 3 ,4}, Sets.newHashSet(1,5,6)));
    }

}
