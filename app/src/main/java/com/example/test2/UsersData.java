package com.example.test2;

public class UsersData {
    private String userId;
    private String full_name;
    private String e_mail;
    private String gender;
    private String num;
    private String imageURL;

    public UsersData() {
    }

    public UsersData(String userId, String full_name, String e_mail, String gender, String num, String imageURL) {
        this.userId = userId;
        this.full_name = full_name;
        this.e_mail = e_mail;
        this.gender = gender;
        this.num = num;
        this.imageURL = imageURL;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
