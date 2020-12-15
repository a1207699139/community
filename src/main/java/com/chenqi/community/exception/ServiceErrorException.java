package com.chenqi.community.exception;
/**
 * @author Ardai
 * 500异常
 */
public class ServiceErrorException extends RuntimeException{
    public ServiceErrorException(String message) {
        super(message);
    }
}
