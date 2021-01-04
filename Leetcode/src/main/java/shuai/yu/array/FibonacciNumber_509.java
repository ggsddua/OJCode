package shuai.yu.array;

/**
 * 斐波那契数
 *
 * @author shuai.yu
 * @version 2021/01/04
 */
public class FibonacciNumber_509
{
    public int fib(int n)
    {
        if (n <= 1)
            return n;
        // 使用数组存储，避免递归消耗
        int[] ans = new int[n + 1];
        ans[0] = 0;
        ans[1] = 1;
        for (int i = 2; i <= n; i++)
        {
            ans[i] = ans[i - 1] + ans[i - 2];
        }
        return ans[n];
    }

    public int fib1(int n)
    {
        if (n <= 1)
            return n;
        // 使用三个数字存储状态，避免数组消耗
        int a = 0;
        int b = 1;
        int c = 0;
        for (int i = 2; i <= n; i++)
        {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }
}
