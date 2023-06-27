import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Main {
    public static void main(String[] args) {

        LogParser parser = new LogParser();
        List<Log> logs = parser.parse("E:\\CodeStorage\\Java\\LogAnalyzer\\source\\currency_flow.txt");

        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.analyze(logs);
        //将logs输出到res.txt
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("E:\\CodeStorage\\Java\\LogAnalyzer\\source\\res.txt"))) {
            for (Log log : logs) {
                writer.write(log.toString());
                writer.newLine();
            }
        }catch (Exception e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }
    }
}