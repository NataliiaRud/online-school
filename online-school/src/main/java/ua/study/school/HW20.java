package ua.study.school;

import ua.study.school.utility.Log;
import ua.study.school.utility.LogFactory;
import ua.study.school.utility.LogLevel;
import ua.study.school.utility.Logger;

import java.io.IOException;
import java.util.Arrays;

public class HW20 {
    public static void execute() throws IOException {
        LogFactory logFactory = new LogFactory();

        logFactory.debug("ua.study.school.HW20", "This is a debug message");
        logFactory.info("ua.study.school.HW20", "This is an info message");
        logFactory.warning("ua.study.school.HW20", "This is a warning message", "Warning stack trace");
        logFactory.error("ua.study.school.HW20", "This is an error message", "Error stack trace");

        Logger logger = new Logger();
        logger.write(logFactory.getLogs());

        Log[] fromLogFile = logger.read();
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
