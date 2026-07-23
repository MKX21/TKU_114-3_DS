package Task;

public class TaskLinkedList {
    private TaskNode head;

    public TaskLinkedList() {
        this.head = null;
    }

    public boolean contains(String taskId) {
        TaskNode current = head;
        while (current != null) {
            if (current.taskId.equals(taskId)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean addUrgentTask(String taskId, String description) {
        if (contains(taskId)) {
            System.out.println("[新增失敗] 工作代碼已存在: " + taskId);
            return false;
        }

        TaskNode newNode = new TaskNode(taskId, description);
        newNode.next = head;
        head = newNode;
        System.out.println("[緊急工作已加至前端] " + taskId + ": " + description);
        return true;
    }

    public boolean addNormalTask(String taskId, String description) {
        if (contains(taskId)) {
            System.out.println("[新增失敗] 工作代碼已存在: " + taskId);
            return false;
        }

        TaskNode newNode = new TaskNode(taskId, description);
        if (head == null) {
            head = newNode;
            System.out.println("[一般工作已加至尾端] " + taskId + ": " + description);
            return true;
        }

        TaskNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
        System.out.println("[一般工作已加至尾端] " + taskId + ": " + description);
        return true;
    }

    public boolean completeTask(String taskId) {
        TaskNode current = head;
        while (current != null) {
            if (current.taskId.equals(taskId)) {
                if (current.isCompleted) {
                    System.out.println("[提示] 工作 " + taskId + " 先前已是完成狀態。");
                } else {
                    current.isCompleted = true;
                    System.out.println("[狀態更新] 工作 " + taskId + " 已標記為完成！");
                }
                return true;
            }
            current = current.next;
        }
        System.out.println("[更新失敗] 找不到代碼為 " + taskId + " 的工作。");
        return false;
    }

    public boolean removeTask(String taskId) {
        if (head == null) {
            System.out.println("[刪除失敗] 工作清單為空。");
            return false;
        }

        if (head.taskId.equals(taskId)) {
            System.out.println("[刪除成功] 已移除工作: " + head.taskId + " (" + head.description + ")");
            head = head.next;
            return true;
        }

        TaskNode current = head;
        while (current.next != null && !current.next.taskId.equals(taskId)) {
            current = current.next;
        }

        if (current.next == null) {
            System.out.println("[刪除失敗] 找不到代碼為 " + taskId + " 的工作。");
            return false;
        }

        System.out.println("[刪除成功] 已移除工作: " + current.next.taskId + " (" + current.next.description + ")");
        current.next = current.next.next;
        return true;
    }

    public void printPendingTasks() {
        if (head == null) {
            System.out.println("【未完成工作】清單目前為空。");
            return;
        }

        TaskNode current = head;
        boolean hasPending = false;
        System.out.println("--- 未完成工作列表 ---");
        while (current != null) {
            if (!current.isCompleted) {
                System.out.println("- [" + current.taskId + "] " + current.description);
                hasPending = true;
            }
            current = current.next;
        }

        if (!hasPending) {
            System.out.println("太棒了！目前沒有任何未完成的工作。");
        }
        System.out.println("----------------------");
    }

    public void printTaskStats() {
        int totalCount = 0;
        int pendingCount = 0;

        TaskNode current = head;
        while (current != null) {
            totalCount++;
            if (!current.isCompleted) {
                pendingCount++;
            }
            current = current.next;
        }

        System.out.println("=== 工作統計數據 ===");
        System.out.println("工作總數: " + totalCount);
        System.out.println("未完成數量: " + pendingCount);
        System.out.println("已完成數量: " + (totalCount - pendingCount));
        System.out.println("====================");
    }
}