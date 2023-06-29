package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LogParserTest {
    private LogParser parser;

    @BeforeEach
    public void setUp() {
        parser = new LogParser();
    }

    @Test
    public void testParse() {
        String logFile = "E:\\CodeStorage\\Github\\week1_logs\\src\\main\\resources\\data\\test.txt";
        List<Log> logs = parser.parse(logFile);
        Assertions.assertEquals(2, logs.size());

        Log log1 = logs.get(0);
        Assertions.assertEquals("1001", log1.getGameSvrId());
        Assertions.assertEquals("2023-06-28T15:00", log1.getDtEventTime().toString());
        Assertions.assertEquals("wx39bde50a0857a5af", log1.getvGameAppid());
        Assertions.assertEquals(1, log1.getPlatID());
        Assertions.assertEquals(1052, log1.getiZoneAreaID());
        Assertions.assertEquals(0, log1.getTempSvrId());
        Assertions.assertEquals("14939794003608167115", log1.getvOpenID());
        Assertions.assertEquals("14939794003608167115$wechat#android@1052", log1.getvUserID());
        Assertions.assertEquals("43185180", log1.getvRoleID());
        Assertions.assertEquals(105, log1.getiLevel());
        Assertions.assertEquals(0, log1.getiVipLevel());
        Assertions.assertEquals(9, log1.getCurrencyType());
        Assertions.assertEquals(1, log1.getChangeValue());
        Assertions.assertEquals(2, log1.getChangeType());
        Assertions.assertEquals(527, log1.getOldValue());
        Assertions.assertEquals(526, log1.getNewValue());
        Assertions.assertEquals(1, log1.getRealChangeValue());
        Assertions.assertEquals(501, log1.getMainReason());
        Assertions.assertEquals(866054013929L, log1.getSubReason());
        Assertions.assertEquals(0, log1.getReason2());
        Assertions.assertEquals(0, log1.getSubReason2());
        Assertions.assertEquals(0, log1.getReason3());
        Assertions.assertEquals(0, log1.getSubReason3());
        Assertions.assertEquals(0, log1.getReason4());
        Assertions.assertEquals(0, log1.getSubReason4());
        Assertions.assertEquals(0, log1.getReason5());
        Assertions.assertEquals(0, log1.getSubReason5());
        Assertions.assertEquals(1847634538738388992L, log1.getSequence());

        Log log2 = logs.get(1);
        Assertions.assertEquals("1001", log2.getGameSvrId());
        Assertions.assertEquals("2023-06-28T15:00", log2.getDtEventTime().toString());
        Assertions.assertEquals("wx39bde50a0857a5af", log2.getvGameAppid());
        Assertions.assertEquals(1, log2.getPlatID());
        Assertions.assertEquals(1010, log2.getiZoneAreaID());
        Assertions.assertEquals(0, log2.getTempSvrId());
        Assertions.assertEquals("2816462413533892932", log2.getvOpenID());
        Assertions.assertEquals("2816462413533892932$wechat#android@1010", log2.getvUserID());
        Assertions.assertEquals("66343922", log2.getvRoleID());
        Assertions.assertEquals(106, log2.getiLevel());
        Assertions.assertEquals(0, log2.getiVipLevel());
        Assertions.assertEquals(9, log2.getCurrencyType());
        Assertions.assertEquals(1, log2.getChangeValue());
        Assertions.assertEquals(2, log2.getChangeType());
        Assertions.assertEquals(1210, log2.getOldValue());
        Assertions.assertEquals(1209, log2.getNewValue());
        Assertions.assertEquals(1, log2.getRealChangeValue());
        Assertions.assertEquals(501, log2.getMainReason());
        Assertions.assertEquals(866053948393L, log2.getSubReason());
        Assertions.assertEquals(0, log2.getReason2());
        Assertions.assertEquals(0, log2.getSubReason2());
        Assertions.assertEquals(0, log2.getReason3());
        Assertions.assertEquals(0, log2.getSubReason3());
        Assertions.assertEquals(0, log2.getReason4());
        Assertions.assertEquals(0, log2.getSubReason4());
        Assertions.assertEquals(0, log2.getReason5());
        Assertions.assertEquals(0, log2.getSubReason5());
        Assertions.assertEquals(1847634538738388993L, log2.getSequence());
    }
}