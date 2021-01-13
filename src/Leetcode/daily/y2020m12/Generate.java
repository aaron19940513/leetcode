package Leetcode.daily.y2020m12;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 118. 杨辉三角
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * <p>
 * <p>
 * <p>
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * <p>
 * 示例:
 * <p>
 * 输入: 5
 * 输出:
 * [
 * [1],
 * [1,1],
 * [1,2,1],
 * [1,3,3,1],
 * [1,4,6,4,1]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/pascals-triangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 12/16/20 13:45
 */
public class Generate {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> pre = new ArrayList<>();
        for (int i = 1; i <= numRows; i++) {
            List<Integer> arr = new ArrayList<>(i);
            for (int j = 0; j < i; j++) {
                if (j == 0 || j == i - 1) {
                    arr.add(1);
                } else {
                    arr.add(pre.get(j - 1) + pre.get(j));
                }
            }
            pre = arr;
            ans.add(arr);
        }
        return  ans;
    }

    @Test
    public void test(){
        for (List<Integer> integers : generate(5)) {
            System.out.println(integers);
        }
    }
}
