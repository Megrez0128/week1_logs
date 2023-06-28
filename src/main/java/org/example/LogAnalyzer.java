package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogAnalyzer {
    public void analyze(List<Log> logs) {
        // TODO: 分析日志信息，例如统计游戏操作次数、计算玩家游戏时长等
    }

    public Map<String,Map<String, Map<String,Map<String,String>>>> summarizeByServerUserChar(List<Log> logs) {
        //统计出区服->用户->角色->的总消耗额度和总增加额度信息
        Map<String,Map<String, Map<String,Map<String,String>>>> serverUserCharSummary = new HashMap<>();
        for (Log log : logs) {
            //对于每一条log，如果其区服信息不在serverUserCharSummary中，则新建一个区服信息
            if (!serverUserCharSummary.containsKey(log.getGameSvrId())) {
                serverUserCharSummary.put(log.getGameSvrId(),new HashMap<>());
            }
            //对于每一条log，如果其用户信息不在serverUserCharSummary中，则新建一个用户信息
            if (!serverUserCharSummary.get(log.getGameSvrId()).containsKey(log.getvUserID())) {
                serverUserCharSummary.get(log.getGameSvrId()).put(log.getvUserID(),new HashMap<>());
            }
            //对于每一条log，如果其角色信息不在serverUserCharSummary中，则新建一个角色信息
            if (!serverUserCharSummary.get(log.getGameSvrId()).get(log.getvUserID()).containsKey(log.getvRoleID())) {
                //新建一个map，key为"总增加额度"和"总消耗额度"，value为0
                Map<String,String> map = new HashMap<>();
                map.put("总增加额度","0");
                map.put("总消耗额度","0");
                serverUserCharSummary.get(log.getGameSvrId()).get(log.getvUserID()).put(log.getvRoleID(), map);
            }
            //如果角色信息已经存在，则更新其消耗额度和增加额度
            Map<String,String> map = serverUserCharSummary.get(log.getGameSvrId()).get(log.getvUserID()).get(log.getvRoleID());
            if(log.getChangeType() == 1){ //增加
                map.put("总增加额度",String.valueOf(Integer.parseInt(map.get("总增加额度")) + log.getChangeValue()));
            }else if(log.getChangeType() == 2){ //消耗
                map.put("总消耗额度",String.valueOf(Integer.parseInt(map.get("总消耗额度")) + log.getChangeValue()));
            }
        }
        return serverUserCharSummary;
    }

    //TODO:
    public Map<String,Map<String, Map<String,Map<Integer,Map<String,String>>>>> summarizeByServerUserCharReason(List<Log> logs) {
        //统计出区服->用户->角色->LogReason的总消耗额度和总增加额度信息
        Map<String,Map<String, Map<String,Map<Integer,Map<String,String>>>>> serverUserCharReasonSummary = new HashMap<>();
        for(Log log : logs) {
            if(!serverUserCharReasonSummary.containsKey(log.getGameSvrId())) {
                serverUserCharReasonSummary.put(log.getGameSvrId(),new HashMap<>());
            }
            if(!serverUserCharReasonSummary.get(log.getGameSvrId()).containsKey(log.getvUserID())) {
                serverUserCharReasonSummary.get(log.getGameSvrId()).put(log.getvUserID(),new HashMap<>());
            }
            if(!serverUserCharReasonSummary.get(log.getGameSvrId()).get(log.getvUserID()).containsKey(log.getvRoleID())) {
                serverUserCharReasonSummary.get(log.getGameSvrId()).get(log.getvUserID()).put(log.getvRoleID(),new HashMap<>());
            }
            if(!serverUserCharReasonSummary.get(log.getGameSvrId()).get(log.getvUserID()).get(log.getvRoleID()).containsKey(log.getMainReason())) {
                Map<String,String> map = new HashMap<>();
                map.put("总增加额度","0");
                map.put("总消耗额度","0");
                serverUserCharReasonSummary.get(log.getGameSvrId()).get(log.getvUserID()).get(log.getvRoleID()).put(log.getMainReason(),map);
            }
            Map<String,String> map = serverUserCharReasonSummary.get(log.getGameSvrId()).get(log.getvUserID()).get(log.getvRoleID()).get(log.getMainReason());
            if(log.getChangeType() == 1) {
                map.put("总增加额度",String.valueOf(Integer.parseInt(map.get("总增加额度")) + log.getChangeValue()));
            }else if(log.getChangeType() == 2) {
                map.put("总消耗额度",String.valueOf(Integer.parseInt(map.get("总消耗额度")) + log.getChangeValue()));
            }
        }
        return serverUserCharReasonSummary;
    }

    public String query(Map<String, Map<String, Map<String, Map<Integer, Map<String, String>>>>> serverUserCharReasonSummary, String gameSvrId, String vUserID, String vRoleID, Integer mainReason) {
        //TODO: 查询区服->用户->角色->LogReason的总消耗额度和总增加额度信息
        if(!serverUserCharReasonSummary.containsKey(gameSvrId)) {
            return "区服不存在";
        }
        if(!serverUserCharReasonSummary.get(gameSvrId).containsKey(vUserID)) {
            return "用户不存在";
        }
        if(!serverUserCharReasonSummary.get(gameSvrId).get(vUserID).containsKey(vRoleID)) {
            return "角色不存在";
        }
        if(!serverUserCharReasonSummary.get(gameSvrId).get(vUserID).get(vRoleID).containsKey(mainReason)) {
            return "原因不存在";
        }
        Map<String,String> map = serverUserCharReasonSummary.get(gameSvrId).get(vUserID).get(vRoleID).get(mainReason);
        return "总增加额度:" + map.get("总增加额度") + "  总消耗额度:" + map.get("总消耗额度");
    }
}