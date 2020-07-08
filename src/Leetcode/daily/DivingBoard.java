package Leetcode.daily;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 * 你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为shorter，长度较长的木板长度为longer。你必须正好使用k块木板。编写一个方法，生成跳水板所有可能的长度。
 * <p>
 * 返回的长度需要从小到大排列。
 * <p>
 * 示例：
 * <p>
 * 输入：
 * shorter = 1
 * longer = 2
 * k = 3
 * 输出： {3,4,5,6}
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/diving-board-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 07/08/20 11:00
 */
public class DivingBoard {
    // after = before + shorter/longer
    public int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0) {
            return new int[0];
        }
        Set<Integer> ans = new LinkedHashSet<>();
        for (int i = 0; i <= k; i++) {
            ans.add(shorter * (k - i) + longer * (i));
        }
        return ans.stream().mapToInt(Number::intValue).toArray();
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[] {3, 4, 5, 6}, divingBoard(1, 2, 3));
        //error case
        Assert.assertArrayEquals(new int[] {}, divingBoard(1, 1, 0));
        Assert.assertArrayEquals(new int[] {}, divingBoard(2, 1118596, 50));
    }
}
