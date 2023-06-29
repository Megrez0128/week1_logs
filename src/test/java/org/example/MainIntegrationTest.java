package org.example;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class MainIntegrationTest {

    @Test
    public void testMain() throws IOException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String[] args = {"-l", "E:\\CodeStorage\\Github\\week1_logs\\src\\main\\resources\\currency_flow.txt"};
        Main.main(args);

        String expectedOutput = "登录的游戏服务器编号 GameSvrId:2931\r\n" +
                "\t用户ID vUserID:zhangheyang004$shadow@2930\r\n" +
                "\t\t玩家角色ID vRoleID:8366962\r\n" +
                "\t\t-总增加额度: 1000000\r\n" +
                "\t\t-总消耗额度: 0\r\n" +
                "\t用户ID vUserID:zhangheyang005$shadow@2930\r\n" +
                "\t\t玩家角色ID vRoleID:8366962\r\n" +
                "\t\t-总增加额度: 1000000\r\n" +
                "\t\t-总消耗额度: 0\r\n" +
                "\t用户ID vUserID:zhangheyang003$shadow@2930\r\n" +
                "\t\t玩家角色ID vRoleID:8366962\r\n" +
                "\t\t-总增加额度: 2000000\r\n" +
                "\t\t-总消耗额度: 0\r\n" +
                "登录的游戏服务器编号 GameSvrId:2930\r\n" +
                "\t用户ID vUserID:zhangheyang004$shadow@2930\r\n" +
                "\t\t玩家角色ID vRoleID:8366962\r\n" +
                "\t\t-总增加额度: 1000000\r\n" +
                "\t\t-总消耗额度: 0\r\n" +
                "\t用户ID vUserID:zhangheyang005$shadow@2930\r\n" +
                "\t\t玩家角色ID vRoleID:8366962\r\n" +
                "\t\t-总增加额度: 1000000\r\n" +
                "\t\t-总消耗额度: 0\r\n" +
                "\t用户ID vUserID:zhangheyang003$shadow@2930\r\n" +
                "\t\t玩家角色ID vRoleID:8366962\r\n" +
                "\t\t-总增加额度: 2000000\r\n" +
                "\t\t-总消耗额度: 0\r\n" +
                "currency类出现的次数currencyCounter:8\r\n" +
                "总的日志记录数totalCounter:8\r\n";

        Assertions.assertEquals(expectedOutput, outContent.toString(String.valueOf(StandardCharsets.UTF_8)));
    }
}