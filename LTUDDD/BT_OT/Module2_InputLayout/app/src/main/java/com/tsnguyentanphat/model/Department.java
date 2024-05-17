package com.tsnguyentanphat.model;

public class Department {
    private int ID;
    private String DepartmentName;

    public Department() {
    }

    public Department(int ID, String departmentName) {
        this.ID = ID;
        DepartmentName = departmentName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
    }
}
