package leetcode.daily.y2021m01;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

/**
 * 989. 数组形式的整数加法 easy
 * 对于非负整数 X 而言，X 的数组形式是每位数字按从左到右的顺序形成的数组。例如，如果 X = 1231，那么其数组形式为 [1,2,3,1]。
 * <p>
 * 给定非负整数 X 的数组形式 A，返回整数 X+K 的数组形式。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：A = [1,2,0,0], K = 34
 * 输出：[1,2,3,4]
 * 解释：1200 + 34 = 1234
 * 示例 2：
 * <p>
 * 输入：A = [2,7,4], K = 181
 * 输出：[4,5,5]
 * 解释：274 + 181 = 455
 * 示例 3：
 * <p>
 * 输入：A = [2,1,5], K = 806
 * 输出：[1,0,2,1]
 * 解释：215 + 806 = 1021
 * 示例 4：
 * <p>
 * 输入：A = [9,9,9,9,9,9,9,9,9,9], K = 1
 * 输出：[1,0,0,0,0,0,0,0,0,0,0]
 * 解释：9999999999 + 1 = 10000000000
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 10000
 * 0 <= A[i] <= 9
 * 0 <= K <= 10000
 * 如果 A.length > 1，那么 A[0] != 0
 *
 * @date 01/22/21 6:55
 */
public class AddToArrayForm {
    public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> ans = new ArrayList<>();
        int round = 0;
        int add = 0;
        for (int i = A.length - 1; i >= 0; i--) {
            if (K > 0) {
                round = K % 10;
                K = K / 10;
            } else {
                round = 0;
            }
            add += round + A[i];
            ans.add(add % 10);
            add = add / 10;
        }
        K = K + add;
        while (K > 0) {
            ans.add(K % 10);
            K = K / 10;
        }
        Collections.reverse(ans);
        return ans;
    }

    @Test
    public void test() {
        Assert.assertEquals(Lists.newArrayList(4,0,0), addToArrayForm(new int[] {2,0,0}, 200));
        Assert.assertEquals(Lists.newArrayList(2,5,6), addToArrayForm(new int[] {9,8}, 158));
        Assert.assertEquals(Lists.newArrayList(1, 2, 3, 4), addToArrayForm(new int[] {1, 2, 0, 0}, 34));
        Assert.assertEquals(Lists.newArrayList(4, 5, 5), addToArrayForm(new int[] {2, 7, 4}, 181));
        Assert.assertEquals(Lists.newArrayList(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), addToArrayForm(new int[] {9, 9, 9, 9, 9, 9, 9, 9, 9, 9}, 1));
    }

}
