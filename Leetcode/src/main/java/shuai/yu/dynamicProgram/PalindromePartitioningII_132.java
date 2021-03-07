package shuai.yu.dynamicProgram;

import java.util.Arrays;

/**
 * 分割回文串 II
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 * 返回符合要求的最少分割次数。
 * 参照131题
 * 输入: "aab"
 * 输出: 1
 * 解释: 进行一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
 *
 * @author shuai.yu
 * @version 2021/03/07
 */
public class PalindromePartitioningII_132
{
    /**
     * 使用两个动态规划解决问题
     */
    public int minCut(String s)
    {
        int len = s.length();
        if (len <= 1)
            return 0;
        // 提前计算出子串是否是回文串
        boolean[][] checkPalindrome = new boolean[len][len];
        for (int right = 0; right < len; right++)
        {
            for (int left = 0; left <= right; left++)
            {
                // 左右相等，且除去左右两个元素，剩下的为空，或者剩下的是回文，则[left,right]是回文
                if (s.charAt(left) == s.charAt(right) && (right - left <= 2 || checkPalindrome[left + 1][right - 1]))
                {
                    checkPalindrome[left][right] = true;
                }
            }
        }

        // 状态定义：dp[i]：前缀子串 s[0:i] （包括索引 i 处的字符）符合要求的最少分割次数
        // 状态转移方程：
        // dp[i] = min(dp[j] + 1 if s[j + 1: i] 是回文 for j in range(i))
        int[] dp = new int[len];
        Arrays.fill(dp, len-1);
        dp[0] = 0;
        for (int i = 1; i < len; i++)
        {
            if (checkPalindrome[0][i])
            {
                dp[i] = 0;
                continue;
            }
            for (int j = 0; j < i; j++)
            {
                if (checkPalindrome[j + 1][i])
                {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[len - 1];
    }

    /**
     * 回溯 加记忆化搜索
     * 回溯算法，我们需要每次都使用isPalindrome来判断是否是回文串
     * 可以使用一个二维数组，保存已经计算过的子串信息。
     * 沿用131的思路，执行超时
     */
    public int minCut1(String s)
    {
        if (s.length() <= 1)
            return 0;
        int ans = Integer.MAX_VALUE;
        int[][] flag = new int[s.length()][s.length()];
        dfs(s, 0, 0, flag, ans);
        return ans;
    }

    private boolean isPalindrome(String s, int begin, int end)
    {
        for (; begin <= end; begin++, end--)
        {
            if (s.charAt(begin) != s.charAt(end))
                return false;
        }
        return true;
    }

    /**
     * 回溯 加记忆化搜索
     */
    private void dfs(String s, int begin, int curSize, int[][] flag, int min)
    {
        // 遍历以begin为开头的所有子串
        for (int end = begin; end < s.length(); end++)
        {
            // 是回文，进行递归；不是回文，找下一个串
            // 0是未计算过，1是回文，-1不是回文
            if (flag[begin][end] == 1 || (flag[begin][end] == 0 && isPalindrome(s, begin, end)))
            {
                flag[begin][end] = 1;
                curSize++;
                // 递归出口
                if (end == s.length() - 1)
                    min = Math.min(min, curSize - 1);
                else
                    dfs(s, end + 1, curSize, flag, min);
                //一次递归退出后，需要移除列表中最后一个子串，即长度减一
                curSize--;
            }
            else
                flag[begin][end] = -1;
        }
    }
}
