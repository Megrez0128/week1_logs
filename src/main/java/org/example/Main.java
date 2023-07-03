package org.example;

import org.apache.commons.cli.CommandLine;
import org.apache.log4j.Logger;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Main {
	// 测试一下用log4j输出日志信息
	private static final Logger logger = Logger.getLogger(Main.class);
	
	public final static String inputPrefix = "src/main/resources/input/";  // 设置输入文件存储相对路径
	public final static String outputPrefix = "src/main/resources/output/";  // 设置输出文件存储相对路径
	static ListFilesInDir listFilesInFolder = new ListFilesInDir(inputPrefix);
	static LogAnalyzer analyzer = new LogAnalyzer();
//    static LogParser parser = new LogParser();
	
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
			currencyLogs.addAll(CurrencyLog.parseFileByPath(Paths.get(logFilePath)));
		} else {
			// 遍历目标文件夹下的所有文件
			for (String file : listFilesInFolder.getFileNameList()) {
				currencyLogs.addAll(CurrencyLog.parseFileByPath(Paths.get(inputPrefix + file)));
			}
		}
		
		if (secondFunc) {
			Map<Integer, Map<String, Map<String, Map<Integer, Map<String, String>>>>> serverUserCharReasonSummary = analyzer.summarizeByServerUserCharReason(currencyLogs);
			//将serverUserCharReasonSummary输出到res.txt
			ResultOutput.outputToFileWithReason(serverUserCharReasonSummary);
			try {
				if (cmd.getOptionValue("i") == null) {
					logger.error("请正确输入参数！" + "正确格式为：java Main -l <logFilePath>或java Main -l <logFilePath> -i <iZoneAreaID> -u <vUserID> -r <vRoleID> -m <mainReason>");
//					System.out.println("请正确输入参数！" + "正确格式为：java Main -l <logFilePath>或java Main -l <logFilePath> -i <iZoneAreaID> -u <vUserID> -r <vRoleID> -m <mainReason>");
					return;
				}
				int iZoneAreaID = Integer.parseInt(cmd.getOptionValue("i"));
				String vUserID = cmd.getOptionValue("u");
				String vRoleID = cmd.getOptionValue("r");
				Integer mainReason = Integer.valueOf(cmd.getOptionValue("m"));
				// 根据命令行参数查询
				//g,u,r,m唯一确定一条记录
				String result = analyzer.query(iZoneAreaID, vUserID, vRoleID, mainReason);
				//将查询结果输出到控制台
				ResultOutput.outputQueryResult(iZoneAreaID, vUserID, vRoleID, mainReason, result);
			} catch (Exception e) {
				String desc = String.format("[org.example]Main.main@Parameter input error|i_zone_area_id=%s|v_user_id=%s|v_role_id=%s|main_reason=%s", cmd.getOptionValue("i"), cmd.getOptionValue("u"), cmd.getOptionValue("r"), cmd.getOptionValue("m"));
				logger.error(desc, e);
				//throw new MainException("请正确输入参数！" + "正确格式为：java Main -l <logFilePath>或java Main -l <logFilePath> -i <iZoneAreaID> -u <vUserID> -r <vRoleID> -m <mainReason>");
			}
		} else {
			Map<Integer, Map<String, Map<String, Map<String, String>>>> serverUserCharSummary = analyzer.summarizeByServerUserChar(currencyLogs);
			//将serverUserCharSummary输出到文件和控制台
			ResultOutput.outputToFile(serverUserCharSummary);
			ResultOutput.outputToConsole(serverUserCharSummary);
			
		}
	}
}
