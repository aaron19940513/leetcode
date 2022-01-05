package leetcode.daily;
import org.junit.Assert;
import org.junit.Test;

/**
 * 207. 课程表
 * 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
 * <p>
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
 * <p>
 * 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2, [[1,0]]
 * 输出: true
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
 * 示例 2:
 * <p>
 * 输入: 2, [[1,0],[0,1]]
 * 输出: false
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
 *  
 * <p>
 * 提示：
 * <p>
 * 输入的先决条件是由 边缘列表 表示的图形，而不是 邻接矩阵 。详情请参见图的表示法。
 * 你可以假定输入的先决条件中没有重复的边。
 * 1 <= numCourses <= 10^5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/course-schedule
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 08/04/20 10:28
 */
public class CanFinish {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[][] dp = new int[numCourses][numCourses];

        for (int[] prerequisite : prerequisites) {
            if (dp[prerequisite[1]][prerequisite[0]] == 1) {
                return false;
            }
            dp[prerequisite[0]][prerequisite[1]] = 1;
            for (int i = 0; i < numCourses; i++) {
                if (dp[prerequisite[1]][i] == 1) {
                    dp[prerequisite[0]][i] = 1;
                }
            }
        }
        return true;
    }

    @Test
    public void test() {
        Assert.assertTrue(canFinish(2, new int[][] {{1, 0}}));
        Assert.assertFalse(canFinish(2, new int[][] {{0, 1}, {1, 0}}));
        Assert.assertTrue(canFinish(5, new int[][] {{1, 0}, {2, 1}, {3, 2}, {4, 3}}));
        Assert.assertFalse(canFinish(5, new int[][] {{1, 0}, {2, 1}, {3, 2}, {0, 3}}));
    }

    @Test
    public void errorCase() {
        //Assert.assertTrue(canFinish(3, new int[][] {{0, 1}, {0, 2}, {1, 2}}));
        Assert.assertFalse(canFinish(3, new int[][] {{1, 0}, {0, 2}, {2, 1}}));
    }
}
