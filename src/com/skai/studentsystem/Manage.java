package com.skai.studentsystem;

public class Manage {
    private String userName;
    private String passWord;
    private String phoneNumber;
    private String idNumber;

    public Manage() {
    }

    public Manage(String userName, String passWord, String phoneNumber, String idNumber) {
        this.userName = userName;
        this.passWord = passWord;
        this.phoneNumber = phoneNumber;
        this.idNumber = idNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
}
