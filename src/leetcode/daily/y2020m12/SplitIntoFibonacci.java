package leetcode.daily.y2020m12;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

/**
 * 842. 将数组拆分成斐波那契序列 中等
 * 给定一个数字字符串 S，比如 S = "123456579"，我们可以将它分成斐波那契式的序列 [123, 456, 579]。
 * <p>
 * 形式上，斐波那契式序列是一个非负整数列表 F，且满足：
 * <p>
 * 0 <= F[i] <= 2^31 - 1，（也就是说，每个整数都符合 32 位有符号整数类型）；
 * F.length >= 3；
 * 对于所有的0 <= i < F.length - 2，都有 F[i] + F[i+1] = F[i+2] 成立。
 * 另外，请注意，将字符串拆分成小块时，每个块的数字一定不要以零开头，除非这个块是数字 0 本身。
 * <p>
 * 返回从 S 拆分出来的任意一组斐波那契式的序列块，如果不能拆分则返回 []。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入："123456579"
 * 输出：[123,456,579]
 * 示例 2：
 * <p>
 * 输入: "11235813"
 * 输出: [1,1,2,3,5,8,13]
 * 示例 3：
 * <p>
 * 输入: "112358130"
 * 输出: []
 * 解释: 这项任务无法完成。
 * 示例 4：
 * <p>
 * 输入："0123"
 * 输出：[]
 * 解释：每个块的数字不能以零开头，因此 "01"，"2"，"3" 不是有效答案。
 * 示例 5：
 * <p>
 * 输入: "1101111"
 * 输出: [110, 1, 111]
 * 解释: 输出 [11,0,11,11] 也同样被接受。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= S.length <= 200
 * 字符串 S 中只含有数字。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/split-array-into-fibonacci-sequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 12/08/20 9:46
 */
public class SplitIntoFibonacci {
    public static String maxInteger = String.valueOf(Integer.MAX_VALUE);

    public List<Integer> splitIntoFibonacci(String S) {
        if (S.length() < 3) {
            return new ArrayList<>();
        }
        Stack<Integer> stack = new Stack<>();
        int length = S.length();
        String F1;
        String F2;
        //i= F1.length
        for (int i = 1; i <= (length - 1) / 2; i++) {
            F1 = S.substring(0, i);
            if ((F1.startsWith("0") && i != 1)) {
                return new ArrayList<>();
            }
            if (F1.length() > maxInteger.length() || (F1.length() == maxInteger.length() && F1.compareTo(maxInteger) > 0)) {
                return new ArrayList<>();
            }
            //j = F2.length
            for (int j = 1; Math.max(i, j) <= S.length() - Math.max(i, j); j++) {
                F2 = S.substring(i, i + j);
                if (F2.startsWith("0") && j != 1) {
                    break;
                }
                if (F2.length() > maxInteger.length() || (F2.length() == maxInteger.length() && F2.compareTo(maxInteger) > 0)) {
                    break;
                }
                if (splitIntoFibonacci(F1, F2, i + j, stack, S)) {
                    List<Integer> ans = new ArrayList<>();
                    ans.add(Integer.parseInt(F1));
                    ans.add(Integer.parseInt(F2));
                    while (!stack.isEmpty()) {
                        ans.add(stack.pop());
                    }
                    return ans;
                }
            }
        }
        return new ArrayList<>();
    }

    private Boolean splitIntoFibonacci(String F1, String F2, int startIndex, Stack<Integer> stack, String S) {
        if (Integer.parseInt(F1) + Integer.parseInt(F2) < 0) {
            return false;
        }
        String sum = String.valueOf(Integer.parseInt(F1) + Integer.parseInt(F2));
        if (sum.length() + startIndex <= S.length() && sum.equals(S.substring(startIndex, startIndex + sum.length()))) {
            if (S.length() == startIndex + sum.length() || splitIntoFibonacci(F2, sum, startIndex + sum.length(), stack, S)) {
                stack.push(Integer.parseInt(sum));
                return true;
            }
            return false;
        } else {
            return false;
        }
    }

    @Test
    public void test() {
        Assert.assertEquals(Arrays.asList(123, 456, 579), splitIntoFibonacci("123456579"));
        Assert.assertEquals(Arrays.asList(1, 1, 2, 3, 5, 8, 13), splitIntoFibonacci("11235813"));
        Assert.assertEquals(Collections.emptyList(), splitIntoFibonacci("112358130"));
        Assert.assertEquals(Collections.emptyList(), splitIntoFibonacci("0123"));
        Assert.assertEquals(Arrays.asList(11, 0, 11, 11), splitIntoFibonacci("1101111"));
    }

    @Test
    public void errorCase() {
        Assert.assertEquals(Arrays.asList(13205, 8, 13213, 13221, 26434, 39655, 66089, 105744, 171833, 277577),
                            splitIntoFibonacci("1320581321313221264343965566089105744171833277577"));
        Assert.assertEquals(Collections.emptyList(), splitIntoFibonacci("214748364721474836422147483641"));
    }
}
