package kr.ac.skhu.controller.exception;

/**
 * Created by Manki Kim on 2017-02-19.
 */
public class StorageFileNotFoundException extends Exception {

    public StorageFileNotFoundException() { super(); }
    public StorageFileNotFoundException(String message) { super(message); }
    public StorageFileNotFoundException(String message, Throwable cause) { super(message, cause); }
    public StorageFileNotFoundException(Throwable cause) { super(cause); }
}
