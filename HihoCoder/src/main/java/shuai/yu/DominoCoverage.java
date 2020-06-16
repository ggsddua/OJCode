package shuai.yu;

import java.util.Scanner;

/**
 * 骨牌覆盖问题
 *
 * @author shuai.yu
 * @version 2020/06/16
 */
public class DominoCoverage
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int result;
        result = solution1(n);
        System.out.println(result);
    }

    /**
     * 骨牌，一种古老的玩具。今天我们要研究的是骨牌的覆盖问题：
     * 我们有一个2xN的长条形棋盘，然后用1x2的骨牌去覆盖整个棋盘。对于这个棋盘，一共有多少种不同的覆盖方法呢？
     *
     * 链接 http://hihocoder.com/problemset/problem/1143
     */
    private static int solution1(int n)
    {
        // TODO: 2020/6/17 可以使用矩阵进行优化 
        if (n == 1 || n == 0)
            return 1;
        return solution1(n - 1) + solution1(n - 2);
    }

    /**
     * 对于3xN的棋盘，使用1x2的骨牌去覆盖一共有多少种不同的覆盖方法呢
     */
    private static void solution2(int n)
    {

    }

    /**
     * 再加强一次题目，对于给定的K和N，我们需要去求KxN棋盘的覆盖方案数
     */
    private static void solution3(int n)
    {

    }
}
