package shuai.yu.heap;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * 滑动窗口最大值
 *
 * @author shuai.yu
 * @version 2021/01/02
 */
public class SlidingWindowMaximum_239
{
    public static void main(String[] args)
    {
        int[] nums = new int[]{1,3,1,2,0,5};
        int k = 3;
        System.out.println(maxSlidingWindow(nums, k));
    }

    // 对暴力算法进行一定的优化，存储最大的两个数，简化每次查找最大值的消耗
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int[] ans = new int[nums.length - k + 1];
        if (nums.length == 0)
            return ans;
        int maxIndex = 0;

        // k比较小时，优化效率并不高，所以每次查找
        if (k <= 3)
        {
            for (int beginIndex = 0; beginIndex + k - 1 < nums.length; beginIndex++)
            {
                maxIndex = beginIndex;
                for (int i = beginIndex + 1; i < beginIndex + k; i++)
                {
                    if (nums[i] > nums[maxIndex])
                    {
                        maxIndex = i;
                    }
                }
                ans[beginIndex] = nums[maxIndex];
            }
        }
        else
        {
            // 存储第一大数和第二大数的下标
            int secondIndex = -1;
            for (int i = 0; i < k; i++)
            {
                if (nums[i] >= nums[maxIndex])
                {
                    maxIndex = i;
                }
            }
            for (int i = 0; i < k; i++)
            {
                if (i == maxIndex)
                    continue;
                if (secondIndex == -1)
                {
                    secondIndex = i;
                    continue;
                }
                if (nums[i] >= nums[secondIndex])
                {
                    secondIndex = i;
                }
            }
            ans[0] = nums[maxIndex];
            for (int beginIndex = 1; beginIndex + k - 1 < nums.length; beginIndex++)
            {
                boolean flag = false;
                if (maxIndex < beginIndex)
                {
                    maxIndex = secondIndex;
                    flag = true;
                }
                else if (secondIndex < beginIndex)
                {
                    flag = true;
                }
                if (nums[beginIndex + k - 1] >= nums[maxIndex])
                {
                    secondIndex = maxIndex;
                    maxIndex = beginIndex + k - 1;
                    flag = false;
                }
                else if (nums[beginIndex + k - 1] >= nums[secondIndex])
                {
                    secondIndex = beginIndex + k - 1;
                    flag = false;
                }
                if (flag)
                {
                    secondIndex = -1;
                    for (int i = beginIndex; i < beginIndex + k; i++)
                    {
                        if (i == maxIndex)
                            continue;
                        if (secondIndex == -1)
                        {
                            secondIndex = i;
                            continue;
                        }
                        if (nums[i] >= nums[secondIndex])
                        {
                            secondIndex = i;
                        }
                    }
                }
                ans[beginIndex] = nums[maxIndex];
            }
        }
        return ans;
    }

    /**
     * 使用优先队列
     */
    public static int[] maxSlidingWindow1(int[] nums, int k) {
        int n = nums.length;
        // 创建一个优先队列，按照从大到小排列
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] pair1, int[] pair2) {
                return pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1];
            }
        });
        for (int i = 0; i < k; ++i) {
            pq.offer(new int[]{nums[i], i});
        }
        int[] ans = new int[n - k + 1];
        ans[0] = pq.peek()[0];
        for (int i = k; i < n; ++i) {
            pq.offer(new int[]{nums[i], i});
            // 如果最大值的下标小于当前滑动窗口的最小下标，移除
            while (pq.peek()[1] <= i - k) {
                pq.poll();
            }
            ans[i - k + 1] = pq.peek()[0];
        }
        return ans;
    }

    /**
     * 单调队列
     * 由于我们需要求出的是滑动窗口的最大值，如果当前的滑动窗口中有两个下标 i和 j，
     * 其中 i在 j的左侧（i<j），并且i对应的元素不大于 j对应的元素。可以将 i永久地移除
     */
    public static int[] maxSlidingWindow2(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> deque = new LinkedList<Integer>();
        for (int i = 0; i < k; ++i) {
            // 如果i大于左侧的值，将左侧的移除
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            // 将i放入
            deque.offerLast(i);
        }

        int[] ans = new int[n - k + 1];
        ans[0] = nums[deque.peekFirst()];
        for (int i = k; i < n; ++i) {
            // 如果i大于左侧的值，将左侧的移除
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            // 如果最大值下标不合法，移除
            while (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
        return ans;
    }
}
