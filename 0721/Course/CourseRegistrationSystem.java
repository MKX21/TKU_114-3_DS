package Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CourseRegistrationSystem {

    private static List<Course> courseList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            System.out.print("請選擇操作選項 (1-7): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addCourse();
                    break;
                case "2":
                    enrollCourse();
                    break;
                case "3":
                    dropCourse();
                    break;
                case "4":
                    deleteCourse();
                    break;
                case "5":
                    searchCourse();
                    break;
                case "6":
                    printSummaryReport();
                    break;
                case "7":
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
        System.out.println("=== 選課管理系統 ===");
        System.out.println("1. 新增課程");
        System.out.println("2. 學生選課 (人數+1)");
        System.out.println("3. 學生退選 (人數-1)");
        System.out.println("4. 刪除課程");
        System.out.println("5. 搜尋課程 (代碼/名稱)");
        System.out.println("6. 輸出課程統計報告");
        System.out.println("7. 離開系統");
        System.out.println("====================");
    }

    private static void addCourse() {
        System.out.print("請輸入課程代碼: ");
        String code = scanner.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("【錯誤】課程代碼不可為空白！");
            return;
        }

        if (findCourseByCode(code) != null) {
            System.out.println("【錯誤】課程代碼 「" + code + "」 已存在，代碼不可重複！");
            return;
        }

        System.out.print("請輸入課程名稱: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("【錯誤】課程名稱不可為空白！");
            return;
        }

        int capacity = readPositiveInt("請輸入課程容量: ");
        if (capacity <= 0) return;

        courseList.add(new Course(code, name, capacity));
        System.out.println("【成功】已成功新增課程: " + name + " (代碼: " + code + ", 容量: " + capacity + ")");
    }

    private static void enrollCourse() {
        System.out.print("請輸入要選課的課程代碼: ");
        String code = scanner.nextLine().trim();

        Course course = findCourseByCode(code);

        if (course == null) {
            System.out.println("【找不到】無此課程代碼: " + code);
            return;
        }

        if (course.enroll()) {
            System.out.println("【成功】選課成功！「" + course.getName() + "」 當前人數: " + course.getEnrolled() + "/" + course.getCapacity());
        } else {
            System.out.println("【失敗】課程 「" + course.getName() + "」 人數已額滿 (" + course.getCapacity() + "人)，無法再加選！");
        }
    }

    private static void dropCourse() {
        System.out.print("請輸入要退選的課程代碼: ");
        String code = scanner.nextLine().trim();

        Course course = findCourseByCode(code);

        if (course == null) {
            System.out.println("【找不到】無此課程代碼: " + code);
            return;
        }

        if (course.drop()) {
            System.out.println("【成功】退選成功！「" + course.getName() + "」 當前人數: " + course.getEnrolled() + "/" + course.getCapacity());
        } else {
            System.out.println("【提示】課程 「" + course.getName() + "」 目前選課人數為 0，無法再退選！");
        }
    }

    private static void deleteCourse() {
        System.out.print("請輸入要刪除的課程代碼: ");
        String code = scanner.nextLine().trim();

        Course course = findCourseByCode(code);

        if (course != null) {
            courseList.remove(course);
            System.out.println("【成功】已成功刪除課程: " + course.getName());
        } else {
            System.out.println("【找不到】無此課程代碼: " + code);
        }
    }

    private static void searchCourse() {
        System.out.print("請輸入要搜尋的課程代碼或名稱: ");
        String keyword = scanner.nextLine().trim();

        if (keyword.isEmpty()) {
            System.out.println("【錯誤】搜尋關鍵字不可為空白！");
            return;
        }

        boolean found = false;
        for (Course course : courseList) {
            if (course.getCode().equalsIgnoreCase(keyword) || course.getName().equalsIgnoreCase(keyword)) {
                System.out.println("【搜尋結果】" + course);
                found = true;
            }
        }

        if (!found) {
            System.out.println("【找不到】找不到符合 「" + keyword + "」 的課程。");
        }
    }

    private static void printSummaryReport() {
        if (courseList.isEmpty()) {
            System.out.println("【提示】目前系統中無任何課程資料。");
            return;
        }

        int totalCourses = courseList.size();
        int totalEnrolled = 0;
        List<Course> fullCourses = new ArrayList<>();

        for (Course course : courseList) {
            totalEnrolled += course.getEnrolled();
            if (course.isFull()) {
                fullCourses.add(course);
            }
        }

        System.out.println("=== 課程統計報告 ===");
        System.out.println("總課程數\t\t: " + totalCourses + " 門");
        System.out.println("總選課人次\t: " + totalEnrolled + " 人次");
        System.out.println("額滿課程數量\t: " + fullCourses.size() + " 門");
        System.out.println("--------------------");

        if (fullCourses.isEmpty()) {
            System.out.println("【額滿課程清單】: 無");
        } else {
            System.out.println("【額滿課程清單】:");
            for (Course fc : fullCourses) {
                System.out.println("  - [" + fc.getCode() + "] " + fc.getName() + " (容量: " + fc.getCapacity() + " 人)");
            }
        }
    }

    private static Course findCourseByCode(String code) {
        if (code.isEmpty()) {
            return null;
        }
        for (Course course : courseList) {
            if (course.getCode().equalsIgnoreCase(code)) {
                return course;
            }
        }
        return null;
    }

    private static int readPositiveInt(String prompt) {
        System.out.print(prompt);
        if (!scanner.hasNextInt()) {
            System.out.println("【錯誤】請輸入有效的整數數字！");
            scanner.nextLine();
            return -1;
        }
        int val = scanner.nextInt();
        scanner.nextLine();
        if (val <= 0) {
            System.out.println("【錯誤】數值必須大於 0！");
            return -1;
        }
        return val;
    }
}