package shuai.yu.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 数组的度
 * 给定一个非空且只包含非负数的整数数组 nums, 数组的度的定义是指数组里任一元素出现频数的最大值。
 * 你的任务是找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度
 * <p>
 * 示例：
 * 输入: [1, 2, 2, 3, 1]
 * 输出: 2
 * 输入: [1,2,2,3,1,4,2]
 * 输出: 6
 *
 * @author shuai.yu
 * @version 2021/02/20
 */
public class DegreeOfAnArray_697
{
    public static void main(String[] args)
    {
        int[] nums = new int[]{1, 2, 2, 3, 1};
        System.out.println(findShortestSubArray(nums));
        nums = new int[]{1, 2, 2, 3, 1, 4, 2};
        System.out.println(findShortestSubArray(nums));
    }


    public static int findShortestSubArray(int[] nums)
    {
        if (nums == null || nums.length == 0)
            return 0;
        // 二维数组，存储（beginIndex，endIndex，出现总次数）
        int[][] border = new int[nums.length][3];
        // map，保存对应数字在数组中的下标
        Map<Integer, Integer> map = new HashMap<>();

        int indexTemp = 0;
        for (int i = 0; i < nums.length; i++)
        {
            if (map.containsKey(nums[i]))
            {
                border[map.get(nums[i])][1] = i;
                border[map.get(nums[i])][2] += 1;
            }
            else
            {
                map.put(nums[i], indexTemp);
                border[indexTemp][0] = i;
                border[indexTemp][1] = i;
                border[indexTemp][2] = 1;
                indexTemp++;
            }
        }
        int degree = 0;
        int minDegreeLength = Integer.MAX_VALUE;
        for (int i = 0; i < indexTemp; i++)
        {
            // 找到度
            if (degree < border[i][2])
            {
                degree = border[i][2];
                minDegreeLength = border[i][1] - border[i][0];
            }
            else if (degree == border[i][2])
            {
                // 度相同，找最小长度
                if (minDegreeLength > border[i][1] - border[i][0])
                {
                    minDegreeLength = border[i][1] - border[i][0];
                }
            }
        }
        return minDegreeLength + 1;
    }
}
