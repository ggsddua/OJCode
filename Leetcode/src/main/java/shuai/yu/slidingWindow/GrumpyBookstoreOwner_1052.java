package shuai.yu.slidingWindow;

/**
 * 爱生气的书店老板
 * 今天，书店老板有一家店打算试营业 customers.length 分钟。每分钟都有一些顾客（customers[i]）会进入书店，所有这些顾客都会在那一分钟结束后离开。
 * 在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。
 * 当书店老板生气时，那一分钟的顾客就会不满意，不生气则他们是满意的。
 * 书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 X 分钟不生气，但却只能使用一次。
 * 请你返回这一天营业下来，最多有多少客户能够感到满意的数量。
 * <p>
 * 数组，滑动窗口
 *
 * @author shuai.yu
 * @version 2021/02/27
 */
public class GrumpyBookstoreOwner_1052
{
    public int maxSatisfied(int[] customers, int[] grumpy, int X)
    {
        // 滑动窗口，找到生气造成最大影响的范围
        int maxBeginIndex = 0;
        int maxNotSatisfiedSum = 0;
        int len = customers.length;
        for (int i = 0; i < len - X + 1; i++)
        {
            int notSatisfiedSum = 0;
            for (int j = i; j < i + X; j++)
            {
                if (grumpy[j] == 1)
                    notSatisfiedSum += customers[j];
            }
            if (notSatisfiedSum > maxNotSatisfiedSum)
            {
                maxNotSatisfiedSum = notSatisfiedSum;
                maxBeginIndex = i;
            }
        }
        // 在该范围内不生气
        for (int i = maxBeginIndex; i < maxBeginIndex + X; i++)
        {
            grumpy[i] = 0;
        }
        // 算结果
        int ans = 0;
        for (int i = 0; i < len; i++)
        {
            if (grumpy[i] == 0)
            {
                ans += customers[i];
            }
        }
        return ans;
    }
}
