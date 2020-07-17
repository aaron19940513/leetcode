package Leetcode.daily;
import org.junit.Assert;
import org.junit.Test;

/**
 * 96. 不同的二叉搜索树  中等
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 * <p>
 * 示例:
 * <p>
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 * <p>
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 07/15/20 14:54
 */
public class NumTrees {
    //当第i个节点作为根节点，(1,i-1)作为i节点的左子树，(i+1,n)作为i节点的右子树,可以划分了n个子问题。 左子树和右子树的笛卡尔积就是第i个节点的可能情况
    //同样(1,i-1)也可以划分为i-1个子问题
    //dp[n] 为 n个节点的二叉搜索树种类
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int k = 1; k <= i; k++) {
                dp[i] += dp[k - 1] * dp[i - k];
            }
        }
        return dp[n];
    }

    @Test
    public void test() {
        Assert.assertEquals(numTrees(1), 1);
        Assert.assertEquals(numTrees(2), 2);
        Assert.assertEquals(numTrees(3), 5);
        Assert.assertEquals(numTrees(4), 14);
    }
}


