package shuai.yu.greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 最后一块石头的重量
 * <p>
 * 有一堆石头，每块石头的重量都是正整数。
 * 每一回合，从中选出两块 最重的 石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 * a：如果 x == y，那么两块石头都会被完全粉碎；
 * b：如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0
 *
 * @author shuai.yu
 * @version 2020/12/30
 */
public class LastStoneWeight_1046
{
    public static void main(String[] args)
    {
        /**
         * 输入：[2,7,4,1,8,1]
         * 输出：1
         */
        int[] stones = new int[]{2,7,4,1,8,1};
        System.out.println(lastStoneWeight(stones));
    }

    public static int lastStoneWeight(int[] stones)
    {
        if (stones == null || stones.length == 0)
            return 0;
        // 排序
        Arrays.sort(stones);
        // 从后到前，每次拿出两个最大的数
        for (int i = stones.length - 1; i > 0; )
        {
            // 如果两个数相同，下表左移2
            stones[i - 1] = stones[i] - stones[i - 1];
            if (stones[i - 1] == 0)
            {
                i -= 2;
            }
            else
            {
                // 两个数不同，将差插入到数组中，并保证数组依旧有序
                int temp = stones[i - 1];
                for (int j = i - 2; j >= 0; j--)
                {
                    if (stones[j] > temp)
                    {
                        stones[j + 1] = stones[j];
                        if (j == 0)
                        {
                            stones[0] = temp;
                            break;
                        }
                    }
                    else
                    {
                        stones[j + 1] = temp;
                        break;
                    }
                }
                i--;
            }
        }
        return stones[0];
    }

    /**
     * 最大堆实现
     */
    public static int lastStoneWeight1(int[] stones)
    {
        // 大顶堆，最大值在上边
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b) -> b - a);
        for (int stone : stones) {
            pq.offer(stone);
        }

        while (pq.size() > 1) {
            // 取出最大的两个数
            int a = pq.poll();
            int b = pq.poll();
            // 如果两个数相等，继续循环；如果不等，先将差值放入优先队列，然后再循环
            if (a > b) {
                pq.offer(a - b);
            }
        }
        return pq.isEmpty() ? 0 : pq.poll();
    }
}
