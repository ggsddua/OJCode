package shuai.yu.dynamicProgram;

/**
 * 最大公共子序列
 *
 * @author shuai.yu
 * @version 2020/07/11
 */
public class LongestCommonSubsequence
{
    public static void main(String[] args)
    {
        String text1 = "abcde";
        String text2 = "ace";
        LCSLength(text1, text2);
    }

    /**
     * 求最大公共子序列
     */
    public static int LCSLength(String text1, String text2)
    {
        // 如果长度为0，则公共子序列为0，直接返回
        if (text1.length() == 0 || text2.length() == 0)
            return 0;

        // 创建[m+1][n+1]的数组，[0][n]或[n][0]用来让算法更通用，减少特殊判断
        int[][] step = new int[text1.length() + 1][text2.length() + 1];

        // 从1开始，可以直接取[m-1]或[n-1]
        for (int i = 1; i <= text1.length(); i++)
        {
            for (int j = 1; j <= text2.length(); j++)
            {
                if (text1.charAt(i - 1) == text2.charAt(j - 1))
                {
                    step[i][j] = step[i - 1][j - 1] + 1;
                }
                else
                {
                    step[i][j] = Math.max(step[i - 1][j], step[i][j - 1]);
                }
            }
        }
        return step[text1.length()][text2.length()];
    }
}
