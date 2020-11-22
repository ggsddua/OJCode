package shuai.yu;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * 根据数字二进制下 1 的数目排序
 * <p>
 * 给你一个整数数组 arr 。请你将数组中的元素按照其二进制表示中数字 1 的数目升序排序。
 * 如果存在多个数字二进制中 1 的数目相同，则必须将它们按照数值大小升序排列。
 *
 * @author shuai.yu
 * @version 2020/11/07
 */
public class SortIntegersByTheNumberOf1Bits_1356 {
    public int[] sortByBits(int[] arr) {
        int len = arr.length;
        int[] bit1Nums = new int[len];
        for (int i = 0; i < len; i++) {
            bit1Nums[i] = getBit1Nums(arr[i]);
        }
        // 选择排序，n2
        for (int i = 0; i < len; i++) {
            int pos = i;
            for (int j = i + 1; j < len; j++) {
                if (bit1Nums[pos] > bit1Nums[j] || (bit1Nums[pos] == bit1Nums[j] && arr[pos] > arr[j])) {
                    pos = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[pos];
            arr[pos] = temp;
            temp = bit1Nums[i];
            bit1Nums[i] = bit1Nums[pos];
            bit1Nums[pos] = temp;
        }
        return arr;
    }

    public int getBit1Nums(int x) {
        int ans = 0;
        while (x > 0) {
            ans += x % 2;
            x = x / 2;
        }
        return ans;
    }

    public int[] sortByBits1(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            // 使用Integer.bitCount 统计1的个数, 然后第一排序关键字放入高位，第二关键字放低位
            arr[i] = Integer.bitCount(arr[i]) * 100000 + arr[i];
        }
        Arrays.sort(arr);
        for (int i = 0; i < len; i++) {
            arr[i] = arr[i] % 100000;
        }
        return arr;
    }
}
