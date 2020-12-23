package shuai.yu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 全排列
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列
 * 递归
 *
 * @author shuai.yu
 * @version 2020/10/29
 */
public class Permutations_46
{
    List<List<Integer>> lists = new LinkedList<>();

    public List<List<Integer>> permute(int[] nums)
    {
        if (nums == null || nums.length == 0)
        {
            return lists;
        }
        recursive(nums, new LinkedList<>(), nums.length);
        return lists;
    }

    public void recursive(int[] nums, List<Integer> list, int length)
    {
        if (list.size() == nums.length)
        {
            lists.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++)
        {
            if (nums[i] == Integer.MIN_VALUE)
            {
                continue;
            }
            int temp = nums[i];
            nums[i] = Integer.MIN_VALUE;
            list.add(temp);
            recursive(nums, list, length);
            list.remove(Integer.valueOf(temp));
            nums[i] = temp;
        }
    }

}
