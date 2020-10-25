package shuai.yu;

/**
 * 合并K个升序链表
 * 复杂度解析：https://leetcode-cn.com/problems/merge-k-sorted-lists/solution/he-bing-kge-pai-xu-lian-biao-by-leetcode-solutio-2/
 *
 * @author shuai.yu
 * @version 2020/10/18
 */
public class MergeKSortedList_23
{
    public static void main(String[] args)
    {

    }

    /**
     * 逐一进行合并
     * O(k^2 n)
     *
     * @param lists
     * @return
     */
    public static ListNode mergeKLists1(ListNode[] lists)
    {
        ListNode root = null;
        if (lists == null || lists.length == 0)
        {
            return root;
        }
        if (lists.length == 1)
        {
            return lists[0];
        }
        root = lists[0];
        for (int i = 1; i < lists.length; i++)
        {
            root = mergeTwoLists(root, lists[i]);
        }
        return root;
    }

    /**
     * 分治算法
     * O(kn×logk)
     *
     * @param lists
     * @return
     */
    public static ListNode mergeKLists2(ListNode[] lists)
    {
        if (lists == null || lists.length == 0)
        {
            return null;
        }
        return merge(lists, 0, lists.length);
    }

    private static ListNode merge(ListNode[] listNodes, int left, int right)
    {
        if (left == right)
            return listNodes[left];
        int mid = left + (right - left) / 2;
        ListNode l1 = merge(listNodes, left, mid);
        ListNode l2 = merge(listNodes, mid + 1, right);
        return mergeTwoLists(l1, l2);
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2)
    {
        ListNode root, last;
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        if (l1.val <= l2.val)
        {
            root = l1;
            last = l1;
            l1 = l1.next;
        }
        else
        {
            root = l2;
            last = l2;
            l2 = l2.next;
        }

        while (true)
        {
            if (l1 == null)
            {
                last.next = l2;
                break;
            }
            else if (l2 == null)
            {
                last.next = l1;
                break;
            }

            if (l1.val <= l2.val)
            {
                last.next = l1;
                last = l1;
                l1 = l1.next;
            }
            else
            {
                last.next = l2;
                last = l2;
                l2 = l2.next;
            }
        }
        return root;
    }
}
