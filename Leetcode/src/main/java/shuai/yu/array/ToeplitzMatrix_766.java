package shuai.yu.array;

/**
 * 托普利茨矩阵
 * 给你一个 m x n 的矩阵 matrix 。如果这个矩阵是托普利茨矩阵，返回 true ；否则，返回 false 。
 * 如果矩阵上每一条由左上到右下的对角线上的元素都相同，那么这个矩阵是 托普利茨矩阵 。
 *
 * @author shuai.yu
 * @version 2021/02/22
 */
public class ToeplitzMatrix_766
{
    public static void main(String[] args)
    {
        /**
         * 输入：matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
         * 输出：true
         */
        int[][] matrix = new int[][]{{1, 2, 3, 4}, {5, 1, 2, 3}, {9, 5, 1, 2}};
        System.out.println(isToeplitzMatrix(matrix));
        matrix = new int[][]{{18}, {66}};
        System.out.println(isToeplitzMatrix(matrix));
    }

    public static boolean isToeplitzMatrix(int[][] matrix)
    {
        if (matrix == null || matrix.length == 0)
        {
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++)
        {
            int temp = matrix[i][0];
            for (int j = 1; j < m - i && j < n; j++)
            {
                if (temp != matrix[j + i][j])
                    return false;
            }
        }

        for (int i = 0; i < n; i++)
        {
            int temp = matrix[0][i];
            for (int j = 1; j < m && j + i < n; j++)
            {
                if (temp != matrix[j][j + i])
                    return false;
            }
        }
        return true;
    }
}
