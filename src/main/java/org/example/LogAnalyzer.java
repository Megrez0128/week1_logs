package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
对日志进行分析
 */
public class LogAnalyzer {
    Map<Integer,Map<String, Map<String,Map<Integer,Map<String,String>>>>> serverUserCharReasonSummary;
    Map<Integer,Map<String, Map<String,Map<String,String>>>> serverUserCharSummary;
    public Map<Integer,Map<String, Map<String,Map<String,String>>>> summarizeByServerUserChar(List<CurrencyLog> currencyLogs) {
        //统计出区服->用户->角色->的totalConsumptionValue和totalAddedValue信息
        serverUserCharSummary = new HashMap<>();
        Map<String, Map<String, Map<String, String>>> stringMapMap;
        Map<String, Map<String, String>> stringMapMap1;
        for (CurrencyLog currencyLog : currencyLogs) {
            int iZoneAreaID = currencyLog.getiZoneAreaID();
            //对于每一条log，如果其区服信息不在serverUserCharSummary中，则新建一个区服信息
            if (!serverUserCharSummary.containsKey(iZoneAreaID)) {
                serverUserCharSummary.put(iZoneAreaID,new HashMap<>());
            }
            String userID = currencyLog.getvUserID();
            //对于每一条log，如果其用户信息不在serverUserCharSummary中，则新建一个用户信息
            stringMapMap = serverUserCharSummary.get(iZoneAreaID);
            if (!stringMapMap.containsKey(userID)) {
                stringMapMap.put(userID,new HashMap<>());
            }
            String roleID = currencyLog.getvRoleID();
            //对于每一条log，如果其角色信息不在serverUserCharSummary中，则新建一个角色信息
            stringMapMap1 = stringMapMap.get(userID);
            if (!stringMapMap1.containsKey(roleID)) {
                //新建一个map，key为"totalAddedValue"和"totalConsumptionValue"，value为0
                Map<String,String> map = new HashMap<>();
                map.put("totalAddedValue","0");
                map.put("totalConsumptionValue","0");
                stringMapMap1.put(roleID, map);
            }
            //如果角色信息已经存在，则更新其消耗额度和增加额度
            Map<String,String> map = stringMapMap1.get(roleID);
            if(currencyLog.getChangeType() == 1){ //增加
                map.put("totalAddedValue",String.valueOf(Integer.parseInt(map.get("totalAddedValue")) + currencyLog.getChangeValue()));
            }else if(currencyLog.getChangeType() == 2){ //消耗
                map.put("totalConsumptionValue",String.valueOf(Integer.parseInt(map.get("totalConsumptionValue")) + currencyLog.getChangeValue()));
            }
        }
        return serverUserCharSummary;
    }

/*
统计出区服->用户->角色->LogReason的totalConsumptionValue和totalAddedValue信息，返回对应的map
 */
    public Map<Integer,Map<String, Map<String,Map<Integer,Map<String,String>>>>> summarizeByServerUserCharReason(List<CurrencyLog> currencyLogs) {
        //统计出区服->用户->角色->LogReason的totalConsumptionValue和totalAddedValue信息
        serverUserCharReasonSummary = new HashMap<>();
        Map<String, Map<String, Map<Integer, Map<String, String>>>> stringMapMap;
        Map<String, Map<Integer, Map<String, String>>> stringMapMap1;
        Map<Integer, Map<String, String>> integerMapMap;
        for(CurrencyLog currencyLog : currencyLogs) {
            int iZoneAreaID = currencyLog.getiZoneAreaID();
            if(!serverUserCharReasonSummary.containsKey(iZoneAreaID)) {
                serverUserCharReasonSummary.put(iZoneAreaID,new HashMap<>());
            }
            String userID = currencyLog.getvUserID();
            stringMapMap = serverUserCharReasonSummary.get(iZoneAreaID);
            if(!stringMapMap.containsKey(userID)) {
                stringMapMap.put(userID,new HashMap<>());
            }
            String roleID = currencyLog.getvRoleID();
            stringMapMap1 = stringMapMap.get(userID);
            if(!stringMapMap1.containsKey(roleID)) {
                stringMapMap1.put(roleID,new HashMap<>());
            }
            integerMapMap = stringMapMap1.get(roleID);
            if(!integerMapMap.containsKey(currencyLog.getMainReason())) {
                Map<String,String> map = new HashMap<>();
                map.put("totalAddedValue","0");
                map.put("totalConsumptionValue","0");
                integerMapMap.put(currencyLog.getMainReason(),map);
            }
            Map<String,String> map = integerMapMap.get(currencyLog.getMainReason());
            if(currencyLog.getChangeType() == 1) {
                map.put("totalAddedValue",String.valueOf(Integer.parseInt(map.get("totalAddedValue")) + currencyLog.getChangeValue()));
            }else if(currencyLog.getChangeType() == 2) {
                map.put("totalConsumptionValue",String.valueOf(Integer.parseInt(map.get("totalConsumptionValue")) + currencyLog.getChangeValue()));
            }
        }
        return serverUserCharReasonSummary;
    }

    public String query(Integer tmpSvrId, String vUserID, String vRoleID, Integer mainReason) {
        Map<String, Map<String, Map<Integer, Map<String, String>>>> stringMapMap;
        Map<String, Map<Integer, Map<String, String>>> stringMapMap1;
        Map<Integer, Map<String, String>> integerMapMap;
        if(!serverUserCharReasonSummary.containsKey(tmpSvrId)) {
            return "Server " + tmpSvrId + " doesn't exist!";
        }

        stringMapMap = serverUserCharReasonSummary.get(tmpSvrId);
        if(!stringMapMap.containsKey(vUserID)) {
            return "User " + vUserID + " doesn't exist in Server " + tmpSvrId;
        }
        stringMapMap1 = stringMapMap.get(vUserID);
        if(!stringMapMap1.containsKey(vRoleID)) {
            return "Role " + vRoleID + " doesn't exist under UserID "  + vUserID + " in Server " + tmpSvrId;
        }
        integerMapMap = stringMapMap1.get(vRoleID);
        if(!integerMapMap.containsKey(mainReason)) {
            return "Reason " + mainReason + " doesn't exist under RoleID " + vRoleID + " under UserID "  + vUserID + " in Server " + tmpSvrId;
        }
        Map<String,String> map = integerMapMap.get(mainReason);
        return "totalAddedValue:" + map.get("totalAddedValue") + "  totalConsumptionValue:" + map.get("totalConsumptionValue");
    }
}
