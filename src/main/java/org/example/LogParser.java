package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


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

public class LogParser {
//    List<Log> logs= new ArrayList<>();
    int  currencyCounter= 0;
    int totalCounter = 0;
    public void printCounter(){
        System.out.println("currency类出现的次数currencyCounter: "  + currencyCounter);
        System.out.println("总的日志记录数totalCounter: "  + totalCounter);
    }
public List<CurrencyLog> parse(String logFile) {
    List<CurrencyLog> currencyLogs = new ArrayList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    try (Stream<String> lines = Files.lines(Paths.get(logFile))) {
        lines.forEach(line -> {
            totalCounter++;
            String[] fields = line.split("\\|");
            if (fields.length != 30) {
                return;
            }

            //以空格为分隔符，将field[0]分割成字符串数组，然后判断数组中最后一个不是"Currency"，则跳过这一行
            String[] str = fields[0].split(" ");
            if (!str[str.length - 1].equals("Currency")) {
                return;
            }
            currencyCounter++;

            CurrencyLog currencyLog = new CurrencyLog(
                fields[1],
                LocalDateTime.parse(fields[2], formatter),
                fields[3],
                Integer.parseInt(fields[4]),
                Integer.parseInt(fields[5]),
                Integer.parseInt(fields[6]),
                fields[7],
                fields[8],
                fields[9],
                Integer.parseInt(fields[10]),
                Integer.parseInt(fields[11]),
                Integer.parseInt(fields[12]),
                Integer.parseInt(fields[13]),
                Integer.parseInt(fields[14]),
                Integer.parseInt(fields[15]),
                Integer.parseInt(fields[16]),
                Integer.parseInt(fields[17]),
                Integer.parseInt(fields[19]),
                Long.parseLong(fields[20]),
                Integer.parseInt(fields[21]),
                Long.parseLong(fields[22]),
                Integer.parseInt(fields[23]),
                Long.parseLong(fields[24]),
                Integer.parseInt(fields[25]),
                Long.parseLong(fields[26]),
                Integer.parseInt(fields[27]),
                Long.parseLong(fields[28]),
                Long.parseLong(fields[29])
            );
            currencyLogs.add(currencyLog);
        });
    } catch (IOException e) {
        e.printStackTrace();
    }
    return currencyLogs;
}
}