package shuai.yu;

/**
 * 最长回文子串
 * 给定一个字符串 s，找到 s 中最长的回文子串
 * <p>
 * 解法：
 * 1.暴力
 * 2.动态规划
 * 3.中心扩散
 * 4.Manacher算法（马拉车算法）
 *
 * @author shuai.yu
 * @version 2020/09/22
 */
public class LongestPalindromicSub_5
{
    public static void main(String[] args)
    {
        longestPalindrome2("babad");
    }

    /**
     * 暴力匹配
     * 简单暴力，代码略
     * 时间复杂度O(n^3)
     * <p>
     * 根据回文子串的定义，枚举所有长度大于等于2的子串，依次判断它们是否是回文；
     * 在具体实现时，可以只针对大于“当前得到的最长回文子串长度”的子串进行“回文验证”；
     * 在记录最长回文子串的时候，可以只记录“当前子串的起始位置”和“子串长度”，不必做截取。这一步我们放在后面的方法中实现。
     */
    public static String longestPalindrome1(String s)
    {
        return s;
    }

    /**
     * 个人思路
     * 中心扩散法
     * 时间复杂度O(n^2)
     *
     * @param s
     * @return
     */
    public static String longestPalindrome2(String s)
    {
        if (s == null || s.isEmpty())
            return "";
        if (s.length() == 1)
            return s;

        int begin = 0;
        int end = 0;
        int maxLen = 0;

        // 从中间往两边摊，找回文
        for (int i = 1; i < s.length(); i++)
        {
            // 中间为字母
            for (int left = i - 1, right = i + 1; true; left--, right++)
            {
                if ((left < 0 || right > s.length() - 1) || s.charAt(left) != s.charAt(right))
                {
                    if (right - left > maxLen)
                    {
                        maxLen = right - left;
                        begin = left;
                        end = right;
                    }
                    break;
                }
            }

            // 中间为空，或者不存在中间字母
            for (int left = i - 1, right = i; true; left--, right++)
            {
                if ((left < 0 || right > s.length() - 1) || s.charAt(left) != s.charAt(right))
                {
                    if (right - left > maxLen)
                    {
                        maxLen = right - left;
                        begin = left;
                        end = right;
                    }
                    break;
                }
            }
        }

        return s.substring(begin + 1, end);
    }

    /**
     * 动态规划
     * 这道题比较烦人的是判断回文子串。因此需要一种能够快速判断原字符串的所有子串是否是回文子串的方法，于是想到了「动态规划」。
     * 「动态规划」的一个关键的步骤是想清楚「状态如何转移」。事实上，「回文」天然具有「状态转移」性质。
     * 一个回文去掉两头以后，剩下的部分依然是回文（这里暂不讨论边界情况）；
     * 依然从回文串的定义展开讨论：
     * 1）如果一个字符串的头尾两个字符都不相等，那么这个字符串一定不是回文串；
     * 2）如果一个字符串的头尾两个字符相等，才有必要继续判断下去。
     * 2.1）如果里面的子串是回文，整体就是回文串；
     * 2.2）如果里面的子串不是回文串，整体就不是回文串。
     * 定义状态：
     * dp[i][j] 表示子串 s[i..j] 是否为回文子串，这里子串 s[i..j] 定义为左闭右闭区间，可以取到 s[i] 和 s[j]
     * 状态转移方程：
     * dp[i][j] = (s[i] == s[j]) and dp[i + 1][j - 1]
     * 参考： https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zhong-xin-kuo-san-dong-tai-gui-hua-by-liweiwei1419/
     */
    public static String longestPalindrome3(String s)
    {
        if (s == null || s.isEmpty())
            return "";
        if (s.length() == 1)
            return s;

        return s;
    }
}
