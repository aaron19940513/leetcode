package leetcode.daily.y2021m01;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 * 399. 除法求值
 * 给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。
 * <p>
 * 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
 * <p>
 * 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。
 * <p>
 * <p>
 * <p>
 * 注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * 输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * 解释：
 * 条件：a / b = 2.0, b / c = 3.0
 * 问题：a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 * 结果：[6.0, 0.5, -1.0, 1.0, -1.0 ]
 * 示例 2：
 * <p>
 * 输入：equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 * 输出：[3.75000,0.40000,5.00000,0.20000]
 * 示例 3：
 * <p>
 * 输入：equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
 * 输出：[0.50000,2.00000,-1.00000,-1.00000]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= equations.length <= 20
 * equations[i].length == 2
 * 1 <= Ai.length, Bi.length <= 5
 * values.length == equations.length
 * 0.0 < values[i] <= 20.0
 * 1 <= queries.length <= 20
 * queries[i].length == 2
 * 1 <= Cj.length, Dj.length <= 5
 * Ai, Bi, Cj, Dj 由小写英文字母与数字组成
 *
 * @date 01/06/21 9:14
 */
public class CalcEquation {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Double> dp = new HashMap<>();
        Set<String> equation = new HashSet<>();
        for (int i = 0; i < equations.size(); i++) {
            dp.put(equations.get(i).get(0) + "," + equations.get(i).get(1), values[i]);
            dp.put(equations.get(i).get(1) + "," + equations.get(i).get(0), 1 / values[i]);
            equation.add(equations.get(i).get(0));
            equation.add(equations.get(i).get(1));
        }
        double[] ans = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String c = queries.get(i).get(0);
            String d = queries.get(i).get(1);
            if (equation.contains(c) && equation.contains(d)) {
                if (c.equals(d)) {
                    ans[i] = 1;
                } else if (dp.containsKey(c + "," + d)) {
                    ans[i] = dp.get(c + "," + d);
                } else if (dp.containsKey(d + "," + c)) {
                    ans[i] = dp.get(d + "," + c);
                } else {
                    for (String s : equation) {
                        if (s.equals(c) || s.equals(d)) {
                            continue;
                        }
                        if (dp.containsKey(c + "," + s) && dp.containsKey(s + "," + d)) {
                            ans[i] = dp.get(c + "," + s) * dp.get(s + "," + d);
                            break;
                        }
                    }
                    if (ans[i] == 0) {
                        ans[i] = -1;
                    }
                }
            } else {
                ans[i] = -1;
            }
        }

        return ans;
    }

    @Test
    public void test() {
        List<List<String>> equations = new ArrayList<>();
        List<String> item = new ArrayList<>();
        item.add("a");
        item.add("b");
        equations.add(item);
        List<String> item1 = new ArrayList<>();
        item1.add("b");
        item1.add("c");
        equations.add(item1);

        List<List<String>> queries = new ArrayList<>();
        List<String> item2 = new ArrayList<>();
        item2.add("a");
        item2.add("c");
        queries.add(item2);
        List<String> item3 = new ArrayList<>();
        item3.add("b");
        item3.add("a");
        queries.add(item3);
        List<String> item4 = new ArrayList<>();
        item4.add("a");
        item4.add("e");
        queries.add(item4);
        List<String> item5 = new ArrayList<>();
        item5.add("a");
        item5.add("a");
        queries.add(item5);
        List<String> item6 = new ArrayList<>();
        item6.add("x");
        item6.add("x");
        queries.add(item6);
        for (double v : calcEquation(equations, new double[] {2.0, 3.0}, queries)) {
            System.out.println(v);
        }
        Assert.assertEquals(new double[]{6.0, 0.5, -1.0, 1.0, -1.0 }, calcEquation(equations, new double[] {2.0, 3.0}, queries));
    }

}


