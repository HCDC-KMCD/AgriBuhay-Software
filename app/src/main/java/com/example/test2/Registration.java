package com.example.test2;

public class Registration {
    private String fullname;
//    private long phone;
    private String email;
    private String username;
    private String password;

    public Registration() {
    }

    public Registration(String fullname, String email, String username, String password) {
        this.fullname = fullname;
//        this.phone = phone;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

//    public long getPhone() {
//        return phone;
//    }

//    public void setPhone(long phone) {
//        this.phone = phone;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
