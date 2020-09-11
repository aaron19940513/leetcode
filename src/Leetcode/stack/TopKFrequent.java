package Leetcode.stack;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 347. 前 K 个高频元素 中等
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 * <p>
 * 输入: nums = [1], k = 1
 * 输出: [1]
 *  
 * <p>
 * 提示：
 * <p>
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
 * 你可以按任意顺序返回答案。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/top-k-frequent-elements
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TopKFrequent {


    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((x, y) -> map.get(x) - map.get(y));
        for (Integer key : map.keySet()) {
            if (minHeap.size() >= k) {
                if (map.get(minHeap.peek()) < map.get(key)) {
                    minHeap.poll();
                    minHeap.add(key);
                }
            } else {
                minHeap.add(key);
            }
        }
        List<Integer> ans = new ArrayList<>();
        while (k > 0) {
            ans.add(minHeap.poll());
            k--;
        }
        Collections.reverse(ans);
        return ans;
    }

    public int[] topKFrequent1(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((x, y) -> map.get(x) - map.get(y));
        for (Integer key : map.keySet()) {
            if (minHeap.size() >= k) {
                if (map.get(minHeap.peek()) < map.get(key)) {
                    minHeap.poll();
                    minHeap.add(key);
                }
            } else {
                minHeap.add(key);
            }
        }
        int[] ans = new int[k];
        while (k > 0) {
            ans[k - 1] = minHeap.poll();
            k--;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1, 1, 1, 2, 2, 3};
        TopKFrequent topKFrequent = new TopKFrequent();
        System.out.println(topKFrequent.topKFrequent(nums, 2));
    }

}
