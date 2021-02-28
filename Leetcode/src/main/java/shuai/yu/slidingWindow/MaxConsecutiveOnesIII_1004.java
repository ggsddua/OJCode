package shuai.yu.slidingWindow;

/**
 * 最大连续1的个数 III
 * 给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。
 * 返回仅包含 1 的最长（连续）子数组的长度。
 * <p>
 * 输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
 * 输出：6
 * 解释：
 * [1,1,1,0,0,1,1,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 6。
 *
 * @author shuai.yu
 * @version 2021/02/19
 */
public class MaxConsecutiveOnesIII_1004
{
    public int longestOnes(int[] A, int K)
    {
        int ans;
        // [left,right) 保存left和right，先遍历一次，然后让left右移，每次遇到移过0，则right也可往右移过一个0
        int left = 0, right = 0;
        for (; right < A.length; right++)
        {
            if (A[right] == 0)
            {
                if (K > 0)
                    K--;
                else
                    break;
            }
        }
        if (right >= A.length)
        {
            return A.length;
        }
        ans = right - left;

        for (left++; true; left++)
        {
            if (A[left - 1] == 1)
                continue;
            boolean flag = true;
            for (; right < A.length; right++)
            {
                if (A[right] == 0)
                {
                    if (flag)
                        flag = false;
                    else
                        break;
                }
            }
            if (right >= A.length)
            {
                ans = Math.max(ans, right - left);
                break;
            }
            ans = Math.max(ans, right - left);
        }
        return ans;
    }
}
