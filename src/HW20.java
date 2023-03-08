import utility.Log;
import utility.LogFactory;
import utility.LogLevel;
import utility.LogService;

import java.io.IOException;
import java.util.Arrays;

public class HW20 {
    public static void execute() throws IOException {
        LogFactory logFactory = new LogFactory();

        logFactory.debug("HW20", "This is a debug message");
        logFactory.info("HW20", "This is an info message");
        logFactory.warning("HW20", "This is a warning message", "Warning stack trace");
        logFactory.error("HW20", "This is an error message", "Error stack trace");

        LogService logService = new LogService();
        logService.write(logFactory.getLogs());

        Log[] fromLogFile = logService.read();
        System.out.println(Arrays.toString(fromLogFile));

        // Home assignment 28, task 3
        Arrays.stream(fromLogFile).forEach(log -> {
            System.out.println(log.getMessage());
        });

        // Home assignment 29, task 3
        int infoCountFromLogFileMiddle = 0;
        for (int i = fromLogFile.length / 2; i < fromLogFile.length; i++) {
            if (fromLogFile[i].getLevel() == LogLevel.INFO) {
                infoCountFromLogFileMiddle++;
            }
        }
        System.out.println("Number of INFO log records from the middle of the file: " + infoCountFromLogFileMiddle);
    }
}
