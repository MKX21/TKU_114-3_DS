class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

public class BuildLinkedList {

    public static void printList(ListNode head) {
        if (head == null) {
            System.out.println("串列為空 (Empty List)");
            return;
        }

        ListNode current = head;
        System.out.print("鏈結串列內容: ");
        while (current != null) {
            System.out.print(current.val + (current.next != null ? " -> " : ""));
            current = current.next;
        }
        System.out.println();
    }

    public static void printListStats(ListNode head) {
        if (head == null) {
            System.out.println("串列為空，節點數：0，總和：0");
            return;
        }

        int count = 0;
        int sum = 0;
        ListNode current = head;

        while (current != null) {
            count++;
            sum += current.val;
            current = current.next;
        }

        System.out.println("節點總數: " + count);
        System.out.println("數值總和: " + sum);
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(10);
        ListNode n2 = new ListNode(20);
        ListNode n3 = new ListNode(30);
        ListNode n4 = new ListNode(40);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        ListNode head = n1;

        System.out.println("=== 測試正常鏈結串列 ===");
        printList(head);
        printListStats(head);

        System.out.println("\n=== 測試空串列邊界條件 ===");
        ListNode emptyHead = null;
        printList(emptyHead);
        printListStats(emptyHead);
    }
}