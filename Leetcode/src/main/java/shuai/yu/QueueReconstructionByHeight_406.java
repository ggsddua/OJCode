package shuai.yu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 根据身高重建队列
 *
 * 填坑过程，我们每次从身高里面找最矮的，最矮的里面找排名最高的，
 * 假设他的排名是 i , 那么就从左往右把他放在第i个还没有被填过的坑里面（坑内相同的元素也要计数）
 *
 * @author shuai.yu
 * @version 2020/11/22
 */
public class QueueReconstructionByHeight_406 {
    public static void main(String[] args) {
        int[][] people = new int[][]{{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
        reconstructQueue(people);
    }
    public static int[][] reconstructQueue(int[][] people) {
        int len = people.length;
        if (len <= 1)
            return people;
        int[][] ans = new int[len][2];
        long[] temp = new long[len];
        // 经过处理，方便按照多个key值，有优先级的排序
        for (int i = 0; i < len; i++) {
            // 总人数少于1100人
            temp[i] = people[i][0] * 100000000L + people[i][1] * 10000L + i;
        }
        Arrays.sort(temp);
        boolean[] flag = new boolean[len];
        for (long tmp : temp) {
            int oldIndex = (int) (tmp % 10000);
            int newIndex = 0;
            int count = 0;
            // 找到该元素应该填的坑
            for (; true; newIndex++) {
                // 如果坑没填，或者填的元素和本元素一致，计数加一
                if (flag[newIndex] && ans[newIndex][0] != people[oldIndex][0]) {
                    continue;
                }
                count++;
                if (count > people[oldIndex][1]) {
                    break;
                }
            }
            ans[newIndex][0] = people[oldIndex][0];
            ans[newIndex][1] = people[oldIndex][1];
            flag[newIndex] = true;
        }
        return ans;
    }
}
