package shuai.yu.sort;

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
                else {
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
    public int[] quickSort(int[] nums){
        return nums;
    }

    /**
     * 希尔排序
     * 是简单插入排序的改进版，又称缩小增量排序
     */
    public int[] shellSort(int[] nums){
        return nums;
    }

    /**
     * 归并排序
     * 使用分治法进行排序。将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。
     * 若将两个有序表合并成一个有序表，称为2-路归并
     */
    public int[] mergeSort(int[] nums){
        return nums;
    }

    /**
     * 堆排序
     * 利用堆这种数据结构所设计的一种排序算法
     */
    public int[] heapSort(int[] nums){
        return nums;
    }


    /**
     * 计数排序
     * 将输入的数据值转化为键存储在额外开辟的数组空间中
     * 线性时间复杂度，要求输入的数据必须是有确定范围的整数
     */
    public int[] countingSort(int[] nums){
        return nums;
    }

    /**
     * 桶排序
     * 计数排序的升级版。将数据分到有限数量的桶里，每个桶再分别排序
     */
    public int[] bucketSort(int[] nums){
        return nums;
    }

    /**
     * 基数排序
     * 基数排序是按照低位先排序，然后收集；再按照高位排序，然后再收集；依次类推，直到最高位
     */
    public int[] radixSort(int[] nums){
        return nums;
    }
}
