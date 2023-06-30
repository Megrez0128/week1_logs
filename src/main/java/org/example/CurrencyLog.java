package org.example;

public class CurrencyLog {
    private final int iZoneAreaID;    //注册服ID，分区服场景使用。其他场景时填写0
    private final String vUserID; //TODO:用户ID
    private final String vRoleID; //TODO:玩家角色ID
    private final int changeValue;    //TODO:修改值
    private final int changeType; //TODO:CurrencyChangeType {1:增加, 2:减少}
    private final int oldValue;   //旧值
    private final int newValue;   //新值 newValue = oldValue + changeValue * (changeType == 1 ? 1 : -1)
    private final int realChangeValue;    //实际变化值 realChangeValue = changeValue * (changeType == 1 ? 1 : -1)
    private final int mainReason; //TODO:货币修改主原因

    public CurrencyLog(int iZoneAreaID, String vUserID, String vRoleID, int changeValue, int changeType, int oldValue, int newValue, int realChangeValue, int mainReason) {
        this.iZoneAreaID = iZoneAreaID;
        this.vUserID = vUserID;
        this.vRoleID = vRoleID;
        this.changeValue = changeValue;
        this.changeType = changeType;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.realChangeValue = realChangeValue;
        this.mainReason = mainReason;
    }
    //重写toString方法，方便打印日志    
    @Override
    public String toString(){
        return String.format("iZoneAreaID:%d,vUserID:%s,vRoleID:%s,changeValue:%d,changeType:%d,oldValue:%d,newValue:%d,realChangeValue:%d,mainReason:%d",
        iZoneAreaID,vUserID,vRoleID,changeValue,changeType,oldValue,newValue,realChangeValue,mainReason);
    }

    public int getiZoneAreaID() {
        return iZoneAreaID;
    }

    public String getvUserID() {
        return vUserID;
    }

    public String getvRoleID() {
        return vRoleID;
    }

    public int getChangeValue() {
        return changeValue;
    }

    public int getChangeType() { return changeType; }

    public int getMainReason() {
        return mainReason;
    }

}