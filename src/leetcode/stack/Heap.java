package leetcode.stack;
/**
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 */
public class Heap {
    public static int[] HeapSort(int[] array) {
        int length = array.length;
        if (length < 1) {
            return array;
        }
        buildMaxHeap(array);
        for (int i = 0; i < length; i++) {
            swap(array, 0, length - 1 - i);
            adjustHeap(array, 0, length - 1 - i);
        }
        return array;
    }

    private static void buildMaxHeap(int[] array) {
        int length = array.length;
        for (int index = length / 2 - 1; index >= 0; index--) {
            adjustHeap(array, index, length);
        }
    }

    private static void adjustHeap(int[] array, int index, int length) {
        int maxIndex = index;
        if (index * 2 + 1 < length && array[index * 2 + 1] > array[maxIndex]) {
            maxIndex = index * 2 + 1;
        }
        if (index * 2 + 2 < length && array[index * 2 + 2] > array[maxIndex]) {
            maxIndex = index * 2 + 2;
        }
        if (maxIndex != index) {
            swap(array, index, maxIndex);
            adjustHeap(array, maxIndex, length);

        }
    }

    private static void swap(int[] array, int index, int maxIndex) {
        int temp = array[index];
        array[index] = array[maxIndex];
        array[maxIndex] = temp;
    }

    public static void main(String[] args) {
        int[] a = new int[] {3, 2, 1, 5, 6, 4};

        for (int i : Heap.HeapSort(a)) {
            System.out.print(i);
            System.out.print("  ");
        }
    }
}
