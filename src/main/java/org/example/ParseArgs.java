package org.example;

import org.apache.commons.cli.*;

public class ParseArgs {
    public static CommandLine parseArgs(String[] args) {
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
