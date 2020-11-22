package shuai.yu;

/**
 * 对链表进行插入排序
 *
 * @author shuai.yu
 * @version 2020/11/21
 */
public class InsertionSortList_147 {
    public ListNode insertionSortList(ListNode head) {
        if (head == null)
            return head;
        ListNode temp = head.next;
        head.next = null;
        ListNode tempt;
        for (; temp != null; temp = tempt) {
            tempt = temp.next;

            if (temp.val <= head.val) {
                temp.next = head;
                head = temp;
                continue;
            }
            ListNode pre = head;
            for (; true; pre = pre.next) {
                if (pre.next == null) {
                    pre.next = temp;
                    temp.next = null;
                    break;
                } else if (temp.val <= pre.next.val) {
                    temp.next = pre.next;
                    pre.next = temp;
                    break;
                }
            }
        }
        return head;
    }
}
