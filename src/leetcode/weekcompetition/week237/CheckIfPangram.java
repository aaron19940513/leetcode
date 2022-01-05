package leetcode.weekcompetition.week237;
/**
 * 5734. 判断句子是否为全字母句 显示英文描述
 * <p>
 * 题目难度Easy
 * 全字母句 指包含英语字母表中每个字母至少一次的句子。
 * <p>
 * 给你一个仅由小写英文字母组成的字符串 sentence ，请你判断 sentence 是否为 全字母句 。
 * <p>
 * 如果是，返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：sentence = "thequickbrownfoxjumpsoverthelazydog"
 * 输出：true
 * 解释：sentence 包含英语字母表中每个字母至少一次。
 * 示例 2：
 * <p>
 * 输入：sentence = "leetcode"
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= sentence.length <= 1000
 * sentence 由小写英语字母组成
 *
 * @date 2021/4/18 10:30
 */
public class CheckIfPangram {
    public boolean checkIfPangram(String sentence) {
        boolean[] program = new boolean[26];
        for (char c : sentence.toCharArray()) {
            program[c - 'a'] = true;
        }
        for (boolean b : program) {
            if (!b) {
                return false;
            }
        }
        return true;
    }

}
