package com.altimetrik.saas.businessLayer;

public class CustomRuntimeException extends RuntimeException{

	public String message;
	
	public CustomRuntimeException(String message){
		this.message = message;
	}
	
	public String getMessage(){
		return this.message;
	}
	
}
