package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CurrencyLogParserTest {
    private LogParser parser;

    @BeforeEach
    public void setUp() {
        parser = new LogParser();
    }

    @Test
    public void testParse() {
        String logFile = "E:\\CodeStorage\\Github\\week1_logs\\src\\main\\resources\\data\\test.txt";
        List<CurrencyLog> currencyLogs = parser.parse(logFile);
        Assertions.assertEquals(2, currencyLogs.size());

        CurrencyLog currencyLog1 = currencyLogs.get(0);
        Assertions.assertEquals("1001", currencyLog1.getGameSvrId());
        Assertions.assertEquals("2023-06-28T15:00", currencyLog1.getDtEventTime().toString());
        Assertions.assertEquals("wx39bde50a0857a5af", currencyLog1.getvGameAppid());
        Assertions.assertEquals(1, currencyLog1.getPlatID());
        Assertions.assertEquals(1052, currencyLog1.getiZoneAreaID());
        Assertions.assertEquals(0, currencyLog1.getTempSvrId());
        Assertions.assertEquals("14939794003608167115", currencyLog1.getvOpenID());
        Assertions.assertEquals("14939794003608167115$wechat#android@1052", currencyLog1.getvUserID());
        Assertions.assertEquals("43185180", currencyLog1.getvRoleID());
        Assertions.assertEquals(105, currencyLog1.getiLevel());
        Assertions.assertEquals(0, currencyLog1.getiVipLevel());
        Assertions.assertEquals(9, currencyLog1.getCurrencyType());
        Assertions.assertEquals(1, currencyLog1.getChangeValue());
        Assertions.assertEquals(2, currencyLog1.getChangeType());
        Assertions.assertEquals(527, currencyLog1.getOldValue());
        Assertions.assertEquals(526, currencyLog1.getNewValue());
        Assertions.assertEquals(1, currencyLog1.getRealChangeValue());
        Assertions.assertEquals(501, currencyLog1.getMainReason());
        Assertions.assertEquals(866054013929L, currencyLog1.getSubReason());
        Assertions.assertEquals(0, currencyLog1.getReason2());
        Assertions.assertEquals(0, currencyLog1.getSubReason2());
        Assertions.assertEquals(0, currencyLog1.getReason3());
        Assertions.assertEquals(0, currencyLog1.getSubReason3());
        Assertions.assertEquals(0, currencyLog1.getReason4());
        Assertions.assertEquals(0, currencyLog1.getSubReason4());
        Assertions.assertEquals(0, currencyLog1.getReason5());
        Assertions.assertEquals(0, currencyLog1.getSubReason5());
        Assertions.assertEquals(1847634538738388992L, currencyLog1.getSequence());

        CurrencyLog currencyLog2 = currencyLogs.get(1);
        Assertions.assertEquals("1001", currencyLog2.getGameSvrId());
        Assertions.assertEquals("2023-06-28T15:00", currencyLog2.getDtEventTime().toString());
        Assertions.assertEquals("wx39bde50a0857a5af", currencyLog2.getvGameAppid());
        Assertions.assertEquals(1, currencyLog2.getPlatID());
        Assertions.assertEquals(1010, currencyLog2.getiZoneAreaID());
        Assertions.assertEquals(0, currencyLog2.getTempSvrId());
        Assertions.assertEquals("2816462413533892932", currencyLog2.getvOpenID());
        Assertions.assertEquals("2816462413533892932$wechat#android@1010", currencyLog2.getvUserID());
        Assertions.assertEquals("66343922", currencyLog2.getvRoleID());
        Assertions.assertEquals(106, currencyLog2.getiLevel());
        Assertions.assertEquals(0, currencyLog2.getiVipLevel());
        Assertions.assertEquals(9, currencyLog2.getCurrencyType());
        Assertions.assertEquals(1, currencyLog2.getChangeValue());
        Assertions.assertEquals(2, currencyLog2.getChangeType());
        Assertions.assertEquals(1210, currencyLog2.getOldValue());
        Assertions.assertEquals(1209, currencyLog2.getNewValue());
        Assertions.assertEquals(1, currencyLog2.getRealChangeValue());
        Assertions.assertEquals(501, currencyLog2.getMainReason());
        Assertions.assertEquals(866053948393L, currencyLog2.getSubReason());
        Assertions.assertEquals(0, currencyLog2.getReason2());
        Assertions.assertEquals(0, currencyLog2.getSubReason2());
        Assertions.assertEquals(0, currencyLog2.getReason3());
        Assertions.assertEquals(0, currencyLog2.getSubReason3());
        Assertions.assertEquals(0, currencyLog2.getReason4());
        Assertions.assertEquals(0, currencyLog2.getSubReason4());
        Assertions.assertEquals(0, currencyLog2.getReason5());
        Assertions.assertEquals(0, currencyLog2.getSubReason5());
        Assertions.assertEquals(1847634538738388993L, currencyLog2.getSequence());
    }
}