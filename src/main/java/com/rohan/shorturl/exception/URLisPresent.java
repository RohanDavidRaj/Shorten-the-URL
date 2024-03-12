package com.rohan.shorturl.exception;

public class URLisPresent extends RuntimeException {

	public URLisPresent() {
        super("URL is already present in the system");
    }

    public URLisPresent(String message) {
        super(message);
    }

    public URLisPresent(String message, Throwable cause) {
        super(message, cause);
    }

    public URLisPresent(Throwable cause) {
        super(cause);
    }
}
