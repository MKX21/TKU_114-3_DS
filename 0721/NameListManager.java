import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NameListManager {

    private static List<String> nameList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            System.out.print("請選擇操作選項 (1-6): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addName();
                    break;
                case "2":
                    searchName();
                    break;
                case "3":
                    updateName();
                    break;
                case "4":
                    deleteName();
                    break;
                case "5":
                    listAllNames();
                    break;
                case "6":
                    System.out.println("系統已結束，謝謝使用！");
                    scanner.close();
                    return;
                default:
                    System.out.println("【錯誤】無效的選項，請重新選擇！");
            }
            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("=== 名單管理系統 ===");
        System.out.println("1. 新增姓名");
        System.out.println("2. 搜尋姓名");
        System.out.println("3. 修改姓名");
        System.out.println("4. 刪除姓名");
        System.out.println("5. 列出全部姓名");
        System.out.println("6. 離開系統");
        System.out.println("====================");
    }

    private static void addName() {
        System.out.print("請輸入要新增的姓名: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("【錯誤】不得加入空白姓名！");
            return;
        }

        nameList.add(name);
        System.out.println("【成功】已成功新增姓名: " + name);
    }

    private static void searchName() {
        System.out.print("請輸入要搜尋的姓名: ");
        String keyword = scanner.nextLine().trim();

        if (keyword.isEmpty()) {
            System.out.println("【錯誤】搜尋關鍵字不可為空白！");
            return;
        }

        boolean found = false;
        for (int i = 0; i < nameList.size(); i++) {
            if (nameList.get(i).equalsIgnoreCase(keyword)) {
                System.out.println("【找到結果】位置索引 [" + i + "] : " + nameList.get(i));
                found = true;
            }
        }

        if (!found) {
            System.out.println("【找不到】找不到姓名: " + keyword);
        }
    }

    private static void updateName() {
        System.out.print("請輸入要修改的舊姓名: ");
        String oldName = scanner.nextLine().trim();

        int targetIndex = findIndexIgnoreCase(oldName);

        if (targetIndex == -1) {
            System.out.println("【找不到】找不到欲修改的姓名: " + oldName);
            return;
        }

        System.out.print("請輸入新的姓名: ");
        String newName = scanner.nextLine().trim();

        if (newName.isEmpty()) {
            System.out.println("【錯誤】新姓名不可為空白，修改失敗！");
            return;
        }

        String originalName = nameList.get(targetIndex);
        nameList.set(targetIndex, newName);
        System.out.println("【成功】已將 「" + originalName + "」 修改為 「" + newName + "」");
    }

    private static void deleteName() {
        System.out.print("請輸入要刪除的姓名: ");
        String targetName = scanner.nextLine().trim();

        int targetIndex = findIndexIgnoreCase(targetName);

        if (targetIndex != -1) {
            String removedName = nameList.remove(targetIndex);
            System.out.println("【成功】已成功刪除姓名: " + removedName);
        } else {
            System.out.println("【找不到】找不到欲刪除的姓名: " + targetName);
        }
    }

    private static void listAllNames() {
        if (nameList.isEmpty()) {
            System.out.println("【提示】目前名單內無任何資料。");
            return;
        }

        System.out.println("=== 目前所有名單 (" + nameList.size() + " 筆) ===");
        for (int i = 0; i < nameList.size(); i++) {
            System.out.println((i + 1) + ". " + nameList.get(i));
        }
    }

    private static int findIndexIgnoreCase(String target) {
        if (target.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < nameList.size(); i++) {
            if (nameList.get(i).equalsIgnoreCase(target)) {
                return i;
            }
        }
        return -1;
    }
}