package shuai.yu;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 单词拆分 II
 *
 * @author shuai.yu
 * @version 2020/11/01
 */
public class WordBreakII_140 {
    public static void main(String[] args) {

    }

    private static List<String> list = new LinkedList<>();

    public static List<String> wordBreak(String s, List<String> wordDict) {
        Map<String, Integer> map = new HashMap<>();
        int maxLen = 0;
        // 使用hashmap存储字典，可以提高匹配效率
        for (String s1 : wordDict) {
            map.put(s1, 0);
            maxLen = Math.max(maxLen, s1.length());
        }

        // 临时加上，为了提高效率，避免极端情况超时
        Map<Character, Integer> charMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            charMap.put(c, charMap.get(c) == null ? 0 : charMap.get(c) + 1);
        }
        for (String s1 : wordDict) {
            for (char c : s1.toCharArray()) {
                charMap.remove(c);
            }
        }
        if (charMap.size() != 0)
            return list;

        recursive(s, 0, maxLen, map, new StringBuilder());
        return list;
    }

    /**
     * 递归方案，超时
     */
    public static void recursive(String s, int begin, int maxLen, Map<String, Integer> map, StringBuilder sb) {
        // 递归出口
        if (begin >= s.length()) {
            if (sb.length() > 0) {
                list.add(sb.toString().substring(1));
            }
            return;
        }
        for (int end = begin + 1; end <= s.length(); end++) {
            String sub = s.substring(begin, end);
            // 快速退出，可以一定程度上提高效率
            if (sub.length() > maxLen) {
                return;
            }
            // 出现相同时，进行下一层递归，同时本层递归不结束
            if (map.containsKey(sub)) {
                sb.append(" ").append(sub);
                recursive(s, end, maxLen, map, sb);
                sb.delete(sb.length() - sub.length() - 1, sb.length());
            }
        }
    }
}
