package Leetcode.daily.y2021m03;
import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.Assert;
import org.junit.Test;

/**
 * 1047. 删除字符串中的所有相邻重复项
 * 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
 * <p>
 * 在 S 上反复执行重复项删除操作，直到无法继续删除。
 * <p>
 * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入："abbaca"
 * 输出："ca"
 * 解释：
 * 例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。之后我们得到字符串 "aaca"，其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= S.length <= 20000
 * S 仅由小写英文字母组成。
 *
 * @date 03/09/21 10:07
 */
public class RemoveDuplicates {
    public String removeDuplicates(String S) {
        Deque<Character> stack = new ArrayDeque<>();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < S.length(); i++) {
            if (stack.isEmpty()) {
                stack.addLast(S.charAt(i));
            } else if (S.charAt(i) != stack.getLast()) {
                stack.addLast(S.charAt(i));
            } else {
                stack.removeLast();
            }
        }
        while(!stack.isEmpty()){
            stringBuilder.append(stack.poll());
        }
        return stringBuilder.toString();
    }

    @Test
    public void test() {
        Assert.assertEquals(removeDuplicates("abbaca"), "ca");
        Assert.assertEquals(removeDuplicates(""), "");
        Assert.assertEquals(removeDuplicates("acabbaca"), "");
    }

}
