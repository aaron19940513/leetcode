package leetcode.daily;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * 120. 三角形最小路径和
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * <p>
 * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 * <p>
 * <p>
 * <p>
 * 例如，给定三角形：
 * <p>
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * <p>
 * <p>
 * <p>
 * 说明：
 * <p>
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 *
 * @date 07/14/20 13:45
 */
public class MinimumTotal {
    //暴力算法超时
    public int minimumTotal1(List<List<Integer>> triangle) {
        if (triangle.isEmpty()) {
            return 0;
        }
        return deep(triangle, 0, 0, 0);
    }

    //超时
    private int deep(List<List<Integer>> triangle, int deep, int deepIndex, int sum) {
        if (deep == triangle.size() - 1) {
            return sum + triangle.get(deep).get(deepIndex);
        }
        int newSum = sum + triangle.get(deep).get(deepIndex);
        return Math.min(deep(triangle, deep + 1, deepIndex, newSum), deep(triangle, deep + 1, deepIndex + 1, newSum));
    }

    //记忆
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.isEmpty()) {
            return 0;
        }
        Integer[][] memo = new Integer[triangle.size()][triangle.size()];
        int deep = deep(triangle, memo, 0, 0);
        return deep;
    }

    private int deep(List<List<Integer>> triangle, Integer[][] memo, int deep, int deepIndex) {
        if (deep == triangle.size()) {
            return 0;
        }
        if (memo[deep][deepIndex] == null) {
            memo[deep][deepIndex] = Math.min(deep(triangle, memo, deep + 1, deepIndex) + triangle.get(deep).get(deepIndex),
                                             deep(triangle, memo, deep + 1, deepIndex + 1) + triangle.get(deep).get(deepIndex));
        }
        return memo[deep][deepIndex];
    }

    @Test
    public void test() {
        List<Integer> deep0 = Collections.singletonList(2);
        List<Integer> deep1 = Arrays.asList(3, 4);
        List<Integer> deep2 = Arrays.asList(6, 5, 7);
        List<Integer> deep3 = Arrays.asList(4, 1, 8, 4);
        Assert.assertEquals(minimumTotal(Arrays.asList(deep0, deep1, deep2, deep3)), 11);
    }
}
