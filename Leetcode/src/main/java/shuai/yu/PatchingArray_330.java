package shuai.yu;

import java.util.LinkedList;
import java.util.List;

/**
 * 按要求补齐数组
 * 给定一个已排序的正整数数组 nums，和一个正整数 n 。
 * 从 [1, n] 区间内选取任意个数字补充到 nums 中，使得 [1, n] 区间内的任何数字都可以用 nums 中某几个数字的和来表示。
 * 请输出满足上述要求的最少需要补充的数字个数。
 *
 * @author shuai.yu
 * @version 2020/12/29
 */
public class PatchingArray_330
{
    public static void main(String[] args)
    {
        /**
         * 输入: nums = [1,3], n = 6
         * 输出: 1
         * 加入2
         * 输入: nums = [1,5,10], n = 20
         * 输出: 2
         * 加入2、4
         * 输入: nums = [1,2,2], n = 5
         * 输出: 0
         * [1,2,31,33]
         * 2147483647
         */
        int[] nums = new int[]{1, 3};
        int n = 6;
        System.out.println(minPatches(nums, n));
        nums = new int[]{1, 5, 10};
        n = 20;
        System.out.println(minPatches(nums, n));
        nums = new int[]{1, 2, 2};
        n = 5;
        System.out.println(minPatches(nums, n));
        nums = new int[]{1, 2, 31, 33};
        n = 2147483647;
        System.out.println(minPatches(nums, n));
    }

    public static int minPatches(int[] nums, int n)
    {
        int ans = 0;
        int index = -1;
        int sumTemp = 0;

        // 状态初始化
        for (int j = 0; j < nums.length; j++)
        {
            if (nums[j] > 1)
                break;
            sumTemp++;
            index = j;
        }
        // 从1开始，遍历，但不是一一遍历，中途根据实际情况，跳过一些数字
        for (int i = 1; i <= n && i > 0; )
        {
            // 求小于等于i的所有数的和，如果和大于i，表示可以以和表示，否则，需要将i加入到数组中
            // 求和时，需要缓存之前的和，不能每次都重新求，某些情况下会导致计算时间过长
            if (sumTemp < i)
            {
                // 和小于i（即表示和等于i-1），将i加入到数组中。和变为2*i-1，即2*i-1以下的数都是可以表示的，直接将i设置为2*i
                sumTemp += i;
                ans++;
                i = i * 2;
            }
            else
            {
                // 和大于i，可以以和表示，i增加
                i = i + sumTemp - i + 1;
            }

            // 找到小于等于i的数的最大索引下标
            for (int j = index + 1; j < nums.length; j++)
            {
                if (nums[j] > i)
                    break;
                sumTemp += nums[j];
                index = j;
            }
        }
        return ans;
    }
}
