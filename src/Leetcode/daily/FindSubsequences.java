package Leetcode.daily;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Sam Gao
 * @date 08/25/20 11:10
 */
// todo
public class FindSubsequences {
    public List<List<Integer>> findSubsequences(int[] nums) {
        Map<Integer, List<Integer>> length2Sub = new HashMap<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            for (int j = 1; j <= nums.length - i; j++) {

            }
        }
        return null;
    }

}
