package shuai.yu.hash;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 猜字谜
 * 外国友人仿照中国字谜设计了一个英文版猜字谜小游戏，请你来猜猜看吧。
 * <p>
 * 字谜的迷面 puzzle 按字符串形式给出，如果一个单词 word 符合下面两个条件，那么它就可以算作谜底：
 * 1)单词 word 中包含谜面 puzzle 的第一个字母。
 * 2)单词 word 中的每一个字母都可以在谜面 puzzle 中找到。
 * 例如，如果字谜的谜面是 "abcdefg"，那么可以作为谜底的单词有 "faced", "cabbage", 和 "baggage"；
 * 而 "beefed"（不含字母 "a"）以及 "based"（其中的 "s" 没有出现在谜面中）都不能作为谜底。
 * 返回一个答案数组 answer，数组中的每个元素 answer[i] 是在给出的单词列表 words 中可以作为字谜迷面 puzzles[i] 所对应的谜底的单词数目。
 *
 * @author shuai.yu
 * @version 2021/02/26
 */
public class NumberOfValidWordsforEachPuzzle_1178
{
    public static void main(String[] args)
    {
        /**
         * 输入：
         * words = ["aaaa","asas","able","ability","actt","actor","access"],
         * puzzles = ["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]
         * 输出：[1,1,3,2,4,0]
         * 解释：
         * 1 个单词可以作为 "aboveyz" 的谜底 : "aaaa"
         * 1 个单词可以作为 "abrodyz" 的谜底 : "aaaa"
         * 3 个单词可以作为 "abslute" 的谜底 : "aaaa", "asas", "able"
         * 2 个单词可以作为 "absoryz" 的谜底 : "aaaa", "asas"
         * 4 个单词可以作为 "actresz" 的谜底 : "aaaa", "asas", "actt", "access"
         * 没有单词可以作为 "gaswxyz" 的谜底，因为列表中的单词都不含字母 'g'。
         */
        String[] words = new String[]{"aaaa", "asas", "able", "ability", "actt", "actor", "access"};
        String[] puzzles = new String[]{"aboveyz", "abrodyz", "abslute", "absoryz", "actresz", "gaswxyz"};
        findNumOfValidWords(words, puzzles);
    }

    /**
     * 使用hashMap，超时
     */
    public static List<Integer> findNumOfValidWords(String[] words, String[] puzzles)
    {
        int pLen = puzzles.length;
        List<Integer> list = new ArrayList<>(pLen);
        for (int i = 0; i < pLen; i++)
        {
            list.add(0);
        }
        List<Map<Character, Character>> puzzlesList = new ArrayList<>(pLen);
        for (int i = 0; i < pLen; i++)
        {
            Map<Character, Character> map = new HashMap<>();
            puzzlesList.add(map);
            for (char c : puzzles[i].toCharArray())
            {
                map.put(c, puzzles[i].toCharArray()[0]);
            }
        }

        for (String word : words)
        {
            char[] temp = word.toCharArray();
            for (int i = 0; i < pLen; i++)
            {
                Map<Character, Character> map = puzzlesList.get(i);
                boolean flag = true;
                boolean containFirst = false;
                for (char c : temp)
                {
                    if (!map.containsKey(c))
                    {
                        flag = false;
                        break;
                    }
                    if (!containFirst && map.get(c) == c)
                        containFirst = true;
                }
                if (flag && containFirst)
                    list.set(i, list.get(i) + 1);
            }
        }
        return list;
    }

    /**
     * 位运算
     *
     * @param words
     * @param puzzles
     * @return
     */
    public static List<Integer> findNumOfValidWords1(String[] words, String[] puzzles)
    {
        Map<Integer, Integer> frequency = new HashMap<Integer, Integer>();

        // a~z共26位，将word用26bit数表示
        for (String word : words)
        {
            int mask = 0;
            for (int i = 0; i < word.length(); ++i)
            {
                char ch = word.charAt(i);
                mask |= (1 << (ch - 'a'));
            }
            // 如果2进制数中1的个数大于7，不为任何字谜的谜底
            if (Integer.bitCount(mask) <= 7)
            {
                frequency.put(mask, frequency.getOrDefault(mask, 0) + 1);
            }
        }

        List<Integer> ans = new ArrayList<Integer>();
        for (String puzzle : puzzles)
        {
            int total = 0;
            int mask = 0;
            for (int i = 1; i < 7; ++i)
            {
                mask |= (1 << (puzzle.charAt(i) - 'a'));
            }
            int subset = mask;
            // 遍历，puzzle第一位字母一定在，后面的，依次减一，然后&mask（保证所有字母在谜面中），直至后六位为0
            do
            {
                int s = subset | (1 << (puzzle.charAt(0) - 'a'));
                if (frequency.containsKey(s))
                {
                    total += frequency.get(s);
                }
                subset = (subset - 1) & mask;
            } while (subset != mask);

            ans.add(total);
        }
        return ans;
    }

    /**
     * 位运算优化
     *
     * @param words
     * @param puzzles
     * @return
     */
    public static List<Integer> findNumOfValidWords2(String[] words, String[] puzzles)
    {
        Map<Integer, Integer> frequency = new HashMap<Integer, Integer>();

        // a~z共26位，将word用26bit数表示
        for (String word : words)
        {
            int mask = 0;
            for (int i = 0; i < word.length(); ++i)
            {
                char ch = word.charAt(i);
                mask |= (1 << (ch - 'a'));
            }
            // 如果2进制数中1的个数大于7，不为任何字谜的谜底
            if (Integer.bitCount(mask) <= 7)
            {
                frequency.put(mask, frequency.getOrDefault(mask, 0) + 1);
            }
        }

        List<Integer> ans = new ArrayList<Integer>();
        for (String puzzle : puzzles)
        {
            int total = 0;
            int mask = 0;
            int[] bit = new int[6];
            for (int i = 1; i < 7; ++i)
            {
                bit[i - 1] = (1 << (puzzle.charAt(i) - 'a'));
            }
            mask = (1 << (puzzle.charAt(0) - 'a'));

            // 遍历，puzzle第一位字母一定在，后六位，可有可无，列出所有的可能
            int[] bitFlag = new int[6];
            for (int i = 0b111111; i >= 0; i--)
            {
                int s = mask;
                for (int temp = i, j = 0; temp > 0; temp >>= 1, j++)
                {
                    if (temp % 2 == 1)
                    {
                        s |= bit[j];
                    }
                }
                if (frequency.containsKey(s))
                {
                    total += frequency.get(s);
                }

            }
            ans.add(total);
        }
        return ans;
    }
}
