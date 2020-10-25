package shuai.yu;

import java.util.Arrays;

/**
 * 下一个排列
 * 找到比当前排列大的下一个排列
 *
 * @author shuai.yu
 * @version 2020/10/25
 */
public class NextPermutation_31
{
    public static void main(String[] args)
    {

    }

    public void nextPermutation(int[] nums)
    {
        if (nums == null || nums.length <= 1)
            return;
        // 从右到左，找到第一个不是递减的值下边
        int index = nums.length - 2;
        int right = nums[nums.length - 1];
        for (; index >= 0; index--)
        {
            if (nums[index] < right)
            {
                right = nums[index];
                break;
            }
            right = nums[index];
        }
        // 如果下标为-1，表示数组整体递减，是最大的，整体进行递增排序
        if(index == -1)
        {
            Arrays.sort(nums);
            return;
        }
        // 在右边，找到比right大的数中的最小的数，剩余的递增排序
        int bigger = Integer.MAX_VALUE;
        int biggerIndex = 0;
        for (int i = nums.length - 1; i > index; i--)
        {
            if (nums[i] > right && nums[i]<bigger)
            {
                bigger = nums[i];
                biggerIndex = i;
            }
        }
        nums[biggerIndex] = right;
        nums[index] = bigger;
        Arrays.sort(nums,index+1,nums.length);
    }
}
