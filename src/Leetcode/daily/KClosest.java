package Leetcode.daily;
import java.util.Comparator;
import java.util.PriorityQueue;

import org.junit.Assert;
import org.junit.Test;

/**
 * 973. 最接近原点的 K 个点
 * 我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
 * <p>
 * （这里，平面上两点之间的距离是欧几里德距离。）
 * <p>
 * 你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：points = [[1,3],[-2,2]], K = 1
 * 输出：[[-2,2]]
 * 解释：
 * (1, 3) 和原点之间的距离为 sqrt(10)，
 * (-2, 2) 和原点之间的距离为 sqrt(8)，
 * 由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
 * 我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
 * 示例 2：
 * <p>
 * 输入：points = [[3,3],[5,-1],[-2,4]], K = 2
 * 输出：[[3,3],[-2,4]]
 * （答案 [[-2,4],[3,3]] 也会被接受。）
 * <p>
 * 提示：
 * <p>
 * 1 <= K <= points.length <= 10000
 * -10000 < points[i][0] < 10000
 * -10000 < points[i][1] < 10000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/k-closest-points-to-origin
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 11/09/20 9:12
 */
public class KClosest {
    // 找出最小的K个数，只需要维护一个size=K的大顶推
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(K, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return pow(o1) > pow(o2) ? -1 : 1;
            }
        });
        for (int i = 0; i < points.length; i++) {
            if (i < K) {
                queue.offer(points[i]);
            } else {
                if (pow(points[i]) < pow(queue.peek())) {
                    queue.poll();
                    queue.offer(points[i]);
                }
            }
        }
        int[][] ans = new int[K][2];
        int count = 0;
        while (queue.size() > 0) {
            ans[count] = queue.poll();
            count++;
        }
        return ans;
    }

    private double pow(int[] arr) {
        return Math.pow(arr[0], 2) + Math.pow(arr[1], 2);
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[][] {{-2, 2}}, kClosest(new int[][] {{1, 3}, {-2, 2}}, 1));
        Assert.assertArrayEquals(new int[][] {{-2, 4}, {3, 3}}, kClosest(new int[][] {{3, 3}, {5, -1}, {-2, 4}}, 2));
    }

    @Test
    public void test1() {
        kClosest(new int[][] {{68, 97}, {34, -84}, {60, 100}, {2, 31}, {-27, -38}, {-73, -74}, {-55, -39}, {62, 91}, {62, 92}, {-57, -67}}, 5);

    }

}
