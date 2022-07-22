package leetcode.daily.y2022m02;

import org.junit.Test;

public class KnightProbability {
    int[][] dirs = new int[][]{{-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {-2, 1}, {-2, -1}, {2, 1}, {2, -1}};

    public double knightProbability(int n, int k, int row, int column) {
        double[][][] dp = new double[n][n][k + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][0] = 1;
            }
        }

        for (int i = 1; i <= k; i++) {
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    for (int[] dir : dirs) {
                        if (x + dir[0] >= 0 && x + dir[0] < n && y + dir[1] >= 0 && y + dir[1] < n) {
                            dp[x][y][i] += dp[x + dir[0]][y + dir[1]][i - 1] / 8.0;
                        }
                    }
                }
            }
        }

        return dp[row][column][k];
    }

    @Test
    public void test() {
        System.out.println(knightProbability(3, 2, 0, 0));
    }


}
