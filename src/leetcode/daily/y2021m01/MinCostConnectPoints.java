package leetcode.daily.y2021m01;
import java.util.PriorityQueue;

import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

/**
 * 1584. 连接所有点的最小费用 mid
 * 给你一个points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi] 。
 * <p>
 * 连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 曼哈顿距离 ：|xi - xj| + |yi - yj| ，其中 |val| 表示 val 的绝对值。
 * <p>
 * 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
 * 输出：20
 * 解释：
 * <p>
 * 我们可以按照上图所示连接所有点得到最小总费用，总费用为 20 。
 * 注意到任意两个点之间只有唯一一条路径互相到达。
 * 示例 2：
 * <p>
 * 输入：points = [[3,12],[-2,5],[-4,1]]
 * 输出：18
 * 示例 3：
 * <p>
 * 输入：points = [[0,0],[1,1],[1,0],[-1,1]]
 * 输出：4
 * 示例 4：
 * <p>
 * 输入：points = [[-1000000,-1000000],[1000000,1000000]]
 * 输出：4000000
 * 示例 5：
 * <p>
 * 输入：points = [[0,0]]
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= points.length <= 1000
 * -106 <= xi, yi <= 106
 * 所有点 (xi, yi) 两两不同。
 *
 * @date 01/19/21 14:07
 */
public class MinCostConnectPoints {
    // 方法1:并查集
    public int minCostConnectPoints(int[][] points) {
        PriorityQueue<Pair<Pair<Integer, Integer>, Integer>> priorityQueue = new PriorityQueue<>(points.length, (t1, t2) -> {
            return t1.getValue() - t2.getValue();
        });
        int ans = 0;
        int length = points.length;
        int[] father = new int[points.length];
        for (int i = 0; i < father.length; i++) {
            father[i] = i;
        }
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                priorityQueue.offer(new Pair<>(new Pair<>(i, j), Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1])));
            }
        }
        while (!priorityQueue.isEmpty()) {
            Pair<Pair<Integer, Integer>, Integer> pair = priorityQueue.poll();
            if (union(pair.getKey().getKey(), pair.getKey().getValue(), father)) {
                ans += pair.getValue();
                length--;
                if (length == 0) {
                    break;
                }
            }
        }

        return ans;
    }

    private boolean union(int i, int j, int[] father) {
        int father1 = findFather(i, father);
        int father2 = findFather(j, father);
        if (father1 != father2) {
            father[father1] = father2;
            return true;
        }
        return false;
    }

    private int findFather(int i, int[] father) {
        return father[i] == i ? i : (father[i] = findFather(father[i], father));
    }

    //方法二 prim
    public int minCostConnectPoints1(int[][] points) {
        int ans = 0;
        int length = points.length;
        int[] VP = new int[length];
        int[] shortest = new int[length];
        VP[0] = -1;
        shortest[0] = -1;
        for (int i = 1; i < length; i++) {
            shortest[i] = Math.abs(points[i][0] - points[0][0]) + Math.abs(points[i][1] - points[0][1]);
        }
        for (int i = 0; i < length - 1; i++) {
            int minIndex = findMinIndex(shortest, VP);
            VP[minIndex] = -1;
            ans += shortest[minIndex];
            shortest[minIndex] = -1;
            for (int j = 0; j < length; j++) {
                if (VP[j] == 0) {
                    shortest[j] = Math.min(shortest[j], Math.abs(points[j][0] - points[minIndex][0]) + Math.abs(points[j][1] - points[minIndex][1]));
                }
            }
        }

        return ans;
    }

    private int findMinIndex(int[] shortest, int[] VP) {
        int minIndex = -1;
        for (int j = 0; j < shortest.length; j++) {
            if (VP[j] == 0 && (minIndex == -1 || shortest[minIndex] > shortest[j])) {
                minIndex = j;
            }
        }
        return minIndex;
    }

    @Test
    public void test() {
        Assert.assertEquals(20, minCostConnectPoints(new int[][] {{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}}));
        Assert.assertEquals(18, minCostConnectPoints(new int[][] {{3, 12}, {-2, 5}, {-4, 1}}));
        Assert.assertEquals(4, minCostConnectPoints(new int[][] {{0, 0}, {1, 1}, {1, 0}, {-1, 1}}));
        Assert.assertEquals(4000000, minCostConnectPoints(new int[][] {{-1000000, -1000000}, {1000000, 1000000}}));
        Assert.assertEquals(0, minCostConnectPoints(new int[][] {{0, 0}}));
    }

    @Test
    public void test1() {
        Assert.assertEquals(20, minCostConnectPoints1(new int[][] {{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}}));
        Assert.assertEquals(18, minCostConnectPoints1(new int[][] {{3, 12}, {-2, 5}, {-4, 1}}));
        Assert.assertEquals(4, minCostConnectPoints1(new int[][] {{0, 0}, {1, 1}, {1, 0}, {-1, 1}}));
        Assert.assertEquals(4000000, minCostConnectPoints1(new int[][] {{-1000000, -1000000}, {1000000, 1000000}}));
        Assert.assertEquals(0, minCostConnectPoints1(new int[][] {{0, 0}}));
    }
}
