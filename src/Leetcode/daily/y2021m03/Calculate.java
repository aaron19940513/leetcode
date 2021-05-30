package Leetcode.daily.y2021m03;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

/**
 * 224. 基本计算器 hard?
 * 实现一个基本的计算器来计算一个简单的字符串表达式 s 的值。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "1 + 1"
 * 输出：2
 * 示例 2：
 * <p>
 * 输入：s = " 2-1 + 2 "
 * 输出：3
 * 示例 3：
 * <p>
 * 输入：s = "(1+(4+5+2)-3)+(6+8)"
 * 输出：23
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 3 * 105
 * s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
 * s 表示一个有效的表达式
 *
 * @date 03/10/21 9:34
 */
public class Calculate {
    public int calculate(String s) {
        s = '(' + s + ')';
        int countOfReduce = 0;
        Stack<Character> lastFlagBeforeBrackets = new Stack<>();
        Character lastFlag = '+';
        int num = 0;
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case ' ':
                    break;
                case '+':
                    ans = sum(ans, num, countOfReduce, lastFlag);
                    num = 0;
                    lastFlag = '+';
                    break;
                case '-':
                    ans = sum(ans, num, countOfReduce, lastFlag);
                    num = 0;
                    lastFlag = '-';
                    break;
                case '(':
                    lastFlagBeforeBrackets.push(lastFlag);
                    if (lastFlagBeforeBrackets.peek() == '-') {
                        countOfReduce = (countOfReduce + 1) % 2;
                    }
                    lastFlag = '+';
                    break;
                case ')':
                    ans = sum(ans, num, countOfReduce, lastFlag);
                    if (lastFlagBeforeBrackets.pop() == '-') {
                        countOfReduce = (countOfReduce + 1) % 2;
                    }
                    lastFlag = ')';
                    break;
                default:
                    num = num * 10 + (s.charAt(i) - '0');
            }
        }
        return ans;
    }

    private int sum(int ans, int num, int countOfReduce, Character lastFlag) {
        if (num != 0 && (lastFlag == '+' || lastFlag == '-')) {
            if (countOfReduce == 0) {
                if (lastFlag == '+') {
                    ans = ans + num;
                } else {
                    ans = ans - num;
                }
            } else {
                if (lastFlag == '+') {
                    ans = ans - num;
                } else {
                    ans = ans + num;
                }
            }
        }
        return ans;
    }

    @Test
    public void test() {
        Assert.assertEquals(calculate("1 + 1"), 2);
        Assert.assertEquals(calculate(" 2-1 + 2 "), 3);
        Assert.assertEquals(calculate("(1+(4+5+2)-3)+(6+8)"), 23);

        Assert.assertEquals(calculate("1-(10-(4+5+2)-(1-(1+3)))-(1-8)"), 6);
    }
}
