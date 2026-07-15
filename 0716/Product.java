public class Product {

    private String name;
    private int price;
    private int stock;

    public Product(String name, int price, int stock) {
        if (name == null || name.trim().isEmpty()) {
            this.name = "未命名商品";
        } else {
            this.name = name.trim();
        }

        this.price = Math.max(price, 1);
        this.stock = Math.max(stock, 0);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public boolean setPrice(int price) {
        if (price <= 0) {
            return false;
        }
        this.price = price;
        return true;
    }

    public boolean restock(int amount) {
        if (amount <= 0) {
            return false;
        }
        this.stock += amount;
        return true;
    }

    public boolean sell(int amount) {
        if (amount <= 0 || amount > this.stock) {
            return false;
        }
        this.stock -= amount;
        return true;
    }

    public boolean isLowStock() {
        return this.stock < 5;
    }

    public int getInventoryValue() {
        return this.price * this.stock;
    }

    @Override
    public String toString() {
        return String.format("商品名稱: %-15s | 單價: NT$ %,6d | 庫存: %3d 件", name, price, stock);
    }
}
