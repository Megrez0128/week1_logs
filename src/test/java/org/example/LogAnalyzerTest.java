package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class LogAnalyzerTest {
    @Test
    public void testSummarizeByServerUserChar() throws IOException {
        LogParser parser = new LogParser();
        List<Log> logs = parser.parse(Main.addressPrefix + "currency_flow.txt");

        LogAnalyzer analyzer = new LogAnalyzer();
        Map<String,Map<String, Map<String,Map<String,String>>>> serverUserCharSummary = analyzer.summarizeByServerUserChar(logs);

        Assertions.assertEquals(2, serverUserCharSummary.size());
        Assertions.assertEquals(3, serverUserCharSummary.get("2931").size());
        Assertions.assertEquals(3, serverUserCharSummary.get("2930").size());
    }

    @Test
    public void testSummarizeByServerUserCharReason() throws IOException {
        LogParser parser = new LogParser();
        List<Log> logs = parser.parse(Main.addressPrefix + "currency_flow.txt");

        LogAnalyzer analyzer = new LogAnalyzer();
        Map<String,Map<String, Map<String,Map<Integer,Map<String,String>>>>> serverUserCharReasonSummary = analyzer.summarizeByServerUserCharReason(logs);

        Assertions.assertEquals(2, serverUserCharReasonSummary.size());
        Assertions.assertEquals(3, serverUserCharReasonSummary.get("2931").size());
        Assertions.assertEquals(3, serverUserCharReasonSummary.get("2930").size());
    }

    @Test
    public void testQuery() throws IOException {
        LogParser parser = new LogParser();
        List<Log> logs = parser.parse(Main.addressPrefix + "currency_flow.txt");

        LogAnalyzer analyzer = new LogAnalyzer();
        Map<String,Map<String, Map<String,Map<Integer,Map<String,String>>>>> serverUserCharReasonSummary = analyzer.summarizeByServerUserCharReason(logs);

        String result = analyzer.query(serverUserCharReasonSummary, "1", "1", "1", 1);

        Assertions.assertEquals("区服不存在", result);
    }
}