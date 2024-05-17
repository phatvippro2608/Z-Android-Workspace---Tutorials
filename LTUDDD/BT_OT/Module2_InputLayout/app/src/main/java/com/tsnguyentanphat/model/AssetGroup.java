package com.tsnguyentanphat.model;

public class AssetGroup {
    private int id;
    private String AssetGroupName;

    public AssetGroup() {
    }

    public AssetGroup(int id, String assetGroupName) {
        this.id = id;
        AssetGroupName = assetGroupName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAssetGroupName() {
        return AssetGroupName;
    }

    public void setAssetGroupName(String assetGroupName) {
        AssetGroupName = assetGroupName;
    }
}
