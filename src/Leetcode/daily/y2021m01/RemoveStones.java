package Leetcode.daily.y2021m01;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 * 947. 移除最多的同行或同列石头 mid
 * n 块石头放置在二维平面中的一些整数坐标点上。每个坐标点上最多只能有一块石头。
 * <p>
 * 如果一块石头的 同行或者同列 上有其他石头存在，那么就可以移除这块石头。
 * <p>
 * 给你一个长度为 n 的数组 stones ，其中 stones[i] = [xi, yi] 表示第 i 块石头的位置，返回 可以移除的石子 的最大数量。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
 * 输出：5
 * 解释：一种移除 5 块石头的方法如下所示：
 * 1. 移除石头 [2,2] ，因为它和 [2,1] 同行。
 * 2. 移除石头 [2,1] ，因为它和 [0,1] 同列。
 * 3. 移除石头 [1,2] ，因为它和 [1,0] 同行。
 * 4. 移除石头 [1,0] ，因为它和 [0,0] 同列。
 * 5. 移除石头 [0,1] ，因为它和 [0,0] 同行。
 * 石头 [0,0] 不能移除，因为它没有与另一块石头同行/列。
 * 示例 2：
 * <p>
 * 输入：stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
 * 输出：3
 * 解释：一种移除 3 块石头的方法如下所示：
 * 1. 移除石头 [2,2] ，因为它和 [2,0] 同行。
 * 2. 移除石头 [2,0] ，因为它和 [0,0] 同列。
 * 3. 移除石头 [0,2] ，因为它和 [0,0] 同行。
 * 石头 [0,0] 和 [1,1] 不能移除，因为它们没有与另一块石头同行/列。
 * 示例 3：
 * <p>
 * 输入：stones = [[0,0]]
 * 输出：0
 * 解释：[0,0] 是平面上唯一一块石头，所以不可以移除它。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= stones.length <= 1000
 * 0 <= xi, yi <= 10
 * 不会有两块石头放在同一个坐标点上
 *
 * @date 01/15/21 13:40
 */
public class RemoveStones {
    public int removeStones(int[][] stones) {
        Map<Integer, List<Integer>> rowMap = new HashMap<>();
        Map<Integer, List<Integer>> colMap = new HashMap<>();
        Set<Integer> allRows = new HashSet<>();
        for (int i = 0; i < stones.length; i++) {
            List<Integer> cols = rowMap.computeIfAbsent(stones[i][0], k -> new ArrayList<>());
            cols.add(stones[i][1]);

            List<Integer> rows = colMap.computeIfAbsent(stones[i][1], k -> new ArrayList<>());
            rows.add(stones[i][0]);

            allRows.add(stones[i][0]);
        }
        int ans = 0;
        for (Integer row : allRows) {
            if (rowMap.containsKey(row)) {
                dfs(row, rowMap, colMap);
                ans++;
            }
        }
        return stones.length - ans;
    }

    private void dfs(int i, Map<Integer, List<Integer>> map1, Map<Integer, List<Integer>> map2) {
        List<Integer> list = map1.get(i);
        map1.remove(i);
        for (Integer j : list) {
            if (map2.containsKey(j)) {
                dfs(j, map2, map1);
            }
        }
    }

    @Test
    public void test() {
        Assert.assertEquals(5, removeStones(new int[][] {{0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}}));
        Assert.assertEquals(3, removeStones(new int[][] {{0, 0}, {0, 2}, {1, 1}, {2, 0}, {2, 2}}));
        Assert.assertEquals(0, removeStones(new int[][] {{0, 0}}));
    }


}
