package shuai.yu;

/**
 * 归并排序
 *
 * @author shuai.yu
 * @version 2020/06/25
 */
public class MergeSort
{
    public static void main(String[] args)
    {
        int[] array = new int[]{5, 10, 8, 7, 9, 6, 2, 4, 1, 3};
        sort(array, 0, array.length - 1);
        for (int i = 0; i < array.length; i++)
        {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void sort(int[] array, int left, int right)
    {
        if (left < right)
        {
            int mid = (left + right) / 2;
            sort(array, left, mid);
            sort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    public static void merge(int[] array, int left, int mid, int right)
    {
        int[] tmpArr = new int[right - left + 1];
        //third记录中间数组的索引
        int third = 0;
        int tmp = left;
        int midTmp = mid + 1;
        while (left <= mid && midTmp <= right)
        {
            //从两个数组中取出最小的放入中间数组
            if (array[left] <= array[midTmp])
            {
                tmpArr[third++] = array[left++];
            }
            else
            {
                tmpArr[third++] = array[midTmp++];
            }
        }

        //剩余部分依次放入中间数组
        while (midTmp <= right)
        {
            tmpArr[third++] = array[midTmp++];
        }

        while (left <= mid)
        {
            tmpArr[third++] = array[left++];
        }

        //将中间数组中的内容复制回原数组
        int i = 0;
        while (tmp <= right)
        {
            array[tmp++] = tmpArr[i++];
        }
    }
}
