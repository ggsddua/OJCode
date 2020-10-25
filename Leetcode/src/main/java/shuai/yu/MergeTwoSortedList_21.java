package shuai.yu;

/**
 * 合并两个有序链表
 * <p>
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * @author shuai.yu
 * @version 2020/10/18
 */
public class MergeTwoSortedList_21
{
    public static void main(String[] args)
    {

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
