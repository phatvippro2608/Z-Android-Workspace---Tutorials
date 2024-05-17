package com.tsnguyentanphat.model;

public class AssetList {
    private String assetName;
    private String assetSN;
    private String department;

    public AssetList() {
    }

    public AssetList(String assetName, String assetSN, String department) {
        this.assetName = assetName;
        this.assetSN = assetSN;
        this.department = department;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getAssetSN() {
        return assetSN;
    }

    public void setAssetSN(String assetSN) {
        this.assetSN = assetSN;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
