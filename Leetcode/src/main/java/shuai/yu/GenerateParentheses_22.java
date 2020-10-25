package shuai.yu;

import java.util.ArrayList;
import java.util.List;

/**
 * 括号生成
 *
 * @author shuai.yu
 * @version 2020/10/24
 */
public class GenerateParentheses_22
{
    public static void main(String[] args)
    {

    }

    private static List<String> list = new ArrayList<>();

    public List<String> generateParenthesis(int n)
    {
        recur(new StringBuilder(), 0, 0, n);
        return list;
    }

    /**
     * 使用StringBuilder递归
     */
    private static void recur(StringBuilder sb, int left, int right, int n)
    {
        if (left == n)
        {
            for (; right < n; right++)
                sb.append(')');
            list.add(sb.toString());
            return;
        }

        StringBuilder temp;
        if (left == right)
        {
            sb.append('(');
            temp = new StringBuilder(sb.toString());
            recur(temp, left + 1, right, n);
        }
        else
        {
            sb.append('(');
            temp = new StringBuilder(sb.toString());
            recur(temp, left + 1, right, n);

            sb.deleteCharAt(sb.length() - 1).append(")");
            temp = new StringBuilder(sb.toString());
            recur(temp, left, right + 1, n);
        }
    }

    /**
     * 使用String递归
     */
    private static void recur1(String s, int left, int right, int n)
    {
        if (left == n)
        {
            for (; right < n; right++)
                s += ")";
            list.add(s);
            return;
        }

        String temp;
        if (left == right)
        {
            recur1(s + "(", left + 1, right, n);
        }
        else
        {
            recur1(s + "(", left + 1, right, n);
            recur1(s.substring(0, s.length() - 1) + ")", left, right + 1, n);
        }
    }
}
