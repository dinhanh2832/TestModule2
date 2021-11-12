package model;

public class BookPhone {
    private String name;
    private String phoneNumber;
    private String dateOfBirth;
    private String address;
    private String gentle;
    private String group;


    public BookPhone(String name, String phoneNumber, String dateOfBirth, String address, String gentle, String group) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.gentle = gentle;
        this.group = group;
    }

    public BookPhone() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGentle() {
        return gentle;
    }

    public void setGentle(String gentle) {
        this.gentle = gentle;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Tên: " + name +
                ", Số điện thoại: " + phoneNumber +
                ", Địa chỉ: " + address +
                ", Giới tính: " + gentle +
                ", Nhóm: " + group ;
    }
}
