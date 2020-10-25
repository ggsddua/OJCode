package shuai.yu;

/**
 * 两数相加
 *
 * @author shuai.yu
 * @version 2020/06/07
 */
public class AddTwoNumbers_2
{
    public ListNode addTwoNumbers(ListNode l1, ListNode l2)
    {
        ListNode rootNode = new ListNode(0);
        ListNode tempNode = rootNode;
        int carry = 0;
        for (; ; )
        {
            if (l1 != null && l2 != null)
            {
                tempNode.next = new ListNode(0);
                tempNode = tempNode.next;
                tempNode.val = (l1.val + l2.val + carry) % 10;
                carry = (l1.val + l2.val + carry) / 10;
                l1 = l1.next;
                l2 = l2.next;
            }
            else if (l1 != null)
            {
                tempNode.next = new ListNode(0);
                tempNode = tempNode.next;
                tempNode.val = (l1.val + carry) % 10;
                carry = (l1.val + carry) / 10;
                l1 = l1.next;
            }
            else if (l2 != null)
            {
                tempNode.next = new ListNode(0);
                tempNode = tempNode.next;
                tempNode.val = (l2.val + carry) % 10;
                carry = (l2.val + carry) / 10;
                l2 = l2.next;
            }
            else
            {
                if (carry != 0)
                {
                    tempNode.next = new ListNode(carry);
                }
                break;
            }
        }
        return rootNode.next;
    }
}

class ListNode
{
    int val;
    ListNode next;

    ListNode(int x)
    {
        val = x;
    }

    ListNode()
    {
    }

    ListNode(int val, ListNode next)
    {
        this.val = val;
        this.next = next;
    }
}
