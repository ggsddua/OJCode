package shuai.yu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 插入区间
 *
 * @author shuai.yu
 * @version 2020/11/04
 */
public class InsertInterval_57 {
    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1, 5},{8,9},{15,16}};
        int[] newInterval = new int[]{8, 10};
        insert1(intervals, newInterval);
    }

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<Integer> list = new LinkedList<>();
        int i = 0;
        int temp = -1;
        boolean hasInsert = false;
        for (; i < intervals.length; i++) {
            if (hasInsert) {
                list.add(i);
                continue;
            }

            if (intervals[i][0] > newInterval[1]) {
                if (temp != -1) {
                    list.add(i);
                    hasInsert = true;
                } else {
                    list.add(-1);
                    list.add(i);
                    hasInsert = true;
                }
            } else if (intervals[i][1] < newInterval[0]) {
                list.add(i);
            } else if (intervals[i][0] <= newInterval[0]) {
                list.add(i);
                if (intervals[i][1] >= newInterval[1]) {
                    hasInsert = true;
                } else {
                    intervals[i][1] = newInterval[1];
                    temp = i;
                }
            } else {
                if (temp == -1) {
                    intervals[i][0] = newInterval[0];
                    list.add(i);
                    if (intervals[i][1] >= newInterval[1]) {
                        hasInsert = true;
                    } else {
                        intervals[i][1] = newInterval[1];
                        temp = i;
                    }
                } else {
                    if (intervals[i][1] >= newInterval[1]) {
                        intervals[temp][1] = intervals[i][1];
                        hasInsert = true;
                    }
                }
            }
        }
        if (!hasInsert && temp == -1)
            list.add(-1);
        int[][] ans = new int[list.size()][2];
        i = 0;
        for (Integer integer : list) {
            if (integer == -1) {
                ans[i][0] = newInterval[0];
                ans[i][1] = newInterval[1];
            } else {
                ans[i][0] = intervals[integer][0];
                ans[i][1] = intervals[integer][1];
            }
            i++;
        }
        return ans;
    }

    public static int[][] insert1(int[][] intervals, int[] newInterval) {
        int left = newInterval[0];
        int right = newInterval[1];
        boolean placed = false;
        List<int[]> ansList = new ArrayList<int[]>();
        for (int[] interval : intervals) {
            if (interval[0] > right) {
                // 在插入区间的右侧且无交集
                if (!placed) {
                    ansList.add(new int[]{left, right});
                    placed = true;
                }
                ansList.add(interval);
            } else if (interval[1] < left) {
                // 在插入区间的左侧且无交集
                ansList.add(interval);
            } else {
                // 与插入区间有交集，计算它们的并集
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }
        }
        if (!placed) {
            ansList.add(new int[]{left, right});
        }
        int[][] ans = new int[ansList.size()][2];
        for (int i = 0; i < ansList.size(); ++i) {
            ans[i] = ansList.get(i);
        }
        return ans;
    }
}
