package Leetcode.daily;
import org.junit.Assert;
import org.junit.Test;

/**
 * 1207. 独一无二的出现次数 简单
 * 给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。
 * <p>
 * 如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [1,2,2,1,1,3]
 * 输出：true
 * 解释：在该数组中，1 出现了 3 次，2 出现了 2 次，3 只出现了 1 次。没有两个数的出现次数相同。
 * 示例 2：
 * <p>
 * 输入：arr = [1,2]
 * 输出：false
 * 示例 3：
 * <p>
 * 输入：arr = [-3,0,1,-3,1,1,1,-3,10,0]
 * 输出：true
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 1000
 * -1000 <= arr[i] <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-number-of-occurrences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 10/28/20 9:29
 */
public class UniqueOccurrences {
    public boolean uniqueOccurrences(int[] arr) {
        int[] count = new int[2001];
        for (int i : arr) {
            count[i + 1000]++;
        }
        boolean[] flag = new boolean[2001];
        for (int i : count) {
            if(i==0){
                continue;
            }
            if (flag[i]) {
                return false;
            }
            flag[i] = true;
        }
        return true;
    }

    @Test
    public void test() {
        Assert.assertTrue(uniqueOccurrences(new int[] {1, 2, 2, 1, 1, 3}));
        Assert.assertTrue(uniqueOccurrences(new int[] {1}));
        Assert.assertFalse(uniqueOccurrences(new int[] {1, 2}));
        Assert.assertTrue(uniqueOccurrences(new int[] {-3, 0, 1, -3, 1, 1, 1, -3, 10, 0}));
    }
}
