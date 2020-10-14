package shuai.yu.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * 活动选择算法
 * 贪心算法
 *
 * @author shuai.yu
 * @version 2020/09/05
 */
public class ActivitySelector
{
    public static void main(String[] args)
    {
        int n = 5;
        int[] s = new int[n + 1];
        int[] f = new int[n + 1];
        recursive(s, f, 0, n);
    }

    /**
     * 递归求解
     *
     * @param s 活动开始时间 下边为0表示一个虚拟的活动a0，开始时间和结束时间都是0
     * @param f 活动结束时间，且已按结束时间，从前到后，排序
     * @param k S<sub>k<sub/> 表示在a<sub>k<sub/>结束后开始的任务合集
     * @param n 问题规模
     * @return 最多可进行的活动数量，如有需要，可以获得活动id
     */
    public static int recursive(int[] s, int[] f, int k, int n)
    {
        int m = k + 1;
        // 找到最先结束的活动
        while (m <= n && s[m] < f[k])
            m++;
        if (m <= n)
        {
            return recursive(s, f, m, n) + 1;
        }
        else
        {
            return 0;
        }
    }

    /**
     * 迭代求解
     *
     * @param s 活动开始时间 下边为0表示一个虚拟的活动a0，开始时间和结束时间都是0
     * @param f 活动结束时间，且已按结束时间，从前到后，排序
     * @return
     */
    public static List greedy(int[] s, int[] f)
    {
        int k = 0;
        List result = new ArrayList<Integer>();
        for (int m = 1; m < s.length; )
        {
            if (s[m] >= f[k])
            {
                result.add(m);
                m = k;
            }
        }
        return result;
    }
}
