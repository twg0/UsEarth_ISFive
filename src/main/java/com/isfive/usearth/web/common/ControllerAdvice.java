package com.isfive.usearth.web.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.isfive.usearth.exception.AuthException;
import com.isfive.usearth.exception.ConflictException;
import com.isfive.usearth.exception.CookieProcessingException;
import com.isfive.usearth.exception.EntityNotFoundException;
import com.isfive.usearth.exception.FileProcessingException;
import com.isfive.usearth.exception.InvalidValueException;
import com.isfive.usearth.web.common.dto.Message;


@RestControllerAdvice
public class ControllerAdvice {
	/**
	 * Entity 가 존재하지 않음
	 */
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity entityNotFound(final EntityNotFoundException exception) {

		return new ResponseEntity(new Message(exception.getLocalizedMessage()), exception.getStatus());
	}

	/**
	 * FileControl 에러
	 */
	@ExceptionHandler(FileProcessingException.class)
	public ResponseEntity fileProcessingException(final FileProcessingException exception) {

		return new ResponseEntity(new Message(exception.getLocalizedMessage()), exception.getStatus());
	}

	/**
	 * 인증 에러
	 */
	@ExceptionHandler(AuthException.class)
	public ResponseEntity authException(final AuthException exception) {

		return new ResponseEntity(new Message(exception.getLocalizedMessage()), exception.getStatus());
	}

	/**
	 * 중복 에러
	 */
	@ExceptionHandler(ConflictException.class)
	public ResponseEntity conflictException(final ConflictException exception) {

		return new ResponseEntity(new Message(exception.getLocalizedMessage()), exception.getStatus());
	}

	/**
	 * InvalidValue 에러
	 */
	@ExceptionHandler(InvalidValueException.class)
	public ResponseEntity invalidValueException(final InvalidValueException exception) {

		return new ResponseEntity(new Message(exception.getLocalizedMessage()), exception.getStatus());
	}

	/**
	 * Cookie 에러
	 */
	@ExceptionHandler(CookieProcessingException.class)
	public ResponseEntity invalidValueException(final CookieProcessingException exception) {

		return new ResponseEntity(new Message(exception.getLocalizedMessage()), exception.getStatus());
	}
}
