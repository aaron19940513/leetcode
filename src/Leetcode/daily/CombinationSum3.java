package Leetcode.daily;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 216. 组合总和 III 中等
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 * <p>
 * 说明：
 * <p>
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 * <p>
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 示例 2:
 * <p>
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Sam Gao
 * @date 09/11/20 8:55
 */
public class CombinationSum3 {

    private List<List<Integer>> ans = new ArrayList<>();

    private int maxValue = 9;

    public List<List<Integer>> combinationSum3(int k, int n) {
        combinationSum(k, n, new ArrayList<>(), 1);
        return ans;
    }

    private void combinationSum(int k, int n, List<Integer> list, int startIndex) {
        if (k == 1) {
            if (n <= maxValue && n >= startIndex) {
                list.add(n);
                ans.add(new ArrayList<>(list));
                list.remove(list.size() - 1);
            }
            return;
        }
        for (int index = startIndex; index < maxValue + 1; index++) {
            if (k > maxValue - index + 1) {
                return;
            } else if (k == maxValue - index + 1) {
                if (n == sequenceSum(index, maxValue)) {
                    List<Integer> item = new ArrayList<>(list);
                    for (int i = index; i <= maxValue; i++) {
                        item.add(i);
                    }
                    ans.add(item);
                }
            } else {
                if (n - index < index + 1) {
                    return;
                }
                if (n - index > sequenceSum(maxValue - k + 2, maxValue)) {
                    continue;
                }
                list.add(index);
                combinationSum(k - 1, n - index, list, index + 1);
                list.remove(list.size() - 1);
            }
        }
    }

    private int sequenceSum(int start, int end) {
        return (start + end) * (end - start + 1) / 2;
    }

    @Test
    public void test() {
        List<List<Integer>> lists = combinationSum3(3, 7);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }

    @Test
    public void test1() {
        List<List<Integer>> lists = combinationSum3(3, 9);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }

    @Test
    public void test2() {
        List<List<Integer>> lists = combinationSum3(4, 24);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }
}
