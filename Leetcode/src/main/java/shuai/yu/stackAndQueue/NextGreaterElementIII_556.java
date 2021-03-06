package shuai.yu.stackAndQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * 下一个更大元素 III
 *
 * @author shuai.yu
 * @version 2021/03/06
 */
public class NextGreaterElementIII_556
{
    public static void main(String[] args)
    {
        System.out.println(nextGreaterElement(230241));
    }

    public static int nextGreaterElement(int n)
    {
        long ans = -1;
        if (n < 10)
            return (int) ans;
        List<Integer> list = new ArrayList<>(10);
        list.add(n % 10);
        n = n / 10;
        while (n > 0)
        {
            int temp = n % 10;
            n = n / 10;
            // 如果从右到左，单调递增，则为最大，否则，可变为更大
            if (temp < list.get(list.size() - 1))
            {
                // 找到最小值
                int minIndex = 0;
                for (int i = 0; i < list.size(); i++)
                {
                    if (list.get(i) > temp)
                    {
                        minIndex = i;
                        break;
                    }
                }
                ans = list.get(minIndex);
                // 将temp设置到对应位置，依然有序
                list.set(minIndex, temp);
                // 按先入先出取值，有序，递增
                for (int i = 0; i < list.size(); i++)
                {
                    ans = ans * 10 + list.get(i);
                }
                ans += n * (long) (Math.pow(10, list.size() + 1));
                break;
            }
            list.add(temp);
        }
        return ans > Integer.MAX_VALUE ? -1 : (int) ans;
    }
}
