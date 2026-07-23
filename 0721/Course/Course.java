package Course;

public class Course {
    private String code;
    private String name;
    private int capacity;
    private int enrolled;

    public Course(String code, String name, int capacity) {
        this.code = code;
        this.name = name;
        this.capacity = capacity;
        this.enrolled = 0;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getEnrolled() {
        return enrolled;
    }

    public void setEnrolled(int enrolled) {
        this.enrolled = enrolled;
    }

    public boolean isFull() {
        return enrolled >= capacity;
    }

    public boolean enroll() {
        if (isFull()) {
            return false;
        }
        enrolled++;
        return true;
    }

    public boolean drop() {
        if (enrolled <= 0) {
            return false;
        }
        enrolled--;
        return true;
    }

    @Override
    public String toString() {
        String status = isFull() ? "【已額滿】" : "【可選課】";
        return "課程代碼: " + code + " | 名稱: " + name + " | 人數: " + enrolled + "/" + capacity + " " + status;
    }
}