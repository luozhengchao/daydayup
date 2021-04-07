package study;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author luozhengchao
 * @Date 2021/4/6 下午6:10
 */
public class ListOrder {


    /**
     * 142. 环形链表 II
     * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     * <p>
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
     * <p>
     * 说明：不允许修改给定的链表。
     * <p>
     * 进阶：
     * <p>
     * 你是否可以使用 O(1) 空间解决此题？
     */
    public class Solution142 {
        public ListNode detectCycle(ListNode head) {
            ListNode pos = head;
            //遍历
            Set<ListNode> visited = new HashSet<>();
            while (pos != null) {
                if (!visited.add(pos)) {
                    return pos;
                }
                pos = pos.next;
            }
            return null;
        }

        /**
         * 双指针
         * 根据：
         *
         * f=2s （快指针每次2步，路程刚好2倍）
         *
         * f = s + nb (相遇时，刚好多走了n圈）
         *
         * 推出：s = nb
         *
         * 从head结点走到入环点需要走 ： a + nb， 而slow已经走了nb，那么slow再走a步就是入环点了。
         *
         * 如何知道slow刚好走了a步？ 从head开始，和slow指针一起走，相遇时刚好就是a步
         *
         * @param head
         * @return
         */
        public ListNode detectCycle2(ListNode head) {
            //快慢指针
            ListNode slow = head;
            ListNode fast = head;
            while (true) {
                if (fast == null || fast.next == null) return null;
                slow = slow.next;
                fast = fast.next.next;
                if (fast == slow) break;
            }
            fast = head;
            while (fast != slow) {
                fast = fast.next;
                slow = slow.next;
            }

            return fast;
        }
    }

}
