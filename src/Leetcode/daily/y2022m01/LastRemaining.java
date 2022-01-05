package leetcode.daily.y2022m01;

import org.junit.Assert;
import org.junit.Test;

/**
 * 390. 消除游戏
 * 列表 arr 由在范围 [1, n] 中的所有整数组成，并按严格递增排序。请你对 arr 应用下述算法：
 * <p>
 * 从左到右，删除第一个数字，然后每隔一个数字删除一个，直到到达列表末尾。
 * 重复上面的步骤，但这次是从右到左。也就是，删除最右侧的数字，然后剩下的数字每隔一个删除一个。
 * 不断重复这两步，从左到右和从右到左交替进行，直到只剩下一个数字。
 * 给你整数 n ，返回 arr 最后剩下的数字。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 9
 * 输出：6
 * 解释：
 * arr = [1, 2, 3, 4, 5, 6, 7, 8, 9]
 * arr = [2, 4, 6, 8]
 * arr = [2, 6]
 * arr = [6]
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 10^9
 */
public class LastRemaining {
    public int lastRemaining(int n) {
        int start = 1;
        int end = n;
        int interval = 1;
        int count = end;
        int round = 1;
        while (count > 1) {
            int less = (count + 1) / 2;
            if (count % 2 == 1) {
                start += interval;
                end -= interval;
            } else if (round % 2 == 0) {
                end -= interval;
            } else {
                start += interval;
            }
            interval = interval * 2;
            round++;
            count = count - less;
        }
        return start;
    }

    @Test
    public void test() {
        Assert.assertEquals(1, lastRemaining(1));
        Assert.assertEquals(6, lastRemaining(9));
        Assert.assertEquals(8, lastRemaining(10));
    }
}
