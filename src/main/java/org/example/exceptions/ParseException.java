package org.example.exceptions;

public class ParseException extends RuntimeException {
	
	//
	private String extraMessage;
	private String[] args;
	public ParseException(String message, String[] args) {
		this.extraMessage = message;
		this.args = args;
	}
	
	@Override
	public String getMessage() {
		return super.getMessage() + extraMessage + " " + String.join(" ", args);
	}
}
