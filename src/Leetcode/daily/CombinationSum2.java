package Leetcode.daily;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * 40. 组合总和 II 中等
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的每个数字在每个组合中只能使用一次。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 * <p>
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 * [1, 7],
 * [1, 2, 5],
 * [2, 6],
 * [1, 1, 6]
 * ]
 * 示例 2:
 * <p>
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 * 通过次数84,418提交次数133,813
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Sam Gao
 * @date 09/10/20 8:54
 */
public class CombinationSum2 {

    private List<List<Integer>> lists = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        combinationSum(new ArrayList<>(), candidates, target, 0);
        return lists;
    }

    private void combinationSum(List<Integer> list, int[] candidates, int target, int startIndex) {
        if (startIndex == candidates.length) {
            return;
        }

        for (int i = startIndex; i < candidates.length; i++) {
            if (i > startIndex && candidates[i] == candidates[i - 1]) {
                continue;
            }
            int value = candidates[i];
            if (value > target) {
                return;
            }
            if (value == target) {
                list.add(candidates[i]);
                lists.add(new ArrayList<>(list));
                list.remove(list.size() - 1);
                return;
            }
            list.add(candidates[i]);
            combinationSum(list, candidates, target - candidates[i], i + 1);
            list.remove(list.size() - 1);
        }
    }

    @Test
    public void test() {
        List<List<Integer>> lists = combinationSum2(new int[] {10, 1, 2, 7, 6, 1, 5}, 8);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }

    @Test
    public void test1() {
        List<List<Integer>> lists = combinationSum2(new int[] {2, 5, 2, 1, 2}, 5);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }
}
