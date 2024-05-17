package com.tsnguyentanphat.nguyentanphat.model;

public class EmployeeLogin {
    private int id;
    private String user;
    private String pass;
    private boolean isAdmin;

    public EmployeeLogin() {
    }

    public EmployeeLogin(int id, String user, String pass, boolean isAdmin) {
        this.id = id;
        this.user = user;
        this.pass = pass;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
