package shuai.yu.dynamicProgram;

/**
 * 最长递增子序列
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * <p>
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 *
 * @author shuai.yu
 * @version 2021/03/06
 */
public class LongestIncreasingSubsequence_300
{
    /**
     * 动态规划 n^2
     */
    public int lengthOfLIS(int[] nums)
    {
        /**
         *  dp[i] 为考虑前 i个元素，且nums[i] 必须被选取，即以第 i个数字结尾的最长上升子序列的长度
         *  我们要求的结果就是 max(dp[i]),其中0≤i<n（注意不是dp[n-1]）
         */
        int len = nums.length;
        if (len <= 1)
        {
            return len;
        }

        int ans = 1;
        int[] dp = new int[len];
        dp[0] = 1;
        for (int i = 1; i < len; i++)
        {
            dp[i] = 1;
            // 遍历前面所有的
            for (int j = 0; j < i; j++)
            {
                if (nums[i] > nums[j])
                {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    /**
     * 动态规划 n^2
     */
    public int lengthOfLIS1(int[] nums)
    {
        /**
         * 重新设计状态定义，tails，其中每个元素 tails[k]的值代表长度为 k+1的子序列尾部元素的值
         * 我们要的结果，即为tails的长度。
         * tails是单调递增的。
         * 示例：{1, 6, 7, 8, 2, 3, 4, 5}
         * 扫描前四个数后，tails为{1, 6, 7, 8}
         * 扫描第五个数后，变为{1, 2, 7, 8}
         * 扫描第六个数后，变为{1, 2, 3, 8}
         * 扫描第七个数后，变为{1, 2, 3, 4}
         * 扫描第八个数后，变为{1, 2, 3, 4, 5}
         */
        int len = nums.length;
        if (len <= 1)
        {
            return len;
        }

        int ans = 0;
        int[] tails = new int[len];
        for (int num : nums)
        {
            int i = 0, j = ans;
            // 二分法找到第一个不小于num的tails[index]
            while (i < j)
            {
                int m = (i + j) / 2;
                if (tails[m] < num) i = m + 1;
                else j = m;
            }
            tails[i] = num;
            if (ans == j) ans++;
        }
        return ans;
    }
}
