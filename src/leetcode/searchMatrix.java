package leetcode;
/**
 * @author sam
 * @date 11/05/19 16:36
 */
public class searchMatrix {
    public static void main(String[] args) {
        int[][] matrix = new int[][] {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
        System.out.println(solution(matrix, 5));
        System.out.println(solution(matrix, 16));
        System.out.println(solution(matrix, 10));
    }

    public static boolean solution(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }
        int rowLength = matrix.length;
        int colLength = matrix[0].length;
        int colLimit = 0;

        for (int i = rowLength - 1; i >= 0; i--) {
            for (int j = colLimit; j < colLength; j++) {
                if (matrix[i][j] < target && j == colLength - 1) {
                    return false;
                } else if (matrix[i][j] > target) {
                    colLimit = j;
                    break;
                } else if (matrix[i][j] == target) {
                    return true;
                }
            }
        }
        return false;
    }
}
