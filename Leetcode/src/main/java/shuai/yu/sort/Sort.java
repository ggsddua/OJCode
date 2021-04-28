package shuai.yu.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 十大经典排序算法
 *
 * @author shuai.yu
 * @version 2021/04/27
 */
public class Sort
{
    /**
     * 冒泡排序
     */
    public int[] bubbleSort(int[] nums)
    {
        int len;
        if (nums == null || (len = nums.length) <= 1)
        {
            return nums;
        }

        for (int i = 1; i < len; i++)
        {
            for (int j = 0; j < len - i; j++)
            {
                // 每次比较满足条件都需要交换
                if (nums[j] > nums[j + 1])
                {
                    int temp = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        return nums;
    }

    /**
     * 选择排序
     * 冒泡排序的改进版本，不用每次比较都交换，便利一次，找到最小或最大的，再换位置
     */
    public int[] selectionSort(int[] nums)
    {
        int len;
        if (nums == null || (len = nums.length) <= 1)
        {
            return nums;
        }

        for (int i = 1; i < len; i++)
        {
            int minIndex = i - 1;
            for (int j = i; j < len; j++)
            {
                // 记录最小索引，最后统一交换一次
                if (nums[j] < nums[minIndex])
                {
                    minIndex = j;
                }
            }
            int temp = nums[minIndex];
            nums[minIndex] = nums[i - 1];
            nums[i - 1] = temp;
        }
        return nums;
    }

    /**
     * 插入排序
     * 先让前一个有序，再插入第二个，插入第三个……
     */
    public int[] InsertionSort(int[] nums)
    {
        int len;
        if (nums == null || (len = nums.length) <= 1)
        {
            return nums;
        }

        // 第一个有序，从第二个开始向前插入
        for (int i = 1; i < len; i++)
        {
            int curIndex = i;
            int curValue = nums[curIndex];
            // 当前的j依次和前面的比较、交换
            for (int j = i - 1; j >= 0; j--)
            {
                if (nums[j] > curValue)
                {
                    nums[curIndex] = nums[j];
                    curIndex = j;
                }
                else
                {
                    break;
                }
            }
            nums[curIndex] = curValue;
        }
        return nums;
    }

    /**
     * 快速排序
     * 通过一趟排序将待排记录分隔成独立的两部分，其中一部分记录的关键字均比另一部分的关键字小，则可分别对这两部分记录继续进行排序，以达到整个序列有序
     */
    public int[] quickSort(int[] nums)
    {
        if (nums == null || (nums.length) <= 1)
        {
            return nums;
        }
        quick(nums, 0, nums.length - 1);
        return nums;
    }

    /**
     * 快速排序，经过优化，但速度反而没有quick1快，可能是左右横跳？
     */
    private void quick(int[] nums, int begin, int end)
    {
        if (begin >= end)
        {
            return;
        }
        // 找到一个基准，将数值分为大约base和小于base的两部分，然后对两部分分别递归
        int baseValue = nums[begin];
        int baseIndex = begin;
        int lastIndex = end;
        while (baseIndex < lastIndex)
        {
            // 从右到左找到第一个小于 baseValue 的值
            while (baseIndex < lastIndex && nums[lastIndex] > baseValue)
            {
                lastIndex--;
            }

            // 从左到右找到第一个小于等于 baseValue 的值
            while (baseIndex < lastIndex && nums[baseIndex] <= baseValue)
            {
                baseIndex++;
            }

            // 交换
            if (baseIndex < lastIndex)
            {
                int temp = nums[baseIndex];
                nums[baseIndex] = nums[lastIndex];
                nums[lastIndex] = temp;
            }
        }
        int temp = nums[baseIndex];
        nums[baseIndex] = baseValue;
        nums[begin] = temp;

        quick(nums, begin, baseIndex - 1);
        quick(nums, baseIndex + 1, end);
    }

    /**
     * 快速排序
     */
    private void quick1(int[] nums, int begin, int end)
    {
        if (begin >= end)
        {
            return;
        }
        // 找到一个基准，将数值分为大约base和小于base的两部分，然后对两部分分别递归
        int baseValue = nums[begin];
        int baseIndex = begin;
        int lastIndex = end;
        int i = begin + 1;
        while (i <= lastIndex)
        {
            // 大于基准，和最后一个值互换位置
            // 不大于基准，和前一个值（即基准）互换位置
            if (nums[i] > baseValue)
            {
                int temp = nums[i];
                nums[i] = nums[lastIndex];
                nums[lastIndex] = temp;
                lastIndex--;
            }
            else
            {
                nums[baseIndex] = nums[i];
                baseIndex = i;
                i++;
            }
        }
        nums[baseIndex] = baseValue;
        quick(nums, begin, baseIndex - 1);
        quick(nums, baseIndex + 1, end);
    }

    /**
     * 希尔排序
     * 是简单插入排序的改进版，又称缩小增量排序
     */
    public int[] shellSort(int[] nums)
    {
        if (nums == null || (nums.length) <= 1)
        {
            return nums;
        }
        // 确定一个增量，增量逐渐缩小
        // 和归并类似，先对两组两个数排序，得到一个长度为四的有序数组，再对两组四个数排序，再对两组八个数排序
        for (int gap = nums.length / 2; gap > 0; gap /= 2)
        {
            for (int i = 0; i < gap; i++)
            {
                // 使用插入排序
                for (int j = i + gap; j < nums.length; j += gap)
                {
                    int baseValue = nums[j];
                    int baseIndex = j;
                    for (int k = j - gap; k >= 0; k -= gap)
                    {
                        if (nums[k] > baseValue)
                        {
                            nums[baseIndex] = nums[k];
                            baseIndex = k;
                        }
                        else
                        {
                            break;
                        }
                    }
                    nums[baseIndex] = baseValue;
                }
            }
        }
        return nums;
    }

    /**
     * 归并排序
     * 使用分治法进行排序。将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。
     * 若将两个有序表合并成一个有序表，称为2-路归并
     * 性能稳定
     */
    public int[] mergeSort(int[] nums)
    {
        if (nums == null || (nums.length) <= 1)
        {
            return nums;
        }
        merge(nums, 0, nums.length - 1);
        return nums;
    }

    private void merge(int[] nums, int begin, int end)
    {
        // 递归出口
        if (begin == end)
        {
            return;
        }
        // 递归拆分
        int mid = (begin + end) / 2;
        merge(nums, begin, mid);
        merge(nums, mid + 1, end);

        // 将两个有序的子序列合并
        int[] temp = new int[end - begin + 1];
        int i = begin, j = mid + 1;
        int tempIndex = 0;
        while (i <= mid && j <= end)
        {
            while (i <= mid && nums[i] <= nums[j])
            {
                temp[tempIndex] = nums[i];
                tempIndex++;
                i++;
            }
            while (j <= end && nums[i] > nums[j])
            {
                temp[tempIndex] = nums[j];
                tempIndex++;
                j++;
            }
        }
        // 某个子序列剩余数据处理
        while (i <= mid)
        {
            temp[tempIndex] = nums[i];
            tempIndex++;
            i++;
        }
        while (j <= end)
        {
            temp[tempIndex] = nums[j];
            tempIndex++;
            j++;
        }
        for (i = 0; i < temp.length; i++)
        {
            nums[begin + i] = temp[i];
        }
    }

    /**
     * 堆排序
     * 利用堆这种数据结构所设计的一种排序算法
     * 小到大排序的时候不建立”最小堆“而是建立”最大堆“.最大堆建立好之后，最大的元素是h[0]，删除h[0]即可
     */
    public static int[] heapSort(int[] nums)
    {
        int len;
        if (nums == null || (len = nums.length) <= 1)
        {
            return nums;
        }
        // 最后一个非叶结点是第n/2个结点，节点从1开始计数，所以下标都需要减一
        for (int i = 0; i < len - 1; i++)
        {
            int curIndex = (len - i) / 2;
            while (curIndex > 0)
            {
                if (curIndex * 2 + 1 - 1 >= (len - i) || nums[curIndex * 2 - 1] > nums[curIndex * 2 + 1 - 1])
                {
                    if (nums[curIndex * 2 - 1] > nums[curIndex - 1])
                    {
                        int temp = nums[curIndex - 1];
                        nums[curIndex - 1] = nums[curIndex * 2 - 1];
                        nums[curIndex * 2 - 1] = temp;
                    }
                }
                else
                {
                    if (nums[curIndex * 2 + 1 - 1] > nums[curIndex - 1])
                    {
                        int temp = nums[curIndex - 1];
                        nums[curIndex - 1] = nums[curIndex * 2 + 1 - 1];
                        nums[curIndex * 2 + 1 - 1] = temp;
                    }
                }
                curIndex--;
            }
            int temp = nums[len - i - 1];
            nums[len - i - 1] = nums[0];
            nums[0] = temp;
        }
        return nums;
    }


    /**
     * 计数排序
     * 将输入的数据值转化为键存储在额外开辟的数组空间中
     * 线性时间复杂度，要求输入的数据必须是有确定范围的整数
     */
    public int[] countingSort(int[] nums)
    {
        if (nums == null || (nums.length) <= 1)
        {
            return nums;
        }
        // 找到最大值
        // 构造桶，能装下所有数据的桶
        int max = Arrays.stream(nums).max().getAsInt();
        int min = Arrays.stream(nums).reduce(Math::min).getAsInt();
        int[] temp = new int[max - min + 1];
        // 统计每个数据出现的次数
        Arrays.fill(temp, 0);
        for (int a : nums)
        {
            temp[a - min] = temp[a - min] + 1;
        }
        // 生成结果
        int index = 0;
        for (int i = min; i <= max; i++)
        {
            while (temp[i - min] > 0)
            {
                nums[index] = i;
                temp[i - min] = temp[i - min] - 1;
                index++;
            }
        }
        return nums;
    }

    /**
     * 桶排序
     * 计数排序的升级版。将数据分到有限数量的桶里，每个桶再分别排序
     */
    public int[] bucketSort(int[] nums)
    {
        if (nums == null || (nums.length) <= 1)
        {
            return nums;
        }
        // 找到最大值
        // 构造大小合适的桶，一个桶中可能装多个数据
        int max = Arrays.stream(nums).max().getAsInt();
        int min = Arrays.stream(nums).reduce(Math::min).getAsInt();
        int len = (max - min) / nums.length + 1;
        List<List<Integer>> bucket = new ArrayList<>(len);
        for (int i = 0; i < len; i++)
        {
            bucket.add(new ArrayList<>());
        }
        // 数据放入桶中
        for (int num : nums)
        {
            bucket.get((num - min) / nums.length).add(num);
        }
        // 对每个桶单独排序
        for (List<Integer> list : bucket)
        {
            Collections.sort(list);
        }

        // 组装成需要的数据格式
        final List<Integer> collect = bucket.stream().flatMap(list -> list.stream()).collect(Collectors.toList());
        for (int i = 0; i < collect.size(); i++)
        {
            nums[i] = collect.get(i);
        }

        return nums;
    }

    /**
     * 基数排序
     * 基数排序是按照低位先排序，然后收集；再按照高位排序，然后再收集；依次类推，直到最高位
     */
    public int[] radixSort(int[] nums)
    {
        if (nums == null || (nums.length) <= 1)
        {
            return nums;
        }
        // TODO: 2021/4/28
        return nums;
    }

    public static void main(String[] args)
    {
        int[] nums = new int[]{5,2,3,1};
        heapSort(nums);
    }
}
