package com.bz.hb.exception;

/**
 * created by srana on 07/10/20 at 10.22 AM
 */

public class NotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1346661033386714510L;

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }
}
