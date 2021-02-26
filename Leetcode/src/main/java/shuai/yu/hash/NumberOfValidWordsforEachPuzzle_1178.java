package shuai.yu.hash;

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

    public static List<Integer> findNumOfValidWords(String[] words, String[] puzzles)
    {
        // TODO: 2021/2/26 超时 
        List<Integer> list = new LinkedList<>();
        Map<Integer, Map<Character, Character>> puzzlesMap = new HashMap<>();
        for (int i = 0; i < puzzles.length; i++)
        {
            Map<Character, Character> map = new HashMap<>();
            puzzlesMap.put(i, map);
            for (char c : puzzles[i].toCharArray())
            {
                map.put(c, puzzles[i].toCharArray()[0]);
            }
        }

        for (Map<Character, Character> map : puzzlesMap.values())
        {
            int ans = 0;
            for (String word : words)
            {
                boolean flag = true;
                boolean containFirst = false;
                for (char c : word.toCharArray())
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
                    ans++;
            }
            list.add(ans);
        }
        return list;
    }
}
