class Node {
    int val;
    Node next;

    Node(int val) {
        this.val = val;
        this.next = null;
    }
}

public class NumberHistoryList {
    private Node head;

    public NumberHistoryList() {
        this.head = null;
    }

    public void addFirst(int val) {
        Node newNode = new Node(val);
        newNode.next = head;
        head = newNode;
        System.out.println("[前端新增] " + val);
    }

    public void addLast(int val) {
        Node newNode = new Node(val);
        if (head == null) {
            head = newNode;
            System.out.println("[尾端新增] " + val);
            return;
        }

        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
        System.out.println("[尾端新增] " + val);
    }

    public boolean search(int target) {
        Node current = head;
        while (current != null) {
            if (current.val == target) {
                System.out.println("[搜尋結果] 找到數值: " + target);
                return true;
            }
            current = current.next;
        }
        System.out.println("[搜尋結果] 找不到數值: " + target);
        return false;
    }

    public boolean remove(int target) {
        if (head == null) {
            System.out.println("[刪除失敗] 串列為空，無法刪除 " + target);
            return false;
        }

        if (head.val == target) {
            head = head.next;
            System.out.println("[刪除成功] 移除頭節點: " + target);
            return true;
        }

        Node current = head;
        while (current.next != null && current.next.val != target) {
            current = current.next;
        }

        if (current.next == null) {
            System.out.println("[刪除失敗] 找不到欲刪除的數值: " + target);
            return false;
        }

        current.next = current.next.next;
        System.out.println("[刪除成功] 移除數值: " + target);
        return true;
    }

    public void printList() {
        if (head == null) {
            System.out.println("當前串列: (空串列)");
            return;
        }

        Node current = head;
        System.out.print("當前串列: ");
        while (current != null) {
            System.out.print(current.val + (current.next != null ? " -> " : ""));
            current = current.next;
        }
        System.out.println();
    }

    public void printStats() {
        if (head == null) {
            System.out.println("=== 統計資訊 ===");
            System.out.println("Size: 0, 總和: 0, 最大值: 無, 最小值: 無 (串列為空)");
            return;
        }

        int size = 0;
        int sum = 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        Node current = head;
        while (current != null) {
            size++;
            sum += current.val;
            if (current.val > max) max = current.val;
            if (current.val < min) min = current.val;
            current = current.next;
        }

        System.out.println("=== 統計資訊 ===");
        System.out.println("Size: " + size + ", 總和: " + sum + ", 最大值: " + max + ", 最小值: " + min);
    }

    public static void main(String[] args) {
        NumberHistoryList list = new NumberHistoryList();

        System.out.println("=== 測試 1: 空串列統計 ===");
        list.printStats();

        System.out.println("\n=== 開始進行 8 次以上操作 ===");
        list.addFirst(30);       // 操作 1
        list.addFirst(10);       // 操作 2
        list.addLast(50);        // 操作 3
        list.addLast(20);        // 操作 4
        list.printList();        // 操作 5

        list.search(50);         // 操作 6
        list.search(99);         // 操作 7

        list.remove(10);         // 操作 8 (刪除 head)
        list.printList();
        
        list.remove(20);         // 操作 9 (刪除尾端)
        list.printList();

        list.remove(100);        // 操作 10 (找不到資料)
        
        System.out.println();
        list.printStats();
    }
}
