package Leetcode.daily.y2021m03;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

/**
 * 90. 子集 II mid
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 * <p>
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 * 示例 2：
 * <p>
 * 输入：nums = [0]
 * 输出：[[],[0]]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 *
 * @date 03/31/21 9:14
 */
public class SubsetsWithDup {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Map<Integer, Map<Integer, List<List<Integer>>>> dp = new HashMap<>();
        List<List<Integer>> ans = new ArrayList<>();
        Map<Integer, Integer> map = new TreeMap<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        Integer[] keys = map.keySet().toArray(new Integer[] {});
        //枚举长度
        for (int length = 1; length <= nums.length; length++) {
            ans.addAll(recursive(0, keys, length, map, dp));
        }
        ans.add(new ArrayList<>());
        return ans;
    }

    private List<List<Integer>> recursive(int index, Integer[] keys, int length, Map<Integer, Integer> countMap,
                                          Map<Integer, Map<Integer, List<List<Integer>>>> dp) {
        Map<Integer, List<List<Integer>>> innerMap;
        if ((innerMap = dp.get(keys[index])) != null && innerMap.get(length) != null) {
            return innerMap.get(length);
        }
        if (innerMap == null) {
            innerMap = new HashMap<>();
        }
        List<List<Integer>> items = new ArrayList<>();
        List<Integer> item;
        int remain = getRemainCount(index + 1, keys, countMap);

        for (Integer keyCount = Math.max(0, length - remain); keyCount <= Math.min(countMap.get(keys[index]), length); keyCount++) {
            if (index + 1 < keys.length && length - keyCount > 0) {
                List<List<Integer>> recursive = recursive(index + 1, keys, length - keyCount, countMap, dp);
                for (List<Integer> integers : recursive) {
                    item = new ArrayList<>();
                    for (int j = 0; j < keyCount; j++) {
                        item.add(keys[index]);
                    }
                    item.addAll(integers);
                    items.add(item);
                }
            } else {
                item = new ArrayList<>();
                for (int j = 0; j < keyCount; j++) {
                    item.add(keys[index]);
                }
                items.add(item);
            }
        }
        innerMap.put(length, items);
        dp.put(keys[index], innerMap);
        return items;
    }

    private int getRemainCount(int index, Integer[] keys, Map<Integer, Integer> countMap) {
        int sum = 0;
        for (int i = index; i < keys.length; i++) {
            sum += countMap.get(keys[i]);
        }
        return sum;
    }

    @Test
    public void test() {
        subsetsWithDup(new int[] {1, 2, 2, 3, 3});
        subsetsWithDup(new int[] {1, 2, 2});
        subsetsWithDup(new int[] {0});
    }
}
