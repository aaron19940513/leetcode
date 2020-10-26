package Leetcode.daily;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sun.xml.internal.ws.policy.AssertionSet;
import org.junit.Assert;
import org.junit.Test;

/**
 * 763. 划分字母区间 中等
 * <p>
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：S = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 解释：
 * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
 *  
 * <p>
 * 提示：
 * <p>
 * S的长度在[1, 500]之间。
 * S只包含小写字母 'a' 到 'z' 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-labels
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 10/22/20 8:52
 */
public class PartitionLabels {
    public List<Integer> partitionLabels(String S) {
        int[] charLastIndex = new int[26];
        Arrays.fill(charLastIndex, -1);
        for (int index = S.length() - 1; index >= 0; index--) {
            if (charLastIndex[S.charAt(index) - 'a'] == -1) {
                charLastIndex[S.charAt(index) - 'a'] = index;
            }
        }
        List<Integer> ans = new ArrayList<>();
        int preIndex;
        int startIndex = 0;
        while (startIndex < S.length()) {
            preIndex = startIndex;
            int endIndex = charLastIndex[S.charAt(startIndex) - 'a'];
            while (startIndex < endIndex) {
                startIndex++;
                endIndex = Math.max(endIndex, charLastIndex[S.charAt(startIndex) - 'a']);
            }
            ans.add(endIndex - preIndex + 1);
            startIndex++;
        }
        return ans;
    }

    @Test
    public void test() {
        Assert.assertEquals(Arrays.asList(9, 7, 8), partitionLabels("ababcbacadefegdehijhklij"));
        Assert.assertEquals(Arrays.asList(24), partitionLabels("ababcbacadefegdehijhklia"));
        Assert.assertEquals(Arrays.asList(1), partitionLabels("a"));
    }
}
