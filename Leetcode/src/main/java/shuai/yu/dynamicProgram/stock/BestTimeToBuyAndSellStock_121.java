package shuai.yu.dynamicProgram.stock;

/**
 * 买卖股票的最佳时机
 * <p>
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 * 注意：你不能在买入股票前卖出股票
 *
 * @author shuai.yu
 * @version 2020/11/08
 */
public class BestTimeToBuyAndSellStock_121 {

    /**
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;
        int min = Integer.MAX_VALUE;
        int max = 0;
        // 遍历，i对应的最大利润，一定为在i之前价格最低时买入
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            } else if (prices[i] - min > max) {
                max = prices[i] - min;
            }
        }
        return max;
    }
}
