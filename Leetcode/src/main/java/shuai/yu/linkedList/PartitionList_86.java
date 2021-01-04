package shuai.yu.linkedList;

import shuai.yu.common.ListNode;

/**
 * 分隔链表
 *
 * @author shuai.yu
 * @version 2021/01/03
 */
public class PartitionList_86
{
    public static void main(String[] args)
    {
        ListNode head = new ListNode(1);
        ListNode e1 = new ListNode(4);
        ListNode e2 = new ListNode(3);
        ListNode e3 = new ListNode(2);
        ListNode e4 = new ListNode(5);
        ListNode e5 = new ListNode(2);
        head.next = e1;
        e1.next = e2;
        e2.next = e3;
        e3.next = e4;
        e4.next = e5;
        partition(head, 3);
    }

    public static ListNode partition(ListNode head, int x)
    {
        ListNode front = new ListNode(0);
        ListNode frontHead = front;
        ListNode behind = new ListNode(0);
        ListNode behindHead = behind;
        for (; head != null; head = head.next)
        {
            if (head.val < x)
            {
                front.next = head;
                front = front.next;
            }
            else
            {
                behind.next = head;
                behind = behind.next;
            }
        }
        front.next = behindHead.next;
        behind.next = null;
        return frontHead.next;
    }
}
