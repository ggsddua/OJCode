package shuai.yu;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 两个数组的交集
 * 给定两个数组，编写一个函数来计算它们的交集
 *
 * @author shuai.yu
 * @version 2020/11/02
 */
public class IntersectionTwoArrays_349
{
    public static void main(String[] args)
    {

    }

    /**
     * 双指针
     */
    public int[] intersection(int[] nums1, int[] nums2)
    {
        Map<Integer, Integer> map = new HashMap<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int index1 = 0;
        int index2 = 0;
        for (; index1 < nums1.length && index2 < nums2.length; )
        {
            if (nums1[index1] == nums2[index2])
            {
                map.put(nums1[index1], 0);
                index1++;
                index2++;
            }
            else if (nums1[index1] > nums2[index2])
            {
                index2++;
            }
            else if (nums1[index1] < nums2[index2])
            {
                index1++;
            }
        }

        int[] ans = new int[map.size()];
        index1 = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet())
        {
            ans[index1] = entry.getKey();
            index1++;
            if (index1 >= ans.length)
                return ans;
        }
        return ans;
    }

    /**
     * 哈希表
     */
    public int[] intersection1(int[] nums1, int[] nums2)
    {
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums1)
        {
            map1.put(i, 0);
        }
        for (int i : nums2)
        {
            map2.put(i, 0);
        }

        if (nums1.length > nums2.length)
        {
            map2.forEach((integer, integer2) -> {
                if(map1.containsKey(integer)){
                    map.put(integer,0);
                }
            });
        }
        else
        {
            map1.forEach((integer, integer2) -> {
                if(map2.containsKey(integer)){
                    map.put(integer, 0);
                }
            });
        }

        int[] ans = new int[map.size()];
        int index1 = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet())
        {
            ans[index1] = entry.getKey();
            index1++;
            if (index1 >= ans.length)
                return ans;
        }
        return ans;
    }
}
