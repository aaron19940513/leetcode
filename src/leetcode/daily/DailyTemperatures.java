package leetcode.daily;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

/**
 * 根据每日 气温 列表，请重新生成一个列表，对应位置的输出是需要再等待多久温度才会升高超过该日的天数。如果之后都不会升高，请在该位置用 0 来代替。
 * <p>
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 * <p>
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/daily-temperatures
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DailyTemperatures {
    public int[] dailyTemperatures(int[] T) {
        Stack<Integer> valueStack = new Stack<>();
        Stack<Integer> indexStack = new Stack<>();
        int[] ans = new int[T.length];
        valueStack.push(T[0]);
        indexStack.push(0);
        for (int index = 1; index < T.length; index++) {

            while (valueStack.size() > 0 && T[index] > valueStack.peek()) {
                valueStack.pop();
                Integer popIndex = indexStack.pop();
                ans[popIndex] = index - popIndex;
            }
            valueStack.push(T[index]);
            indexStack.push(index);

        }
        for (Integer index : indexStack) {
            ans[index] = 0;
        }
        return ans;
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[] {1, 1, 4, 2, 1, 1, 0, 0}, dailyTemperatures(new int[] {73, 74, 75, 71, 69, 72, 76, 73}));
        Assert.assertArrayEquals(new int[] {0, 0, 0, 0, 4, 3, 2, 1, 0}, dailyTemperatures(new int[] {75, 69, 68, 67, 65, 64, 63, 62, 66}));
    }
}
