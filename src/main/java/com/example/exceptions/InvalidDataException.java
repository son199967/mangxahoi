package com.example.exceptions;

public class InvalidDataException extends Exception {
	
	private static final long serialVersionUID = 3511153178457523348L;

	
	public InvalidDataException() {
		super();
	}

	
	public InvalidDataException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public InvalidDataException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public InvalidDataException(String arg0) {
		super(arg0);
	}

	public InvalidDataException(Throwable arg0) {
		super(arg0);
	}

}