package Leetcode.stack;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequent {


    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((x, y) -> map.get(x) - map.get(y));
        for (Integer key : map.keySet()) {
            if (minHeap.size() >= k) {
                if(map.get(minHeap.peek()) < map.get(key)){
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

    public static void main(String[] args) {
        int[] nums = new int[] {1, 1, 1, 2, 2, 3};
        TopKFrequent topKFrequent = new TopKFrequent();
        System.out.println(topKFrequent.topKFrequent(nums, 2));
    }

}
