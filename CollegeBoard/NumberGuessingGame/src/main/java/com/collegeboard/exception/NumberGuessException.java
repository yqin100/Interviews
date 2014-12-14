package com.collegeboard.exception;

/**
 * Custom Exception for Number Guessing game.
 * @author Yue Chao Qin
 */
public class NumberGuessException extends Throwable {
	/**
	 * Default serialize id.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Exception with message and throwable object.
	 * @param message Custom message.
	 * @param cause Throwable object.
	 */
	public NumberGuessException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Exception with message.
	 * @param message Custom message.
	 */
	public NumberGuessException(String message) {
		super(message);
	}
}
