package leetcode.daily.y2021m02;
import org.junit.Assert;
import org.junit.Test;

/**
 * 978. 最长湍流子数组
 * 当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组：
 * <p>
 * 若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
 * 或 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。
 * 也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。
 * <p>
 * 返回 A 的最大湍流子数组的长度。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：[9,4,2,10,7,8,8,1,9]
 * 输出：5
 * 解释：(A[1] > A[2] < A[3] > A[4] < A[5])
 * 示例 2：
 * <p>
 * 输入：[4,8,12,16]
 * 输出：2
 * 示例 3：
 * <p>
 * 输入：[100]
 * 输出：1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 40000
 * 0 <= A[i] <= 10^9
 *
 * @date 02/08/21 10:43
 */
public class MaxTurbulenceSize {
    public int maxTurbulenceSize(int[] arr) {
        int flag = 0;
        int ans = 1;
        int pre = arr[0];
        int tempLength = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > pre && flag <= 0) {
                flag = 1;
                tempLength++;
            } else if (arr[i] < pre && flag >= 0) {
                flag = -1;
                tempLength++;
            } else if (arr[i] == pre) {
                ans = Math.max(tempLength, ans);
                flag = 0;
                tempLength = 1;
            } else {
                ans = Math.max(tempLength, ans);
                tempLength = 2;
            }
            pre = arr[i];
        }
        return  Math.max(tempLength, ans);
    }

    @Test
    public void test() {
        Assert.assertEquals(maxTurbulenceSize(new int[] {9, 4, 2, 10, 7, 8, 8, 1, 9}), 5);
        Assert.assertEquals(maxTurbulenceSize(new int[] {4, 8, 12, 16}), 2);
        Assert.assertEquals(maxTurbulenceSize(new int[] {100}), 1);
    }

    @Test
    public void errorCase(){
        Assert.assertEquals(maxTurbulenceSize(new int[] {0,8,45,88,48,68,28,55,17,24}), 8);
    }
}
