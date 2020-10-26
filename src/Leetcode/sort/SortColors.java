package Leetcode.sort;
import org.junit.Test;

/**
 * 75. 颜色分类 中等
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * <p>
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * <p>
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 * <p>
 * 示例:
 * <p>
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 * 进阶：
 * <p>
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-colors
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 10/19/20 15:12
 */
public class SortColors {

    public void sortColors(int[] nums) {
        int leftWhiteIndex = -1;
        int rightWhiteIndex = -1;

        int startIndex = 0;
        int endIndex = nums.length - 1;
        while (startIndex <= endIndex) {
            if (nums[startIndex] == 0) {
                if (leftWhiteIndex != -1) {
                    nums[startIndex] = 1;
                    nums[leftWhiteIndex] = 0;
                    leftWhiteIndex++;
                }
                startIndex++;
            } else if (nums[startIndex] == 1) {
                if (leftWhiteIndex == -1) {
                    leftWhiteIndex = startIndex;
                }
                startIndex++;
            } else {
                while (nums[endIndex] == 2 && startIndex < endIndex) {
                    endIndex--;
                }
                if (startIndex == endIndex) {
                    return;
                }
                if (nums[endIndex] == 0) {
                    if (leftWhiteIndex != -1) {
                        nums[startIndex] = 1;
                        nums[leftWhiteIndex] = 0;
                        leftWhiteIndex++;
                    } else {
                        nums[startIndex] = 0;
                    }
                    startIndex++;
                    nums[endIndex--] = 2;
                } else {
                    if (leftWhiteIndex == -1) {
                        leftWhiteIndex = startIndex;
                    }
                    nums[startIndex++] = 1;
                    nums[endIndex--] = 2;
                }
            }
        }
    }

    @Test
    public void test() {
        int[] arr = new int[] {2, 0, 2, 1, 1, 0};
        sortColors(arr);
    }

    @Test
    public void test1() {
        int[] arr = new int[] {2, 0, 2, 0, 1, 0};
        sortColors(arr);
    }

    @Test
    public void test2() {
        int[] arr = new int[] {0, 2, 1, 0, 2, 2, 0, 0, 1, 0, 2};
        sortColors(arr);
    }
}
