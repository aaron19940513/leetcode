package leetcode.weekcompetition.week227;
import org.junit.Assert;
import org.junit.Test;

/**
 * 5674. 构造字典序最大的合并字符串 显示英文描述
 * 通过的用户数402
 * 尝试过的用户数681
 * 用户总通过次数404
 * 用户总提交次数977
 * 题目难度Medium
 * 给你两个字符串 word1 和 word2 。你需要按下述方式构造一个新字符串 merge ：如果 word1 或 word2 非空，选择 下面选项之一 继续操作：
 * <p>
 * 如果 word1 非空，将 word1 中的第一个字符附加到 merge 的末尾，并将其从 word1 中移除。
 * 例如，word1 = "abc" 且 merge = "dv" ，在执行此选项操作之后，word1 = "bc" ，同时 merge = "dva" 。
 * 如果 word2 非空，将 word2 中的第一个字符附加到 merge 的末尾，并将其从 word2 中移除。
 * 例如，word2 = "abc" 且 merge = "" ，在执行此选项操作之后，word2 = "bc" ，同时 merge = "a" 。
 * 返回你可以构造的字典序 最大 的合并字符串 merge 。
 * <p>
 * 长度相同的两个字符串 a 和 b 比较字典序大小，如果在 a 和 b 出现不同的第一个位置，a 中字符在字母表中的出现顺序位于 b 中相应字符之后，就认为字符串 a 按字典序比字符串 b 更大。例如，"abcd" 按字典序比 "abcc" 更大，因为两个字符串出现不同的第一个位置是第四个字符，而 d
 * 在字母表中的出现顺序位于 c 之后。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：word1 = "cabaa", word2 = "bcaaa"
 * 输出："cbcabaaaaa"
 * 解释：构造字典序最大的合并字符串，可行的一种方法如下所示：
 * - 从 word1 中取第一个字符：merge = "c"，word1 = "abaa"，word2 = "bcaaa"
 * - 从 word2 中取第一个字符：merge = "cb"，word1 = "abaa"，word2 = "caaa"
 * - 从 word2 中取第一个字符：merge = "cbc"，word1 = "abaa"，word2 = "aaa"
 * - 从 word1 中取第一个字符：merge = "cbca"，word1 = "baa"，word2 = "aaa"
 * - 从 word1 中取第一个字符：merge = "cbcab"，word1 = "aa"，word2 = "aaa"
 * - 将 word1 和 word2 中剩下的 5 个 a 附加到 merge 的末尾。
 * 示例 2：
 * <p>
 * 输入：word1 = "abcabc", word2 = "abdcaba"
 * 输出："abdcabcabcaba"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= word1.length, word2.length <= 3000
 * word1 和 word2 仅由小写英文组成
 *
 * @date 2021/2/7 11:18
 */
public class LargestMerge {
    public String largestMerge(String word1, String word2) {
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        int j = 0;
        while (i < word1.length() && j < word2.length()) {
            if (word1.charAt(i) == word2.charAt(j)) {
                int m = i;
                int n = j;
                while (m < word1.length() && n < word2.length() && word1.charAt(m) == word2.charAt(n)) {
                    m++;
                    n++;
                }
                if (m == word1.length()) {
                    stringBuffer.append(word1, i, Math.min(m + 1, word1.length()));
                    i = m + 1;
                    break;
                }
                if (n == word2.length()) {
                    stringBuffer.append(word2, j, Math.min(n + 1, word2.length()));
                    j = n + 1;
                    break;
                }
                if (word1.charAt(m) > word2.charAt(n)) {
                    stringBuffer.append(word1, i, Math.min(m + 1, word1.length()));
                    i = m + 1;
                } else {
                    stringBuffer.append(word2, j, Math.min(n + 1, word2.length()));
                    j = n + 1;
                }
            }
            while (i < word1.length() && j < word2.length() && word1.charAt(i) > word2.charAt(j)) {
                stringBuffer.append(word1.charAt(i++));
            }
            while (i < word1.length() && j < word2.length() && word1.charAt(i) < word2.charAt(j)) {
                stringBuffer.append(word2.charAt(j++));
            }
        }
        if (i < word1.length()) {
            stringBuffer.append(word1.substring(i));
        } else if (j < word2.length()) {
            stringBuffer.append(word2.substring(j));
        }
        return stringBuffer.toString();
    }

    @Test
    public void test() {
        Assert.assertEquals(largestMerge("cabaa", "bcaaa"), "cbcabaaaaa");
        Assert.assertEquals(largestMerge("abcabc", "abdcaba"), "abdcabcabcaba");
    }

    @Test
    public void errorCase() {
        Assert.assertEquals(largestMerge("ddwdddddddddddddwwddddddwddw",
                                         "wwwwwwwwwddwwdwwwwwwwwwwwwwww"), "wwwwwwwwwddwwdwwwwwwwwwwwwwwwddwdddddddddddddwwddddddwddw");
        Assert.assertEquals(largestMerge("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbb",
                                         "bbbbbbbbbbbbbbbbbbbb"), "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");

    }
}
