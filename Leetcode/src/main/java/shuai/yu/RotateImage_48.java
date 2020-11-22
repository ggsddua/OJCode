package shuai.yu;

/**
 * 旋转图像
 *
 * @author shuai.yu
 * @version 2020/10/31
 */
public class RotateImage_48 {
    public static void main(String[] args) {
        int[][] matrix = new int[3][3];
        System.out.println(matrix.length);
    }

    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 1)
            return;
        int temp = 0;
        int len = matrix.length;
        for (int i = 0; i < len / 2; i++) {
            for (int j = i; j < len - i - 1; j++) {
                // 一个点，和四个点的对应，正好一圈
                // 根据对应关系，可知，也可以先左上右下对角线镜像，然后在竖直线翻转
                temp = matrix[i][j];
                matrix[i][j] = matrix[len - j - 1][i];
                matrix[len - j - 1][i] = matrix[len - i - 1][len - j - 1];
                matrix[len - i - 1][len - j - 1] = matrix[j][len - i - 1];
                matrix[j][len - i - 1] = temp;
            }
        }
    }
}
