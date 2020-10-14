package shuai.yu;

/**
 * 寻找两个正序数组的中位数
 *
 * <p>给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))
 *
 * <p>解法一：简单粗暴，先将两个数组合并，两个有序数组的合并也是归并排序中的一部分。然后根据奇数，还是偶数，返回中位
 *
 * <p>解法二：不需要将两个数组真的合并，只需要找到中位数在哪里。奇数偶数以及边界判断会比较复杂。但将代码优化后，逻辑也挺清晰的</p>
 *
 * <p>解法三：解法二中，我们一次遍历就相当于去掉不可能是中位数的一个值，也就是一个一个排除。
 * 由于数列是有序的，其实我们完全可以一半儿一半儿的排除。假设我们要找第 k 小数，我们可以每次循环排除掉 k/2 个数</p>
 *
 * @author shuai.yu
 * @version 2020/09/25
 */
public class MedianSortedArrays_4
{
    public static void main(String[] args)
    {
        int[] nums1 = new int[]{3};
        int[] nums2 = new int[]{1, 2, 4};
        findMedianSortedArrays3(nums1, nums2);
    }

    /**
     * 复杂度 O(m + n)
     * 解法二
     */
    public static double findMedianSortedArrays1(int[] nums1, int[] nums2)
    {
        if (nums1 == null || nums1.length == 0)
        {
            if (nums2.length == 0)
                return 0;
            if (nums2.length % 2 == 1)
                return nums2[nums2.length / 2];
            return (nums2[nums2.length / 2 - 1] + nums2[nums2.length / 2]) / 2.0;
        }
        else if (nums2 == null || nums2.length == 0)
        {
            if (nums1.length % 2 == 1)
                return nums1[nums1.length / 2];
            return (nums1[nums1.length / 2 - 1] + nums1[nums1.length / 2]) / 2.0;
        }
        else
        {
            int i = 0, j = 0;
            int length = nums1.length + nums2.length;
            int mid1 = 0, mid2 = 0;
            boolean hasMid1 = false, hasMid2 = false;
            boolean isOdd = length % 2 == 1;
            int mid = isOdd ? length / 2 : length / 2 - 1;
            for (; i < nums1.length && j < nums2.length; )
            {
                if (nums1[i] < nums2[j])
                {
                    if (i + j >= mid)
                    {
                        if (isOdd)
                        {
                            return nums1[i];
                        }
                        else
                        {
                            if (hasMid1)
                            {
                                mid2 = nums1[i];
                                hasMid2 = true;
                                break;
                            }
                            mid1 = nums1[i];
                            hasMid1 = true;
                        }
                    }
                    i++;
                }
                else
                {
                    if (i + j >= mid)
                    {
                        if (isOdd)
                            return nums2[j];
                        else
                        {
                            if (hasMid1)
                            {
                                mid2 = nums2[j];
                                hasMid2 = true;
                                break;
                            }
                            mid1 = nums2[j];
                            hasMid1 = true;
                        }
                    }
                    j++;
                }
            }
            if (hasMid1 && hasMid2)
                return (mid1 + mid2) / 2.0;
            if (i < nums1.length)
            {
                if (hasMid1)
                {
                    return (mid1 + nums1[i]) / 2.0;
                }
                if (isOdd)
                {
                    return nums1[mid - nums2.length];
                }
                else
                {
                    return (nums1[mid - nums2.length] + nums1[mid - nums2.length + 1]) / 2.0;
                }
            }
            else
            {
                if (hasMid1)
                {
                    return (mid1 + nums2[j]) / 2.0;
                }
                if (isOdd)
                {
                    return nums2[mid - nums1.length];
                }
                else
                {
                    return (nums2[mid - nums1.length] + nums2[mid - nums1.length + 1]) / 2.0;
                }
            }
        }
    }

    /**
     * 复杂度 O(m + n)
     * 解法二 优化 便利方式优化
     */
    public static double findMedianSortedArrays2(int[] nums1, int[] nums2)
    {
        int m = nums1.length;
        int n = nums2.length;
        int len = m + n;
        int left = -1, right = -1;
        int aStart = 0, bStart = 0;
        for (int i = 0; i <= len / 2; i++)
        {
            left = right;
            // 可以遍历两个数组，对findMedianSortedArrays1中的遍历方式进行优化
            if (aStart < m && (bStart >= n || nums1[aStart] < nums2[bStart]))
            {
                right = nums1[aStart++];
            }
            else
            {
                right = nums2[bStart++];
            }
        }
        if ((len & 1) == 0)
            return (left + right) / 2.0;
        else
            return right;
    }

    /**
     * 复杂度 O(log(m + n))
     * 太繁琐了，各种边界处理
     */
    public static double findMedianSortedArrays3(int[] nums1, int[] nums2)
    {
        int m = nums1.length;
        int n = nums2.length;
        int len = m + n;
        int left = -1, right = -1;
        int aStart = 0, bStart = 0;
        int k = len / 2 + 1;
        boolean isEven = len % 2 == 0;
        for (; k >= 0; )
        {
            // 只剩一个数组，可以直接取，不用继续循环
            if (aStart >= m)
            {
                left = nums2[bStart + k - 1];
                if (isEven)
                {
                    if (bStart + k - 2 >= 0 && m > 0)
                        right = Math.max(nums1[m - 1], nums2[bStart + k - 2]);
                    else if (m > 0)
                        right = nums1[m - 1];
                    else
                        right = nums2[bStart + k - 2];
                }
                break;
            }
            else if (bStart >= n)
            {
                left = nums1[aStart + k - 1];
                if (isEven)
                {
                    if (aStart + k - 2 >= 0 && n > 0)
                        right = Math.max(nums1[aStart + k - 2], nums2[n - 1]);
                    else if (n > 0)
                        right = nums2[n - 1];
                    else
                        right = nums1[aStart + k - 2];
                }
                break;
            }

            // 下一个就是，跳出循环
            if (k == 1)
            {
                if (nums1[aStart] < nums2[bStart])
                {
                    left = nums1[aStart];
                    if (isEven)
                    {
                        if (aStart - 1 >= 0 && bStart - 1 >= 0)
                            right = Math.max(nums1[aStart - 1], nums2[bStart - 1]);
                        else if (aStart - 1 >= 0)
                            right = nums1[aStart - 1];
                        else
                            right = nums2[bStart - 1];
                    }
                }
                else
                {
                    left = nums2[bStart];
                    if (isEven)
                        if (aStart - 1 >= 0 && bStart - 1 >= 0)
                            right = Math.max(nums1[aStart - 1], nums2[bStart - 1]);
                        else if (aStart - 1 >= 0)
                            right = nums1[aStart - 1];
                        else
                            right = nums2[bStart - 1];
                }
                break;
            }

            // 正常循环流程
            int i = Math.min(aStart + k / 2 - 1, m - 1);
            int j = Math.min(bStart + k / 2 - 1, n - 1);

            if (nums1[i] > nums2[j])
            {
                k = k - (j + 1 - bStart);
                bStart = j + 1;
            }
            else
            {
                k = k - (i + 1 - aStart);
                aStart = i + 1;
            }
        }
        if (isEven)
            return (left + right) / 2.0;
        return left;
    }
}
