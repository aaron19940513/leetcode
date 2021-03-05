package Leetcode.daily.y2021m03;
import org.junit.Assert;
import org.junit.Test;

/**
 * 338. 比特位计数
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2
 * 输出: [0,1,1]
 * 示例 2:
 * <p>
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 * 进阶:
 * <p>
 * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
 * 要求算法的空间复杂度为O(n)。
 * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
 *
 * @date 03/03/21 8:53
 */
public class CountBits {
    public int[] countBits(int num) {
        int[] ans = new int[num + 1];
        int totalCount = 1;
        int roundCount = 0;
        for (int i = 1; i <= num; i++) {
            if (i == totalCount) {
                totalCount *= 2;
                roundCount = 0;
            }
            ans[i] = 1 + ans[roundCount];
            roundCount++;
        }
        return ans;
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[] {0}, countBits(0));
        Assert.assertArrayEquals(new int[] {0, 1, 1}, countBits(2));
        Assert.assertArrayEquals(new int[] {0, 1, 1, 2, 1, 2}, countBits(5));
    }

}
