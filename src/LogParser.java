import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
// Log:
    //29个属性，第18个属性reasons是废弃的
    // private String gameSvrId;   //登录的游戏服务器编号
    // private LocalDateTime dtEventTime;   //游戏事件的时间
    // private String vGameAppid;  //游戏APPID
    // private int platID; //ios 0/android 1
    // private int iZoneAreaID;    //注册服ID，分区服场景使用。其他场景时填写0
    // private int tempSvrId;  //临时服务器编号。非临时服期间填写0
    // private String vOpenID; //用户OPENID号
    // private String vUserID; //用户ID
    // private String vRoleID; //玩家角色ID
    // private int iLevel; //等级
    // private int iVipLevel;  //VIP等级
import java.time.format.DateTimeFormatter;

    // private int currencyType;   //货币类型
    // private int changeValue;    //修改值
    // private int changeType; //CurrencyChangeType
    // private int oldValue;   //旧值
    // private int newValue;   //新值
    // // <entry name="reasons" type="string" size="256" desc="变化原因(废弃)"/>
    // private int realChangeValue;    //实际变化值
    // private int mainReason; //MainReason
    // private long subReason; //SubReason
    // private int reason2;    //道具流动一级原因2
    // private long subReason2;    //道具流动二级原因2
    // private int reason3;    //道具流动一级原因3
    // private long subReason3;    //道具流动二级原因3
    // private int reason4;    //道具流动一级原因4
    // private long subReason4;    //道具流动二级原因4
    // private int reason5;    //道具流动一级原因5
    // private long subReason5;    //道具流动二级原因5
    // private long sequence;  //操作序列号，用于关联一次购买产生多条不同类型的货币日志
public class LogParser {
    public List<Log> parse(String logFile) {
        List<Log> logs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(logFile))) {
            String line;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split("\\|");
                if (fields.length != 30) {
                    continue;
                }
                String gameSvrId = fields[1];
                System.out.println(fields[2]);
                
                LocalDateTime dtEventTime = LocalDateTime.parse(fields[2], formatter);
                String vGameAppid = fields[3];
                int platID = Integer.parseInt(fields[4]);
                int iZoneAreaID = Integer.parseInt(fields[5]);
                int tempSvrId = Integer.parseInt(fields[6]);
                String vOpenID = fields[7];
                String vUserID = fields[8];
                String vRoleID = fields[9];
                int iLevel = Integer.parseInt(fields[10]);
                int iVipLevel = Integer.parseInt(fields[11]);
                int currencyType = Integer.parseInt(fields[12]);
                int changeValue = Integer.parseInt(fields[13]);
                int changeType = Integer.parseInt(fields[14]);
                int oldValue = Integer.parseInt(fields[15]);
                System.out.println(fields[18]);
                int newValue = Integer.parseInt(fields[16]);
                int realChangeValue = Integer.parseInt(fields[17]);
                int mainReason = Integer.parseInt(fields[19]);
                long subReason = Long.parseLong(fields[20]);
                int reason2 = Integer.parseInt(fields[21]);
                long subReason2 = Long.parseLong(fields[22]);
                int reason3 = Integer.parseInt(fields[23]);
                long subReason3 = Long.parseLong(fields[24]);
                int reason4 = Integer.parseInt(fields[25]);
                long subReason4 = Long.parseLong(fields[26]);
                int reason5 = Integer.parseInt(fields[27]);
                long subReason5 = Long.parseLong(fields[28]);
                long sequence = Long.parseLong(fields[29]);

                Log log = new Log(gameSvrId,dtEventTime,vGameAppid,platID,iZoneAreaID,tempSvrId,vOpenID,vUserID,vRoleID,iLevel,iVipLevel,currencyType,changeValue,changeType,oldValue,newValue,realChangeValue,mainReason,subReason,reason2,subReason2,reason3,subReason3,reason4,subReason4,reason5,subReason5,sequence);
                logs.add(log);

                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return logs;
    }
}