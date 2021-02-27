package shuai.yu.recursion;

import java.util.HashMap;
import java.util.Map;

/**
 * 至少有 K 个重复字符的最长子串
 * 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。
 * 返回这一子串的长度。
 *
 * @author shuai.yu
 * @version 2021/02/27
 */
public class LongestSubstringWithAtLeastKRepeatingCharacters_395
{
    public static void main(String[] args)
    {

    }

    int max = 0;

    public int longestSubstring(String s, int k)
    {
        recursion(s.toCharArray(), 0, s.length() - 1, k);
        return max;
    }

    /**
     * 递归求解
     */
    public void recursion(char[] chars, int begin, int end, int k)
    {
        if (end - begin + 1 < k)
        {
            return;
        }

        // 统计出每个字符的数量
        Map<Character, Integer> map = new HashMap<>();
        for (int i = begin; i <= end; i++)
        {
            map.put(chars[i], map.getOrDefault(chars[i], 0) + 1);
        }

        // 将数量大于k的删除，留下的字符用来做分隔符，进行递归
        map.entrySet().removeIf(entry -> entry.getValue() >= k);
        if (map.isEmpty())
        {
            max = Math.max(end - begin + 1, max);
            return;
        }
        for (int i = begin; i <= end; i++)
        {
            if (map.containsKey(chars[i]))
            {
                recursion(chars, begin, i - 1, k);
                begin = i + 1;
            }
        }
        if (begin < end)
            recursion(chars, begin, end, k);
    }
}
