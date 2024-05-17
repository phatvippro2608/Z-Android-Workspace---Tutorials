package com.tsnguyentanphat.model;

public class Employee {
    private int id;
    private boolean isAdmin;
    private String user;
    private String pass;

    public Employee() {
    }

    public Employee(int id, boolean isAdmin, String user, String pass) {
        this.id = id;
        this.isAdmin = isAdmin;
        this.user = user;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
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
}
