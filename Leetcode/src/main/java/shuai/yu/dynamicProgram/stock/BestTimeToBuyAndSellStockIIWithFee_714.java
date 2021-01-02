package shuai.yu.dynamicProgram.stock;

/**
 * 带手续费的
 *  在122题的基础上，加入手续费
 * @author shuai.yu
 * @version 2020/12/22
 */
public class BestTimeToBuyAndSellStockIIWithFee_714 {
    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length <= 1)
            return 0;
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            // 正常的股票购买动态规划，在卖出时收取手续费即可
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[prices.length - 1][0];
    }
}
