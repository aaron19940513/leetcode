package Leetcode.daily;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

/**
 * 130. 被围绕的区域 中等
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 * <p>
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * <p>
 * 示例:
 * <p>
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 运行你的函数后，矩阵变为：
 * <p>
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * 解释:
 * <p>
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/surrounded-regions
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 08/11/20 8:59
 */
public class SurroundingArea {
    /**
     * X: -1表示
     * O:
     * 1.贴边的O,0b0TTTT
     * 2.中间的O,0b1TTTT表示，
     * T分表代表是上下左右，如果上是X,占位是1，上是O，占位为0，
     */
    public void surroundingArea1(char[][] board) {
        int rowLength = board.length;
        if (rowLength == 0) {
            return;
        }
        int colLength = board[0].length;
        int[][] dp = new int[rowLength][colLength];
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                if (board[i][j] == 'X') {
                    dp[i][j] = -1;
                } else if (i == 0 || i == rowLength - 1 || j == 0 || j == colLength - 1) {
                    dp[i][j] = -1;
                } else {
                    int up = board[i - 1][j] == 'X' ? 1 : 0;
                    int down = board[i + 1][j] == 'X' ? 1 : 0;
                    int left = board[i][j - 1] == 'X' ? 1 : 0;
                    int right = board[i][j + 1] == 'X' ? 1 : 0;
                    dp[i][j] = up << 3 | down << 2 | left << 1 | right;
                }
            }
        }

        Stack<Integer> indexIndex = new Stack<>();
        int flag = 0;
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                if (dp[i][j] != 15) {
                    Set<Integer> set = new HashSet<>();
                    set.add(i * colLength + j);
                    indexIndex.push(i * colLength + j);
                    while (!indexIndex.isEmpty()) {
                        Integer pop = indexIndex.pop();
                        int value = dp[pop / colLength][pop % colLength];
                        if (value == -1) {
                            flag = -1;
                        } else {
                            if ((dp[pop / colLength][pop % colLength] | 0b0111) == 0b0111) {
                                if (!set.contains(pop - colLength)) {
                                    set.add(pop - colLength);
                                    indexIndex.push(pop - colLength);
                                }
                            }
                            if ((dp[pop / colLength][pop % colLength] | 0b1011) == 0b1011) {
                                if (!set.contains(pop - colLength)) {
                                    set.add(pop + colLength);
                                    indexIndex.push(pop + colLength);
                                }
                            }
                            if ((dp[pop / colLength][pop % colLength] | 0b1101) == 0b1101) {
                                if (!set.contains(pop - 1)) {
                                    set.add(pop - 1);
                                    indexIndex.push(pop - 1);
                                }
                            }
                            if ((dp[pop / colLength][pop % colLength] | 0b1110) == 0b1110) {
                                if (!set.contains(pop + 1)) {
                                    set.add(pop + 1);
                                    indexIndex.push(pop + 1);
                                }
                            }
                        }
                        dp[pop / colLength][pop % colLength] = 15;
                    }
                    if (flag == 0) {
                        modifyBoard(board, set, colLength);
                    }
                }
            }
        }
    }

    public void surroundingArea(char[][] board) {
        int rowLength = board.length;
        if (rowLength == 0) {
            return;
        }
        int colLength = board[0].length;
        int[][] dp = new int[rowLength][colLength];

        Stack<Integer> indexIndex = new Stack<>();
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                if (board[i][j] == 'O' && dp[i][j] == 0) {
                    Set<Integer> set = new HashSet<>();
                    int flag = 0;
                    // save index
                    set.add(i * colLength + j);
                    indexIndex.push(i * colLength + j);
                    while (!indexIndex.isEmpty()) {
                        Integer pop = indexIndex.pop();
                        int row = pop / colLength;
                        int col = pop % colLength;
                        dp[row][col] = 1;
                        // out range
                        if (row == 0 || row == rowLength - 1 || col == 0 || col == colLength - 1) {
                            flag = -1;
                        }
                        //上
                        if (!set.contains(pop - colLength) && row != 0 && board[row - 1][col] == 'O') {
                            set.add(pop - colLength);
                            indexIndex.push(pop - colLength);
                        }
                        //下
                        if (!set.contains(pop + colLength) && row != rowLength - 1 && board[row + 1][col] == 'O') {
                            set.add(pop + colLength);
                            indexIndex.push(pop + colLength);
                        }
                        //左
                        if (!set.contains(pop - 1) && col != 0 && board[row][col - 1] == 'O') {
                            set.add(pop - 1);
                            indexIndex.push(pop - 1);
                        }
                        //右
                        if (!set.contains(pop + 1) && col != colLength - 1 && board[row][col + 1] == 'O') {
                            set.add(pop + 1);
                            indexIndex.push(pop + 1);
                        }
                    }
                    if (flag == 0) {
                        modifyBoard(board, set, colLength);
                    }
                }
                dp[i][j] = 1;
            }
        }
    }

    private void modifyBoard(char[][] board, Set<Integer> set, int colLength) {
        char target = 'X';
        for (Integer index : set) {
            board[index / colLength][index % colLength] = target;
        }
    }

    @Test
    public void test() {
        char[][] board = new char[][] {{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
        surroundingArea(board);
        Assert.assertEquals(board, new char[][] {{'X', 'X', 'X', 'X'}, {'X', 'X', 'X', 'X'}, {'X', 'X', 'X', 'X'}, {'X', 'O', 'X', 'X'}});
    }

    @Test
    public void test1() {
        char[][] board = new char[][] {{'X', 'O', 'O', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
        surroundingArea(board);
        Assert.assertEquals(board, new char[][] {{'X', 'O', 'O', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}});
    }

    @Test
    public void test2() {
        char[][] board = new char[][] {{'X', 'X', 'X', 'X'}, {'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
        surroundingArea(board);
    }

    // error case
    @Test
    public void test3() {
        char[][] board = new char[][] {};
        Assert.assertEquals(board, new char[][] {});
    }

    @Test
    public void test4() {
        char[][] board = new char[][] {{'X', 'X', 'X'}, {'X', 'O', 'X'}, {'X', 'X', 'X'}};
        surroundingArea(board);
        Assert.assertEquals(board, new char[][] {{'X', 'X', 'X'}, {'X', 'X', 'X'}, {'X', 'X', 'X'}});
    }
}
