package leetcode.daily.y2022m11;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class OrderOfLargestPlusSign {

    public int orderOfLargestPlusSign(int n, int[][] mines) {
        Set<Integer> zeroIndexSet = new HashSet<>();
        for (int[] mine : mines) {
            zeroIndexSet.add(getIndex(n, mine[0], mine[1]));
        }

        int result = 0;
        int maxRound = (n + 1) / 2;
        for (int i = 1; i <= (n + 1) / 2; i++, maxRound--) {
            if (result >= maxRound) {
                return result;
            }
            int[][] next = next(n, i);
            for (int[] position : next) {
                int round = getRound(zeroIndexSet, n, position[0], position[1]);
                if (round == maxRound) {
                    return maxRound;
                }
                result = Math.max(result, round);
            }
        }

        return result;
    }

    public int[][] next(int n, int round) {
        round -= 1;
        int left = 0;
        int right = 0;
        if (n % 2 == 0) {
            left = n / 2 - 1 - round;
            right = n / 2 + round;
        } else {
            left = (n - 1) / 2 - round;
            right = (n - 1) / 2 + round;
        }

        if (left == right) {
            return new int[][]{{left, right}};
        }
        int length = (right - left) * 4;
        int[][] result = new int[length][2];
        int temp = 0;
        //左上角 往右走
        for (int j = 0; j < right - left; j++, temp++) {
            result[temp] = new int[]{left, left + j};
        }
        //右上角  往下走
        for (int j = 0; j < right - left; j++, temp++) {
            result[temp] = new int[]{left + j, right};
        }
        //右下角  往左走
        for (int j = 0; j < right - left; j++, temp++) {
            result[temp] = new int[]{right, right - j};
        }
        //左下角 往上走
        for (int j = 0; j < right - left; j++, temp++) {
            result[temp] = new int[]{right - j, left};
        }

        return result;
    }

    private int getIndex(int n, int row, int col) {
        return row  * n + col;
    }

    private int getRound(Set<Integer> zeroIndexSet, int n, int row, int col) {
        int result = 0;
        while (true) {
            //up
            if (row - result < 0 || zeroIndexSet.contains(getIndex(n, row - result, col))) {
                break;
            }
            //down
            if (row + result == n || zeroIndexSet.contains(getIndex(n, row + result, col))) {
                break;
            }
            //left
            if (col - result < 0 || zeroIndexSet.contains(getIndex(n, row, col - result))) {
                break;
            }
            //right
            if (col + result == n || zeroIndexSet.contains(getIndex(n, row, col + result))) {
                break;
            }
            result++;
        }

        return result;
    }

    @Test
    public void test() {
        Assert.assertEquals(2, orderOfLargestPlusSign(5, new int[][]{{4, 2}}));
        Assert.assertEquals(2, orderOfLargestPlusSign(5, new int[][]{{3, 2},{4, 2}}));
        Assert.assertEquals(1, orderOfLargestPlusSign(1, new int[][]{{0, 0}}));
    }

    @Test
    public void testRound() {
        System.out.println(Arrays.deepToString(next(5, 1)));
        System.out.println(Arrays.deepToString(next(5, 2)));
        System.out.println(Arrays.deepToString(next(6, 1)));
        System.out.println(Arrays.deepToString(next(6, 2)));
    }
}
