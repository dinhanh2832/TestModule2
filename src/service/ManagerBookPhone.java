package service;

import model.BookPhone;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManagerBookPhone implements Manager<BookPhone> {
    private static ManagerBookPhone instance = null;
    static {
        try {
            instance = new ManagerBookPhone();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ArrayList<BookPhone> listBookPhone = new ArrayList<>();

    public ArrayList<BookPhone> getListBookPhone() {
        return listBookPhone;
    }

    public static ManagerBookPhone getInstance(){
        return instance;
    }
    public ManagerBookPhone() throws Exception {
        listBookPhone.addAll(readFile());
    }

    public void setListBookPhone(ArrayList<BookPhone> listBookPhone) {
        this.listBookPhone = listBookPhone;
    }

    @Override
    public int findIndexByPhone(String phone) {
        for (int i = 0; i < listBookPhone.size(); i++) {
            if (listBookPhone.get(i).getPhoneNumber().equals(phone)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void add(BookPhone bookPhone) {
        listBookPhone.add(bookPhone);
    }

    @Override
    public void update(String phone) {
        int index = findIndexByPhone(phone);
        BookPhone bookPhone1 = getListBookPhone().get(index);
        boolean check = bookPhone1.getPhoneNumber().equals(phone);
        if(check){
            Scanner sc = new Scanner(System.in);
            System.out.println("Nhập tên: ");
            String name = sc.nextLine();
            bookPhone1.setName(name);
            System.out.println("Nhập vào giới tính:");
            String gentle = sc.nextLine();
            bookPhone1.setGentle(gentle);
            System.out.println("Nhập vào địa chỉ:");
            String address = sc.nextLine();
            bookPhone1.setAddress(address);
            System.out.println("Nhập vào ngày sinh:");
            String day = sc.nextLine();
            bookPhone1.setDateOfBirth(day);
            System.out.println("Nhập vào nhóm danh bạ:");
            String group = sc.nextLine();
            bookPhone1.setGroup(group);
        } else System.out.println("Không tìm được danh bạ với số điện thoại trên !");
    }

    @Override
    public void deleteByPhone(String phone) {
        int index = findIndexByPhone(phone);
        listBookPhone.remove(index);
    }

    @Override
    public void print() {
        for (int i = 1; i < getListBookPhone().size() + 1; i++) {
            System.out.println(getListBookPhone().get(i - 1));
            if (i % 5 == 0) {
                System.out.println("Nhấn enter để xem!");
                Scanner sc = new Scanner(System.in);
                String enter = sc.nextLine();
            }
        }
    }

    @Override
    public List<BookPhone> readFile() throws Exception {
        List<BookPhone> bookPhoneList = new ArrayList<>();
        BufferedReader bFile1 = new BufferedReader(new FileReader("contacts.csv"));
        bFile1.readLine();
        String line;
        while ((line = bFile1.readLine()) != null) {
            String[] list = line.split(",");
            String name = list[0];
            String phoneNumber = list[1];
            String dateOfBirth = list[2];
            String address = list[3];
            String gentle = list[4];
            String group = list[5];
            BookPhone bookPhone = new BookPhone(name, phoneNumber, dateOfBirth, address, gentle, group);
            bookPhoneList.add(bookPhone);
        }
        return bookPhoneList;
    }

    @Override
    public void writeFile() throws Exception {
        BufferedWriter bFile = new BufferedWriter(new FileWriter("contacts.csv"));
        bFile.write("Tên , Số điện thoại , Ngày sinh , Địa chỉ , Giới tính , Nhóm ");
        for (BookPhone bookPhone : listBookPhone) {
            bFile.write(
                    "\n" + bookPhone.getName() + "," +
                            bookPhone.getPhoneNumber() + "," +
                            bookPhone.getDateOfBirth() + "," +
                            bookPhone.getAddress() + "," +
                            bookPhone.getGentle() + "," +
                            bookPhone.getGroup()
            );
        }
        bFile.close();
    }

    @Override
    public BookPhone getBookPhone(String phone) throws Exception {
        for (BookPhone bookPhone : readFile()) {
            if (bookPhone.getPhoneNumber().equals(phone)) {
                return bookPhone;
            }
        }
        return null;
    }
}
