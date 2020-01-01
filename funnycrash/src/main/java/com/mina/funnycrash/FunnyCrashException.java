package com.mina.funnycrash;

/**
 * Created by bali on 02/08/17.
 */

/**
 * Represents an error condition specific to the Crash Reporter for Android.
 */
public class FunnyCrashException extends RuntimeException {
    static final long serialVersionUID = 1;

    /**
     * Constructs a new CrashReporterException.
     */
    public FunnyCrashException() {
        super();
    }

    /**
     * Constructs a new CrashReporterException.
     *
     * @param message the detail message of this exception
     */
    public FunnyCrashException(String message) {
        super(message);
    }

    /**
     * Constructs a new CrashReporterException.
     *
     * @param format the format string (see {@link java.util.Formatter#format})
     * @param args   the list of arguments passed to the formatter.
     */
    public FunnyCrashException(String format, Object... args) {
        this(String.format(format, args));
    }

    /**
     * Constructs a new CrashReporterException.
     *
     * @param message   the detail message of this exception
     * @param throwable the cause of this exception
     */
    public FunnyCrashException(String message, Throwable throwable) {
        super(message, throwable);
    }

    /**
     * Constructs a new CrashReporterException.
     *
     * @param throwable the cause of this exception
     */
    public FunnyCrashException(Throwable throwable) {
        super(throwable);
    }

    @Override
    public String toString() {
        // Throwable.toString() returns "CrashReporterException:{message}". Returning just "{message}"
        // should be fine here.
        return getMessage();
    }
}
