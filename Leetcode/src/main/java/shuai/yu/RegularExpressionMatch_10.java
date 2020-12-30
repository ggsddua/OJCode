package shuai.yu;

/**
 * 正则表达式匹配
 * <p>
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配
 * <p>
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * <p>
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 *
 * @author shuai.yu
 * @version 2020/10/14
 */
public class RegularExpressionMatch_10
{
    public static void main(String[] args)
    {
        String s = "aaaaa";
        String p = "aaa.a*";
    }

    private static boolean isMatch(String s, String p)
    {
        if (s == null|| p == null)
            return false;
        int[][] dp = new int[s.length()][p.length()];

        return true;
    }
}
