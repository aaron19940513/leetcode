package leetcode.weekcompetition.week243;
import org.junit.Assert;
import org.junit.Test;

/**
 * 5772. 检查某单词是否等于两单词之和 显示英文描述
 * 通过的用户数0
 * 尝试过的用户数0
 * 用户总通过次数0
 * 用户总提交次数0
 * 题目难度Easy
 * 字母的 字母值 取决于字母在字母表中的位置，从 0 开始 计数。即，'a' -> 0、'b' -> 1、'c' -> 2，以此类推。
 * <p>
 * 对某个由小写字母组成的字符串 s 而言，其 数值 就等于将 s 中每个字母的 字母值 按顺序 连接 并 转换 成对应整数。
 * <p>
 * 例如，s = "acb" ，依次连接每个字母的字母值可以得到 "021" ，转换为整数得到 21 。
 * 给你三个字符串 firstWord、secondWord 和 targetWord ，每个字符串都由从 'a' 到 'j' （含 'a' 和 'j' ）的小写英文字母组成。
 * <p>
 * 如果 firstWord 和 secondWord 的 数值之和 等于 targetWord 的数值，返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：firstWord = "acb", secondWord = "cba", targetWord = "cdb"
 * 输出：true
 * 解释：
 * firstWord 的数值为 "acb" -> "021" -> 21
 * secondWord 的数值为 "cba" -> "210" -> 210
 * targetWord 的数值为 "cdb" -> "231" -> 231
 * 由于 21 + 210 == 231 ，返回 true
 * 示例 2：
 * <p>
 * 输入：firstWord = "aaa", secondWord = "a", targetWord = "aab"
 * 输出：false
 * 解释：
 * firstWord 的数值为 "aaa" -> "000" -> 0
 * secondWord 的数值为 "a" -> "0" -> 0
 * targetWord 的数值为 "aab" -> "001" -> 1
 * 由于 0 + 0 != 1 ，返回 false
 * 示例 3：
 * <p>
 * 输入：firstWord = "aaa", secondWord = "a", targetWord = "aaaa"
 * 输出：true
 * 解释：
 * firstWord 的数值为 "aaa" -> "000" -> 0
 * secondWord 的数值为 "a" -> "0" -> 0
 * targetWord 的数值为 "aaaa" -> "0000" -> 0
 * 由于 0 + 0 == 0 ，返回 true
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= firstWord.length, secondWord.length, targetWord.length <= 8
 * firstWord、secondWord 和 targetWord 仅由从 'a' 到 'j' （含 'a' 和 'j' ）的小写英文字母组成。
 *
 * @date 2021/5/30 10:30
 */
public class IsSumEqual {
    public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
        int i = firstWord.length() - 1;
        int j = secondWord.length() - 1;
        int k = targetWord.length() - 1;
        int n = Math.max(Math.max(i, j), k);
        int add = 0;
        while (n >= 0) {
            int temp = (i >= 0 ? firstWord.charAt(i--) - 'a' : 0) + (j >= 0 ? secondWord.charAt(j--) - 'a' : 0) + add;
            if (temp >= 10) {
                add = temp / 10;
                temp = temp % 10;
            } else {
                add = 0;
            }
            if (temp != (k >= 0 ? targetWord.charAt(k--) - 'a' : 0)) {
                return false;
            }
            n--;
        }
        return add == 0;
    }

    @Test
    public void test() {
        Assert.assertTrue(isSumEqual("acb", "cba", "cdb"));
        Assert.assertFalse(isSumEqual("aaa", "a", "aab"));
        Assert.assertTrue(isSumEqual("aaa", "a", "aaaa"));

    }

    @Test
    public void errorCase() {
        Assert.assertTrue(isSumEqual("hgidd",
                                     "cjhhe",
                                     "baggah"));
    }
}