/*
【問題 1】編譯錯誤 (Syntax Error)
* 錯誤位置：第 21 行 (System.out.println("系統結束，年齡：" + age))
* 錯誤類型：編譯錯誤 (Syntax Error)
* 原因：該行程式碼尾端缺少 Java 必要的陳述式結束符號「分號 (;)」。
* 修正方式：在該行尾端補上分號，修改為：
  System.out.println("系統結束，年齡：" + age);

【問題 2】陣列越界錯誤 (ArrayIndexOutOfBoundsException)
* 錯誤位置：第 9 行 (for (int i = 0; i <= scores.length; i++))
* 錯誤類型：執行時期錯誤 (Runtime Error / Exception)
* 原因：迴圈終止條件使用「<=」。當 i 等於 scores.length (3) 時，
       scores[3] 已超出陣列索引範圍 (0 ~ 2)，導致系統崩潰。
* 修正方式：將終止條件修改為「小于 (<)」：
  for (int i = 0; i < scores.length; i++)

【問題 3】整數除法造成的邏輯錯誤
* 錯誤位置：第 13 行 (double average = total / scores.length;)
* 錯誤類型：邏輯錯誤 (Logical Error)
* 原因：total (int) 與 scores.length (int) 皆為整數，進行除法時會捨去小數點，
       算出整數 82，之後才隱式轉換為 double (82.00)，遺失了精確的小數值。
* 修正方式：將其中一個運算元強制轉型為 double，使其進行浮點數除法：
  double average = (double) total / scores.length;

【問題 4】Scanner 換行殘留問題
* 錯誤位置：第 18 行 (String command = sc.nextLine(); 之前)
* 錯誤類型：邏輯與輸入行為異常 (Behavioral Error)
* 原因：呼叫 sc.nextInt() 讀取整數後，留在緩衝區中的「換行字元 (\n)」未被讀取。
       隨後呼叫 sc.nextLine() 會直接讀取該換行字元，導致使用者還沒輸入指令程式就直接跳過。
* 修正方式：在讀取下一個字串前，先插入一個空的 sc.nextLine() 來消耗緩衝區中的換行字元：
  int age = sc.nextInt();
  sc.nextLine(); // 消耗換行符

【問題 5】字串比較錯誤
* 錯誤位置：第 20 行 (if (command == "exit"))
* 錯誤類型：邏輯錯誤 (Logical Error)
* 原因：在 Java 中，使用「==」比較字串比較的是兩者的「記憶體位址 (Reference)」，而非「字串內容」。
* 修正方式：應改用 equals() 方法比較字串內容：
  if ("exit".equals(command))

=========================================
   使用 Breakpoint 記錄錯誤發生時的重要變數值
=========================================
當程式執行至各中斷點 (Breakpoint) 時，關鍵變數之實際狀態如下：

1. 中斷點設定於第 10 行 (total += scores[i]; 內部)：
   * 當 i = 0 時 -> scores[0] = 80, total = 80
   * 當 i = 1 時 -> scores[1] = 75, total = 155
   * 當 i = 2 時 -> scores[2] = 92, total = 247
   * 當 i = 3 時 -> 觸發 ArrayIndexOutOfBoundsException 異常崩潰。

2. 中斷點設定於第 13 行 (計算平均數前)：
   * 變數狀態：total = 247, scores.length = 3
   * 計算結果比較：
     - 修正前：total / scores.length = 82 (整數除法，無小數點)
     - 修正後：(double) total / scores.length = 82.3333...

3. 中斷點設定於第 19 行 (輸入指令 command 前)：
   * 變數狀態：age = [使用者輸入的值]
   * 緩衝區狀態：
     - 修正前：command 直接被賦值為空字串 "" (因直接讀入 \n)
     - 修正後：sc.nextLine() 成功阻擋並等待使用者手動輸入 "exit"
*/

import java.util.Scanner;

public class DebuggingChallenge {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] scores = {80, 75, 92};
        int total = 0;

        for (int i = 0; i < scores.length; i++) {
            total += scores[i];
        }

        double average = (double) total / scores.length;
        System.out.printf("平均：%.2f%n", average);

        System.out.print("請輸入年齡：");
        int age = sc.nextInt();
        sc.nextLine(); 

        System.out.print("請輸入指令：");
        String command = sc.nextLine();

        if ("exit".equals(command)) {
            System.out.println("系統結束，年齡：" + age);
        }

        sc.close();
    }
}