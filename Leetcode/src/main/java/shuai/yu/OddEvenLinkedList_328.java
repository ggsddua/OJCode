package shuai.yu;

/**
 * 奇偶链表
 *
 * @author shuai.yu
 * @version 2020/11/22
 */
public class OddEvenLinkedList_328 {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 奇数
        ListNode oddLast = head;
        // 偶数
        ListNode evenLast = head.next;
        ListNode evenFirst = evenLast;
        ListNode temp = evenFirst.next;
        boolean isOdd = true;
        while (temp != null) {
            if (isOdd) {
                oddLast.next = temp;
                oddLast = temp;
                isOdd = false;
            } else {
                evenLast.next = temp;
                evenLast = temp;
                isOdd = true;
            }
            temp = temp.next;
        }
        oddLast.next = evenFirst;
        evenLast.next = null;
        return head;
    }
}
