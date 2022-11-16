package leetcode.daily.y2022m10;

import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.Assert;
import org.junit.Test;

public class ShortestBridge {

    public int shortestBridge(int[][] grid) {
        Deque<int[]> deque = new ArrayDeque<>();
        //找到第一座岛的某个点
        boolean point = false;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    //dfs找到第一坐岛的所有点
                    dfs(grid, i, j, deque);
                    point =true;
                    break;
                }
            }
            if(point){
                break;
            }
        }
        //bfs连通第二座岛
        int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        Deque<int[]> temp;
        int round = 0;
        while (true) {
            temp = new ArrayDeque<>();
            while (!deque.isEmpty()) {
                int[] ints = deque.pollFirst();
                for (int[] dir : dirs) {
                    int row = ints[0] + dir[0];
                    int col = ints[1] + dir[1];
                    if (row >= 0 && row < grid.length && col >= 0 && col < grid.length) {
                        if (grid[row][col] == 1) {
                            return round;
                        } else if (grid[row][col] == 0) {
                            grid[row][col] = -1;
                            temp.add(new int[]{row, col});
                        }
                    }
                }
            }
            deque = temp;
            round++;
        }
    }

    private int dfs(int[][] grid, int i, int j, Deque<int[]> deque) {
        if (i < 0 || i >= grid.length) {
            return -2;
        }
        if (j < 0 || j >= grid.length) {
            return -2;
        }

        if (grid[i][j] == 1) {
            grid[i][j] = -1;
        } else {
            return grid[i][j];
        }

        //up
        int dfs = dfs(grid, i - 1, j, deque);
        //down
        int dfs1 = dfs(grid, i + 1, j, deque);
        //left
        int dfs2 = dfs(grid, i, j - 1, deque);
        //right
        int dfs3 = dfs(grid, i, j + 1, deque);

        if (dfs == 0 || dfs1 == 0 || dfs2 == 0 || dfs3 == 0) {
            deque.add(new int[]{i, j});
        }

        return 1;
    }


    @Test
    public void test() {
        Assert.assertEquals(shortestBridge(new int[][]{{0, 1}, {1, 0}}), 1);
        Assert.assertEquals(shortestBridge(new int[][]{{0, 1, 0}, {0, 0, 0}, {0, 0, 1}}), 2);
        Assert.assertEquals(shortestBridge(new int[][]{{1, 1, 1, 1, 1}, {1, 0, 0, 0, 1}, {1, 0, 1, 0, 1}, {1, 0, 0, 0, 1}, {1, 1, 1, 1, 1}}), 1);
    }
}
