class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

public class LinkedListReverse {

    public static ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        ListNode nextTemp = null;

        while (current != null) {
            nextTemp = current.next;
            current.next = prev;
            prev = current;
            current = nextTemp;
        }

        return prev;
    }

    public static void printList(ListNode head) {
        if (head == null) {
            System.out.println("當前串列狀況: (空串列)");
            return;
        }

        ListNode current = head;
        System.out.print("當前串列狀況: ");
        while (current != null) {
            System.out.print(current.val + (current.next != null ? " -> " : ""));
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("=== 測試 1：多節點反轉 ===");
        ListNode head1 = new ListNode(10);
        head1.next = new ListNode(20);
        head1.next.next = new ListNode(30);
        head1.next.next.next = new ListNode(40);

        System.out.print("反轉前：");
        printList(head1);
        head1 = reverse(head1);
        System.out.print("反轉後：");
        printList(head1);

        System.out.println("\n=== 測試 2：單一節點反轉 ===");
        ListNode head2 = new ListNode(100);

        System.out.print("反轉前：");
        printList(head2);
        head2 = reverse(head2);
        System.out.print("反轉後：");
        printList(head2);

        System.out.println("\n=== 測試 3：空串列反轉 ===");
        ListNode head3 = null;

        System.out.print("反轉前：");
        printList(head3);
        head3 = reverse(head3);
        System.out.print("反轉後：");
        printList(head3);
    }
}