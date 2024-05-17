package com.tsnguyentanphat.model;

import java.util.Date;

public class Asset {
    private int AssetID;
    private String AssetSN;
    private String AssetName;
    private int DepartmentLocationID;
    private int EmployeeID;
    private int AssetGroupID;
    private String Description;
    private Date WarrantyDate;

    public Asset() {
    }

    public Asset(int assetID, String assetSN, String assetName, int departmentLocationID, int employeeID, int assetGroupID, String description, Date warrantyDate) {
        AssetID = assetID;
        AssetSN = assetSN;
        AssetName = assetName;
        DepartmentLocationID = departmentLocationID;
        EmployeeID = employeeID;
        AssetGroupID = assetGroupID;
        Description = description;
        WarrantyDate = warrantyDate;
    }

    public int getAssetID() {
        return AssetID;
    }

    public void setAssetID(int assetID) {
        AssetID = assetID;
    }

    public String getAssetSN() {
        return AssetSN;
    }

    public void setAssetSN(String assetSN) {
        AssetSN = assetSN;
    }

    public String getAssetName() {
        return AssetName;
    }

    public void setAssetName(String assetName) {
        AssetName = assetName;
    }

    public int getDepartmentLocationID() {
        return DepartmentLocationID;
    }

    public void setDepartmentLocationID(int departmentLocationID) {
        DepartmentLocationID = departmentLocationID;
    }

    public int getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(int employeeID) {
        EmployeeID = employeeID;
    }

    public int getAssetGroupID() {
        return AssetGroupID;
    }

    public void setAssetGroupID(int assetGroupID) {
        AssetGroupID = assetGroupID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Date getWarrantyDate() {
        return WarrantyDate;
    }

    public void setWarrantyDate(Date warrantyDate) {
        WarrantyDate = warrantyDate;
    }
}
