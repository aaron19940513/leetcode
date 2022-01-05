package leetcode.weekcompetition.week199;
import org.junit.Assert;
import org.junit.Test;

/**
 * 1528. 重新排列字符串 简单
 * 给你一个字符串 s 和一个 长度相同 的整数数组 indices 。
 * <p>
 * 请你重新排列字符串 s ，其中第 i 个字符需要移动到 indices[i] 指示的位置。
 * <p>
 * 返回重新排列后的字符串。
 * <p>
 * 示例 1：
 * 输入：s = "codeleet", indices = [4,5,6,7,0,2,1,3]
 * 输出："leetcode"
 * 解释：如图所示，"codeleet" 重新排列后变为 "leetcode" 。
 * 示例 2：
 * <p>
 * 输入：s = "abc", indices = [0,1,2]
 * 输出："abc"
 * 解释：重新排列后，每个字符都还留在原来的位置上。
 * 示例 3：
 * <p>
 * 输入：s = "aiohn", indices = [3,1,4,2,0]
 * 输出："nihao"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shuffle-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 07/27/20 17:13
 */
public class RestoreString {
    public String restoreString(String s, int[] indices) {
        char[] chars = new char[s.length()];
        // s.charAt(i) 是当前被插入的值，indices[i]是值的索引。
        for (int i = 0; i < indices.length; i++) {
            chars[indices[i]] = s.charAt(i);
        }
        StringBuilder sb = new StringBuilder();
        for (char ch : chars) {
            sb.append(ch);
        }
        return sb.toString();
    }

    @Test
    public void test() {
        Assert.assertEquals(restoreString("codeleet", new int[] {4, 5, 6, 7, 0, 2, 1, 3}), "leetcode");
    }
}
