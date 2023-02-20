package com.blog.app.task.util;

public enum Status {

	OK(0), FAIL(-1);
	  
	private int value;  
	private Status(int value){  
	this.setValue(value);  
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}  
}
