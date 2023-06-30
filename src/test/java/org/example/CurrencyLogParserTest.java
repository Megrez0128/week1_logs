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

    }
}