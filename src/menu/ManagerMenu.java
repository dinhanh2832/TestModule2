package menu;

import model.BookPhone;
import service.ManagerBookPhone;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManagerMenu {
    private final ManagerBookPhone list = ManagerBookPhone.getInstance();
    private static final String PHONE_NUMBER = "^0[9,8,7][0-9]{8}$";

    public ManagerMenu() throws Exception {
    }

    public void showMenu() {
        System.out.println("---* CHƯƠNG TRÌNH QUẢN LÝ DANH BẠ *---");
        System.out.println("Chọn chức năng theo số(để tiếp tục)");
        System.out.println("1. Xem danh sách ");
        System.out.println("2. Thêm mới ");
        System.out.println("3. Cập nhật ");
        System.out.println("4. Xóa ");
        System.out.println("5. Tìm kiếm ");
        System.out.println("6. Đọc từ File ");
        System.out.println("7. Ghi vào File ");
        System.out.println("8. Thoát");
        System.out.println("Chọn chức năng: ");
    }

    public void watchList() {
        list.print();
    }

    public boolean validate(String content) {
        Pattern p1 = Pattern.compile(PHONE_NUMBER);
        Matcher m1 = p1.matcher(content);
        return m1.matches();
    }

    public BookPhone Input() {
        int choice = -1;
        String name = "";
        String number = "";
        String date = "";
        String address = "";
        String gentle = "";
        String group = "";
        while (choice == -1) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Nhập tên: ");
            name = sc.nextLine();
            System.out.println("Nhập số điện thoại: ");
            number = sc.nextLine();
            if (validate(number)) {
                System.out.println("Nhập ngày sinh: ");
                date = sc.nextLine();
                System.out.println("Nhập địa chỉ: ");
                address = sc.nextLine();
                System.out.println("Nhập giới tính: ");
                gentle = sc.nextLine();
                System.out.println("Nhập nhóm: ");
                group = sc.nextLine();
                choice = -2;
            } else System.out.println("Sai định dạng điện thoại!");
        }
        return new BookPhone(name, number, date, address, gentle, group);
    }

    public void addNew() {
        list.add(Input());
        System.out.println("Thêm thành công!");
    }

    public void update(String phone) {
        list.update(phone);
    }

    public void delete(String phone) {
        Scanner sc = new Scanner(System.in);
        int index = list.findIndexByPhone(phone);
        BookPhone bookPhone = list.getListBookPhone().get(index);
        boolean check = bookPhone.getPhoneNumber().equals(phone);
        if (check) {
            System.out.println("Nhập Y để xóa!");
            String y = sc.nextLine();
            if (y.equals("y")) {
                list.deleteByPhone(phone);
            }
        } else {
            System.out.println("không tìm được danh bạ với số điện thoại trên !");
        }
    }

    public void search() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập số điện thoại cần tìm: ");
        sc.nextLine();
        String phone = sc.nextLine();
        System.out.println(list.getBookPhone(phone));
    }

    public void writeFile() throws Exception {
        list.writeFile();
    }

    public void readFile() throws Exception {
        for (int i = 0; i < list.readFile().size(); i++) {
            System.out.println(list.readFile().get(i));
        }
    }

    public void start() throws Exception {
        Scanner sc = new Scanner(System.in);
        int choice = -1;
        while (true) {
            showMenu();
            while (choice == -1) {
                try {
                    choice = sc.nextInt();
                } catch (Exception e) {
                    sc.nextLine();
                }
            }
            switch (choice) {
                case 1 -> watchList();
                case 2 -> addNew();
                case 3 -> {
                    System.out.println("Nhập số điện thoại cần sửa: ");
                    sc.nextLine();
                    String phone = sc.nextLine();
                    if (phone.equals("")) {
                        start();
                    } else update(phone);
                }
                case 4 -> {
                    System.out.println("Nhập số điện thoại cần xóa: ");
                    sc.nextLine();
                    String phone = sc.nextLine();
                    if (!phone.equals("")) {
                        delete(phone);
                    } else {
                        start();
                    }
                }
                case 5 -> search();
                case 6 -> readFile();
                case 7 -> {
                    writeFile();
                    System.out.println("Ghi thành công !");
                }
                case 8 -> System.exit(0);
                default -> System.out.println("No choice!");
            }
            choice = -1;
        }
    }

}
