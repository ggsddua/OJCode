package shuai.yu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jnlp.ClipboardService;

/**
 * 三数之和
 *
 * @author shuai.yu
 * @version 2020/10/17
 */
public class ThreeSum_15
{
    public static void main(String[] args)
    {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        threeSum1(nums);
    }

    /**
     * 固定两个数，找第三个数
     */
    public static List<List<Integer>> threeSum1(int[] nums)
    {
        List<List<Integer>> result = new ArrayList<>();
        // 特例判断
        if (nums == null || nums.length < 3)
            return result;
        // 排序
        Arrays.sort(nums);

        // 使用hash表存储数据，数字和数字出现的次数，可快速访问
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums)
        {
            if (map.containsKey(i))
            {
                map.put(i, map.get(i) + 1);
            }
            else
            {
                map.put(i, 1);
            }
        }

        // 定两个数，找第三个数，且第一个数 <= 第二个数 <= 第三个数
        for (int i = 0; i < nums.length - 2; )
        {
            // 从map中减少一次
            map.put(nums[i], map.get(nums[i]) - 1);
            // 遍历第二个数
            for (int j = i + 1; j < nums.length - 1; )
            {
                // 从map中减少一次
                map.put(nums[j], map.get(nums[j]) - 1);
                // 找出符合要求所需的第三个数的值
                int need = -nums[i] - nums[j];
                // 第三个数不存在，或者第三个数的次数剩余为0，或者第三个数比第二个数小(表示已经出现过)，跳过
                if (!map.containsKey(need) || map.get(need) <= 0 || need < nums[j])
                {
                    // 次数恢复
                    map.put(nums[j], map.get(nums[j]) + 1);
                    // 跳过所有相同的第二个数
                    j += map.get(nums[j]);
                    continue;
                }
                result.add(Arrays.asList(nums[i], nums[j], need));
                // 次数恢复
                map.put(nums[j], map.get(nums[j]) + 1);
                // 跳过所有相同的第二个数
                j += map.get(nums[j]);
            }
            // 次数恢复
            map.put(nums[i], map.get(nums[i]) + 1);
            // 跳过所有相同的第一个数
            i += map.get(nums[i]);
        }
        return result;
    }

    /**
     * 固定一个数，找另外两个数
     */
    public static List<List<Integer>> threeSum2(int[] nums)
    {
        List<List<Integer>> result = new ArrayList<>();
        // 特例判断
        if (nums == null || nums.length < 3)
            return result;
        // 排序
        Arrays.sort(nums);
        int len = nums.length;

        for (int i = 0; i < len - 2; i++)
        {
            if (nums[i] > 0) break; // 如果当前数字大于0，则三数之和一定大于0，所以结束循环
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 去重
            int L = i + 1;
            int R = len - 1;
            while (L < R)
            {
                int sum = nums[i] + nums[L] + nums[R];
                if (sum == 0)
                {
                    result.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    while (L < R && nums[L] == nums[L + 1]) L++; // 去重
                    while (L < R && nums[R] == nums[R - 1]) R--; // 去重
                    L++;
                    R--;
                }
                else if (sum < 0) L++;
                else if (sum > 0) R--;
            }
        }
        return result;
    }
}
