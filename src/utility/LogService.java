package utility;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LogService {
    private static final String LOG_FILE_NAME = "log-file.log";

    public void write(Log[] logs) throws IOException {
        FileOutputStream fos = new FileOutputStream(LOG_FILE_NAME);

        for (int i = 0; i < logs.length; i++) {
            Log log = logs[i];

            if (i > 0) {
                fos.write("\r\n".getBytes());
            }

            String s = log.getDate()
                    + "\t" + log.getLevel()
                    + "\t" + log.getName()
                    + "\t" + log.getMessage()
                    + "\t" + log.getStacktrace();
            fos.write(s.getBytes());
        }

        fos.close();
    }

    public Log[] read() throws IOException {
        List<Log> list = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE_NAME));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] items = line.split("\t");

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

    public void deleteLogFile() {
        File file = new File(LOG_FILE_NAME);
        if (file.exists()) {
            file.delete();
        }
    }
}
