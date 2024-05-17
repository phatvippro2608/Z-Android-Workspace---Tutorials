package com.tsnguyentanphat.model;

import androidx.annotation.NonNull;

public class AssetList {
    String AssetName;
    String DepartmentName;
    String AssetSN;

    public AssetList() {
    }

    public AssetList(String assetName, String departmentName, String assetSN) {
        AssetName = assetName;
        DepartmentName = departmentName;
        AssetSN = assetSN;
    }

    public String getAssetName() {
        return AssetName;
    }

    public void setAssetName(String assetName) {
        AssetName = assetName;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
    }

    public String getAssetSN() {
        return AssetSN;
    }

    public void setAssetSN(String assetSN) {
        AssetSN = assetSN;
    }

    @NonNull
    @Override
    public String toString() {
        return AssetName+"\n"+
                DepartmentName+"\n"+
                AssetSN;
    }
}
