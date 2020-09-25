package shuai.yu;

import java.util.HashMap;
import java.util.Map;

/**
 * longest substring without repeating characters
 * 无重复字符的最长子串
 *
 * @author shuai.yu
 * @version 2020/09/21
 */
public class LongestSubWithoutRepeat_3
{
    public static void main(String[] args)
    {
        lengthOfLongestSubstring("abcddfgc");
        lengthOfLongestSubstringSlidingWindow("pwwkew");
        lengthOfLongestSubstringSlidingWindow("au");
    }

    /**
     * 暴力求解
     */
    public static int lengthOfLongestSubstring(String s)
    {
        if (s == null || s.isEmpty())
            return 0;
        int length = s.length();
        int longestSub = 1;
        for (int i = 0; i < length - 1; i++)
        {
            for (int j = i + 1; j < length; j++)
            {
                if (s.substring(i, j).contains(String.valueOf(s.charAt(j))))
                {
                    longestSub = (j - i) > longestSub ? (j - i) : longestSub;
                    break;
                }
                longestSub = (j - i + 1) > longestSub ? (j - i + 1) : longestSub;
            }
        }
        return longestSub;
    }

    /**
     * 滑动窗口求解
     * <p>
     * 滑动窗口其实就是一个队列,比如例题中的 abcabcbb，进入这个队列（窗口）为 abc 满足题目要求，当再进入 a，队列变成了 abca，
     * 这时候不满足要求。所以，我们要移动这个队列！
     * <p>
     * 如何移动？
     * <p>
     * 只要把队列的左边的元素移出就行了，直到满足题目要求！
     * <p>
     * 一直维持这样的队列，找出队列出现最长的长度时候，求出解！
     */
    public static int lengthOfLongestSubstringSlidingWindow(String s)
    {
        if (s == null || s.isEmpty())
            return 0;
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++)
        {
            if(map.containsKey(s.charAt(i)))
            {
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - left + 1);
        }
        return max;
    }
}
