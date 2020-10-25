package shuai.yu;

import java.util.Stack;

/**
 * 有效的括号
 *
 * @author shuai.yu
 * @version 2020/10/18
 */
public class ValidParentheses_20
{
    public static void main(String[] args)
    {

    }

    /**
     * 使用栈进行处理
     */
    public static boolean isValid(String s)
    {
        if (s == null || s.isEmpty())
            return true;
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray())
        {
            switch (c)
            {
                case '(':
                case '[':
                case '{':
                    stack.push(c);
                    break;
                case ')':
                    if (stack.isEmpty() || stack.pop() != '(')
                        return false;
                    break;
                case ']':
                    if (stack.isEmpty() || stack.pop() != '[')
                        return false;
                    break;
                case '}':
                    if (stack.isEmpty() || stack.pop() != '{')
                        return false;
                    break;
            }
        }
        if (stack.isEmpty())
            return true;
        return false;
    }
}
