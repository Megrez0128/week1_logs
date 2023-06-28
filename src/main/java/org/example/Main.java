package org.example;

import org.apache.commons.cli.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static String addressPrefix = "E:\\CodeStorage\\Github\\week1_logs\\src\\main\\resources\\";
    public static void main(String[] args) throws IOException {
        CommandLine cmd = parseArgs(args);
        if (cmd == null) {
            return;
        }
        //打印命令行参数
        System.out.println("打印命令行参数");
        boolean secondFunc = cmd.hasOption("m");
        String logFilePath = cmd.getOptionValue("l");

        LogParser parser = new LogParser();
        List<Log> logs = parser.parse(addressPrefix + "currency_flow.txt");

        LogAnalyzer analyzer = new LogAnalyzer();

        String gameSvrId = cmd.getOptionValue("g");
        String vUserID = cmd.getOptionValue("u");
        String vRoleID = cmd.getOptionValue("r");
        String mainReason = cmd.getOptionValue("m");

        if (secondFunc) {
            Map<String, Map<String, Map<String, Map<String, String>>>> serverUserCharReasonSummary = analyzer.summarizeByServerUserCharReason(logs);
            // 输出到文件或控制台...
        } else {
            Map<String,Map<String, Map<String,Map<String,String>>>> serverUserCharSummary = analyzer.summarizeByServerUserChar(logs);
            // 输出到文件或控制台...
            //将serverUserCharSummary输出到res.txt
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(addressPrefix +"res.txt"))) {
                for (Map.Entry<String, Map<String, Map<String, Map<String, String>>>> entry : serverUserCharSummary.entrySet()) {
                    writer.write("登录的游戏服务器编号 GameSvrId:" + entry.getKey());
                    writer.newLine();
                    for (Map.Entry<String, Map<String, Map<String, String>>> entry1 : entry.getValue().entrySet()) {
                        writer.write("用户ID vUserID:" + entry1.getKey());
                        writer.newLine();
                        for (Map.Entry<String, Map<String, String>> entry2 : entry1.getValue().entrySet()) {
                            writer.write("玩家角色ID vRoleID:" + entry2.getKey());
                            writer.newLine();
                            for (Map.Entry<String, String> entry3 : entry2.getValue().entrySet()) {
                                writer.write("-" + entry3.getKey() + " " + entry3.getValue());
                                writer.newLine();
                            }
                        }
                    }
                }
            } catch (Exception e) {
                throw new MainException(e);
            }
            //将serverUserCharSummary输出到控制台
            for (Map.Entry<String, Map<String, Map<String, Map<String, String>>>> entry : serverUserCharSummary.entrySet()) {
                System.out.println("登录的游戏服务器编号 GameSvrId:" + entry.getKey());
                for (Map.Entry<String, Map<String, Map<String, String>>> entry1 : entry.getValue().entrySet()) {
                    System.out.println("用户ID vUserID:" + entry1.getKey());
                    for (Map.Entry<String, Map<String, String>> entry2 : entry1.getValue().entrySet()) {
                        System.out.println("玩家角色ID vRoleID:" + entry2.getKey());
                        for (Map.Entry<String, String> entry3 : entry2.getValue().entrySet()) {
                            System.out.println("-" + entry3.getKey() + ": " + entry3.getValue());
                        }
                    }
                }
            }
        }
        analyzer.analyze(logs);
        //将logs输出到res.txt
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(addressPrefix +"res.txt"))) {
//            for (Log log : logs) {
//                writer.write(log.toString());
//                writer.newLine();
//            }
//        }catch (Exception e) {
//            throw new MainException(e);
//        }
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
