package com.tsnguyentanphat.nguyentanphat.model;

public class AssetList {
    private String assetName;
    private String department;
    private String assetSN;

    public AssetList() {
    }

    public AssetList(String assetName, String department, String assetSN) {
        this.assetName = assetName;
        this.department = department;
        this.assetSN = assetSN;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAssetSN() {
        return assetSN;
    }

    public void setAssetSN(String assetSN) {
        this.assetSN = assetSN;
    }
}
