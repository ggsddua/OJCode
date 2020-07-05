package shuai.yu.dynamicProgram;

/**
 * 钢条切割
 *
 * @author shuai.yu
 * @version 2020/07/04
 */
public class CutRod
{
    public static void main(String[] args)
    {
        int[] p = new int[]{0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        //        for (int i = 1; i <= 10; i++)
        //        {
        //            System.out.println(i + ":" + recursiveCutRod(p, i));
        //        }

        //        int[] r = new int[11];
        //        r[0] = 0;
        //        for (int i = 1; i < 11; i++)
        //        {
        //            r[i] = Integer.MIN_VALUE;
        //        }
        //        for (int i = 1; i <= 10; i++)
        //        {
        //            System.out.println(i + ":" + memoizedCutRod(p, i, r));
        //        }

        for (int i = 1; i <= 10; i++)
        {
            System.out.println(i + ":" + BottomUpCutRod(p, i));
        }
    }

    /**
     * 递归算法
     * rn = max(pi + r(n-i))
     *
     * @param p 价格表,下标从1开始
     * @param n 钢条长度
     */
    private static int recursiveCutRod(int[] p, int n)
    {
        if (n == 0)
            return 0;
        int q = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++)
        {
            q = Math.max(q, p[i] + recursiveCutRod(p, n - i));
        }
        return q;
    }

    /**
     * 带备忘的自顶向下法
     *
     * @param p 价格表,下标从1开始
     * @param n 钢条长度
     * @param r 辅助数组，用来存储已经计算过的子问题收益
     */
    private static int memoizedCutRod(int[] p, int n, int[] r)
    {
        if (r[n] > Integer.MIN_VALUE)
            return r[n];
        if (n == 0)
            return 0;
        int q = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++)
        {
            q = Math.max(q, p[i] + memoizedCutRod(p, n - i, r));
        }
        r[n] = q;
        return q;
    }

    /**
     * 自底向上法
     *
     * @param p 价格表,下标从1开始
     * @param n 钢条长度
     */
    private static int BottomUpCutRod(int[] p, int n)
    {
        int[] r = new int[n + 1];
        r[0] = 0;
        // 用来记录解本身，从而输出最优解，而不仅仅是一个值
        int[] s = new int[n + 1];
        for (int i = 1; i <= n; i++)
        {
            int q = Integer.MIN_VALUE;
            for (int j = 1; j <= i; j++)
            {
                if (q < p[j] + r[i - j])
                {
                    q = p[j] + r[i - j];
                    s[i] = j;
                }
            }
            r[i] = q;
        }
        return r[n];
    }
}
