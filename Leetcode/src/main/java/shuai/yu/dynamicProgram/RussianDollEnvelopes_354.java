package shuai.yu.dynamicProgram;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 俄罗斯套娃信封问题
 * 给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
 * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 * 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 * 注意：不允许旋转信封。
 * 输入：envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * 输出：3
 *
 * @author shuai.yu
 * @version 2021/03/04
 */
public class RussianDollEnvelopes_354
{
    public static void main(String[] args)
    {
        int[][] envelopes = new int[][]{{30,50},{12,2},{3,4},{12,15}};
        maxEnvelopes1(envelopes);
    }
    /**
     * 动态规划 最长单调子序列
     */
    public int maxEnvelopes(int[][] envelopes)
    {
        int len = envelopes.length;
        if (len <= 1)
            return len;
        Arrays.sort(envelopes, new Comparator<int[]>()
        {
            @Override
            public int compare(int[] o1, int[] o2)
            {
                if (o1[0] != o2[0])
                {
                    return o1[0] - o2[0];
                }
                // 此处二维数据倒序排列，可以避免一维数据相同导致的干扰
                return o2[1] - o1[1];
            }
        });

        int ans = 1;
        // 子串末位为nums[i]的子串长度
        int[] dp = new int[len];
        dp[0] = 1;
        for (int i = 1; i < len; i++)
        {
            dp[i] = 1;
            for (int j = 0; j < i; j++)
            {
                // 需要考虑一维变量相等的情况
                if (envelopes[i][1] > envelopes[j][1])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

    /**
     * 动态规划 二分查找
     */
    public static int maxEnvelopes1(int[][] envelopes)
    {
        int len = envelopes.length;
        if (len <= 1)
            return len;
        Arrays.sort(envelopes, new Comparator<int[]>()
        {
            @Override
            public int compare(int[] o1, int[] o2)
            {
                if (o1[0] != o2[0])
                {
                    return o1[0] - o2[0];
                }
                // 此处二维数据倒序排列，可以避免一维数据相同导致的干扰
                return o2[1] - o1[1];
            }
        });

        int ans = 0;
        // 长度为i的单调子串，末尾最小值为tails[i-1]
        int[] tails = new int[len];
        for (int[] a : envelopes)
        {
            // 二分查找
            int i = 0, j = ans;
            while (i < j)
            {
                int m = (i + j) / 2;
                if (a[1] > tails[m]) i = m + 1;
                else j = m;
            }
            tails[i] = a[1];
            if (j == ans)
                ans++;
        }

        return ans;
    }
}
