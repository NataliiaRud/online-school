package ua.study.school.utility;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;

public class LogFactory {
    private Log[] logs = new Log[0];

    private void log(String name, LogLevel level, String message, Throwable t) {
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        String exceptionAsString = sw.toString();

        log(name, level, message, exceptionAsString);
    }

    private void log(String name, LogLevel level, String message, String stacktrace) {
        Log[] newLogs = new Log[logs.length + 1];
        System.arraycopy(logs, 0, newLogs, 0, logs.length);
        logs = newLogs;

        Log log = new Log();
        log.setDate(LocalDateTime.now());
        log.setLevel(level);
        log.setMessage(message);
        log.setName(name);
        log.setStacktrace(stacktrace);
        logs[logs.length - 1] = log;
    }

    public void debug(String name, String message) {
        log(name, LogLevel.DEBUG, message, "");
    }

    public void info(String name, String message) {
        log(name, LogLevel.INFO, message, "");
    }

    public void warning(String name, String message, String stacktrace) {
        log(name, LogLevel.WARNING, message, stacktrace);
    }

    public void warning(String name, String message, Throwable t) {
        log(name, LogLevel.WARNING, message, t);
    }

    public void error(String name, String message, String stacktrace) {
        log(name, LogLevel.ERROR, message, stacktrace);
    }

    public void error(String name, String message, Throwable t) {
        log(name, LogLevel.ERROR, message, t);
    }

    public Log[] getLogs() {
        return logs;
    }

    public void setLogs(Log[] logs) {
        this.logs = logs;
    }
}
