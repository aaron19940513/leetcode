package leetcode.daily;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * 39. 组合总和 中等
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的数字可以无限制重复被选取。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1：
 * <p>
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 * [7],
 * [2,2,3]
 * ]
 * 示例 2：
 * <p>
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Sam Gao
 * @date 09/09/20 8:57
 */
public class CombinationSum {
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        combinationSum(new ArrayList<>(), candidates, target, 0);
        return ans;
    }

    private void combinationSum(List<Integer> list, int[] candidates, int target, int index) {
        if (index >= candidates.length) {
            return;
        }
        int value = candidates[index];
        if (value > target) {
            return;
        }
        int div = target / value;
        fill(list, value, div);
        if (div * value == target) {
            ans.add(new ArrayList<>(list));
        }
        for (int i = div; i >= 0; i--) {
            combinationSum(list, candidates, target - value * i, index + 1);
            if (i != 0) {
                list.remove(list.size() - 1);
            }
        }
    }

    private void fill(List<Integer> list, int value, int count) {
        for (int i = 0; i < count; i++) {
            list.add(value);
        }
    }

    @Test
    public void test() {
        List<List<Integer>> lists = combinationSum(new int[] {2, 3, 5}, 8);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }

    @Test
    public void test1() {
        List<List<Integer>> lists = combinationSum(new int[] {2, 3, 6, 7}, 7);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }

    @Test
    public void test2() {
        List<List<Integer>> lists = combinationSum(new int[] {2, 3, 5}, 7);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }
}
