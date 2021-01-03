package shuai.yu.common;

/**
 * @author shuai.yu
 * @version 2021/01/03
 */
public class ListNode
{
    public int val;
    public ListNode next;

    public ListNode(int x)
    {
        val = x;
    }

    public ListNode()
    {
    }

    public ListNode(int val, ListNode next)
    {
        this.val = val;
        this.next = next;
    }
}
