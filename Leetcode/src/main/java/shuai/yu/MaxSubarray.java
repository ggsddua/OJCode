package shuai.yu;

/**
 * 最大子数组
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * @author shuai.yu
 * @version 2020/06/26
 */
public class MaxSubarray
{
    public static void main(String[] args)
    {
    }

    /**
     * 暴力破解
     */
    public static int maxSubarray1(int[] nums)
    {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++)
        {
            int maxTmp = 0;
            for (int j = i; j < nums.length; j++)
            {
                // j >= i
                // 计算子数组[i,j]的和,并取最大值
                maxTmp += nums[j];
                if (maxTmp > max)
                    max = maxTmp;
            }
        }
        return max;
    }

    /**
     * 分治法
     */
    public static int maxSubarray2(int[] nums, int left, int right)
    {
        if (left == right)
            return nums[left];

        int mid = (left + right) / 2;
        int lowSum = maxSubarray2(nums, left, mid);
        int highSum = maxSubarray2(nums, mid + 1, right);
        int crossSum = findMaxCrossingSubarray(nums, mid, left, right);
        if (lowSum >= highSum && lowSum >= crossSum)
        {
            return lowSum;
        }
        else if (highSum >= lowSum && highSum >= crossSum)
        {
            return highSum;
        }
        return crossSum;
    }

    /**
     * 找到跨越某点的最大子数组
     */
    private static int findMaxCrossingSubarray(int[] nums, int mid, int left, int right)
    {
        int maxLow = Integer.MIN_VALUE;
        int maxHigh = Integer.MIN_VALUE;
        int maxTmp = 0;
        for (int i = mid; i >= left; i--)
        {
            maxTmp += nums[i];
            if (maxTmp > maxLow)
                maxLow = maxTmp;
        }

        maxTmp = 0;
        for (int i = mid + 1; i <= right; i++)
        {
            maxTmp += nums[i];
            if (maxTmp > maxHigh)
                maxHigh = maxTmp;
        }
        return (maxLow + maxHigh);
    }
}
