public class TemperatureLevel {
    public static void main(String[] args) {
        double temperature = 29;

        if (temperature < 15) {
            System.out.println("temperature level: Cold");
        } else if (temperature < 28) {
            System.out.println("temperature level: Comfortable");
        } else {
            System.out.println("temperature level: Hot");
        }
    }
}   
