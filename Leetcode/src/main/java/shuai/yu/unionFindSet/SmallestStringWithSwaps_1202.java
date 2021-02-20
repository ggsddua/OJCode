package shuai.yu.unionFindSet;

import shuai.yu.common.UnionFindSet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 交换字符串中的元素
 * 给你一个字符串 s，以及该字符串中的一些「索引对」数组 pairs，其中 pairs[i] = [a, b] 表示字符串中的两个索引（编号从 0 开始）。
 * 你可以任意多次交换在 pairs 中的任意一对索引处的字符。
 * 返回在经过若干次交换后，s 可以变成的按字典序最小的字符串（即字母从小到大）
 *
 * @author shuai.yu
 * @version 2021/02/19
 */
public class SmallestStringWithSwaps_1202
{
    public static void main(String[] args)
    {
        /**
         * 输入：s = "dcab", pairs = [[0,3],[1,2]]
         * 输出："bacd"
         * 解释：
         * 交换 s[0] 和 s[3], s = "bcad"
         * 交换 s[1] 和 s[2], s = "bacd"
         *
         * 输入：s = "dcab", pairs = [[0,3],[1,2],[0,2]]
         * 输出："abcd"
         * 解释：
         * 交换 s[0] 和 s[3], s = "bcad"
         * 交换 s[0] 和 s[2], s = "acbd"
         * 交换 s[1] 和 s[2], s = "abcd"
         *
         * 输入：s = "cba", pairs = [[0,1],[1,2]]
         * 输出："abc"
         * 解释：
         * 交换 s[0] 和 s[1], s = "bca"
         * 交换 s[1] 和 s[2], s = "bac"
         * 交换 s[0] 和 s[1], s = "abc"
         */

    }

    public static String smallestStringWithSwaps(String s, List<List<Integer>> pairs)
    {
        if (s == null || s.length() <= 1 || pairs == null || pairs.isEmpty())
            return s;

        // 第 1 步：将任意交换的结点对输入并查集
        UnionFindSet unionFindSet = new UnionFindSet();
        for (int i = 0; i < s.length(); i++)
        {
            unionFindSet.add(i);
        }
        pairs.forEach(list ->
        {
            unionFindSet.merge(list.get(0), list.get(1));
        });

        // 第 2 步：构建映射关系
        char[] charArray = s.toCharArray();
        // key：连通分量的代表元，value：同一个连通分量的字符集合（保存在一个优先队列中）
        Map<Integer, PriorityQueue<Character>> hashMap = new HashMap<>(s.length());
        for (int i = 0; i < s.length(); i++)
        {
            int root = unionFindSet.findRoot(i);
            hashMap.computeIfAbsent(root, key -> new PriorityQueue<>()).offer(charArray[i]);
        }

        // 第 3 步：重组字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++)
        {
            int root = unionFindSet.findRoot(i);
            stringBuilder.append(hashMap.get(root).poll());
        }
        return stringBuilder.toString();
    }
}
