package Leetcode.daily;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

/**
 * 841. 钥匙和房间  中等
 * <p>
 * 有 N 个房间，开始时你位于 0 号房间。每个房间有不同的号码：0，1，2，...，N-1，并且房间里可能有一些钥匙能使你进入下一个房间。
 * <p>
 * 在形式上，对于每个房间 i 都有一个钥匙列表 rooms[i]，每个钥匙 rooms[i][j] 由 [0,1，...，N-1] 中的一个整数表示，其中 N = rooms.length。 钥匙 rooms[i][j] = v 可以打开编号为 v 的房间。
 * <p>
 * 最初，除 0 号房间外的其余所有房间都被锁住。
 * <p>
 * 你可以自由地在房间之间来回走动。
 * <p>
 * 如果能进入每个房间返回 true，否则返回 false。
 * <p>
 * 示例 1：
 * <p>
 * 输入: [[1],[2],[3],[]]
 * 输出: true
 * 解释:
 * 我们从 0 号房间开始，拿到钥匙 1。
 * 之后我们去 1 号房间，拿到钥匙 2。
 * 然后我们去 2 号房间，拿到钥匙 3。
 * 最后我们去了 3 号房间。
 * 由于我们能够进入每个房间，我们返回 true。
 * 示例 2：
 * <p>
 * 输入：[[1,3],[3,0,1],[2],[0]]
 * 输出：false
 * 解释：我们不能进入 2 号房间。
 * 提示：
 * <p>
 * 1 <= rooms.length <= 1000
 * 0 <= rooms[i].length <= 1000
 * 所有房间中的钥匙数量总计不超过 3000。
 *
 * @author Sam Gao
 * @date 08/31/20 9:15
 */
public class CanVisitAllRooms {

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        if (rooms == null || rooms.isEmpty()) {
            return true;
        }
        int roomCount = rooms.size();
        Set<Integer> visitRooms = new HashSet<>(roomCount);
        visitRooms.add(0);
        Stack<Integer> canVisitRooms = new Stack<>();
        canVisitRooms.push(0);
        List<Integer> keys;
        while (!canVisitRooms.isEmpty()) {
            keys = rooms.get(canVisitRooms.pop());
            for (Integer key : keys) {
                if (!visitRooms.contains(key)) {
                    visitRooms.add(key);
                    canVisitRooms.push(key);
                }
            }
            if (roomCount == visitRooms.size()) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void test() {
        List<List<Integer>> request = new ArrayList<>();
        request.add(Arrays.asList(1));
        request.add(Arrays.asList(2));
        request.add(Arrays.asList(3));
        request.add(Arrays.asList());
        Assert.assertTrue(canVisitAllRooms(request));
    }
    @Test
    public void test1() {
        List<List<Integer>> request = new ArrayList<>();
        request.add(Arrays.asList(1,3));
        request.add(Arrays.asList(3,0,1));
        request.add(Arrays.asList(2));
        request.add(Arrays.asList(0));
        Assert.assertFalse(canVisitAllRooms(request));
    }

}
