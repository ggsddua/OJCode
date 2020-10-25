package shuai.yu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 电话号码的字母组合
 *
 * @author shuai.yu
 * @version 2020/10/18
 */
public class LetterCombinations_17
{
    public static void main(String[] args)
    {
        letterCombinations1("23");
    }

    /**
     * 使用4进制求解
     */
    public static List<String> letterCombinations1(String digits)
    {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.isEmpty())
            return result;
        Map<Character, List<Character>> map = new HashMap<>();
        map.put('2', Arrays.asList('a', 'b', 'c'));
        map.put('3', Arrays.asList('d', 'e', 'f'));
        map.put('4', Arrays.asList('g', 'h', 'i'));
        map.put('5', Arrays.asList('j', 'k', 'l'));
        map.put('6', Arrays.asList('m', 'n', 'o'));
        map.put('7', Arrays.asList('p', 'q', 'r', 's'));
        map.put('8', Arrays.asList('t', 'u', 'v'));
        map.put('9', Arrays.asList('w', 'x', 'y', 'z'));
        char[] digit = digits.toCharArray();
        int len = digit.length;
        int all = 4;
        for (int i = 0; i < len - 1; i++)
            all *= 4;
        for (int i = 0; i < all; i++)
        {
            StringBuilder sb = new StringBuilder();
            boolean success = true;
            int temp = i;
            for (int j = len - 1; j >= 0; j--)
            {
                int pos = temp % 4;
                // 下标超了，直接返回。非79，下标为3时，超
                if (pos > map.get(digit[j]).size() - 1)
                {
                    success = false;
                    break;
                }
                temp >>= 2;
                sb.append(map.get(digit[j]).get(pos));
            }
            if (success)
                // 翻转
                result.add(sb.reverse().toString());
        }

        return result;
    }

    /**
     * 递归实现
     */
    public static List<String> letterCombinations2(String digits)
    {
        if (digits == null || digits.isEmpty())
            return result;

        map.put('2', Arrays.asList('a', 'b', 'c'));
        map.put('3', Arrays.asList('d', 'e', 'f'));
        map.put('4', Arrays.asList('g', 'h', 'i'));
        map.put('5', Arrays.asList('j', 'k', 'l'));
        map.put('6', Arrays.asList('m', 'n', 'o'));
        map.put('7', Arrays.asList('p', 'q', 'r', 's'));
        map.put('8', Arrays.asList('t', 'u', 'v'));
        map.put('9', Arrays.asList('w', 'x', 'y', 'z'));
        char[] digit = digits.toCharArray();
        iterStr(digit, new StringBuilder(), 0);
        return result;
    }

    static List<String> result = new ArrayList<>();
    static Map<Character, List<Character>> map = new HashMap<>();

    private static void iterStr(char[] digit, StringBuilder letter, int index)
    {
        // 递归终止条件
        if (index == digit.length)
        {
            result.add(letter.toString());
            return;
        }

        // 遍历index下的所有字符
        for (char c : map.get(digit[index]))
        {
            letter.append(c);
            // 调用下一层
            iterStr(digit, letter, index + 1);
            letter.deleteCharAt(letter.length() - 1);
        }
    }
}
