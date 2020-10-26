package Leetcode.daily;
import org.junit.Assert;
import org.junit.Test;

/**
 * 844. 比较含退格的字符串 简单
 * 给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
 * <p>
 * 注意：如果对空文本输入退格字符，文本继续为空。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：S = "ab#c", T = "ad#c"
 * 输出：true
 * 解释：S 和 T 都会变成 “ac”。
 * 示例 2：
 * <p>
 * 输入：S = "ab##", T = "c#d#"
 * 输出：true
 * 解释：S 和 T 都会变成 “”。
 * 示例 3：
 * <p>
 * 输入：S = "a##c", T = "#a#c"
 * 输出：true
 * 解释：S 和 T 都会变成 “c”。
 * 示例 4：
 * <p>
 * 输入：S = "a#c", T = "b"
 * 输出：false
 * 解释：S 会变成 “c”，但 T 仍然是 “b”。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= S.length <= 200
 * 1 <= T.length <= 200
 * S 和 T 只含有小写字母以及字符 '#'。
 *  
 * <p>
 * 进阶：
 * <p>
 * 你可以用 O(N) 的时间复杂度和 O(1) 的空间复杂度解决该问题吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/backspace-string-compare
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 10/19/20 9:05
 */
public class BackspaceCompare {

    public boolean backspaceCompare(String S, String T) {
        int endIndex1 = S.length() - 1;
        int endIndex2 = T.length() - 1;
        int backCount1 = 0;
        int backCount2 = 0;
        while (endIndex1 >= 0 || endIndex2 >= 0) {
            while (endIndex1 >= 0 && (backCount1 != 0 || S.charAt(endIndex1) == '#')) {
                if (S.charAt(endIndex1) == '#') {
                    backCount1++;
                } else {
                    backCount1--;
                }
                endIndex1--;
            }
            while (endIndex2 >= 0 && (backCount2 != 0 || T.charAt(endIndex2) == '#')) {
                if (T.charAt(endIndex2) == '#') {
                    backCount2++;
                } else {
                    backCount2--;
                }
                endIndex2--;
            }

            if (endIndex1 < 0 && endIndex2 < 0) {
                return true;
            }
            if (endIndex1 < 0 || endIndex2 < 0 || S.charAt(endIndex1) != T.charAt(endIndex2)) {
                return false;
            }
            endIndex1--;
            endIndex2--;
        }
        return true;
    }

    @Test
    public void test() {
        Assert.assertTrue(backspaceCompare("ab#cadf##y", "ad#cagh##y"));
        Assert.assertTrue(backspaceCompare("ab#cad#f#y", "ad#cag#h#y"));

        Assert.assertFalse(backspaceCompare("a##c", "b"));
    }

    @Test
    public void test1() {
        Assert.assertTrue(backspaceCompare("##", "#"));
        Assert.assertTrue(backspaceCompare("##ab#cad#f#y", "#ad#cag#h#y"));
    }

}
