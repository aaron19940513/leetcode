package Leetcode.daily.y2021m01;
import org.junit.Assert;
import org.junit.Test;

/**
 * 1128. 等价多米诺骨牌对的数量
 * 给你一个由一些多米诺骨牌组成的列表 dominoes。
 * <p>
 * 如果其中某一张多米诺骨牌可以通过旋转 0 度或 180 度得到另一张多米诺骨牌，我们就认为这两张牌是等价的。
 * <p>
 * 形式上，dominoes[i] = [a, b] 和 dominoes[j] = [c, d] 等价的前提是 a==c 且 b==d，或是 a==d 且 b==c。
 * <p>
 * 在 0 <= i < j < dominoes.length 的前提下，找出满足 dominoes[i] 和 dominoes[j] 等价的骨牌对 (i, j) 的数量。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：dominoes = [[1,2],[2,1],[3,4],[5,6]]
 * 输出：1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= dominoes.length <= 40000
 * 1 <= dominoes[i][j] <= 9
 *
 * @date 01/26/21 17:59
 */
public class NumEquivDominoPairs {
    public int numEquivDominoPairs(int[][] dominoes) {
        int[] flag = new int[100];

        int ans = 0;
        for (int i = 0; i < dominoes.length; i++) {
            int temp = Math.max(dominoes[i][0] * 10 + dominoes[i][1], dominoes[i][1] * 10 + dominoes[i][0]);
            ans += flag[temp];
            flag[temp]++;
        }
        return ans;
    }

    @Test
    public void test() {
        Assert.assertEquals(numEquivDominoPairs(new int[][] {{1, 2}, {2, 1}, {3, 4}, {5, 6}}), 1);
    }
}
