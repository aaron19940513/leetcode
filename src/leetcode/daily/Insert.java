package leetcode.daily;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * 57. 插入区间  困难
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 * <p>
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 * 示例 2：
 * <p>
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insert-interval
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 11/04/20 9:17
 */
public class Insert {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int flag = newInterval[0];
        List<Integer> indexList = new ArrayList<>(2);
        for (int i = 0; i < intervals.length; i++) {
            for (int j = 0; j < 2; j++) {
                if (flag < intervals[i][j]) {
                    indexList.add(4 * i + 2 * j);
                    if (indexList.size() == 2) {
                        return insert(intervals, newInterval, indexList.stream().mapToInt(Integer::intValue).toArray());
                    }
                    flag = newInterval[1];
                    j--;
                } else if (flag == intervals[i][j]) {
                    indexList.add(4 * i + 2 * j + 1);
                    if (indexList.size() == 2) {
                        return insert(intervals, newInterval, indexList.stream().mapToInt(Integer::intValue).toArray());
                    }
                    flag = newInterval[1];
                    j--;
                }
            }
        }
        while (indexList.size() < 2) {
            indexList.add(intervals.length * 4);
        }
        return insert(intervals, newInterval, indexList.stream().mapToInt(Integer::intValue).toArray());

    }

    public int[][] insert(int[][] intervals, int[] newInterval, int[] indexArr) {
        int range = 0;
        if (indexArr[1] / 4 == indexArr[0] / 4) {
            if (indexArr[0] % 4 == 0 && indexArr[1] % 4 == 0) {
                range++;
            }
        } else {
            range -= (indexArr[1] / 4 - indexArr[0] / 4);
            if (indexArr[1] % 4 == 0) {
                range++;
            }
        }
        int[][] ans = new int[intervals.length + range][2];
        for (int index = 0; index < ans.length; index++) {
            if (index < indexArr[0] / 4) {
                ans[index] = intervals[index];
            } else if (index == indexArr[0] / 4) {
                if (indexArr[1] / 4 == indexArr[0] / 4) {
                    if (indexArr[0] % 4 == 0 && indexArr[1] % 4 == 0) {
                        ans[index] = newInterval;
                    } else {
                        ans[index] = new int[] {Math.min(intervals[indexArr[0] / 4][0], newInterval[0]),
                            Math.max(intervals[indexArr[0] / 4][1], newInterval[1])};
                    }
                } else {
                    if (indexArr[1] % 4 == 0) {
                        ans[index] = new int[] {Math.min(intervals[indexArr[0] / 4][0], newInterval[0]),
                            newInterval[1]};
                    } else {
                        ans[index] = new int[] {Math.min(intervals[indexArr[0] / 4][0], newInterval[0]),
                            Math.max(intervals[indexArr[1] / 4][1], newInterval[1])};
                    }
                }
            } else {
                ans[index] = intervals[index - range];
            }
        }
        return ans;
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(insert(new int[][] {{1, 3}, {6, 9}}, new int[] {2, 5}), new int[][] {{1, 5}, {6, 9}});
        Assert.assertArrayEquals(insert(new int[][] {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[] {4, 8}), new int[][] {{1, 2}, {3, 10}, {12, 16}});

    }

    @Test
    public void test1() {
        Assert.assertArrayEquals(insert(new int[][] {}, new int[] {5, 7}), new int[][] {{5, 7}});
        Assert.assertArrayEquals(insert(new int[][] {{1, 5}}, new int[] {2, 3}), new int[][] {{1, 5}});
        Assert.assertArrayEquals(insert(new int[][] {{1, 5}, {6, 8}, {10, 15}}, new int[] {7, 20}), new int[][] {{1, 5}, {6, 20}});
    }
}
