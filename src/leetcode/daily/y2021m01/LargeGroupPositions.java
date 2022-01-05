package leetcode.daily.y2021m01;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * 830. 较大分组的位置 easy
 * 在一个由小写字母构成的字符串 s 中，包含由一些连续的相同字符所构成的分组。
 * <p>
 * 例如，在字符串 s = "abbxxxxzyy" 中，就含有 "a", "bb", "xxxx", "z" 和 "yy" 这样的一些分组。
 * <p>
 * 分组可以用区间 [start, end] 表示，其中 start 和 end 分别表示该分组的起始和终止位置的下标。上例中的 "xxxx" 分组用区间表示为 [3,6] 。
 * <p>
 * 我们称所有包含大于或等于三个连续字符的分组为 较大分组 。
 * <p>
 * 找到每一个 较大分组 的区间，按起始位置下标递增顺序排序后，返回结果。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "abbxxxxzzy"
 * 输出：[[3,6]]
 * 解释："xxxx" 是一个起始于 3 且终止于 6 的较大分组。
 * 示例 2：
 * <p>
 * 输入：s = "abc"
 * 输出：[]
 * 解释："a","b" 和 "c" 均不是符合要求的较大分组。
 * 示例 3：
 * <p>
 * 输入：s = "abcdddeeeeaabbbcd"
 * 输出：[[3,5],[6,9],[12,14]]
 * 解释：较大分组为 "ddd", "eeee" 和 "bbb"
 * 示例 4：
 * <p>
 * 输入：s = "aba"
 * 输出：[]
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 1000
 * s 仅含小写英文字母
 *
 * @date 01/05/21 16:37
 */
public class LargeGroupPositions {
    public List<List<Integer>> largeGroupPositions(String s) {
        char pre = s.charAt(0);
        int count = 1;
        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == pre) {
                count++;
            } else {
                pre = s.charAt(i);
                if (count >= 3) {
                    List<Integer> item = new ArrayList<>();
                    item.add(i - count);
                    item.add(i - 1);
                    ans.add(item);
                }
                count = 1;
            }
        }
        if (count >= 3) {
            List<Integer> item = new ArrayList<>();
            item.add(s.length() - count);
            item.add(s.length() - 1);
            ans.add(item);
        }
        return ans;
    }

    @Test
    public void test() {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> item = new ArrayList<>();
        item.add(3);
        item.add(6);
        ans.add(item);
        Assert.assertEquals(largeGroupPositions("abbxxxxzzy"), ans);
    }

    @Test
    public void test1() {
        Assert.assertEquals(largeGroupPositions("abc"), new ArrayList<>());
    }

    @Test
    public void test2() {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> item = new ArrayList<>();
        item.add(3);
        item.add(5);
        ans.add(item);
        List<Integer> item1 = new ArrayList<>();
        item1.add(6);
        item1.add(9);
        ans.add(item1);
        List<Integer> item2 = new ArrayList<>();
        item2.add(12);
        item2.add(14);
        ans.add(item2);
        List<Integer> item3 = new ArrayList<>();
        item3.add(16);
        item3.add(18);
        ans.add(item3);
        Assert.assertEquals(largeGroupPositions("abcdddeeeeaabbbcddd"), ans);
    }
}
