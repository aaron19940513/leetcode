// package Leetcode.daily.y2021m03;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.Stack;
//
// import org.junit.Assert;
// import org.junit.Test;
//
// /**
//  * 227. 基本计算器 II
//  * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
//  * <p>
//  * 整数除法仅保留整数部分。
//  * <p>
//  * 示例 1：
//  * <p>
//  * 输入：s = "3+2*2"
//  * 输出：7
//  * 示例 2：
//  * <p>
//  * 输入：s = " 3/2 "
//  * 输出：1
//  * 示例 3：
//  * <p>
//  * 输入：s = " 3+5 / 2 "
//  * 输出：5
//  * <p>
//  * <p>
//  * 提示：
//  * <p>
//  * 1 <= s.length <= 3 * 105
//  * s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
//  * s 表示一个 有效表达式
//  * 表达式中的所有整数都是非负整数，且在范围 [0, 231 - 1] 内
//  * 题目数据保证答案是一个 32-bit 整数
//  *
//  * @date 03/10/21 9:34
//  */
// public class Calculate2 {
//     public int calculate(String s) {
//         Map<Character, Integer> map = new HashMap<>();
//         map.put('*', 1);
//         map.put('/', 1);
//         map.put('+', 0);
//         map.put('-', 0);
//
//         Stack<Character> flagStack = new Stack<>();
//         Stack<Long> numStack = new Stack<>();
//         int curNum = 0;
//         for (int i = 0; i < s.length(); i++) {
//             char c = s.charAt(i);
//             if (c == '/' || c == '*' || c == '+' || c == '-') {
//                 if (flagStack.isEmpty() || map.get(c) > flagStack.peek()) {
//                     numStack.push(curNum);
//                     flagStack.push(c);
//                 } else {
//                     while (!flagStack.isEmpty() && map.get(c) <= flagStack.peek()) {
//                         Character pop = flagStack.pop();
//                         switch (pop) {
//                             case '+':
//                                 numStack.push(numStack.pop() + curNum);
//                                 curNum = 0;
//                                 break;
//                             case '-':
//                                 numStack.push(numStack.pop() - curNum);
//                                 curNum = 0;
//                                 break;
//                             case '/':
//                                 numStack.push(numStack.pop() / curNum);
//                                 curNum = 0;
//                                 break;
//                             case '*':
//                                 numStack.push(numStack.pop() * curNum);
//                                 curNum = 0;
//                                 break;
//                         }
//                     }
//                 }
//             } else if (c == ' ') {
//                 curNum = 0;
//             } else {
//                 curNum = curNum * 10 + (s.charAt(i) - '0');
//             }
//         }
//         return numStack.pop().intValue();
//     }
//
//
//     @Test
//     public void test() {
//         Assert.assertEquals(calculate("3+2*2"), 7);
//         Assert.assertEquals(calculate(" 3/2 "), 1);
//         Assert.assertEquals(calculate(" 3+5 / 2 "), 5);
//
//     }
// }
