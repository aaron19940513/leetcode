package Leetcode.daily.y2021m02;
import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.Assert;
import org.junit.Test;

/**
 * 1004. 最大连续1的个数 III
 * 给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。
 * <p>
 * 返回仅包含 1 的最长（连续）子数组的长度。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
 * 输出：6
 * 解释：
 * [1,1,1,0,0,1,1,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 6。
 * 示例 2：
 * <p>
 * 输入：A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
 * 输出：10
 * 解释：
 * [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 10。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 20000
 * 0 <= K <= A.length
 * A[i] 为 0 或 1
 *
 * @date 02/19/21 9:45
 */
public class LongestOnes {
    public int longestOnes(int[] A, int K) {
        Deque<Integer> dq = new ArrayDeque<>(K);
        int ans = 0;
        int tempLength = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 0) {
                if (dq.size() < K) {
                    dq.offer(i);
                } else if (K > 0) {
                    ans = Math.max(ans, tempLength);
                    Integer first = dq.pollFirst();
                    tempLength = i - first - 1;
                    dq.offer(i);
                } else {
                    ans = Math.max(ans, tempLength);
                    tempLength = -1;
                }
            }
            tempLength++;
        }
        return Math.max(ans, tempLength);
    }

    @Test
    public void test() {
        Assert.assertEquals(longestOnes(new int[] {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2), 6);
        Assert.assertEquals(longestOnes(new int[] {0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3), 10);
        Assert.assertEquals(longestOnes(new int[] {0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 0), 4);
    }


}
