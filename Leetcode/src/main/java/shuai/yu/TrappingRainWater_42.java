package shuai.yu;

import java.util.Arrays;

/**
 * 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水
 *
 * @author shuai.yu
 * @version 2020/10/30
 */
public class TrappingRainWater_42 {
    public static void main(String[] args) {
        int[] height = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        trap1(height);
    }

    public static int trap1(int[] height) {
        if (height == null || height.length <= 2)
            return 0;
        // 先找到最大索引
        int maxIndex = 0;
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] > max) {
                max = height[i];
                maxIndex = i;
            }
        }

        // 从后往前，直到最大索引处
        int sum = 0;
        int right = height.length - 1;
        int left = height.length - 2;
        int black = 0;
        for (; left >= maxIndex; left--) {
            if (height[left] < height[right]) {
                // 左边比右边小，就可以存水，且未达到存量最大值
                // 记录黑色块数量，存水时删除
                black += height[left];
            } else {
                // 左边比右边大，或者相同，此时存量达到最大，计算存量，然后重新设置右墙所在索引
                sum += (right - left - 1) * height[right] - black;
                black = 0;
                right = left;
            }
        }

        // 从前往后，直到最大索引处
        right = 1;
        left = 0;
        for (; right <= maxIndex; right++) {
            if (height[left] > height[right]) {
                black += height[right];
            } else {
                sum += (right - left - 1) * height[left] - black;
                black = 0;
                left = right;
            }
        }
        return sum;
    }
}
