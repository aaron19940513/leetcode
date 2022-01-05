package leetcode.daily;
import org.junit.Assert;
import org.junit.Test;

/**
 * 剑指 Offer 11. 旋转数组的最小数字  简单
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  
 * <p>
 * 示例 1：
 * <p>
 * 输入：[3,4,5,1,2]
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：[2,2,2,0,1]
 * 输出：0
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 07/22/20 10:27
 */
public class MinArray {
    public int minArray(int[] numbers) {
        if (numbers.length == 0) {
            return 0;
        }
        if (numbers.length == 1) {
            return numbers[0];
        }
        int start = 0;
        int end = numbers.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (numbers[mid] == numbers[end]) {
                end--;
                continue;
            }
            if (numbers[mid] < numbers[end]) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return numbers[end];
    }

    @Test
    public void test() {
        Assert.assertEquals(minArray(new int[] {3, 4, 5, 1, 2}), 1);
        Assert.assertEquals(minArray(new int[] {2, 2, 2, 0, 1}), 0);
        Assert.assertEquals(minArray(new int[] {1, 2, 3, 4, 5}), 1);
        Assert.assertEquals(minArray(new int[] {}), 0);
        Assert.assertEquals(minArray(new int[] {1}), 1);
        Assert.assertEquals(minArray(new int[] {2, 1}), 1);
        //error case
        Assert.assertEquals(minArray(new int[] {1, 1, 3}), 1);
        Assert.assertEquals(minArray(new int[] {10, 1, 10, 10, 10}), 1);
        Assert.assertEquals(minArray(new int[] {10, 20, 10}), 10);
        Assert.assertEquals(minArray(new int[] {0, 0, 1, 1, 2, 0}), 0);
    }
}
