package leetcode.daily;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 77. 组合 中等
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * <p>
 * 示例:
 * <p>
 * 输入: n = 4, k = 2
 * 输出:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 * 通过次数87,012提交次数115,187
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combinations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Sam Gao
 * @date 09/08/20 14:24
 */
public class Combine {
    List<List<Integer>> lists = new ArrayList<>();

    //背包问题
    public List<List<Integer>> combine(int n, int k) {
        List<Integer> list = new ArrayList<>();
        int[] maxValue = new int[k + 1];
        for (int i = 1; i <= k; i++) {
            maxValue[i] = i + n - k;
        }
        combine(new ArrayList<Integer>(), 1, maxValue, k);
        return lists;
    }

    public void combine(List<Integer> list, int start, int[] maxValue, int k) {
        if (list.size() == k) {
            lists.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i <= maxValue[list.size() + 1]; i++) {
            list.add(i);
            combine(list, i + 1, maxValue, k);
            list.remove(list.size() - 1);
        }
    }

    @Test
    public void test() {
        List<List<Integer>> combine = combine(4, 2);
        for (List<Integer> integers : combine) {
            System.out.println(integers);
        }
    }


    @Test
    public void test1() {
        List<List<Integer>> combine = combine(7, 4);
        for (List<Integer> integers : combine) {
            System.out.println(integers);
        }
    }
}
