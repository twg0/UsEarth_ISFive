package com.isfive.usearth.exception;

public class BusinessException extends RuntimeException{
	private final String detail;
	private final ErrorCode errorCode;

	public BusinessException(ErrorCode errorCode, String detail) {
		super(errorCode.getMessage() + (detail == null ? "" : "(" + detail + ")"));
		this.detail = detail;
		this.errorCode = errorCode;
	}

	public BusinessException(ErrorCode errorCode) {
		this(errorCode, null);
	}
}
