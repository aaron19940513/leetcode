package leetcode.daily.y2022m01;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class NumberOfWeakCharacters {
    public int numberOfWeakCharacters(int[][] properties) {
        int result = 0;
        Arrays.sort(properties, (int[] a, int[] b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return b[1] - a[1];
        });
        int maxy = properties[properties.length - 1][1];
        for (int i = properties.length - 2; i >= 0; i--) {
            if (properties[i][1] < maxy) {
                result++;
            } else {
                maxy = properties[i][1];
            }
        }

        return result;
    }

    @Test
    public void test() {
        Assert.assertEquals(0, numberOfWeakCharacters(new int[][]{{5, 5}, {6, 3}, {3, 6}}));
        Assert.assertEquals(1, numberOfWeakCharacters(new int[][]{{2, 2}, {3, 3}}));
        Assert.assertEquals(1, numberOfWeakCharacters(new int[][]{{1, 5}, {10, 4}, {4, 3}}));
        Assert.assertEquals(1, numberOfWeakCharacters(new int[][]{{2, 1}, {2, 3}, {2, 4}, {3, 3}}));
    }

    @Test
    public void errorCase() {
        Assert.assertEquals(1, numberOfWeakCharacters(new int[][]{{1, 1}, {2, 1}, {2, 2}, {1, 2}}));
    }

}
