package org.example;

import org.apache.commons.cli.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static String addressPrefix = "E:\\CodeStorage\\Github\\week1_logs\\src\\main\\resources\\data\\";
    static ListFilesInFolder listFilesInFolder = new ListFilesInFolder(addressPrefix);
    static LogAnalyzer analyzer = new LogAnalyzer();
    static LogParser parser = new LogParser();
    // TODO: 目前设置的是电脑本地绝对路径，可以改成相对路径
    public static void main(String[] args) throws IOException {
        CommandLine cmd = parseArgs(args);
        if (cmd == null) {
            return;
        }
        boolean secondFunc = cmd.hasOption("m");
        String logFilePath = cmd.getOptionValue("l");
        List<Log> logs = new ArrayList<>();

        //遍历listFilesInFolder
        for (String file : listFilesInFolder.getFileNameList()) {
            //解析文件
            logs.addAll(parser.parse(addressPrefix + file));
        }

        if (secondFunc) {
            Map<String,Map<String, Map<String,Map<Integer,Map<String,String>>>>> serverUserCharReasonSummary = analyzer.summarizeByServerUserCharReason(logs);
            //将serverUserCharReasonSummary输出到res.txt
            outputToFileWithReason(serverUserCharReasonSummary);
            //将serverUserCharReasonSummary输出到控制台
            //outputToConsoleWithReason(serverUserCharReasonSummary);

            String gameSvrId = cmd.getOptionValue("g");
            String vUserID = cmd.getOptionValue("u");
            String vRoleID = cmd.getOptionValue("r");
            Integer mainReason = Integer.valueOf(cmd.getOptionValue("m"));
            //查询,根据命令行参数查询
            //g,u,r,m唯一确定一条记录
            String result = analyzer.query(serverUserCharReasonSummary,gameSvrId,vUserID,vRoleID,mainReason);
            //将查询结果输出到控制台
            outputQueryResult(gameSvrId, vUserID, vRoleID, mainReason, result);            
        } else {
            Map<String,Map<String, Map<String,Map<String,String>>>> serverUserCharSummary = analyzer.summarizeByServerUserChar(logs);
            // 输出到文件或控制台...
            //将serverUserCharSummary输出到res.txt
            outputToFile(serverUserCharSummary);
            outputToConsole(serverUserCharSummary);
        }

        parser.printCounter();
    }

    private static void outputQueryResult(String gameSvrId, String vUserID, String vRoleID, Integer mainReason, String result) {
        System.out.println("查询结果：");
        System.out.println(String.format("登录的游戏服务器编号 GameSvrId: %s", gameSvrId));
        System.out.println(String.format("\t用户ID vUserID: %s", vUserID));
        System.out.println(String.format("\t\t玩家角色ID vRoleID: %s", vRoleID));
        System.out.println(String.format("\t\t\t主要原因 MainReason: %d", mainReason));
        System.out.println(String.format("\t\t\t%s", result));
    }

    public static void outputToFile(Map<String, Map<String, Map<String, Map<String, String>>>> serverUserCharSummary) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(addressPrefix +"res.txt"))) {
            for (Map.Entry<String, Map<String, Map<String, Map<String, String>>>> entry : serverUserCharSummary.entrySet()) {
                writer.write("登录的游戏服务器编号 GameSvrId:" + entry.getKey());
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

    public static void outputToFileWithReason(Map<String,Map<String, Map<String,Map<Integer,Map<String,String>>>>> serverUserCharReasonSummary) {
        //将serverUserCharReasonSummary输出到res.txt
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(addressPrefix +"res.txt"))) {
            for(Map.Entry<String,Map<String, Map<String,Map<Integer,Map<String,String>>>>> entry : serverUserCharReasonSummary.entrySet()){
                writer.write("登录的游戏服务器编号 GameSvrId:" + entry.getKey());
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

    private static void outputToConsole(Map<String, Map<String, Map<String, Map<String, String>>>> serverUserCharSummary) {
        //将serverUserCharSummary输出到控制台
        for (Map.Entry<String, Map<String, Map<String, Map<String, String>>>> entry : serverUserCharSummary.entrySet()) {
            System.out.println("登录的游戏服务器编号 GameSvrId:" + entry.getKey());
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
    public static void outputToConsoleWithReason(Map<String,Map<String, Map<String,Map<Integer,Map<String,String>>>>> serverUserCharReasonSummary) {
        //将serverUserCharReasonSummary输出到控制台
        for(Map.Entry<String,Map<String, Map<String,Map<Integer,Map<String,String>>>>> entry : serverUserCharReasonSummary.entrySet()){
            System.out.println("登录的游戏服务器编号 GameSvrId:" + entry.getKey());
            for(Map.Entry<String, Map<String,Map<Integer,Map<String,String>>>> entry1 : entry.getValue().entrySet()){
                System.out.println("\t用户ID vUserID:" + entry1.getKey());
                for(Map.Entry<String,Map<Integer,Map<String,String>>> entry2 : entry1.getValue().entrySet()){
                    System.out.println("\t\t玩家角色ID vRoleID:" + entry2.getKey());
                    for(Map.Entry<Integer,Map<String,String>> entry3 : entry2.getValue().entrySet()){
                        System.out.println("\t\t\t主要原因 MainReason:" + entry3.getKey());
                        for(Map.Entry<String,String> entry4 : entry3.getValue().entrySet()){
                            System.out.println("\t\t\t-" + entry4.getKey() + ": " + entry4.getValue());
                        }
                    }
                }
            }
        }
    }

    private static CommandLine parseArgs(String[] args) {
        Options options = new Options();
        Option logFileOption = new Option("l", "logFilePath", true, "Path of log file");
        logFileOption.setRequired(true);
        options.addOption(logFileOption);
        Option gameSvrIdOption = new Option("g", "gameSvrId", true, "Game server ID");
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
