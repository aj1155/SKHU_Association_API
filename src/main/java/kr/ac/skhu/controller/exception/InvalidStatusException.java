package kr.ac.skhu.controller.exception;

/**
 * Manki Kim
 */
public class InvalidStatusException extends RuntimeException {
    public InvalidStatusException() { super(); }
    public InvalidStatusException(String message) { super(message); }
    public InvalidStatusException(String message, Throwable cause) { super(message, cause); }
    public InvalidStatusException(Throwable cause) { super(cause); }
}
