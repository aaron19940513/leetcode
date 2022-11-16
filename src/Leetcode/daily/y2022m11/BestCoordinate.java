package leetcode.daily.y2022m11;

import org.junit.Assert;
import org.junit.Test;

public class BestCoordinate {

    public int[] bestCoordinate(int[][] towers, int radius) {
        int maxX = 0;
        int maxY = 0;
        for (int[] tower : towers) {
            maxX = Math.max(tower[0], maxX);
            maxY = Math.max(tower[1], maxY);
        }
        int[] result = new int[]{0, 0};
        int max = 0;
        for (int i = 0; i <= maxX; i++) {
            for (int j = 0; j <= maxY; j++) {
                int temp = 0;
                for (int[] tower : towers) {
                    double dis = Math.sqrt(Math.pow(tower[0] - i, 2) + Math.pow(tower[1] - j, 2));
                    if (dis <= radius) {
                        temp += Math.floor(tower[2] / (1 + dis));
                    }
                }
                if (temp > max) {
                    max = temp;
                    result = new int[]{i, j};
                }
            }
        }
        return result;
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[]{2, 1}, bestCoordinate(new int[][]{{1, 2, 5}, {2, 1, 7}, {3, 1, 9}}, 2));
        Assert.assertArrayEquals(new int[]{23,11},bestCoordinate(new int[][]{{23,11,21}}, 9));
        Assert.assertArrayEquals(new int[]{1,2},bestCoordinate(new int[][]{{1,2,13},{2,1,7},{0,1,9}}, 2));
    }
}
