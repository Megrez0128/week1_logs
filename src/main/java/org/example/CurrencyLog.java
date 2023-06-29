package org.example;
import java.time.LocalDateTime;

public class CurrencyLog {
    private String gameSvrId;
    private LocalDateTime dtEventTime;   //游戏事件的时间
    private String vGameAppid;  //游戏APPID
    private int platID; //ios 0/android 1
    private int iZoneAreaID;    //注册服ID，分区服场景使用。其他场景时填写0
    private int tempSvrId;  //临时服务器编号。非临时服期间填写0 TODO
    private String vOpenID; //用户OPENID号
    private String vUserID; //TODO:用户ID
    private String vRoleID; //TODO:玩家角色ID
    private int iLevel; //等级
    private int iVipLevel;  //VIP等级

    private int currencyType;   //货币类型
    private int changeValue;    //TODO:修改值 
    private int changeType; //TODO:CurrencyChangeType {1:增加, 2:减少}
    private int oldValue;   //旧值
    private int newValue;   //新值 newValue = oldValue + changeValue * (changeType == 1 ? 1 : -1)
    // <entry name="reasons" type="string" size="256" desc="变化原因(废弃)"/>
    private int realChangeValue;    //实际变化值 realChangeValue = changeValue * (changeType == 1 ? 1 : -1)
    private int mainReason; //TODO:货币修改主原因
    private long subReason; //SubReason
    private int reason2;    //道具流动一级原因2
    private long subReason2;    //道具流动二级原因2
    private int reason3;    //道具流动一级原因3
    private long subReason3;    //道具流动二级原因3
    private int reason4;    //道具流动一级原因4
    private long subReason4;    //道具流动二级原因4
    private int reason5;    //道具流动一级原因5
    private long subReason5;    //道具流动二级原因5
    private long sequence;  //操作序列号，用于关联一次购买产生多条不同类型的货币日志

    public CurrencyLog(String gameSvrId, LocalDateTime dtEventTime, String vGameAppid, int platID, int iZoneAreaID, int tempSvrId, String vOpenID, String vUserID, String vRoleID, int iLevel, int iVipLevel, int currencyType, int changeValue, int changeType, int oldValue, int newValue, int realChangeValue, int mainReason, long subReason, int reason2, long subReason2, int reason3, long subReason3, int reason4, long subReason4, int reason5, long subReason5, long sequence) {
        this.gameSvrId = gameSvrId;
        this.dtEventTime = dtEventTime;
        this.vGameAppid = vGameAppid;
        this.platID = platID;
        this.iZoneAreaID = iZoneAreaID;
        this.tempSvrId = tempSvrId;
        this.vOpenID = vOpenID;
        this.vUserID = vUserID;
        this.vRoleID = vRoleID;
        this.iLevel = iLevel;
        this.iVipLevel = iVipLevel;
        this.currencyType = currencyType;
        this.changeValue = changeValue;
        this.changeType = changeType;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.realChangeValue = realChangeValue;
        this.mainReason = mainReason;
        this.subReason = subReason;
        this.reason2 = reason2;
        this.subReason2 = subReason2;
        this.reason3 = reason3;
        this.subReason3 = subReason3;
        this.reason4 = reason4;
        this.subReason4 = subReason4;
        this.reason5 = reason5;
        this.subReason5 = subReason5;
        this.sequence = sequence;
    }
    //重写toString方法，方便打印日志    
    @Override
    public String toString(){
        return String.format("gameSvrId:%s,dtEventTime:%s,vGameAppid:%s,platID:%d,iZoneAreaID:%d,tempSvrId:%d,vOpenID:%s,vUserID:%s,vRoleID:%s,iLevel:%d,iVipLevel:%d,currencyType:%d,changeValue:%d,changeType:%d,oldValue:%d,newValue:%d,realChangeValue:%d,mainReason:%d,subReason:%d,reason2:%d,subReason2:%d,reason3:%d,subReason3:%d,reason4:%d,subReason4:%d,reason5:%d,subReason5:%d,sequence:%d",
        gameSvrId,dtEventTime,vGameAppid,platID,iZoneAreaID,tempSvrId,vOpenID,vUserID,vRoleID,iLevel,iVipLevel,currencyType,changeValue,changeType,oldValue,newValue,realChangeValue,mainReason,subReason,reason2,subReason2,reason3,subReason3,reason4,subReason4,reason5,subReason5,sequence);
    }
    //TODO:之后再按要求的格式输出
    public String toTLOG(){
        return String.format("Currency|gameSvrId:%s|dtEventTime:%s|vGameAppid:%s|platID:%d|iZoneAreaID:%d|tempSvrId:%d|vOpenID:%s|vUserID:%s|vRoleID:%s|iLevel:%d|iVipLevel:%d|currencyType:%d|changeValue:%d|changeType:%d|oldValue:%d|newValue:%d|realChangeValue:%d|mainReason:%d|subReason:%d|reason2:%d|subReason2:%d|reason3:%d|subReason3:%d|reason4:%d|subReason4:%d|reason5:%d|subReason5:%d|sequence:%d",
        gameSvrId,dtEventTime,vGameAppid,platID,iZoneAreaID,tempSvrId,vOpenID,vUserID,vRoleID,iLevel,iVipLevel,currencyType,changeValue,changeType,oldValue,newValue,realChangeValue,mainReason,subReason,reason2,subReason2,reason3,subReason3,reason4,subReason4,reason5,subReason5,sequence);
    }
    public String getGameSvrId() {
        return gameSvrId;
    }

    public LocalDateTime getDtEventTime() {
        return dtEventTime;
    }

    public String getvGameAppid() {
        return vGameAppid;
    }

    public int getPlatID() {
        return platID;
    }

    public int getiZoneAreaID() {
        return iZoneAreaID;
    }

    public int getTempSvrId() {
        return tempSvrId;
    }

    public String getvOpenID() {
        return vOpenID;
    }

    public String getvUserID() {
        return vUserID;
    }

    public String getvRoleID() {
        return vRoleID;
    }

    public int getiLevel() {
        return iLevel;
    }

    public int getiVipLevel() {
        return iVipLevel;
    }

    public int getCurrencyType() {
        return currencyType;
    }

    public int getChangeValue() {
        return changeValue;
    }

    public int getChangeType() {
        return changeType;
    }

    public int getOldValue() {
        return oldValue;
    }

    public int getNewValue() {
        return newValue;
    }

    public int getRealChangeValue() {
        return realChangeValue;
    }

    public int getMainReason() {
        return mainReason;
    }

    public long getSubReason() {
        return subReason;
    }

    public int getReason2() {
        return reason2;
    }

    public long getSubReason2() {
        return subReason2;
    }

    public int getReason3() {
        return reason3;
    }

    public long getSubReason3() {
        return subReason3;
    }

    public int getReason4() {
        return reason4;
    }

    public long getSubReason4() {
        return subReason4;
    }

    public int getReason5() {
        return reason5;
    }

    public long getSubReason5() {
        return subReason5;
    }

    public long getSequence() {
        return sequence;
    }
}