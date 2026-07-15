public class Rectangle {

    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = Math.max(width, 0.0);
        this.height = Math.max(height, 0.0);
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public boolean setWidth(double width) {
        if (width <= 0.0) {
            System.out.println("錯誤：寬度必須大於 0 ！");
            return false;
        }
        this.width = width;
        return true;
    }

    public boolean setHeight(double height) {
        if (height <= 0.0) {
            System.out.println("錯誤：高度必須大於 0 ！");
            return false;
        }
        this.height = height;
        return true;
    }

    public double calculateArea() {
        return this.width * this.height;
    }

    public double calculatePerimeter() {
        return 2 * (this.width + this.height);
    }

    public boolean isSquare() {
        if (this.width <= 0.0 || this.height <= 0.0) {
            return false;
        }
        return this.width == this.height;
    }

    @Override
    public String toString() {
        return String.format("矩形尺寸: %.2f x %.2f", width, height);
    }
}