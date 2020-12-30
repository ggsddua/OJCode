package shuai.yu;

/**
 * 有效的山脉数组
 * 给定一个整数数组 A，如果它是有效的山脉数组就返回 true，否则返回 false
 * 有效的山脉数组：长度大于等于3，存在一个非边界数，使得左边严格递增，右边严格递减（包括该数）
 *
 * @author shuai.yu
 * @version 2020/11/03
 */
public class ValidMountainArray_941 {
    public static void main(String[] args) {
        int[] a = new int[]{0, 3, 2, 1};
        validMountainArray(a);
    }

    public static boolean validMountainArray(int[] A) {
        if (A == null || A.length < 3)
            return false;
        if (A[0] >= A[1]) {
            return false;
        }
        boolean isIncrease = true;
        for (int i = 1; i < A.length - 1; i++) {
            if (isIncrease) {
                if (A[i] > A[i + 1]) {
                    isIncrease = false;
                } else if (A[i] == A[i + 1]) {
                    return false;
                }
            } else {
                if (A[i] <= A[i + 1]) {
                    return false;
                }
            }
        }
        return !isIncrease;
    }
}
