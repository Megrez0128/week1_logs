package org.example;

import org.apache.commons.cli.*;

import java.beans.Introspector;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            //outputToConsoleWithReason(serverUserCharReasonSummary);

            Integer iZoneAreaID = Integer.valueOf(cmd.getOptionValue("i"));
            String vUserID = cmd.getOptionValue("u");
            String vRoleID = cmd.getOptionValue("r");
            Integer mainReason = Integer.valueOf(cmd.getOptionValue("m"));
            //查询,根据命令行参数查询
            //g,u,r,m唯一确定一条记录
            String result = analyzer.query(serverUserCharReasonSummary,iZoneAreaID,vUserID,vRoleID,mainReason);
            //将查询结果输出到控制台
            outputQueryResult(iZoneAreaID, vUserID, vRoleID, mainReason, result);
        } else {
            Map<Integer,Map<String, Map<String,Map<String,String>>>> serverUserCharSummary = analyzer.summarizeByServerUserChar(currencyLogs);
            // 输出到文件或控制台...
            //将serverUserCharSummary输出到res.txt
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

    public static void outputToFile(Map<Integer, Map<String, Map<String, Map<String, String>>>> serverUserCharSummary) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(addressPrefix +"output.1"))) {
            for (Map.Entry<Integer, Map<String, Map<String, Map<String, String>>>> entry : serverUserCharSummary.entrySet()) {
                writer.write("注册的游戏服务器编号 iZoneAreaID:" + entry.getKey());
                writer.newLine();
                for (Map.Entry<String, Map<String, Map<String, String>>> entry1 : entry.getValue().entrySet()) {
                    writer.write("\t用户ID vUserID:" + entry1.getKey());
                    writer.newLine();
                    for (Map.Entry<String, Map<String, String>> entry2 : entry1.getValue().entrySet()) {
                        writer.write("\t\t玩家角色ID vRoleID:" + entry2.getKey());
                        writer.newLine();
                        for (Map.Entry<String, String> entry3 : entry2.getValue().entrySet()) {
                            writer.write("\t\t-" + entry3.getKey() + " " + entry3.getValue());
                            writer.newLine();
                        }
                    }
                }
            }
        } catch (IOException  e) {
            throw new MainException(e);
        }
    }

    public static void outputToFileWithReason(Map<Integer,Map<String, Map<String,Map<Integer,Map<String,String>>>>> serverUserCharReasonSummary) {
        //将serverUserCharReasonSummary输出到res.txt
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(addressPrefix +"res.txt"))) {
            for(Map.Entry<Integer,Map<String, Map<String,Map<Integer,Map<String,String>>>>> entry : serverUserCharReasonSummary.entrySet()){
                writer.write("注册的游戏服务器编号 iZoneAreaID:" + entry.getKey());
                writer.newLine();
                for(Map.Entry<String, Map<String,Map<Integer,Map<String,String>>>> entry1 : entry.getValue().entrySet()){
                    writer.write("\t用户ID vUserID:" + entry1.getKey());
                    writer.newLine();
                    for(Map.Entry<String,Map<Integer,Map<String,String>>> entry2 : entry1.getValue().entrySet()){
                        writer.write("\t\t玩家角色ID vRoleID:" + entry2.getKey());
                        writer.newLine();
                        for(Map.Entry<Integer,Map<String,String>> entry3 : entry2.getValue().entrySet()){
                            writer.write("\t\t\t主要原因 MainReason:" + entry3.getKey());
                            writer.newLine();
                            for(Map.Entry<String,String> entry4 : entry3.getValue().entrySet()){
                                writer.write("\t\t\t-" + entry4.getKey() + ": " + entry4.getValue());
                                writer.newLine();
                            }
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
        //logFileOption.setRequired(true);
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
