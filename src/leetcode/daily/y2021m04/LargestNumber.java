package leetcode.daily.y2021m04;
import java.util.Arrays;
import java.util.Comparator;

import org.junit.Assert;
import org.junit.Test;

/**
 * 179. 最大数 mid
 * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
 * <p>
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [10,2]
 * 输出："210"
 * 示例 2：
 * <p>
 * 输入：nums = [3,30,34,5,9]
 * 输出："9534330"
 * 示例 3：
 * <p>
 * 输入：nums = [1]
 * 输出："1"
 * 示例 4：
 * <p>
 * 输入：nums = [10]
 * 输出："10"
 * <p>
 * <p>
 *
 * @date 04/12/21 9:04
 */
public class LargestNumber {
    public String largestNumber(int[] nums) {
        String[] stringNums = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            stringNums[i] = String.valueOf(nums[i]);
        }
        StringBuilder stringBuilder = new StringBuilder();
        Arrays.sort(stringNums, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o2 + o1).compareTo(o1 + o2);
            }
        });
        int index = 0;
        while (index < stringNums.length - 1 && stringNums[index].equals("0")) {
            index++;
        }
        for (; index < stringNums.length; index++) {
            stringBuilder.append(stringNums[index]);
        }
        return stringBuilder.toString();
    }

    @Test
    public void test() {
        Assert.assertEquals("210", largestNumber(new int[] {10, 2}));
        Assert.assertEquals("9534330", largestNumber(new int[] {3, 30, 34, 5, 9}));
        Assert.assertEquals("1", largestNumber(new int[] {1}));
        Assert.assertEquals("10", largestNumber(new int[] {10}));
    }

    @Test
    public void errorCase() {
        Assert.assertEquals("43243432", largestNumber(new int[] {432, 43243}));
        Assert.assertEquals("0", largestNumber(new int[] {0, 0}));
    }
}
