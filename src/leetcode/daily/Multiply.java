package leetcode.daily;


import org.junit.Assert;
import org.junit.Test;

/**
 * 43. 字符串相乘 中等
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * <p>
 * 示例 1:
 * <p>
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 * <p>
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 * <p>
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/multiply-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 08/13/20 8:55
 */
public class Multiply {

    /**
     * 用num2的每一个数去和num1相乘，然后再做加法
     */
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        String[] multiplyResult = new String[10];
        multiplyResult[0] = "0";
        String result = "";
        StringBuilder postString = new StringBuilder();
        for (int i = num2.length() - 1; i >= 0; i--) {
            String multiply = "";
            if (multiplyResult[num2.charAt(i) - '0'] != null) {
                multiply = multiplyResult[num2.charAt(i) - '0'];
            } else {
                multiply = multiply(num1, num2.charAt(i));
                multiplyResult[num2.charAt(i) - '0'] = multiply;
            }
            result = add(result, multiply + postString);
            postString.append('0');
        }
        return result;
    }

    private String multiply(String num1, char num2Item) {
        int add = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = num1.length() - 1; i >= 0; i--) {
            int result = (num2Item - '0') * (num1.charAt(i) - '0') + add;
            stringBuilder.append(result % 10);
            add = result / 10;
        }
        if (add != 0) {
            stringBuilder.append(add);
        }
        return stringBuilder.reverse().toString();
    }

    private String add(String num1, String num2) {
        if (num1.length() < num2.length()) {
            return add(num2, num1);
        }
        int interval = num1.length() - num2.length();
        StringBuilder stringBuilder = new StringBuilder();
        int add = 0;
        for (int i = num1.length() - 1; i >= 0; i--) {
            int result = add + (num1.charAt(i) - '0') + (i - interval >= 0 ? num2.charAt(i - interval) - '0' : 0);
            stringBuilder.append(result % 10);
            add = result / 10;
        }
        if (add != 0) {
            stringBuilder.append(add);
        }
        return stringBuilder.reverse().toString();
    }

    @Test
    public void test() {
        Assert.assertEquals(multiply("12345", '9'), "111105");
        Assert.assertEquals(multiply("98765", '9'), "888885");
    }

    @Test
    public void test1() {
        Assert.assertEquals(add("123", "54321"), "54444");
        Assert.assertEquals(add("98765", "5678999"), "5777764");
    }

    @Test
    public void test2() {
        Assert.assertEquals(multiply("12345", "1234"), "15233730");
        Assert.assertEquals(multiply("98765", "56789"), "5608765585");
    }
}
