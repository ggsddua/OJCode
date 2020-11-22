package shuai.yu;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 移掉K位数字
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小
 *
 * @author shuai.yu
 * @version 2020/11/15
 */
public class RemoveKDigits_402 {
    public static void main(String[] args) {
        String num = "1432219";
        int k = 3;
        removeKdigits1(num, k);
    }

    /**
     * 列表求解，时间较长
     */
    public static String removeKdigits(String num, int k) {
        if (num == null || num.length() == 0 || num.length() == k)
            return "0";
        List<Integer> list = new LinkedList<>();
        for (char c : num.toCharArray()) {
            list.add((int) (c - '0'));
        }
        for (; k > 0; k--) {
            Integer pre = -1;
            for (Integer i : list) {
                if (i < pre) {
                    break;
                }
                pre = i;
            }
            list.remove(pre);
        }
        StringBuilder sb = new StringBuilder();
        boolean flag = true;
        for (Integer i : list) {
            if (flag && i == 0) {
                continue;
            }
            flag = false;
            sb.append(i);
        }
        return sb.toString();
    }

    public static String removeKdigits1(String num, int k) {
        if (num == null || num.length() == 0 || num.length() == k)
            return "0";
        Stack<Integer> stack = new Stack<>();
        for (char c : new StringBuilder(num).reverse().toString().toCharArray()) {
            stack.push((int) (c - '0'));
        }
        Stack<Integer> temp = new Stack<>();
        for (; k > 0; k--) {
            Integer pre = -1;
            if (!temp.isEmpty()) {
                pre = temp.peek();
            }
            while (true) {
                if (stack.isEmpty()) {
                    break;
                }
                Integer next = stack.peek();
                if (next < pre) {
                    break;
                }
                stack.pop();
                temp.push(next);
                pre = next;
            }
            temp.pop();
        }
        // 注意：栈遍历时是从栈底到栈顶！！！！
        // 和pop出栈顺序正好相反
        StringBuilder sbL = new StringBuilder();
        boolean flag = true;
        for (Integer integer : temp) {
            if(flag && integer == 0)
                continue;
            flag = false;
            sbL.append(integer);
        }
        StringBuilder sbR = new StringBuilder();
        while (!stack.isEmpty()){
            Integer integer = stack.pop();
            if(flag && integer == 0)
                continue;
            flag = false;
            sbR.append(integer);
        }
        String ans = sbL.append(sbR).toString();
        return ans.length() == 0 ? "0" : ans;
    }
}
