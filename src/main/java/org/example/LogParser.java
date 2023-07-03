package org.example;

/*Log:
    29个属性，第18个属性reasons是废弃的
     private String gameSvrId;   //登录的游戏服务器编号
     private LocalDateTime dtEventTime;   //游戏事件的时间
     private String vGameAppid;  //游戏APPID
     private int platID; //ios 0/android 1
     private int iZoneAreaID;    //注册服ID，分区服场景使用。其他场景时填写0
     private int tempSvrId;  //临时服务器编号。非临时服期间填写0
     private String vOpenID; //用户OPENID号
     private String vUserID; //用户ID
     private String vRoleID; //玩家角色ID
     private int iLevel; //等级
     private int iVipLevel;  //VIP等级
     private int currencyType;   //货币类型
     private int changeValue;    //修改值
     private int changeType; //CurrencyChangeType
     private int oldValue;   //旧值
     private int newValue;   //新值
     // <entry name="reasons" type="string" size="256" desc="变化原因(废弃)"/>
     private int realChangeValue;    //实际变化值
     private int mainReason; //MainReason
     private long subReason; //SubReason
     private int reason2;    //道具流动一级原因2
     private long subReason2;    //道具流动二级原因2
     private int reason3;    //道具流动一级原因3
     private long subReason3;    //道具流动二级原因3
     private int reason4;    //道具流动一级原因4
     private long subReason4;    //道具流动二级原因4
     private int reason5;    //道具流动一级原因5
     private long subReason5;    //道具流动二级原因5
     private long sequence;  //操作序列号，用于关联一次购买产生多条不同类型的货币日志 */

//将parser变成接口，然后让CurrencyParser实现这个接口
public interface LogParser {
	//将parse方法变成抽象方法
    void parse(String[] fields);
}