package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CurrencyLog implements LogParser {
	public static final int CURRENCY_LOG_LENGTH = 30;
	public static final int I_ZONE_AREA_ID_INDEX = 5;
	public static final int V_USER_ID_INDEX = 8;
	public static final int V_ROLE_ID_INDEX = 9;
	public static final int CHANGE_VALUE_INDEX = 13;
	public static final int CHANGE_TYPE_INDEX = 14;
	public static final int OLD_VALUE_INDEX = 15;
	public static final int NEW_VALUE_INDEX = 16;
	public static final int REAL_CHANGE_VALUE_INDEX = 17;
	public static final int MAIN_REASON_INDEX = 19;
	
	private int iZoneAreaID;      //注册服ID，分区服场景使用。其他场景时填写0
	private String vUserID;       //TODO:用户ID
	private String vRoleID;       //TODO:玩家角色ID
	private int changeValue;      //TODO:修改值
	private int changeType;       //TODO:CurrencyChangeType {1:增加, 2:减少}
	private int oldValue;         //旧值
	private int newValue;         //新值 newValue = oldValue + changeValue * (changeType == 1 ? 1 : -1)
	private int realChangeValue;  //实际变化值 realChangeValue = changeValue * (changeType == 1 ? 1 : -1)
	private int mainReason;       //TODO:货币修改主原因
	
	public CurrencyLog() {
	}
	
	public void parse(String[] fields) {

		
		this.iZoneAreaID = Integer.parseInt(fields[I_ZONE_AREA_ID_INDEX]);
		this.vUserID = fields[V_USER_ID_INDEX];
		this.vRoleID = fields[V_ROLE_ID_INDEX];
		this.changeValue = Integer.parseInt(fields[CHANGE_VALUE_INDEX]);
		this.changeType = Integer.parseInt(fields[CHANGE_TYPE_INDEX]);
		this.oldValue = Integer.parseInt(fields[OLD_VALUE_INDEX]);
		this.newValue = Integer.parseInt(fields[NEW_VALUE_INDEX]);
		this.realChangeValue = Integer.parseInt(fields[REAL_CHANGE_VALUE_INDEX]);
		this.mainReason = Integer.parseInt(fields[MAIN_REASON_INDEX]);
	}
	
	static public List<CurrencyLog> parseFileByPath(Path logFilePath) {
		List<CurrencyLog> currencyLogs = new ArrayList<>();
		try {
			Stream<String> lines = Files.lines(logFilePath);
			for (String line : (Iterable<String>) lines::iterator) {
				String[] fields = line.split("\\|");
				if (fields.length != CURRENCY_LOG_LENGTH) {
					continue;
				}

				//以空格为分隔符，将field[0]分割成字符串数组，然后判断数组中最后一个不是"Currency"，则跳过这一行
				String[] str = fields[0].split(" ");
				if (!str[str.length - 1].equals("Currency")) {
					continue;
				}
				CurrencyLog currencyLog = new CurrencyLog();
				currencyLog.parse(fields);
				currencyLogs.add(currencyLog);
			}
			lines.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return currencyLogs;
	}
	
	//重写toString方法，方便打印日志
	@Override
	public final String toString() {
		return String.format("org.example.CurrencyLog{iZoneAreaID=%d, vUserID=%s, vRoleID=%s, changeValue=%d, changeType=%d, oldValue=%d, newValue=%d, realChangeValue=%d, mainReason=%d}", iZoneAreaID, vUserID, vRoleID, changeValue, changeType, oldValue, newValue, realChangeValue, mainReason);
	}
	
	public final int getiZoneAreaID() {
		return this.iZoneAreaID;
	}
	
	public final String getvUserID() {
		return this.vUserID;
	}
	
	public final String getvRoleID() {
		return this.vRoleID;
	}
	
	public final int getChangeValue() {
		return this.changeValue;
	}
	
	public final int getChangeType() {
		return this.changeType;
	}
	
	public final int getMainReason() {
		return this.mainReason;
	}
}