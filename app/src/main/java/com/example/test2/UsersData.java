package com.example.test2;

public class UsersData {
    private String userId;
    private String fullname;
    private String email;
    private String gender;
    private String phone;
    private String imageURL;

    public UsersData() {
    }

    public UsersData(String userId, String fullname, String email, String gender, String phone, String imageURL) {
        this.userId = userId;
        this.fullname = fullname;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.imageURL = imageURL;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
