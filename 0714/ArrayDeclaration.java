public class ArrayDeclaration {
    public static void main(String[] args) {
        int[] scores = {80, 75, 92, 68, 88};
        String[] products = {"Keyboard", "Mouse", "Monitor"};
        
        System.out.println("成績陣列長度：" + scores.length);
        System.out.println("商品陣列長度：" + products.length);
        System.out.println("成績最後一筆是：" + scores[scores.length - 1]);
    }
}

