package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogAnalyzer {

    public Map<Integer,Map<String, Map<String,Map<String,String>>>> summarizeByServerUserChar(List<CurrencyLog> currencyLogs) {
        //统计出区服->用户->角色->的总消耗额度和总增加额度信息
        Map<Integer,Map<String, Map<String,Map<String,String>>>> serverUserCharSummary = new HashMap<>();
        for (CurrencyLog currencyLog : currencyLogs) {
            //对于每一条log，如果其区服信息不在serverUserCharSummary中，则新建一个区服信息
            if (!serverUserCharSummary.containsKey(currencyLog.getiZoneAreaID())) {
                serverUserCharSummary.put(currencyLog.getiZoneAreaID(),new HashMap<>());
            }
            //对于每一条log，如果其用户信息不在serverUserCharSummary中，则新建一个用户信息
            if (!serverUserCharSummary.get(currencyLog.getiZoneAreaID()).containsKey(currencyLog.getvUserID())) {
                serverUserCharSummary.get(currencyLog.getiZoneAreaID()).put(currencyLog.getvUserID(),new HashMap<>());
            }
            //对于每一条log，如果其角色信息不在serverUserCharSummary中，则新建一个角色信息
            if (!serverUserCharSummary.get(currencyLog.getiZoneAreaID()).get(currencyLog.getvUserID()).containsKey(currencyLog.getvRoleID())) {
                //新建一个map，key为"总增加额度"和"总消耗额度"，value为0
                Map<String,String> map = new HashMap<>();
                map.put("总增加额度","0");
                map.put("总消耗额度","0");
                serverUserCharSummary.get(currencyLog.getiZoneAreaID()).get(currencyLog.getvUserID()).put(currencyLog.getvRoleID(), map);
            }
            //如果角色信息已经存在，则更新其消耗额度和增加额度
            Map<String,String> map = serverUserCharSummary.get(currencyLog.getiZoneAreaID()).get(currencyLog.getvUserID()).get(currencyLog.getvRoleID());
            if(currencyLog.getChangeType() == 1){ //增加
                map.put("总增加额度",String.valueOf(Integer.parseInt(map.get("总增加额度")) + currencyLog.getChangeValue()));
            }else if(currencyLog.getChangeType() == 2){ //消耗
                map.put("总消耗额度",String.valueOf(Integer.parseInt(map.get("总消耗额度")) + currencyLog.getChangeValue()));
            }
        }
        return serverUserCharSummary;
    }

    //TODO:将此处的中文删除；可以用单个变量进行函数get的替换，不需要每次调用函数获取
    public Map<Integer,Map<String, Map<String,Map<Integer,Map<String,String>>>>> summarizeByServerUserCharReason(List<CurrencyLog> currencyLogs) {
        //统计出区服->用户->角色->LogReason的总消耗额度和总增加额度信息
        Map<Integer,Map<String, Map<String,Map<Integer,Map<String,String>>>>> serverUserCharReasonSummary = new HashMap<>();
        for(CurrencyLog currencyLog : currencyLogs) {
            if(!serverUserCharReasonSummary.containsKey(currencyLog.getiZoneAreaID())) {
                serverUserCharReasonSummary.put(currencyLog.getiZoneAreaID(),new HashMap<>());
            }
            if(!serverUserCharReasonSummary.get(currencyLog.getiZoneAreaID()).containsKey(currencyLog.getvUserID())) {
                serverUserCharReasonSummary.get(currencyLog.getiZoneAreaID()).put(currencyLog.getvUserID(),new HashMap<>());
            }
            if(!serverUserCharReasonSummary.get(currencyLog.getiZoneAreaID()).get(currencyLog.getvUserID()).containsKey(currencyLog.getvRoleID())) {
                serverUserCharReasonSummary.get(currencyLog.getiZoneAreaID()).get(currencyLog.getvUserID()).put(currencyLog.getvRoleID(),new HashMap<>());
            }
            if(!serverUserCharReasonSummary.get(currencyLog.getiZoneAreaID()).get(currencyLog.getvUserID()).get(currencyLog.getvRoleID()).containsKey(currencyLog.getMainReason())) {
                Map<String,String> map = new HashMap<>();
                map.put("总增加额度","0");
                map.put("总消耗额度","0");
                serverUserCharReasonSummary.get(currencyLog.getiZoneAreaID()).get(currencyLog.getvUserID()).get(currencyLog.getvRoleID()).put(currencyLog.getMainReason(),map);
            }
            Map<String,String> map = serverUserCharReasonSummary.get(currencyLog.getiZoneAreaID()).get(currencyLog.getvUserID()).get(currencyLog.getvRoleID()).get(currencyLog.getMainReason());
            if(currencyLog.getChangeType() == 1) {
                map.put("总增加额度",String.valueOf(Integer.parseInt(map.get("总增加额度")) + currencyLog.getChangeValue()));
            }else if(currencyLog.getChangeType() == 2) {
                map.put("总消耗额度",String.valueOf(Integer.parseInt(map.get("总消耗额度")) + currencyLog.getChangeValue()));
            }
        }
        return serverUserCharReasonSummary;
    }

    //TODO：优化判断，不建议每次都调用函数，可以存一个变量
    public String query(Map<Integer, Map<String, Map<String, Map<Integer, Map<String, String>>>>> serverUserCharReasonSummary, Integer tmpSvrId, String vUserID, String vRoleID, Integer mainReason) {

        if(!serverUserCharReasonSummary.containsKey(tmpSvrId)) {
            return "区服不存在";
        }
        if(!serverUserCharReasonSummary.get(tmpSvrId).containsKey(vUserID)) {
            return "用户不存在";
        }
        if(!serverUserCharReasonSummary.get(tmpSvrId).get(vUserID).containsKey(vRoleID)) {
            return "角色不存在";
        }
        if(!serverUserCharReasonSummary.get(tmpSvrId).get(vUserID).get(vRoleID).containsKey(mainReason)) {
            return "原因不存在";
        }
        Map<String,String> map = serverUserCharReasonSummary.get(tmpSvrId).get(vUserID).get(vRoleID).get(mainReason);
        return "总增加额度:" + map.get("总增加额度") + "  总消耗额度:" + map.get("总消耗额度");
    }
}
