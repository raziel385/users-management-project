/**
 * 
 */
package com.example.usersmanagement.rest.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author benedetto.cosentino
 *
 */
public abstract class ApiController {

	protected static final String BASE_API_PATH = "/ui/api";
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@ExceptionHandler({ IllegalStateException.class, IllegalArgumentException.class, RuntimeException.class, MethodArgumentNotValidException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
    public Error handleAppException(Throwable exception) {
		final String code = HttpStatus.BAD_REQUEST.name();
		final String error = exception.getMessage();
        return new Error(code, error);
	}
	
	protected void log(String message, Object... args) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(message, args);
		}
	}
	
	protected static final class Error {

		private final String code;
		private final String message;

		protected Error(String code, String message) {
			super();
			this.code = code;
			this.message = message;
		}

		public String getCode() {
			return code;
		}

		public String getMessage() {
			return message;
		}
	}
	
}
