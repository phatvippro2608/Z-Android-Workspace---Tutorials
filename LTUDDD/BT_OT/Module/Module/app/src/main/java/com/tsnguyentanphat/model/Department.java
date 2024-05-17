package com.tsnguyentanphat.model;

public class Department {
    private int id;
    private String DepartmentName;

    public Department() {
    }

    public Department(int id, String departmentName) {
        this.id = id;
        DepartmentName = departmentName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
    }
}
