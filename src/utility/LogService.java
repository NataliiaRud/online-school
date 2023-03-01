package utility;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LogService {
    private static final String LOG_FILE_NAME = "log-file.log";

    private String name;

    public LogService() {
        this.name = getClass().getName();
    }

    public LogService(String name) {
        this.name = name;
    }

    public void write(Log log) throws IOException {
        FileOutputStream fos = new FileOutputStream(LOG_FILE_NAME, true);

        String s = log.getDate()
                + "\t" + log.getLevel()
                + "\t" + log.getName()
                + "\t" + log.getMessage()
                + "\t" + log.getStacktrace()
                + "\r\n";
        fos.write(s.getBytes());

        fos.close();
    }

    public void write(Log[] logs) throws IOException {
        FileOutputStream fos = new FileOutputStream(LOG_FILE_NAME, true);

        for (Log log : logs) {
            String s = log.getDate()
                    + "\t" + log.getLevel()
                    + "\t" + log.getName()
                    + "\t" + log.getMessage()
                    + "\t" + log.getStacktrace()
                    + "\r\n";
            fos.write(s.getBytes());
        }

        fos.close();
    }

    public Log[] read() throws IOException {
        List<Log> list = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE_NAME));
        String line;

        while ((line = reader.readLine()) != null) {
            if (line.trim().length() == 0) {
                continue;
            }

            String[] items = line.split("\t");

            if (items.length < 4) {
                if (!list.isEmpty()) {
                    Log lastLog = list.get(list.size() - 1);
                    lastLog.setStacktrace(lastLog.getStacktrace() + "\r\n" + line);
                }
                continue;
            }

            Log log = new Log();
            log.setDate(LocalDateTime.parse(items[0]));
            log.setLevel(LogLevel.valueOf(items[1]));
            log.setName(items[2]);
            log.setMessage(items[3]);
            if (items.length > 4) {
                log.setStacktrace(items[4]);
            } else {
                log.setStacktrace("");
            }
            list.add(log);
        }

        reader.close();

        return list.toArray(new Log[0]);
    }

    public void error(String message, Throwable t) {
        LogFactory logFactory = new LogFactory();
        logFactory.error(name, message, t);
        try {
            write(logFactory.getLogs());
        } catch (Throwable th) {
            // ignored
        }
    }
}
