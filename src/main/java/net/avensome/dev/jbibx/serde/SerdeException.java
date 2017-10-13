package net.avensome.dev.jbibx.serde;

public class SerdeException extends RuntimeException {
    public SerdeException() {
    }

    public SerdeException(String message) {
        this(message, null);
    }

    public SerdeException(String message, Throwable cause) {
        super(message, cause instanceof SerdeException ? cause.getCause() : cause);
    }

    public SerdeException(Throwable cause) {
        this(null, cause);
    }

    public SerdeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause instanceof SerdeException ? cause.getCause() : cause, enableSuppression, writableStackTrace);
    }
}
