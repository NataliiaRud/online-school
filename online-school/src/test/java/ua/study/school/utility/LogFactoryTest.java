package ua.study.school.utility;

import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogFactoryTest {
    @Test
    public void testDebug() {
        String name = this.getClass().getName();
        String message = "This is a debug message";

        LogFactory logFactory = new LogFactory();
        logFactory.debug(name, message);
        Log[] logs = logFactory.getLogs();

        assertNotNull(logs);
        assertEquals(logs.length, 1);
        Log log = logs[0];
        assertEquals(log.getLevel(), LogLevel.DEBUG);
        assertEquals(log.getStacktrace(), "");
        assertEquals(log.getName(), name);
        assertEquals(log.getMessage(), message);
    }

    @Test
    public void testInfo() {
        String name = this.getClass().getName();
        String message = "This is an info message";

        LogFactory logFactory = new LogFactory();
        logFactory.info(name, message);
        Log[] logs = logFactory.getLogs();

        assertNotNull(logs);
        assertEquals(logs.length, 1);
        Log log = logs[0];
        assertEquals(log.getLevel(), LogLevel.INFO);
        assertEquals(log.getStacktrace(), "");
        assertEquals(log.getName(), name);
        assertEquals(log.getMessage(), message);
    }

    @Test
    public void testWarning() {
        String name = this.getClass().getName();
        String message = "This is a warning message";
        String stackTrace = "stack trace";

        LogFactory logFactory = new LogFactory();
        logFactory.warning(name, message, stackTrace);
        Log[] logs = logFactory.getLogs();

        assertNotNull(logs);
        assertEquals(logs.length, 1);
        Log log = logs[0];
        assertEquals(log.getLevel(), LogLevel.WARNING);
        assertEquals(log.getStacktrace(), stackTrace);
        assertEquals(log.getName(), name);
        assertEquals(log.getMessage(), message);
    }

    @Test
    public void testError() {
        String name = this.getClass().getName();
        String message = "This is an error message";
        String stackTrace = "stack trace";

        LogFactory logFactory = new LogFactory();
        logFactory.error(name, message, stackTrace);
        Log[] logs = logFactory.getLogs();

        assertNotNull(logs);
        assertEquals(logs.length, 1);
        Log log = logs[0];
        assertEquals(log.getLevel(), LogLevel.ERROR);
        assertEquals(log.getStacktrace(), stackTrace);
        assertEquals(log.getName(), name);
        assertEquals(log.getMessage(), message);
    }

    @Test
    public void testErrorWithException() {
        Exception exception = new RuntimeException("Runtime exception occurred");

        StringWriter sw = new StringWriter();
        exception.printStackTrace(new PrintWriter(sw));
        String exceptionAsString = sw.toString();

        String name = this.getClass().getName();
        String message = "This is an error message";

        LogFactory logFactory = new LogFactory();
        logFactory.error(name, message, exception);
        Log[] logs = logFactory.getLogs();

        assertNotNull(logs);
        assertEquals(logs.length, 1);
        Log log = logs[0];
        assertEquals(log.getLevel(), LogLevel.ERROR);
        assertEquals(log.getStacktrace(), exceptionAsString);
        assertEquals(log.getName(), name);
        assertEquals(log.getMessage(), message);
    }

    @Test
    public void testMultipleLog() {
        String name = this.getClass().getName();
        String message = "This is a log message";
        String stackTrace = "stack trace";

        LogFactory logFactory = new LogFactory();
        logFactory.debug(name, message);
        logFactory.info(name, message);
        logFactory.warning(name, message, stackTrace);
        logFactory.error(name, message, stackTrace);
        Log[] logs = logFactory.getLogs();

        assertNotNull(logs);
        assertEquals(logs.length, 4);
        assertEquals(logs[0].getLevel(), LogLevel.DEBUG);
        assertEquals(logs[1].getLevel(), LogLevel.INFO);
        assertEquals(logs[2].getLevel(), LogLevel.WARNING);
        assertEquals(logs[3].getLevel(), LogLevel.ERROR);
    }
}