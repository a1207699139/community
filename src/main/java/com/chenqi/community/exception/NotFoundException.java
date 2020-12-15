package com.chenqi.community.exception;
/**
 * @author Ardai
 * 404异常
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
