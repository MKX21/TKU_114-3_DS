package Task;

public class TaskNode {
    String taskId;
    String description;
    boolean isCompleted;
    TaskNode next;

    public TaskNode(String taskId, String description) {
        this.taskId = taskId;
        this.description = description;
        this.isCompleted = false;
        this.next = null;
    }
}
