package com.example.laplichbaotri;

public class AssetName {
    int ID;
    String AssetName;

    public AssetName(int ID, String assetName) {
        this.ID = ID;
        AssetName = assetName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getAssetName() {
        return AssetName;
    }

    public void setAssetName(String assetName) {
        AssetName = assetName;
    }

    @Override
    public String toString() {
        return this.AssetName;
    }
}
