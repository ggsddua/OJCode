package shuai.yu.dynamicProgram;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 分割回文串
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 * 返回 s 所有可能的分割方案
 * 输入: "aab"
 * 输出:
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 *
 * 回溯，记忆化搜索，动态规划
 *
 * @author shuai.yu
 * @version 2021/03/07
 */
public class PalindromePartitioning_131
{
    List<List<String>> listList = new LinkedList<>();

    /**
     * 回溯
     */
    public List<List<String>> partition(String s)
    {
        dfs(s, 0, new ArrayList<>());
        return listList;
    }

    private void dfs(String s, int begin, List<String> list)
    {
        // 遍历以begin为开头的所有子串
        for (int end = begin; end < s.length(); end++)
        {
            // 是回文，进行递归；不是回文，找下一个串
            if (isPalindrome(s, begin, end))
            {
                list.add(s.substring(begin, end + 1));
                // 递归出口
                if (end == s.length() - 1)
                    listList.add(new ArrayList<>(list));
                else
                    dfs(s, end + 1, list);
                //一次递归退出后，需要移除列表中最后一个子串
                list.remove(list.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s, int begin, int end)
    {
        for (; begin <= end; begin++, end--)
        {
            if (s.charAt(begin) != s.charAt(end))
                return false;
        }
        return true;
    }

    /**
     * 回溯 加记忆化搜索
     * 回溯算法，我们需要每次都使用isPalindrome来判断是否是回文串
     * 可以使用一个二维数组，保存已经计算过的子串信息。
     */
    public List<List<String>> partition1(String s)
    {
        int[][] flag = new int[s.length()][s.length()];
        dfs(s, 0, new ArrayList<>(), flag);
        return listList;
    }

    /**
     * 回溯 加记忆化搜索
     */
    private void dfs(String s, int begin, List<String> list, int[][] flag)
    {
        // 遍历以begin为开头的所有子串
        for (int end = begin; end < s.length(); end++)
        {
            // 是回文，进行递归；不是回文，找下一个串
            // 0是未计算过，1是回文，-1不是回文
            if (flag[begin][end] == 1 || (flag[begin][end] == 0 && isPalindrome(s, begin, end)))
            {
                flag[begin][end] = 1;
                list.add(s.substring(begin, end + 1));
                // 递归出口
                if (end == s.length() - 1)
                    listList.add(new ArrayList<>(list));
                else
                    dfs(s, end + 1, list, flag);
                //一次递归退出后，需要移除列表中最后一个子串
                list.remove(list.size() - 1);
            }
            else
                flag[begin][end] = -1;
        }
    }
}
