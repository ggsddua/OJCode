package shuai.yu.dynamicProgram;

import java.util.Arrays;

/**
 * 比特位计数
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 *
 * @author shuai.yu
 * @version 2021/03/03
 */
public class CountingBits_338
{
    public static void main(String[] args)
    {
        System.out.println(Arrays.toString(countBits(2)));
    }

    /**
     * T(2^i + a) = T(a) + 1 (a < 2^i)
     */
    public static int[] countBits(int num)
    {
        int[] array = new int[num + 1];
        array[0] = 0;
        int index = 1;
        while (index <= num)
        {
            int temp = index;
            for (int i = 0; i < index && temp <= num; i++, temp++)
            {
                array[temp] = array[i] + 1;
            }
            index *= 2;
        }
        return array;
    }
}
