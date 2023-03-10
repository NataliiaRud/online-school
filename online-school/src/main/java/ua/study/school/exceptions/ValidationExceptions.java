package ua.study.school.exceptions;

public class ValidationExceptions extends Exception{
    public ValidationExceptions() {
    }

    public ValidationExceptions(String message) {
        super(message);
    }

    public ValidationExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationExceptions(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

