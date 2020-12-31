package shuai.yu.stock;

import java.util.Arrays;

/**
 * 买卖股票的最佳时机 III
 * <p>
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）
 *
 * @author shuai.yu
 * @version 2020/11/08
 */
public class BestTimeToBuyAndSellStockIII_123 {
    public static void main(String[] args) {
        int[] price = new int[]{2, 1, 4};
        maxProfit(price);
    }

    public static int maxProfit(int[] prices) {
        int len = prices.length;
        if (len <= 1)
            return 0;
        int[][] dp = new int[len][5];

        // 动态规划入口
        for (int[] a : dp) {
            Arrays.fill(a, -0xfffffff);
        }
        for (int i = 0; i < dp[0].length; i++) {
            if (i % 2 == 0)
                dp[0][i] = 0;
        }
        dp[0][1] = -prices[0];

        // 状态转化过程
        for (int i = 1; i < len; i++) {
            // 第i天一次没买
            dp[i][0] = 0;
            // 第i天买了一次
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            // 第i天买卖了一次
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
            // 第i天第二次买
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
            // 第i天两次买卖
            dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
        }
        int ans = Integer.MIN_VALUE;
        for (int i : dp[len - 1]) {
            if (ans < i) {
                ans = i;
            }
        }
        return ans;
    }
}
