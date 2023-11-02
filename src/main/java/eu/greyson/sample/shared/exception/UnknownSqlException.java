package eu.greyson.sample.shared.exception;

/**
 * Throws when unknown SQL error is received.
 */
public class UnknownSqlException extends RuntimeException {
    public UnknownSqlException(String message) { super(message); }
}
