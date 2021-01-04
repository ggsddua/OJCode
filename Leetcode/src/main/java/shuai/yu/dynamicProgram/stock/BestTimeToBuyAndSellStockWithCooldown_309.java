package shuai.yu.dynamicProgram.stock;

/**
 * 包含冷冻期
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 *
 * @author shuai.yu
 * @version 2020/12/22
 */
public class BestTimeToBuyAndSellStockWithCooldown_309 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1)
            return 0;
        int[][] dp = new int[prices.length][3];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0;
        for (int i = 1; i < prices.length; i++) {
            // 手里没股票，处于可购买状态
            // 只能是前一天也没有，或者前一天刚卖
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2]);
            // 手里有股票
            // 只能是前一天手里就有，或者前一天处于可购买状态
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            // 股票卖了
            // 前一天手里有股票，今天卖了
            dp[i][2] = dp[i - 1][1] + prices[i];
        }
        return Math.max(dp[prices.length - 1][0], dp[prices.length - 1][2]);
    }
}
