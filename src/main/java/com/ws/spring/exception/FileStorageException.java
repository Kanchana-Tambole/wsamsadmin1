package com.ws.spring.exception;

public class FileStorageException extends RuntimeException {
    /**
     * 
     */
    private static final long serialVersionUID = 9024306951727858712L;

    public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
