package Leetcode.sort;
/**
 * @author sam
 * @date 10/09/19 9:14
 */
public class QuickSort {

    public static void main(String[] args) {
        Integer[] array = new Integer[] {5, 3, 7, 6, 4, 1, 0, 2, 9, 10, 8};
        sort(array, 0, array.length - 1);
        output(array);

        Integer[] array1 = new Integer[] {5,4,4,3,3,2,1};
        sort(array1, 0, array1.length - 1);
        output(array1);
    }


    private static void sort(Integer[] array, Integer startIndex, Integer endIndex) {
        if (endIndex > startIndex) {
            int flag = array[startIndex];
            int i = startIndex;
            int j = endIndex;
            while (i < j) {
                while (i < j && array[j] >= flag) {
                    j--;
                }
                array[i] =array[j];

                while (i < j && array[i] <= flag) {
                    i++;
                }
                array[j] = array[i];
            }
            array[i] = flag;
            output(array);
            sort(array, startIndex, i - 1);
            sort(array, i + 1, endIndex);
        }
    }


    private static Integer findBig(int flag, Integer[] array, Integer startIndex) {
        Integer index = startIndex;
        while (index <= array.length - 1) {
            if (array[index] > flag) {
                return index;
            }
            index++;
        }
        return null;
    }


    private static void output(Integer[] array) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Integer integer : array) {
            stringBuffer.append(integer).append("  ");
        }
        System.out.println(stringBuffer.toString());
    }
}
