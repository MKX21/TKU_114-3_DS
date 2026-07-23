package Equipment;

public class Equipment {
    private String id;
    private String name;
    private boolean isAvailable;

    public Equipment(String id, String name) {
        this.id = id;
        this.name = name;
        this.isAvailable = true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        String statusStr = isAvailable ? "可借用" : "已借出";
        return "設備代碼: " + id + " | 名稱: " + name + " | 狀態: " + statusStr;
    }
}