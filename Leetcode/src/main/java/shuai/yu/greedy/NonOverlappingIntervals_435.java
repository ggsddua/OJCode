package shuai.yu.greedy;

/**
 * 无重叠区间
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 *
 * @author shuai.yu
 * @version 2020/12/31
 */
public class NonOverlappingIntervals_435
{
    public static void main(String[] args)
    {
        /**
         * 输入: [ [1,2], [2,3], [3,4], [1,3] ]
         * 输出: 1
         * 输入: [ [1,2], [1,2], [1,2] ]
         * 输出: 2
         * 输入: [ [1,2], [2,3] ]
         * 输出: 0
         */
        int[][] intervals = new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        System.out.println(eraseOverlapIntervals(intervals));
        intervals = new int[][]{{1, 2}, {1, 2}, {1, 2}};
        System.out.println(eraseOverlapIntervals(intervals));
        intervals = new int[][]{{1, 2}, {2, 3}};
        System.out.println(eraseOverlapIntervals(intervals));
        intervals = new int[][]{{1, 100}, {11, 22}, {1, 11}, {2, 12}};
        System.out.println(eraseOverlapIntervals(intervals));

    }

    public static int eraseOverlapIntervals(int[][] intervals)
    {
        int ans = 0;
        if (intervals == null || intervals.length == 0)
            return 0;
        // 排序
        for (int i = 0; i < intervals.length; i++)
        {
            int minIndex = i;
            for (int j = i + 1; j < intervals.length; j++)
            {
                if (intervals[minIndex][1] > intervals[j][1])
                {
                    minIndex = j;
                }
            }
            int temp = intervals[i][0];
            intervals[i][0] = intervals[minIndex][0];
            intervals[minIndex][0] = temp;
            temp = intervals[i][1];
            intervals[i][1] = intervals[minIndex][1];
            intervals[minIndex][1] = temp;
        }
        // 从第一个区间开始，找到下一个在该区间结束后才开始的区间，中间忽略一个就意味着需要删除一个
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++)
        {
            if (intervals[i][0] >= end)
            {
                end = intervals[i][1];
            }
            else
            {
                ans++;
            }
        }
        return ans;
    }
}
