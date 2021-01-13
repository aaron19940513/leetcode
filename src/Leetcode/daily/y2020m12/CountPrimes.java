package Leetcode.daily.y2020m12;
import org.junit.Assert;
import org.junit.Test;

/**
 * 204. 计数质数 简单
 * 统计所有小于非负整数 n 的质数的数量。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 10
 * 输出：4
 * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 * 示例 2：
 * <p>
 * 输入：n = 0
 * 输出：0
 * 示例 3：
 * <p>
 * 输入：n = 1
 * 输出：0
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= n <= 5 * 106
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-primes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 12/03/20 8:55
 */
public class CountPrimes {
    //判断K是否为质素，只需要它是否能被根号K以外的数整除
    //这样的暴力算法容易超时
    public int countPrimes(int n) {
        int ans = 0;
        for (int i = 2; i < n; i++) {
            boolean flag = true;
            for (int k = 2; k <= Math.sqrt(i); k++) {
                if (i % k == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                ans++;
            }
        }
        return ans;
    }

    //厄拉多塞筛法
    //如果我们在进行顺序遍历时，每取得一个数（排除0、1），如果将它所有的倍数（排除0、1、本身）都清除，那么，剩下的数是不是必为素数？
    public int countPrimes1(int n) {
        int ans = 0;
        boolean[] cache = new boolean[n];
        for (int i = 2; i < n; i++) {
            if (!cache[i]) {
                ans++;
                for (int j = i + i; j < n; j = j + i) {
                    cache[j] = true;
                }
            }
        }
        return ans;
    }

    //厄拉多塞筛法+bit占位，压缩空间
    public int countPrimes2(int n) {
        int count = 0;
        //一个byte 占一个字节,即8位比特;
        byte[] signs = new byte[n / 8 + 1];
        for (int i = 2; i < n; i++) {
            if ((signs[i / 8] & (1 << (i % 8))) == 0) {
                count++;
                for (int j = i + i; j < n; j += i) {
                    //登记该数字
                    signs[j / 8] |= 1 << (j % 8);
                }
            }
        }
        return count;
    }


    @Test
    public void test() {
        Assert.assertEquals(countPrimes(0), 0);
        Assert.assertEquals(countPrimes(1), 0);
        Assert.assertEquals(countPrimes(2), 0);
        Assert.assertEquals(countPrimes(10), 4);
    }

    @Test
    public void test1() {
        Assert.assertEquals(countPrimes1(10), 4);
    }

    @Test
    public void test2() {
        Assert.assertEquals(countPrimes2(64), 18);
    }


    public static byte[] toLH(int n) {
        byte[] b = new byte[4];
        b[0] = (byte) (n & 0xff);
        b[1] = (byte) (n >> 8 & 0xff);
        b[2] = (byte) (n >> 16 & 0xff);
        b[3] = (byte) (n >> 24 & 0xff);
        return b;
    }
}
