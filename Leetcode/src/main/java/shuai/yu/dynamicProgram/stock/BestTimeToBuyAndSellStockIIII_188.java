package shuai.yu.dynamicProgram.stock;

/**
 * 条件同上
 * 你最多可以完成 k 笔交易
 *
 * @author shuai.yu
 * @version 2020/12/21
 */
public class BestTimeToBuyAndSellStockIIII_188 {
    /**
     * 使用123题解决方案，将所有状态列出来
     */
    public static int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if (len <= 1 || k<=0)
            return 0;
        int[][] dp = new int[len][k * 2 + 1];

        // 动态规划入口
        for (int i = 0; i < dp[0].length; i++) {
            if (i % 2 == 0)
                dp[0][i] = 0;
        }
        dp[0][1] = -prices[0];

        // 状态转化过程
        for (int i = 1; i < len; i++) {
            // 一次没买，收益为0
            dp[i][0] = 0;
            for (int j = 1; j < dp[i].length; j++) {
                if (j % 2 == 0) {
                    // 第i天买了又卖的收益
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] + prices[i]);
                } else {
                    // 第i天买了的收益
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] - prices[i]);
                }
            }
        }
        // 找到最后一天的最大收益
        int ans = Integer.MIN_VALUE;
        for (int i : dp[len - 1]) {
            if (ans < i) {
                ans = i;
            }
        }
        return ans;
    }
}
