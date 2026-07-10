public class PricePrinter {
    public static void main(String[] args) {
        printItem("Apple", 30);
        printItem("Banana", 25);
    }

    public static void printItem(String itemName, int price) {
        System.out.println("Item: " + itemName + ", Price: $" + price);
    }
}