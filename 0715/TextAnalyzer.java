import java.util.Scanner;

public class TextAnalyzer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        String input = getValidInput(scanner);

        System.out.println("原始字元數: " + input.length());

        String trimmedInput = input.trim();
        System.out.println("有效字元數 (trim後): " + trimmedInput.length());

        String[] words = splitIntoWords(trimmedInput);

        System.out.println("單字數量: " + words.length);

        int vowelCount = countVowels(trimmedInput);
        System.out.println("母音 (a, e, i, o, u) 總數: " + vowelCount);

        String longestWord = findLongestWord(words);
        System.out.println("最長單字: " + longestWord);

        System.out.print("請輸入要搜尋的關鍵字: ");
        String keyword = scanner.next();
        int keywordCount = countKeywordMatches(words, keyword);
        System.out.println("關鍵字 \"" + keyword + "\" 出現次數 (忽略大小寫): " + keywordCount);

        scanner.close();
    }

    public static String getValidInput(Scanner scanner) {
        String input;
        while (true) {
            System.out.print("請輸入一行非空白文字: ");
            input = scanner.nextLine();
            if (input != null && !input.trim().isEmpty()) {
                break;
            }
            System.out.println("錯誤：輸入不能為空或全空白，請重新輸入！");
        }
        return input;
    }

    public static String[] splitIntoWords(String text) {
        if (text == null || text.isEmpty()) {
            return new String[0];
        }
        return text.split("\\s+");
    }

    public static int countVowels(String text) {
        int count = 0;
        String lowerText = text.toLowerCase();
        for (int i = 0; i < lowerText.length(); i++) {
            char ch = lowerText.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                count++;
            }
        }
        return count;
    }

    public static String findLongestWord(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        String longest = words[0];
        for (String word : words) {
            if (word.length() > longest.length()) {
                longest = word;
            }
        }
        return longest;
    }

    public static int countKeywordMatches(String[] words, String keyword) {
        if (words == null || keyword == null) {
            return 0;
        }
        int count = 0;
        for (String word : words) {
            if (word.equalsIgnoreCase(keyword)) {
                count++;
            }
        }
        return count;
    }
}
