package com.ibm.gbs.ebp.core.exception;

public class NotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8074972544460523454L;
	
	//Parameterless Constructor
    public NotFoundException() {}

    //Constructor that accepts a message
    public NotFoundException(String message)
    {
       super(message);
    }

}
