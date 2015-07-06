package com.ibm.gbs.ebp.core.exception;

public class ConnectionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8074972544460523454L;
	
	//Parameterless Constructor
    public ConnectionException() {}

    //Constructor that accepts a message
    public ConnectionException(String message)
    {
       super(message);
    }

}
