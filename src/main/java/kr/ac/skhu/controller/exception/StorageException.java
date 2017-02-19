package kr.ac.skhu.controller.exception;

/**
 * Created by Manki Kim on 2017-02-19.
 */
public class StorageException extends Exception {
    public StorageException() { super(); }
    public StorageException(String message) { super(message); }
    public StorageException(String message, Throwable cause) { super(message, cause); }
    public StorageException(Throwable cause) { super(cause); }
}
