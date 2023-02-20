package com.blog.app.task.util;

public class Response<T> {

	private int statusCode;
	private String Message;
	private String resquestUri;
	private String parameters;
	private String pageInformation;
	private Object  content;
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public String getResquestUri() {
		return resquestUri;
	}
	public void setResquestUri(String resquestUri) {
		this.resquestUri = resquestUri;
	}
	public String getParameters() {
		return parameters;
	}
	public void setParameters(String parameters) {
		this.parameters = parameters;
	}
	public String getPageInformation() {
		return pageInformation;
	}
	public void setPageInformation(String pageInformation) {
		this.pageInformation = pageInformation;
	}
	public Object getContent() {
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
	}
	
	
}
