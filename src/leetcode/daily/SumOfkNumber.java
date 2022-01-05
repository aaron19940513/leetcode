package leetcode.daily;
/**
 * 寻找和为定值的多个数
 * 题目描述
 * 输入两个整数n和sum，从数列1，2，3.......n 中随意取几个数，使其和等于sum，要求将其中所有的可能组合列出来。
 *
 * @author sam
 * @date 10/09/19 17:04
 */
public class SumOfkNumber {

    public static void main(String[] args) {
        // 初始化解空间
        int N = 50;
        int M = 6;
        Boolean[] X = new Boolean[N + 1];
        int sum = (int) ((N + 1) * N * 0.5f);
        if (1 > M || sum < M) // 预先排除无解情况
        {
            System.out.println("not found\n");
            return;
        }
        String f = "false";
        solve(0, 1, sum, M, f, X);
        if (f.equals(false)) {
            System.out.println("not found\n");
        }

    }

    private static void solve(int t, int k, int r, int M, String flag, Boolean[] X) {
        // 选第k个数
        X[k] = true;
        // 若找到一个和为M，则设置解向量的标志位，输出解
        if (t + k == M) {
            flag = "true";
            for (int i = 1; i <= k; ++i) {
                if (X[i]) {
                    System.out.print(String.format("%d ", i));
                }
            }
            System.out.println();
        } else {
            // 若第k+1个数满足条件，则递归左子树
            if (t + k + (k + 1) <= M) {
                solve(t + k, k + 1, r - k, M, flag, X);
            }
            // 若不选第k个数，选第k+1个数满足条件，则递归右子树
            if ((t + r - k >= M) && (t + (k + 1) <= M)) {
                X[k] = false;
                solve(t, k + 1, r - k, M, flag, X);
            }
        }
    }
}
