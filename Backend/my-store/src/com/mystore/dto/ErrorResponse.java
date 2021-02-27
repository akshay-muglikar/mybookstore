package com.mystore.dto;

public class ErrorResponse {

	public String status;
	public String message;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ErrorResponse(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	
	
}
