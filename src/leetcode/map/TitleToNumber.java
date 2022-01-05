package leetcode.map;
/**
 * 给定一个Excel表格中的列名称，返回其相应的列序号。
 * <p>
 * 例如，
 * <p>
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 * 示例 1:
 * <p>
 * 输入: "A"
 * 输出: 1
 * 示例 2:
 * <p>
 * 输入: "AB"
 * 输出: 28
 * 示例 3:
 * <p>
 * 输入: "ZY"
 * 输出: 701
 */
public class TitleToNumber {
    public int titleToNumber(String s) {
        int ans = 0;
        int length = s.length();
        for (char c : s.toCharArray()) {
            ans += (c - 'A' + 1) * Math.pow(26, --length);
        }
        return ans;
    }

    public static void main(String[] args) {
        TitleToNumber titleToNumber = new TitleToNumber();
        System.out.println(titleToNumber.titleToNumber("AB"));
        System.out.println(titleToNumber.titleToNumber("ZY"));
        System.out.println(titleToNumber.titleToNumber("AXB"));
    }
}
