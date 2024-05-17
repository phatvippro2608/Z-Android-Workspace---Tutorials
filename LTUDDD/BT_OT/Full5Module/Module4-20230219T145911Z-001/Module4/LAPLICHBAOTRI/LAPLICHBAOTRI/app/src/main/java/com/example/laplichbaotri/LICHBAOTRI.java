package com.example.laplichbaotri;

public class LICHBAOTRI {
    String LongMaintenancesID,AssetID,AssetName,AssetSN,TaskName,ScheduleType;
    int isComplete;

    public LICHBAOTRI(String longMaintenancesID, String assetID, String assetName, String assetSN, String taskName, String scheduleType, int isComplete) {
        LongMaintenancesID = longMaintenancesID;
        AssetID = assetID;
        AssetName = assetName;
        AssetSN = assetSN;
        TaskName = taskName;
        ScheduleType = scheduleType;
        this.isComplete = isComplete;
    }

    public String getLongMaintenancesID() {
        return LongMaintenancesID;
    }

    public void setLongMaintenancesID(String longMaintenancesID) {
        LongMaintenancesID = longMaintenancesID;
    }

    public String getAssetID() {
        return AssetID;
    }

    public void setAssetID(String assetID) {
        AssetID = assetID;
    }

    public String getAssetName() {
        return AssetName;
    }

    public void setAssetName(String assetName) {
        AssetName = assetName;
    }

    public String getAssetSN() {
        return AssetSN;
    }

    public void setAssetSN(String assetSN) {
        AssetSN = assetSN;
    }

    public String getTaskName() {
        return TaskName;
    }

    public void setTaskName(String taskName) {
        TaskName = taskName;
    }

    public String getScheduleType() {
        return ScheduleType;
    }

    public void setScheduleType(String scheduleType) {
        ScheduleType = scheduleType;
    }

    public int getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(int isComplete) {
        this.isComplete = isComplete;
    }
}
