package Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactBookSystem {

    private static List<Contact> contactList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            System.out.print("請選擇操作選項 (1-6): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addContact();
                    break;
                case "2":
                    searchContact();
                    break;
                case "3":
                    updateContactPhone();
                    break;
                case "4":
                    deleteContact();
                    break;
                case "5":
                    listAllContacts();
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
        System.out.println("=== 聯絡人管理系統 ===");
        System.out.println("1. 新增聯絡人");
        System.out.println("2. 搜尋聯絡人 (代碼/姓名)");
        System.out.println("3. 修改聯絡人電話");
        System.out.println("4. 刪除聯絡人");
        System.out.println("5. 列出完整清單");
        System.out.println("6. 離開系統");
        System.out.println("======================");
    }

    private static void addContact() {
        System.out.print("請輸入聯絡人代碼: ");
        String id = scanner.nextLine().trim();

        if (id.isEmpty()) {
            System.out.println("【錯誤】代碼不可為空白！");
            return;
        }

        if (findContactById(id) != null) {
            System.out.println("【錯誤】代碼 「" + id + "」 已存在，代碼不可重複！");
            return;
        }

        System.out.print("請輸入姓名: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("【錯誤】空白姓名不可加入！");
            return;
        }

        System.out.print("請輸入電話: ");
        String phone = scanner.nextLine().trim();

        System.out.print("請輸入電子郵件: ");
        String email = scanner.nextLine().trim();

        contactList.add(new Contact(id, name, phone, email));
        System.out.println("【成功】已成功新增聯絡人: " + name);
    }

    private static void searchContact() {
        System.out.print("請輸入要搜尋的代碼或姓名: ");
        String keyword = scanner.nextLine().trim();

        if (keyword.isEmpty()) {
            System.out.println("【錯誤】搜尋關鍵字不可為空白！");
            return;
        }

        boolean found = false;
        for (Contact contact : contactList) {
            if (contact.getId().equalsIgnoreCase(keyword) || contact.getName().equalsIgnoreCase(keyword)) {
                System.out.println("【找到結果】" + contact);
                found = true;
            }
        }

        if (!found) {
            System.out.println("【找不到】找不到符合 「" + keyword + "」 的聯絡人。");
        }
    }

    private static void updateContactPhone() {
        System.out.print("請輸入要修改電話的聯絡人代碼: ");
        String id = scanner.nextLine().trim();

        Contact target = findContactById(id);

        if (target == null) {
            System.out.println("【找不到】找不到代碼為 「" + id + "」 的聯絡人！");
            return;
        }

        System.out.println("當前聯絡人資訊: " + target);
        System.out.print("請輸入新的電話號碼: ");
        String newPhone = scanner.nextLine().trim();

        if (newPhone.isEmpty()) {
            System.out.println("【錯誤】電話號碼不可為空白！");
            return;
        }

        target.setPhone(newPhone);
        System.out.println("【成功】已更新聯絡人 「" + target.getName() + "」 的電話號碼為: " + newPhone);
    }

    private static void deleteContact() {
        System.out.print("請輸入要刪除的聯絡人代碼: ");
        String id = scanner.nextLine().trim();

        Contact target = findContactById(id);

        if (target != null) {
            contactList.remove(target);
            System.out.println("【成功】已成功刪除聯絡人: " + target.getName());
        } else {
            System.out.println("【找不到】找不到代碼為 「" + id + "」 的聯絡人！");
        }
    }

    private static void listAllContacts() {
        if (contactList.isEmpty()) {
            System.out.println("【提示】目前通訊錄中沒有任何聯絡人資料。");
            return;
        }

        System.out.println("=== 完整聯絡人清單 (" + contactList.size() + " 筆) ===");
        for (Contact contact : contactList) {
            System.out.println(contact);
        }
    }

    private static Contact findContactById(String id) {
        if (id.isEmpty()) {
            return null;
        }
        for (Contact contact : contactList) {
            if (contact.getId().equalsIgnoreCase(id)) {
                return contact;
            }
        }
        return null;
    }
}