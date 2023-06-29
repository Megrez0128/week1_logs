package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CurrencyLogAnalyzerTest {
    @Test
    public void testSummarizeByServerUserChar() throws IOException {
        LogParser parser = new LogParser();
        List<CurrencyLog> currencyLogs = parser.parse("E:\\CodeStorage\\Github\\week1_logs\\src\\main\\resources\\currency_flow.txt");

        LogAnalyzer analyzer = new LogAnalyzer();
        Map<Integer,Map<String, Map<String,Map<String,String>>>> serverUserCharSummary = analyzer.summarizeByServerUserChar(currencyLogs);

        Assertions.assertEquals(2, serverUserCharSummary.size());
        Assertions.assertEquals(3, serverUserCharSummary.get("2931").size());
        Assertions.assertEquals(3, serverUserCharSummary.get("2930").size());
    }

    @Test
    public void testSummarizeByServerUserCharReason() throws IOException {
        LogParser parser = new LogParser();
        List<CurrencyLog> currencyLogs = parser.parse("E:\\CodeStorage\\Github\\week1_logs\\src\\main\\resources\\currency_flow.txt");

        LogAnalyzer analyzer = new LogAnalyzer();
        Map<Integer,Map<String, Map<String,Map<Integer,Map<String,String>>>>> serverUserCharReasonSummary = analyzer.summarizeByServerUserCharReason(currencyLogs);

        Assertions.assertEquals(2, serverUserCharReasonSummary.size());
        Assertions.assertEquals(3, serverUserCharReasonSummary.get("2931").size());
        Assertions.assertEquals(3, serverUserCharReasonSummary.get("2930").size());
    }

    @Test
    public void testQuery() throws IOException {
        LogParser parser = new LogParser();
        List<CurrencyLog> currencyLogs = parser.parse("E:\\CodeStorage\\Github\\week1_logs\\src\\main\\resources\\currency_flow.txt");

        LogAnalyzer analyzer = new LogAnalyzer();
        Map<Integer,Map<String, Map<String,Map<Integer,Map<String,String>>>>> serverUserCharReasonSummary = analyzer.summarizeByServerUserCharReason(currencyLogs);

        String result = analyzer.query(serverUserCharReasonSummary, 1, "1", "1", 1);

        Assertions.assertEquals("区服不存在", result);
    }
}