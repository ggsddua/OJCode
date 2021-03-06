package shuai.yu.stackAndQueue;

import java.util.Arrays;
import java.util.Stack;

/**
 * 下一个更大元素 II
 * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。
 * 数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。
 * 如果不存在，则输出 -1。
 * 输入: [1,2,1]
 * 输出: [2,-1,2]
 *
 * @author shuai.yu
 * @version 2021/03/06
 */
public class NextGreaterElementII_503
{
    /**
     * 循环数组，保利求解
     */
    public int[] nextGreaterElements(int[] nums)
    {
        int len = nums.length;
        int[] ans = new int[len];
        for (int i = 0; i < len; i++)
        {
            boolean flag = false;
            for (int j = (i + 1) % len; j != i; j = (j + 1) % len)
            {
                if (nums[j] > nums[i])
                {
                    ans[i] = nums[j];
                    flag = true;
                    break;
                }
            }
            if (!flag)
                ans[i] = -1;
        }
        return ans;
    }

    /**
     * 使用单调栈
     * 比如说 [6,5,4,3,8]，对于前面的 [6,5,4,3] 等数字是递减的.
     * 元素 6 向后找到元素 8 才找到了比自己的大的数字，那么对于元素 [5,4,3] 来说，它们都比元素 6 更小，所以比它们更大的元素一定是元素 8
     * <p>
     * 建立「单调递减栈」，并对原数组遍历一次：
     * 1)如果栈为空，则把当前元素放入栈内；
     * 2)如果栈不为空，则需要判断当前元素和栈顶元素的大小：
     * 2.1)如果当前元素比栈顶元素大：说明当前元素是前面一些元素的「下一个更大元素」，则逐个弹出栈顶元素，直到当前元素比栈顶元素小为止。
     * 2.2)如果当前元素比栈顶元素小：说明当前元素的「下一个更大元素」与栈顶元素相同，则把当前元素入栈。
     */
    public int[] nextGreaterElements1(int[] nums)
    {
        int len = nums.length;
        int[] ans = new int[len];
        Stack<Integer> stack = new Stack<Integer>();
        Arrays.fill(ans, -1);
        for (int i = 0; i < 2 * len; i++)
        {
            while (!stack.isEmpty() && nums[i % len] > nums[stack.peek()])
            {
                ans[stack.pop()] = nums[i % len];
            }
            stack.push(i % len);
        }
        return ans;
    }
}
