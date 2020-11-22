package shuai.yu.dynamicProgram;

/**
 * 背包问题
 *
 * @author shuai.yu
 * @version 2020/09/19
 */
public class Backpack
{
    public static void main(String[] args)
    {

    }

    /**
     * 0-1背包
     * 带备忘的自顶向下
     * 最优子结构：dp[i][j]表示将前i件物品装进限重为j的背包可以获得的最大价值, 0<=i<=N, 0<=j<=M
     * 状态转移方程：dp[i][j] = max(dp[i−1][j], dp[i−1][j−w[i]]+v[i]) // j >= w[i]
     *
     * @param v
     * @param w
     * @param m
     * @return
     */
    private static int memoized01Backpack(int[] v, int[] w, int m)
    {
        int[][] dp = new int[v.length][m];
        for (int i = 0; i < v.length; i++)
        {
            if (m >= w[i])
            {

            }
        }
        return 0;
    }

    private static int BottomUp01Backpack()
    {
        return 0;
    }
}
