package org.example;

import org.apache.log4j.Logger;
import org.example.exceptions.MainException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/*
将serverUserCharReasonSummary以日志形式输出到文件
其中，正常情况下会调用outputToFile，只有在需要查询mainReason时会调用outputToFileWithReason
 */
public interface ResultOutput {

	Logger logger = Logger.getLogger(ResultOutput.class);
	static void outputToFile(Map<Integer, Map<String, Map<String, Map<String, String>>>> serverUserCharSummary) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(String.format("%soutput.1", Main.outputPrefix)))) {
			String iZoneAreaID;
			String userId;
			String roleId;
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
						writer.write(String.format("Currency|%s|%s|%s%s", iZoneAreaID, userId, roleId, str));
						writer.newLine();
					}
				}
			}
		} catch (IOException e) {
			String desc = String.format("[org.example]ResultOutput.outputToFile@Log analysis completed, but could not be written to the output.1 file|serverUserCharSummary: %s", serverUserCharSummary);
			logger.error(desc, e);
			throw new MainException("完成日志分析，但无法写入output.1文件中！");
		}
	}
	
	static void outputToFileWithReason(Map<Integer, Map<String, Map<String, Map<Integer, Map<String, String>>>>> serverUserCharReasonSummary) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(String.format("%soutput.1", Main.outputPrefix)))) {
			String iZoneAreaID;
			String userId;
			String roleId;
			String mainReason;
			for (Map.Entry<Integer, Map<String, Map<String, Map<Integer, Map<String, String>>>>> entry : serverUserCharReasonSummary.entrySet()) {
				iZoneAreaID = String.valueOf(entry.getKey());
				for (Map.Entry<String, Map<String, Map<Integer, Map<String, String>>>> entry1 : entry.getValue().entrySet()) {
					userId = entry1.getKey();
					for (Map.Entry<String, Map<Integer, Map<String, String>>> entry2 : entry1.getValue().entrySet()) {
						roleId = entry2.getKey();
						for (Map.Entry<Integer, Map<String, String>> entry3 : entry2.getValue().entrySet()) {
							mainReason = String.valueOf(entry3.getKey());
							StringBuilder str = new StringBuilder();
							for (Map.Entry<String, String> entry4 : entry3.getValue().entrySet()) {
								str.append("|").append(entry4.getValue());
							}
							//要改写成format形式来拼接字符串
							writer.write(String.format("Currency|%s|%s|%s%s|%s", iZoneAreaID, userId, roleId, str, mainReason));
							writer.newLine();
						}
					}
				}
			}
		} catch (IOException e) {
			String desc = String.format("[org.example]ResultOutput.outputToFileWithReason@Log analysis completed, but could not be written to the output.1 file|serverUserCharReasonSummary: %s", serverUserCharReasonSummary);
			logger.error(desc, e);
			throw new MainException("完成日志分析，但无法写入output.1文件中！");
		}
	}
	
	static void outputToConsole(Map<Integer, Map<String, Map<String, Map<String, String>>>> serverUserCharSummary) {
		//将serverUserCharSummary输出到控制台,使用logger.info输出信息
		for (Map.Entry<Integer, Map<String, Map<String, Map<String, String>>>> entry : serverUserCharSummary.entrySet()) {
			logger.info(String.format("i_zone_area_id: %d", entry.getKey()));
			for (Map.Entry<String, Map<String, Map<String, String>>> entry1 : entry.getValue().entrySet()) {
				logger.info(String.format("\tv_user_id: %s", entry1.getKey()));
				for (Map.Entry<String, Map<String, String>> entry2 : entry1.getValue().entrySet()) {
					logger.info(String.format("\t\tv_role_id: %s", entry2.getKey()));
					for (Map.Entry<String, String> entry3 : entry2.getValue().entrySet()) {
						logger.info(String.format("\t\t-%s: %s", entry3.getKey(), entry3.getValue()));
					}
				}
			}
		}
	}
	
	static void outputQueryResult(int getiZoneAreaID, String vUserID, String vRoleID, Integer mainReason, String result) {
		logger.info("The ouput of search:\n");
		logger.info(String.format("i_zone_area_id: %d", getiZoneAreaID));
		logger.info(String.format("\tv_user_id: %s", vUserID));
		logger.info(String.format("\t\tv_role_id: %s", vRoleID));
		logger.info(String.format("\t\t\tMainReason: %d", mainReason));
		logger.info(String.format("\t\t\t%s", result));
	}
	
}
