package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class CurrencyLogAnalyzerTest {
    @Test
    public void testSummarizeByServerUserChar() throws IOException {
        LogParser parser = new LogParser();
        List<CurrencyLog> currencyLogs = parser.parse("E:\\CodeStorage\\Github\\week1_logs\\src\\main\\resources\\currency_flow.txt");

        LogAnalyzer analyzer = new LogAnalyzer();
         analyzer.summarizeByServerUserChar(currencyLogs);
    }

    @Test
    public void testSummarizeByServerUserCharReason() throws IOException {
        LogParser parser = new LogParser();
        List<CurrencyLog> currencyLogs = parser.parse("E:\\CodeStorage\\Github\\week1_logs\\src\\main\\resources\\currency_flow.txt");

        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.summarizeByServerUserCharReason(currencyLogs);
    }

    @Test
    public void testQuery() throws IOException {
        LogParser parser = new LogParser();
        List<CurrencyLog> currencyLogs = parser.parse("E:\\CodeStorage\\Github\\week1_logs\\src\\main\\resources\\currency_flow.txt");

        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.summarizeByServerUserCharReason(currencyLogs);

        String result = analyzer.query(1, "1", "1", 1);

        Assertions.assertEquals("区服不存在", result);
    }
}