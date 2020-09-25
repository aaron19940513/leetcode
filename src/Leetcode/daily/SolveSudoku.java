package Leetcode.daily;
import java.util.Arrays;

/**
 * 37. 解数独 困难
 * 编写一个程序，通过已填充的空格来解决数独问题。
 *
 * 一个数独的解法需遵循如下规则：
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 空白格用 '.' 表示。

 *
 * 给定的数独序列只包含数字 1-9 和字符 '.' 。
 * 你可以假设给定的数独只有唯一解。
 * 给定数独永远是 9x9 形式的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sudoku-solver
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author Sam Gao
 * @date 09/15/20 14:05
 */
public class SolveSudoku {
    public void solveSudoku(char[][] board) {
        char[][] copy = new char[9][9];
        for (int i = 0; i < board.length; i++) {
            copy[i] = Arrays.copyOf(board[i],9);
        }



        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(copy[i][j] == 0){

                }
            }

        }


    }
}
