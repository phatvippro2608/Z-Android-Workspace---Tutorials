package com.tsnguyentanphat.model;

public class AssetListView {
    private String AssetName;
    private String Department;
    private String AssetSN;

    public AssetListView() {
    }

    public AssetListView(String assetName, String department, String assetSN) {
        AssetName = assetName;
        Department = department;
        AssetSN = assetSN;
    }

    public String getAssetName() {
        return AssetName;
    }

    public void setAssetName(String assetName) {
        AssetName = assetName;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getAssetSN() {
        return AssetSN;
    }

    public void setAssetSN(String assetSN) {
        AssetSN = assetSN;
    }

    @Override
    public String toString() {
        return AssetName+"\n"+
                Department+"\n"+
                AssetSN;
    }
}
