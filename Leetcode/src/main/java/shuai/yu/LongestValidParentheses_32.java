package shuai.yu;

import java.util.Stack;

/**
 * 最长有效括号
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度
 *
 * @author shuai.yu
 * @version 2020/10/25
 */
public class LongestValidParentheses_32 {
    public static void main(String[] args) {
        longestValidParentheses("(()");
    }

    /**
     * 多次遍历，括号消除法
     */
    public static int longestValidParentheses(String s) {
        // 特例判断
        if (s == null || s.length() <= 1)
            return 0;
        char[] chars = s.toCharArray();
        // 消除括号，一对对消除，直至不能消除
        boolean flag;
        do {
            flag = false;
            int right = 0;
            int left = -1;
            for (; right < s.length(); right++) {
                if (chars[right] == '(') {
                    left = right;
                } else if (chars[right] == ')') {
                    if (left != -1) {
                        flag = true;
                        chars[left] = '0';
                        chars[right] = '0';
                        left = -1;
                    }
                }
            }
        } while (flag);
        // 找到最长的“000”串
        int max = 0;
        int maxTmp = 0;
        for (char c : chars) {
            if (c == '0') {
                maxTmp++;
            } else {
                if (maxTmp > max)
                    max = maxTmp;
                maxTmp = 0;
            }
        }
        if (maxTmp > max)
            max = maxTmp;
        return max;
    }

    /**
     * 栈
     * <p>
     * 比较反直觉
     * 1遇到右括号竟然不是先判断栈内有没有元素，而是先弹栈再判断（原因是事先入栈了一个分隔元素）
     * 2竟然是边入栈边更新长度的，而不是碰到下一个分隔符后，计算两个分隔符之间的差距
     * 3每次更新的长度是最近一对括号的长度，而不是一组连续有效括号的长度
     */
    public static int longestValidParentheses1(String s) {
        // 特例判断
        if (s == null || s.length() <= 1)
            return 0;
        char[] chars = s.toCharArray();
        Stack<Integer> stack = new Stack<>();
        // 初始时入栈-1，为了统一
        stack.push(-1);
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (chars[i] == '(') {
                // 左括号入栈
                stack.push(i);
            } else {
                // 右括号，出栈站内的左括号
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    max = Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }

    /**
     * 动态规划
     */
    public static int longestValidParentheses2(String s) {
        // 特例判断
        if (s == null || s.length() <= 1)
            return 0;
        int max = 0;
        // dp表示以下标i字符结尾的最长有效括号的长度
        int[] dp = new int[s.length()];
        char[] chars = s.toCharArray();
        for (int i = 1; i < s.length(); i++) {
            // 为")"时，有效长度才有可能增加
            if (chars[i] == ')') {
                // 去除前面的有效长度，判断是否有和")"对应的"("
                if (i - dp[i - 1] - 1 >= 0 && chars[i - dp[i - 1] - 1] == '(') {
                    dp[i] = dp[i - 1] + 2 + ((i - dp[i - 1]) > 2 ? dp[i - dp[i - 1] - 2] : 0);
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }
}
