package leetcode.weekcompetition.week241;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sam Gao
 * @date 2021/5/16 10:55
 */
public class FindSumPairs {
    int[] nums1;
    int[] nums2;
    Map<Integer, Integer> nums1Count = new HashMap<>();
    Map<Integer, Integer> nums2Count = new HashMap<>();

    public FindSumPairs(int[] nums1, int[] nums2) {
        this.nums2 = nums2;
        for (int i : nums1) {
            nums1Count.put(i, nums1Count.getOrDefault(i, 0) + 1);
        }
        for (int i : nums2) {
            nums2Count.put(i, nums2Count.getOrDefault(i, 0) + 1);
        }
    }

    public void add(int index, int val) {
        nums2Count.put(nums2[index], nums2Count.get(nums2[index]) - 1);
        nums2Count.put(nums2[index] + val, nums2Count.getOrDefault(nums2[index] + val, 0) + 1);
    }

    public int count(int tot) {
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : nums1Count.entrySet()) {
            if (nums2Count.containsKey(tot - entry.getKey())) {
                ans += entry.getValue() * nums2Count.get(tot - entry.getKey());
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        FindSumPairs findSumPairs = new FindSumPairs(new int[] {1, 1, 2, 2, 2, 3}, new int[] {1, 4, 5, 2, 5, 4});
        int temp = findSumPairs.count(7);  // 返回 8 ; 下标对 (2,2), (3,2), (4,2), (2,4), (3,4), (4,4) 满足 2 + 5 = 7 ，下标对 (5,1), (5,5) 满足 3 + 4 = 7
        findSumPairs.add(3, 2); // 此时 nums2 = [1,4,5,4,5,4]
        temp = findSumPairs.count(8);  // 返回 2 ；下标对 (5,2), (5,4) 满足 3 + 5 = 8
        temp = findSumPairs.count(4);  // 返回 1 ；下标对 (5,0) 满足 3 + 1 = 4
        findSumPairs.add(0, 1); // 此时 nums2 = [2,4,5,4,5,4]
        findSumPairs.add(1, 1); // 此时 nums2 = [2,5,5,4,5,4]
        temp = findSumPairs.count(7);  // 返回 11 ；下标对 (2,1), (2,2), (2,4), (3,1), (3,2), (3,4), (4,1), (4,2), (4,4) 满足 2 + 5 = 7 ，下标对 (5,3), (5,5) 满足 3 + 4 = 7
    }


}
