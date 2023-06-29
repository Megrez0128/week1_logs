package org.example;

import org.apache.commons.cli.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// TODO:注意较重要的注释按照标准规范写成如下格式
/*
Main函数
尽量不要出现汉字（改控制台print）
Exception或者报错信息：最好能够返回更具体的出错情况context
 */
public class Main {
    // 设置为相对路径
    public static String addressPrefix = "src/main/resources/data/";
    static ListFilesInFolder listFilesInFolder = new ListFilesInFolder(addressPrefix);
    static LogAnalyzer analyzer = new LogAnalyzer();
    static LogParser parser = new LogParser();

    public static void main(String[] args) {
        CommandLine cmd = parseArgs(args);
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
            outputToFileWithReason(serverUserCharReasonSummary);
            //将serverUserCharReasonSummary输出到控制台

            try {
                if(cmd.getOptionValue("i")==null){
                    System.out.println("请正确输入参数");
                    return;
                }
                int iZoneAreaID = Integer.parseInt(cmd.getOptionValue("i"));
                String vUserID = cmd.getOptionValue("u");
                String vRoleID = cmd.getOptionValue("r");
                Integer mainReason = Integer.valueOf(cmd.getOptionValue("m"));
                //查询,根据命令行参数查询
                //g,u,r,m唯一确定一条记录
                String result = analyzer.query(serverUserCharReasonSummary, iZoneAreaID, vUserID, vRoleID, mainReason);
                //将查询结果输出到控制台
                outputQueryResult(iZoneAreaID, vUserID, vRoleID, mainReason, result);
            }catch (Exception e){
                throw new MainException(e);
            }
        } else {
            Map<Integer,Map<String, Map<String,Map<String,String>>>> serverUserCharSummary = analyzer.summarizeByServerUserChar(currencyLogs);
            //将serverUserCharSummary输出到文件和控制台
            outputToFile(serverUserCharSummary);
            outputToConsole(serverUserCharSummary);
        }

        parser.printCounter();
    }

    private static void outputQueryResult(int getiZoneAreaID, String vUserID, String vRoleID, Integer mainReason, String result) {
        System.out.println("查询结果：");
        System.out.printf("注册的游戏服务器编号 iZoneAreaID: %d%n", getiZoneAreaID);
        System.out.printf("\t用户ID vUserID: %s%n", vUserID);
        System.out.printf("\t\t玩家角色ID vRoleID: %s%n", vRoleID);
        System.out.printf("\t\t\t主要原因 MainReason: %d%n", mainReason);
        System.out.printf("\t\t\t%s%n", result);
    }

    //将serverUserCharReasonSummary以日志形式输出到文件
    public static void outputToFile(Map<Integer, Map<String, Map<String, Map<String, String>>>> serverUserCharSummary) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(addressPrefix +"output.1"))) {
            String iZoneAreaID = "";
            String userId = "";
            String roleId = "";
            for (Map.Entry<Integer, Map<String, Map<String, Map<String, String>>>> entry : serverUserCharSummary.entrySet()) {
                iZoneAreaID = String.valueOf(entry.getKey());
                for (Map.Entry<String, Map<String, Map<String, String>>> entry1 : entry.getValue().entrySet()) {
                    userId = entry1.getKey();
                    for (Map.Entry<String, Map<String, String>> entry2 : entry1.getValue().entrySet()) {
                        roleId = entry2.getKey();
                        StringBuilder str = new StringBuilder();
                        for (Map.Entry<String, String> entry3 : entry2.getValue().entrySet()) {
                            str.append("|").append(entry3.getValue());
                        }
                        writer.write("Currency|" + iZoneAreaID + "|" +userId +"|" + roleId + str );
                        writer.newLine();
                    }
                }
            }
        } catch (IOException  e) {
            throw new MainException(e);
        }
    }

    public static void outputToFileWithReason(Map<Integer,Map<String, Map<String,Map<Integer,Map<String,String>>>>> serverUserCharReasonSummary) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(addressPrefix +"res.txt"))) {
            String iZoneAreaID = "";
            String userId = "";
            String roleId = "";
            String mainReason = "";
            for(Map.Entry<Integer,Map<String, Map<String,Map<Integer,Map<String,String>>>>> entry : serverUserCharReasonSummary.entrySet()){
                iZoneAreaID = String.valueOf(entry.getKey());
                for(Map.Entry<String, Map<String,Map<Integer,Map<String,String>>>> entry1 : entry.getValue().entrySet()){
                    userId = entry1.getKey();
                    for(Map.Entry<String,Map<Integer,Map<String,String>>> entry2 : entry1.getValue().entrySet()){
                        roleId = entry2.getKey();
                        for(Map.Entry<Integer,Map<String,String>> entry3 : entry2.getValue().entrySet()){
                            mainReason = String.valueOf(entry3.getKey());
                            StringBuilder str = new StringBuilder();
                            for (Map.Entry<String, String> entry4 : entry3.getValue().entrySet()) {
                                str.append("|").append(entry4.getValue());
                            }
                            writer.write("Currency|" + iZoneAreaID + "|" +userId +"|" + roleId + str + "|" + mainReason);
                            writer.newLine();
                        }
                    }
                }
            }
        } catch (IOException  e) {
            throw new MainException(e);
        }
    }

    private static void outputToConsole(Map<Integer, Map<String, Map<String, Map<String, String>>>> serverUserCharSummary) {
        //将serverUserCharSummary输出到控制台
        for (Map.Entry<Integer, Map<String, Map<String, Map<String, String>>>> entry : serverUserCharSummary.entrySet()) {
            System.out.println("注册的游戏服务器编号 iZoneAreaID:" + entry.getKey());
            for (Map.Entry<String, Map<String, Map<String, String>>> entry1 : entry.getValue().entrySet()) {
                System.out.println("\t用户ID vUserID:" + entry1.getKey());
                for (Map.Entry<String, Map<String, String>> entry2 : entry1.getValue().entrySet()) {
                    System.out.println("\t\t玩家角色ID vRoleID:" + entry2.getKey());
                    for (Map.Entry<String, String> entry3 : entry2.getValue().entrySet()) {
                        System.out.println("\t\t-" + entry3.getKey() + ": " + entry3.getValue());
                    }
                }
            }
        }
    }


    private static CommandLine parseArgs(String[] args) {
        Options options = new Options();
        Option logFileOption = new Option("l", "logFilePath", true, "Path of log file");
        options.addOption(logFileOption);
        Option gameSvrIdOption = new Option("i", "iZoneAreaID", true, "Game server ID");
        options.addOption(gameSvrIdOption);
        Option vUserIDOption = new Option("u", "vUserID", true, "Virtual user ID");
        options.addOption(vUserIDOption);
        Option vRoleIDOption = new Option("r", "vRoleID", true, "Virtual role ID");
        options.addOption(vRoleIDOption);
        Option mainReasonOption = new Option("m", "mainReason", true, "Main reason of log");
        options.addOption(mainReasonOption);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        try {
            return parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("java Main", options);
            return null;
        }
    }
}
