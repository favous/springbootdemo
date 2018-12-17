package com.demo.order.exceptions;

public class RedisException extends RuntimeException {

	private static final long serialVersionUID = -8721594625820545390L;

	public RedisException() {
		super();
	}

	public RedisException(String message) {
		super(message);
	}
	
	public RedisException(Throwable cause) {
		super(cause);
	}

	public RedisException(String message, Throwable cause) {
		super(message, cause);
	}

	protected RedisException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
