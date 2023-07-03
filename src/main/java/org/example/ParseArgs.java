package org.example;

import org.apache.commons.cli.*;
import org.apache.log4j.Logger;

public class ParseArgs {
	static Logger logger = Logger.getLogger(ParseArgs.class);
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
		try {
			return parser.parse(options, args);
		} catch (ParseException e) {
			String desc = String.format("[org.example]ParseArgs.parseArgs@ParseException|args=%s", args);
			logger.error(desc, e);
			throw new org.example.exceptions.ParseException("org.example.parse_args.parse_args出现错误，错误信息为" + e.getMessage(), args);
			
		}
	}
}
