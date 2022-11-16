package leetcode.daily.y2022m11;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Assert;
import org.junit.Test;

/**
 * 1710. 卡车上的最大单元数
 * 请你将一些箱子装在 一辆卡车 上。给你一个二维数组 boxTypes ，其中 boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi] ：
 *
 * numberOfBoxesi 是类型 i 的箱子的数量。
 * numberOfUnitsPerBoxi 是类型 i 每个箱子可以装载的单元数量。
 * 整数 truckSize 表示卡车上可以装载 箱子 的 最大数量 。只要箱子数量不超过 truckSize ，你就可以选择任意箱子装到卡车上。
 *
 * 返回卡车可以装载 单元 的 最大 总数。
 *
 */
public class MaximumUnits {

    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });
        int result = 0;
        for (int i = 0; i < boxTypes.length; i++) {
            truckSize = truckSize - boxTypes[i][0];
            if (truckSize > 0) {
                result += boxTypes[i][1] * boxTypes[i][0];
            } else {
                result += boxTypes[i][1] * (boxTypes[i][0] + truckSize);
                break;
            }
        }

        return result;
    }

    @Test
    public void test(){
        Assert.assertEquals(8, maximumUnits(new int[][]{{1, 3}, {2, 2}, {3, 1}}, 4));
        Assert.assertEquals(91, maximumUnits(new int[][]{{5, 10}, {2, 5}, {4, 7}, {3, 9}}, 10));
    }
}
