package leetcode.daily.y2021m03;
import org.junit.Assert;
import org.junit.Test;

/**
 * 59. 螺旋矩阵 II mid
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：n = 3
 * 输出：[[1,2,3],[8,9,4],[7,6,5]]
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：[[1]]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 20
 *
 * @date 03/16/21 8:44
 */
public class GenerateMatrix {
    public int[][] generateMatrix(int n) {
        int rowStart = 0;
        int rowEnd = n;
        int colStart = 0;
        int colEnd = n;
        int[][] ans = new int[n][n];
        int flag = 0;
        int index = 1;
        while (rowStart <= rowEnd && colStart <= colEnd) {
            if (flag % 4 == 0) {
                for (int i = colStart; i < colEnd; i++, index++) {
                    ans[rowStart][i] = index;
                }
                rowStart++;
            } else if (flag % 4 == 1) {
                for (int i = rowStart; i < rowEnd; i++, index++) {
                    ans[i][rowEnd-1] = index;
                }
                colEnd--;
            } else if (flag % 4 == 2) {
                for (int i = colEnd-1; i >= colStart; i--, index++) {
                    ans[rowEnd-1][i] = index;
                }
                rowEnd--;
            }else{
                for (int i = rowEnd-1; i >= rowStart; i--, index++) {
                    ans[i][colStart] = index;
                }
                colStart++;
            }
            flag++;
        }
        return ans;
    }

    @Test
    public void test(){
        Assert.assertArrayEquals(new int[][]{{1,2,3},{8,9,4},{7,6,5}},generateMatrix(3));
        Assert.assertArrayEquals(new int[][]{{1}},generateMatrix(1));
    }
}
