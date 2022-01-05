package leetcode.daily.y2020m12;
import org.junit.Assert;
import org.junit.Test;

/**
 * 738. 单调递增的数字  mid
 * 给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
 * <p>
 * （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）
 * <p>
 * 示例 1:
 * <p>
 * 输入: N = 10
 * 输出: 9
 * 示例 2:
 * <p>
 * 输入: N = 1234
 * 输出: 1234
 * 示例 3:
 * <p>
 * 输入: N = 332
 * 输出: 299
 * 说明: N 是在 [0, 10^9] 范围内的一个整数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/monotone-increasing-digits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 12/15/20 9:08
 */
public class MonotoneIncreasingDigits {
    public int monotoneIncreasingDigits(int N) {
        String str = String.valueOf(N);
        int[] arr = new int[str.length()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(String.valueOf(str.charAt(i)));
        }
        int sentry = -1;
        int i = 1;
        boolean increase = true;
        for (; i < arr.length; i++) {
            if (arr[i] > arr[i - 1]) {
                sentry = -1;
            } else if (arr[i] == arr[i - 1]) {
                if (sentry == -1) {
                    sentry = i - 1;
                }
            } else {
                if (sentry == -1) {
                    sentry = i - 1;
                }
                increase = false;
                break;
            }
        }
        if (increase) {
            return N;
        } else {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < arr.length; j++) {
                if (j < sentry) {
                    sb.append(arr[j]);
                } else if (j == sentry) {
                    sb.append((arr[j] - 1));
                } else {
                    sb.append('9');
                }
            }
            return Integer.parseInt(sb.toString());
        }
    }

    @Test
    public void test() {
        Assert.assertEquals(9, monotoneIncreasingDigits(10));
        Assert.assertEquals(1234, monotoneIncreasingDigits(1234));
        Assert.assertEquals(1111, monotoneIncreasingDigits(1111));
        Assert.assertEquals(299, monotoneIncreasingDigits(332));
        Assert.assertEquals(999, monotoneIncreasingDigits(1100));
        Assert.assertEquals(123449999, monotoneIncreasingDigits(123454321));
    }
}
