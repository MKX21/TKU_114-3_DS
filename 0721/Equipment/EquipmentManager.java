package Equipment;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EquipmentManager {

    private static List<Equipment> equipmentList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            System.out.print("請選擇操作選項 (1-6): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addEquipment();
                    break;
                case "2":
                    searchById();
                    break;
                case "3":
                    borrowEquipment();
                    break;
                case "4":
                    returnEquipment();
                    break;
                case "5":
                    listAvailableEquipment();
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
        System.out.println("=== 設備管理系統 ===");
        System.out.println("1. 新增設備");
        System.out.println("2. 依代碼搜尋設備");
        System.out.println("3. 借出設備");
        System.out.println("4. 歸還設備");
        System.out.println("5. 列出可借用設備");
        System.out.println("6. 離開系統");
        System.out.println("====================");
    }

    private static void addEquipment() {
        System.out.print("請輸入設備代碼: ");
        String id = scanner.nextLine().trim();

        if (id.isEmpty()) {
            System.out.println("【錯誤】設備代碼不可為空白！");
            return;
        }

        if (findEquipmentById(id) != null) {
            System.out.println("【錯誤】設備代碼 「" + id + "」 已存在，代碼不可重複！");
            return;
        }

        System.out.print("請輸入設備名稱: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("【錯誤】設備名稱不可為空白！");
            return;
        }

        equipmentList.add(new Equipment(id, name));
        System.out.println("【成功】已成功新增設備: " + name + " (代碼: " + id + ")");
    }

    private static void searchById() {
        System.out.print("請輸入要搜尋的設備代碼: ");
        String id = scanner.nextLine().trim();

        if (id.isEmpty()) {
            System.out.println("【錯誤】設備代碼不可為空白！");
            return;
        }

        Equipment target = findEquipmentById(id);
        if (target != null) {
            System.out.println("【搜尋結果】" + target);
        } else {
            System.out.println("【找不到】找不到設備代碼: " + id);
        }
    }

    private static void borrowEquipment() {
        System.out.print("請輸入要借出的設備代碼: ");
        String id = scanner.nextLine().trim();

        Equipment target = findEquipmentById(id);

        if (target == null) {
            System.out.println("【找不到】找不到設備代碼: " + id);
            return;
        }

        if (!target.isAvailable()) {
            System.out.println("【失敗】設備 「" + target.getName() + "」 目前已被借出！");
        } else {
            target.setAvailable(false);
            System.out.println("【成功】已成功借出設備: " + target.getName());
        }
    }

    private static void returnEquipment() {
        System.out.print("請輸入要歸還的設備代碼: ");
        String id = scanner.nextLine().trim();

        Equipment target = findEquipmentById(id);

        if (target == null) {
            System.out.println("【找不到】找不到設備代碼: " + id);
            return;
        }

        if (target.isAvailable()) {
            System.out.println("【提示】設備 「" + target.getName() + "」 原本即為未借出狀態。");
        } else {
            target.setAvailable(true);
            System.out.println("【成功】已成功歸還設備: " + target.getName());
        }
    }

    private static void listAvailableEquipment() {
        List<Equipment> availableList = new ArrayList<>();
        for (Equipment e : equipmentList) {
            if (e.isAvailable()) {
                availableList.add(e);
            }
        }

        if (availableList.isEmpty()) {
            System.out.println("【提示】目前沒有可借用的設備。");
            return;
        }

        System.out.println("=== 目前可借用設備名單 (" + availableList.size() + " 筆) ===");
        for (Equipment e : availableList) {
            System.out.println(e);
        }
    }

    private static Equipment findEquipmentById(String id) {
        if (id.isEmpty()) {
            return null;
        }
        for (Equipment e : equipmentList) {
            if (e.getId().equalsIgnoreCase(id)) {
                return e;
            }
        }
        return null;
    }
}