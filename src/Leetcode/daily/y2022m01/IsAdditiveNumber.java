//package leetcode.daily.y2022m01;
//
//import org.junit.Assert;
//import org.junit.Test;
//
///**
// * 306. 累加数
// * 累加数 是一个字符串，组成它的数字可以形成累加序列。
// * <p>
// * 一个有效的 累加序列 必须 至少 包含 3 个数。除了最开始的两个数以外，字符串中的其他数都等于它之前两个数相加的和。
// * <p>
// * 给你一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是 累加数 。如果是，返回 true ；否则，返回 false 。
// * <p>
// * 说明：累加序列里的数 不会 以 0 开头，所以不会出现 1, 2, 03 或者 1, 02, 3 的情况。
// * <p>
// * <p>
// * <p>
// * 示例 1：
// * <p>
// * 输入："112358"
// * 输出：true
// * 解释：累加序列为: 1, 1, 2, 3, 5, 8 。1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
// * 示例 2：
// * <p>
// * 输入："199100199"
// * 输出：true
// * 解释：累加序列为: 1, 99, 100, 199。1 + 99 = 100, 99 + 100 = 199
// * <p>
// * <p>
// * 提示：
// * <p>
// * 1 <= num.length <= 35
// * num 仅由数字（0 - 9）组成
// * <p>
// * <p>
// * 进阶：你计划如何处理由过大的整数输入导致的溢出?
// */
//public class IsAdditiveNumber {
//    public boolean isAdditiveNumber(String num) {
//        int pos1Start = 0;
//        int pos2Start = 1;
//        for (int i = 0; i < num.length() / 2 + 1; i++) {
//            for (int j = i + 1; j < num.length() / 2 + 1; j++) {
//
//            }
//        }
//    }
//
//
//    public boolean isAdditiveNumber(String num, int pos1, int pos2, int pos3) {
//        if (num.length() - 1 == pos3) {
//            return true;
//        }
//        if (num.charAt(pos3 + 1) == '0') {
//            return false;
//        }
//        String result = add(num.substring(pos1, pos2), num.substring(pos2, pos3 + 1);
//    }
//
//    public String add(String a, String b) {
//        StringBuilder sb = new StringBuilder();
//        int plus = 0;
//        int index = 0;
//        while (a.length() - index > 0 || b.length() - index > 0 || plus > 0) {
//            if (a.length() - index > 0) {
//                plus += a.charAt(a.length() - index - 1) - '0';
//            }
//            if (b.length() - index > 0) {
//                plus += b.charAt(b.length() - index - 1) - '0';
//            }
//            sb.append(plus % 10);
//            plus = plus / 10;
//            index++;
//        }
//        return sb.reverse().toString();
//    }
//
//    @Test
//    public void test() {
//        Assert.assertEquals("199", add("100", "99"));
//        Assert.assertEquals("198", add("99", "99"));
//    }
//}
