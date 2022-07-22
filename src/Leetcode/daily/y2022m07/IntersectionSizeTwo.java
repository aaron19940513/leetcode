package leetcode.daily.y2022m07;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

/**
 * 757. 设置交集大小至少为2
 * 一个整数区间 [a, b]  ( a < b ) 代表着从 a 到 b 的所有连续整数，包括 a 和 b。
 *
 * 给你一组整数区间intervals，请找到一个最小的集合 S，使得 S 里的元素与区间intervals中的每一个整数区间都至少有2个元素相交。
 *
 * 输出这个最小集合S的大小。
 *
 * 示例 1:
 *
 * 输入: intervals = [[1, 3], [1, 4], [2, 5], [3, 5]]
 * 输出: 3
 * 解释:
 * 考虑集合 S = {2, 3, 4}. S与intervals中的四个区间都有至少2个相交的元素。
 * 且这是S最小的情况，故我们输出3。
 * 示例 2:
 *
 * 输入: intervals = [[1, 2], [2, 3], [2, 4], [4, 5]]
 * 输出: 5
 * 解释:
 * 最小的集合S = {1, 2, 3, 4, 5}.
 * 注意:
 *
 * intervals 的长度范围为[1, 3000]。
 * intervals[i] 长度为 2，分别代表左、右边界。
 * intervals[i][j] 的值是 [0, 10^8]范围内的整数。
 */
public class IntersectionSizeTwo {

    public int intersectionSizeTwo(int[][] intervals) {
        //右端点升序，左端点降序
        Arrays.sort(intervals, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o2[0] - o2[1];
            }
            return o1[1] - o2[1];
        });
        //次大值
        int a = intervals[0][1] - 1;
        //最大值
        int b = intervals[0][1];
        int ans = 2;
        for (int i = 1; i < intervals.length; i++) {
            //无交集
            if(intervals[i][0] > b){
                ans += 2;
                a = intervals[i][1] - 1;
                b = intervals[i][1];
            } else if (intervals[i][0] > a) {
                ans++;
                a = b;
                b = intervals[i][1];
            }
        }

        return ans;
    }

    @Test
    public void test() {
        Assert.assertEquals(3, intersectionSizeTwo(new int[][]{{1, 3}, {1, 4}, {2, 5}, {3, 5}}));
        Assert.assertEquals(5, intersectionSizeTwo(new int[][]{{1, 2}, {2, 3}, {2, 4}, {4, 5}}));
    }


}
