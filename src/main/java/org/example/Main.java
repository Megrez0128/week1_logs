package org.example;

import org.apache.commons.cli.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static String addressPrefix = "src/main/resources/data/";  // 设置数据存储相对路径
    static ListFilesInFolder listFilesInFolder = new ListFilesInFolder(addressPrefix);
    static LogAnalyzer analyzer = new LogAnalyzer();
    static LogParser parser = new LogParser();

    public static void main(String[] args) {
        CommandLine cmd = ParseArgs.parseArgs(args);
        if (cmd == null) {
            return;
        }
        boolean secondFunc = cmd.hasOption("m");
        String logFilePath = cmd.getOptionValue("l");
        List<CurrencyLog> currencyLogs = new ArrayList<>();

        if (logFilePath != null) {
            // 读取指定的文件
            currencyLogs.addAll(parser.parse(logFilePath));
        } else {
            // 遍历目标文件夹下的所有文件
            for (String file : listFilesInFolder.getFileNameList()) {
                currencyLogs.addAll(parser.parse(addressPrefix + file));
            }
        }

        if (secondFunc) {
            Map<Integer,Map<String, Map<String,Map<Integer,Map<String,String>>>>> serverUserCharReasonSummary = analyzer.summarizeByServerUserCharReason(currencyLogs);
            //将serverUserCharReasonSummary输出到res.txt
            ResultOutput.outputToFileWithReason(serverUserCharReasonSummary);
            try {
                if(cmd.getOptionValue("i")==null){
                    System.out.println("请正确输入参数！" +
                            "正确格式为：java Main -l <logFilePath>或java Main -l <logFilePath> -i <iZoneAreaID> -u <vUserID> -r <vRoleID> -m <mainReason>");
                    return;
                }
                int iZoneAreaID = Integer.parseInt(cmd.getOptionValue("i"));
                String vUserID = cmd.getOptionValue("u");
                String vRoleID = cmd.getOptionValue("r");
                Integer mainReason = Integer.valueOf(cmd.getOptionValue("m"));
                // 根据命令行参数查询
                //g,u,r,m唯一确定一条记录
                String result = analyzer.query( iZoneAreaID, vUserID, vRoleID, mainReason);
                //将查询结果输出到控制台
                ResultOutput.outputQueryResult(iZoneAreaID, vUserID, vRoleID, mainReason, result);
            }catch (Exception e){
                throw new MainException("请正确输入参数！" +
                        "正确格式为：java Main -l <logFilePath>或java Main -l <logFilePath> -i <iZoneAreaID> -u <vUserID> -r <vRoleID> -m <mainReason>");
            }
        } else {
            Map<Integer,Map<String, Map<String,Map<String,String>>>> serverUserCharSummary = analyzer.summarizeByServerUserChar(currencyLogs);
            //将serverUserCharSummary输出到文件和控制台
            ResultOutput.outputToFile(serverUserCharSummary);
            ResultOutput.outputToConsole(serverUserCharSummary);

        }

        parser.printCounter();
    }
}
