package Leetcode.daily.y2021m01;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * 1018. 可被 5 整除的二进制前缀 easy
 * 给定由若干 0 和 1 组成的数组 A。我们定义 N_i：从 A[0] 到 A[i] 的第 i 个子数组被解释为一个二进制数（从最高有效位到最低有效位）。
 * <p>
 * 返回布尔值列表 answer，只有当 N_i 可以被 5 整除时，答案 answer[i] 为 true，否则为 false。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：[0,1,1]
 * 输出：[true,false,false]
 * 解释：
 * 输入数字为 0, 01, 011；也就是十进制中的 0, 1, 3 。只有第一个数可以被 5 整除，因此 answer[0] 为真。
 * 示例 2：
 * <p>
 * 输入：[1,1,1]
 * 输出：[false,false,false]
 * 示例 3：
 * <p>
 * 输入：[0,1,1,1,1,1]
 * 输出：[true,false,false,false,true,false]
 * 示例 4：
 * <p>
 * 输入：[1,1,1,0,1]
 * 输出：[false,false,false,false,false]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 30000
 * A[i] 为 0 或 1
 *
 * @date 01/14/21 9:32
 */
public class PrefixesDivBy5 {
    public List<Boolean> prefixesDivBy5(int[] A) {
        int round = 0;
        List<Boolean> ans = new ArrayList<>(A.length);
        for (int i = 0; i < A.length; i++) {
            round = round * 2 + A[i];
            if (round % 5 == 0) {
                round = 0;
                ans.add(Boolean.TRUE);
            } else {
                round = round % 5;
                ans.add(Boolean.FALSE);
            }
        }
        return ans;
    }

    @Test
    public void test() {
        List<Boolean> ans = new ArrayList<>();
        ans.add(true);
        ans.add(false);
        ans.add(false);
        Assert.assertEquals(ans, prefixesDivBy5(new int[] {0, 1, 1}));
    }

    @Test
    public void test1() {
        List<Boolean> ans = new ArrayList<>();
        ans.add(false);
        ans.add(false);
        ans.add(false);
        Assert.assertEquals(ans, prefixesDivBy5(new int[] {1, 1, 1}));
    }

    @Test
    public void test2() {
        List<Boolean> ans = new ArrayList<>();
        ans.add(true);
        ans.add(false);
        ans.add(false);
        ans.add(false);
        ans.add(true);
        ans.add(false);
        Assert.assertEquals(ans, prefixesDivBy5(new int[] {0, 1, 1, 1, 1, 1}));
    }


    @Test
    public void test3() {
        List<Boolean> ans = new ArrayList<>();
        ans.add(false);
        ans.add(false);
        ans.add(false);
        ans.add(false);
        ans.add(false);
        Assert.assertEquals(ans, prefixesDivBy5(new int[] {1, 1, 1, 0, 1}));
    }

    @Test
    public void test4() {
        System.out.println(prefixesDivBy5(
            new int[] {1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1}));
    }
}
