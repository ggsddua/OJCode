package shuai.yu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 组合总和
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合
 *
 * @author shuai.yu
 * @version 2020/10/27
 */
public class CombinationSum_39 {
    public static void main(String[] args) {
        combinationSum(new int[]{2, 3, 6, 7}, 7);
    }

    static List<List<Integer>> lists = new ArrayList<>();

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> list = new LinkedList<>();
        recursive(0, candidates, target, 0, list);
        return lists;
    }

    private static void recursive(int currentSum, int[] candidates, int target, int index, List<Integer> list) {
        if (index >= candidates.length) {
            return;
        }

        // 和等于目标时，退出
        if (currentSum == target) {
            lists.add(new ArrayList<>(list));
            return;
        }

        // 直接忽略本index，递归下一个index
        recursive(currentSum, candidates, target, index + 1, list);

        if (currentSum + candidates[index] < target) {
            list.add(candidates[index]);
            currentSum += candidates[index];
            // 本index递归
            recursive(currentSum, candidates, target, index, list);
            list.remove(list.size() - 1);
        }
    }
}
