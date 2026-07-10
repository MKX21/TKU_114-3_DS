public class SubtotalCalculator {
    public static void main(String[] args) {
        int result = calculateSubtotal(50, 3);
        System.out.println("Subtotal: " + result);
    }

    public static int calculateSubtotal(int price, int quantity) {
        return price * quantity;
    }
}
