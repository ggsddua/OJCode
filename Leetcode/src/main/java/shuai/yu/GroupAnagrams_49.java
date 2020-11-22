package shuai.yu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 字母异位词分组
 *
 * @author shuai.yu
 * @version 2020/10/31
 */
public class GroupAnagrams_49 {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        char[] c;
        String key;
        for (String s : strs) {
            // 先排序，排序后，就可以找到唯一性，使用map存储
            c = s.toCharArray();
            Arrays.sort(c);
            key = Arrays.toString(c);
            List<String> list = map.computeIfAbsent(key, k -> new LinkedList<>());
            list.add(s);
        }
        map.forEach((s, strings) -> {
            ans.add(strings);
        });
        return ans;
    }
}
