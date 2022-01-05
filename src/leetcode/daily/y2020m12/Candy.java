package leetcode.daily.y2020m12;
import org.junit.Assert;
import org.junit.Test;

/**
 * 135. 分发糖果 hard
 * 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
 * <p>
 * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
 * <p>
 * 每个孩子至少分配到 1 个糖果。
 * 相邻的孩子中，评分高的孩子必须获得更多的糖果。
 * 那么这样下来，老师至少需要准备多少颗糖果呢？
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,0,2]
 * 输出: 5
 * 解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。
 * 示例 2:
 * <p>
 * 输入: [1,2,2]
 * 输出: 4
 * 解释: 你可以分别给这三个孩子分发 1、2、1 颗糖果。
 * 第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/candy
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 12/24/20 9:49
 */
public class Candy {
    public int candy(int[] ratings) {
        int ans = 1;
        int preValue = ratings[0];
        int preCandy = 1;
        for (int i = 1; i < ratings.length; ) {
            int count = 1;
            if (ratings[i] > preValue) {
                while (i < ratings.length && ratings[i] > preValue) {
                    preValue = ratings[i];
                    count++;
                    i++;
                }
                ans += (1 + count) * count / 2 - preCandy;
                preCandy = count;
            } else if (ratings[i] < preValue) {
                while (i < ratings.length && ratings[i] < preValue) {
                    preValue = ratings[i];
                    count++;
                    i++;
                }
                if (count >= preCandy) {
                    ans += (1 + count) * count / 2 - preCandy;
                } else {
                    ans += (count) * (count - 1) / 2;
                }
                preCandy = 1;

            } else {
                while (i < ratings.length && ratings[i] == preValue) {
                    count++;
                    i++;
                }
                ans += count - 1;
                preCandy = 1;
            }
        }
        return ans;
    }

    @Test
    public void test() {
        Assert.assertEquals(candy(new int[] {1, 1,2,2,3,3}), 8);
        Assert.assertEquals(candy(new int[] {1, 0, 2}), 5);
        Assert.assertEquals(candy(new int[] {1, 2, 2}), 4);
        Assert.assertEquals(candy(new int[] {1, 2, 3, 3, 3, 4, 5, 6, 7, 4, 3}), 25);
        Assert.assertEquals(candy(new int[] {1, 2, 7, 6, 5, 4, 3, 2, 1}), 31);
    }
}
