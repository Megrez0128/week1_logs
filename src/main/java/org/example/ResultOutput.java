package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public interface ResultOutput {
    //将serverUserCharReasonSummary以日志形式输出到文件
    static void outputToFile(Map<Integer, Map<String, Map<String, Map<String, String>>>> serverUserCharSummary) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Main.addressPrefix + "output.1"))) {
            String iZoneAreaID = "";
            String userId = "";
            String roleId = "";
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
                        writer.write("Currency|" + iZoneAreaID + "|" + userId + "|" + roleId + str);
                        writer.newLine();
                    }
                }
            }
        } catch (IOException e) {
            throw new MainException(e);
        }
    }

    static void outputToFileWithReason(Map<Integer, Map<String, Map<String, Map<Integer, Map<String, String>>>>> serverUserCharReasonSummary) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Main.addressPrefix + "res.txt"))) {
            String iZoneAreaID = "";
            String userId = "";
            String roleId = "";
            String mainReason = "";
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
                            writer.write("Currency|" + iZoneAreaID + "|" + userId + "|" + roleId + str + "|" + mainReason);
                            writer.newLine();
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new MainException(e);
        }
    }

    public static void outputToConsole(Map<Integer, Map<String, Map<String, Map<String, String>>>> serverUserCharSummary) {
        //将serverUserCharSummary输出到控制台
        for (Map.Entry<Integer, Map<String, Map<String, Map<String, String>>>> entry : serverUserCharSummary.entrySet()) {
            System.out.println("注册的游戏服务器编号 iZoneAreaID:" + entry.getKey());
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

    public static void outputQueryResult(int getiZoneAreaID, String vUserID, String vRoleID, Integer mainReason, String result) {
        System.out.println("查询结果：");
        System.out.printf("注册的游戏服务器编号 iZoneAreaID: %d%n", getiZoneAreaID);
        System.out.printf("\t用户ID vUserID: %s%n", vUserID);
        System.out.printf("\t\t玩家角色ID vRoleID: %s%n", vRoleID);
        System.out.printf("\t\t\t主要原因 MainReason: %d%n", mainReason);
        System.out.printf("\t\t\t%s%n", result);
    }

}
