package leetcode.daily;

import org.junit.Test;

/**
 * 529. 扫雷游戏 中等
 * 让我们一起来玩扫雷游戏！
 * <p>
 * 给定一个代表游戏板的二维字符矩阵。 'M' 代表一个未挖出的地雷，'E' 代表一个未挖出的空方块，'B' 代表没有相邻（上，下，左，右，和所有4个对角线）地雷的已挖出的空白方块，数字（'1' 到 '8'）表示有多少地雷与这块已挖出的方块相邻，'X' 则表示一个已挖出的地雷。
 * <p>
 * 现在给出在所有未挖出的方块中（'M'或者'E'）的下一个点击位置（行和列索引），根据以下规则，返回相应位置被点击后对应的面板：
 * <p>
 * 如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为 'X'。
 * 如果一个没有相邻地雷的空方块（'E'）被挖出，修改它为（'B'），并且所有和其相邻的未挖出方块都应该被递归地揭露。
 * 如果一个至少与一个地雷相邻的空方块（'E'）被挖出，修改它为数字（'1'到'8'），表示相邻地雷的数量。
 * 如果在此次点击中，若无更多方块可被揭露，则返回面板。
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入:
 * <p>
 * [['E', 'E', 'E', 'E', 'E'],
 * ['E', 'E', 'M', 'E', 'E'],
 * ['E', 'E', 'E', 'E', 'E'],
 * ['E', 'E', 'E', 'E', 'E']]
 * <p>
 * Click : [3,0]
 * <p>
 * 输出:
 * <p>
 * [['B', '1', 'E', '1', 'B'],
 * ['B', '1', 'M', '1', 'B'],
 * ['B', '1', '1', '1', 'B'],
 * ['B', 'B', 'B', 'B', 'B']]
 * <p>
 * 解释:
 * <p>
 * 示例 2：
 * <p>
 * 输入:
 * <p>
 * [['B', '1', 'E', '1', 'B'],
 * ['B', '1', 'M', '1', 'B'],
 * ['B', '1', '1', '1', 'B'],
 * ['B', 'B', 'B', 'B', 'B']]
 * <p>
 * Click : [1,2]
 * <p>
 * 输出:
 * <p>
 * [['B', '1', 'E', '1', 'B'],
 * ['B', '1', 'X', '1', 'B'],
 * ['B', '1', '1', '1', 'B'],
 * ['B', 'B', 'B', 'B', 'B']]
 * <p>
 * 解释:
 * <p>
 *  
 * <p>
 * 注意：
 * <p>
 * 输入矩阵的宽和高的范围为 [1,50]。
 * 点击的位置只能是未被挖出的方块 ('M' 或者 'E')，这也意味着面板至少包含一个可点击的方块。
 * 输入面板不会是游戏结束的状态（即有地雷已被挖出）。
 * 简单起见，未提及的规则在这个问题中可被忽略。例如，当游戏结束时你不需要挖出所有地雷，考虑所有你可能赢得游戏或标记方块的情况。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minesweeper
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 08/20/20 8:54
 */
// todo
public class UpdateBoard {
    int rowLength;
    int colLength;

    public char[][] updateBoard(char[][] board, int[] click) {
        rowLength = board.length;
        colLength = board[0].length;
        return board;
    }

    //暴力算法，计算八个方向每个变量
    private void click(char[][] board, int row, int col) {
        if (board[row][col] == 'E') {
            if (board[row][col] != 'M') {
                int count = 0;
                if (row - 1 >= 0 && col - 1 >= 0 && board[row - 1][col - 1] == 'M') {
                    count++;
                }
                if (row - 1 >= 0 && board[row - 1][col] == 'M') {
                    count++;
                }
                if (row - 1 >= 0 && col + 1 < colLength && board[row - 1][col + 1] == 'M') {
                    count++;
                }
                if (col - 1 >= 0 && board[row][col - 1] == 'M') {
                    count++;
                }
                if (col + 1 < colLength && board[row][col + 1] == 'M') {
                    count++;
                }
                if (row + 1 < rowLength && col - 1 >= 0 && board[row + 1][col - 1] == 'M') {
                    count++;
                }
                if (row + 1 < rowLength && board[row + 1][col] == 'M') {
                    count++;
                }
                if (row + 1 < rowLength && col + 1 < colLength && board[row + 1][col + 1] == 'M') {
                    count++;
                }
                board[row][col] = count == 0 ? 'B' : (char) (count + '0');
            }
            //上
            click(board, row - 1, col);
            //下
            click(board, row + 1, col);
            //左
            click(board, row, col - 1);
            //右
            click(board, row, col + 1);
        }
        return;
    }

    @Test
    public void test() {

        char[][] board = new char[][] {
            {'E', 'E', 'E', 'E', 'E'},
            {'E', 'E', 'M', 'E', 'E'},
            {'E', 'E', 'E', 'E', 'E'},
            {'E', 'E', 'E', 'E', 'E'}
        };
        updateBoard(board, new int[] {3, 0});
    }
}
