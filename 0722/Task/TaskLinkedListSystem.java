package Task;

public class TaskLinkedListSystem {
    public static void main(String[] args) {
        TaskLinkedList taskList = new TaskLinkedList();

        System.out.println("=== 1. 測試空串列狀態與統計 ===");
        taskList.printPendingTasks();
        taskList.printTaskStats();

        System.out.println("\n=== 2. 新增一般與緊急工作 ===");
        taskList.addNormalTask("T001", "撰寫 Java 課後作業報告");
        taskList.addNormalTask("T002", "整理 MySQL 資料庫結構");
        taskList.addUrgentTask("T000", "修復伺服器重大 Bug (緊急)");
        taskList.addNormalTask("T003", "準備期末簡報");

        System.out.println("\n=== 3. 檢視當前未完成工作與統計 ===");
        taskList.printPendingTasks();
        taskList.printTaskStats();

        System.out.println("\n=== 4. 測試標記完成工作 ===");
        taskList.completeTask("T000"); // 完成緊急工作
        taskList.completeTask("T002"); // 完成一般工作
        taskList.completeTask("T099"); // 測試找不到工作

        System.out.println("\n=== 5. 列出未完成工作與最新統計 ===");
        taskList.printPendingTasks();
        taskList.printTaskStats();

        System.out.println("\n=== 6. 測試刪除工作 (刪除頭節點與中間節點) ===");
        taskList.removeTask("T000"); // 刪除頭節點
        taskList.removeTask("T001"); // 刪除未完成的中間節點
        taskList.removeTask("T099"); // 刪除不存在節點

        System.out.println("\n=== 7. 最終狀態檢視 ===");
        taskList.printPendingTasks();
        taskList.printTaskStats();
    }
}