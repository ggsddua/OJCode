package shuai.yu;

/**
 * 盛最多水的容器
 * <p>
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水
 *
 * @author shuai.yu
 * @version 2020/10/16
 */
public class MostWater_11
{
    public static void main(String[] args)
    {

    }

    /**
     * 暴力破解
     *
     * @param height
     * @return
     */
    public static int maxArea1(int[] height)
    {
        int max = 0;
        for (int i = 0; i < height.length - 1; i++)
            for (int j = i + 1; j < height.length; j++)
            {
                max = Math.max(max, (j - i) * Math.min(height[i], height[j]));
            }
        return max;
    }

    /**
     * 双指针法 两边向中间移动，每次排除一个柱子
     *
     * @param height
     * @return
     */
    public static int maxArea2(int[] height)
    {
        int max = 0;
        int i = 0, j = height.length - 1;
        while (i < j)
        {
            max = Math.max(max, (j - i) * Math.min(height[i], height[j]));
            if (height[i] < height[j])
            {
                i++;
            }
            else
            {
                j--;
            }
        }
        return max;
    }
}
