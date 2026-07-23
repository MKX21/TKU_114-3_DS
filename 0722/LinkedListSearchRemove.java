class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

public class LinkedListSearchRemove {

    public static boolean contains(ListNode head, int target) {
        ListNode current = head;
        while (current != null) {
            if (current.val == target) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public static ListNode removeValue(ListNode head, int target) {
        if (head == null) {
            System.out.println("【警告】串列為空，無法進行刪除操作。");
            return null;
        }

        if (head.val == target) {
            System.out.println("刪除頭節點 (head): " + target);
            return head.next;
        }

        ListNode current = head;
        while (current.next != null && current.next.val != target) {
            current = current.next;
        }

        if (current.next == null) {
            System.out.println("找不到欲刪除的資料: " + target);
            return head;
        }

        System.out.println("成功刪除節點: " + target);
        current.next = current.next.next;
        return head;
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
        ListNode head = new ListNode(10);
        head.next = new ListNode(20);
        head.next.next = new ListNode(30);
        head.next.next.next = new ListNode(40);

        System.out.println("=== 初始狀態 ===");
        printList(head);

        System.out.println("\n=== 測試 contains 搜尋功能 ===");
        System.out.println("是否包含 20？ " + contains(head, 20));
        System.out.println("是否包含 99？ " + contains(head, 99));

        System.out.println("\n=== 1. 測試刪除中間節點 (刪除 20) ===");
        head = removeValue(head, 20);
        printList(head);

        System.out.println("\n=== 2. 測試刪除尾端節點 (刪除 40) ===");
        head = removeValue(head, 40);
        printList(head);

        System.out.println("\n=== 3. 測試刪除頭節點 (刪除 10) ===");
        head = removeValue(head, 10);
        printList(head);

        System.out.println("\n=== 4. 測試找不到資料 (刪除 99) ===");
        head = removeValue(head, 99);
        printList(head);

        System.out.println("\n=== 5. 測試空串列刪除 ===");
        head = removeValue(head, 30); // 刪除最後一個節點，使其變為空串列
        printList(head);
        head = removeValue(head, 100); // 嘗試從空串列刪除
        printList(head);
    }
}