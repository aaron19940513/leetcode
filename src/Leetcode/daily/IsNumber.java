package Leetcode.daily;
import org.junit.Assert;
import org.junit.Test;

/**
 * 剑指 Offer 20. 表示数值的字符串
 *
 * @date 09/02/20 8:42
 */
public class IsNumber {
    public boolean isNumber(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        String trim = s.trim();
        return isNumber(trim.toCharArray(), 0, trim.length());
    }

    public boolean isNumber(char[] arr, int start, int end) {
        int decimalPointCount = 0;
        int decimalCount = 0;
        for (int i = start; i < end; i++) {
            char c = arr[i];
            if (c == '+' || c == '-') {
                if (start != i) {
                    return false;
                }
            } else if (c == 'e' || c == 'E') {
                if (decimalCount == 0) {
                    return false;
                }
                return isInteger(arr, i + 1, end);
            } else if (c >= '0' && c <= '9') {
                decimalCount++;
            } else if (c == '.') {
                if (++decimalPointCount > 1) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return decimalCount > 0;
    }

    public boolean isInteger(char[] arr, int start, int end) {
        int decimalCount = 0;
        for (int i = start; i < end; i++) {
            char c = arr[i];
            if (c == '+' || c == '-') {
                if (start != i) {
                    return false;
                }
            } else if (c >= '0' && c <= '9') {
                decimalCount++;
            } else {
                return false;
            }
        }
        return decimalCount > 0;
    }


    @Test
    public void test() {
        Assert.assertTrue(isNumber("+100"));
        Assert.assertTrue(isNumber("5e2"));
        Assert.assertTrue(isNumber("-123"));
        Assert.assertTrue(isNumber("3.1416"));
        Assert.assertTrue(isNumber("-1E-16"));
        Assert.assertTrue(isNumber("0123"));
    }

    @Test
    public void test1() {
        Assert.assertFalse(isNumber("12e"));
        Assert.assertFalse(isNumber("1a3.14"));
        Assert.assertFalse(isNumber("1.2.3"));
        Assert.assertFalse(isNumber("+-5"));
        Assert.assertFalse(isNumber("12e+5.4"));

    }

    @Test
    public void test2() {
        Assert.assertFalse(isNumber("e9"));
        Assert.assertTrue(isNumber("  1  "));
        Assert.assertTrue(isNumber("0123."));
    }
}
