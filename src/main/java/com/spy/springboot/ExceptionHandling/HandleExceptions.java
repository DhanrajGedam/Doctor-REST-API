package com.spy.springboot.ExceptionHandling;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

public class HandleExceptions {

	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<Object> handle1(){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("error", "The Url is incorrect or doesn't exist");
		return new ResponseEntity<Object>(map,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Object> handle2(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error", "Enter proper value into the path");	
		return new ResponseEntity<Object>(map,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<Object> handle(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error", "Delete Url is incorect");
		return new ResponseEntity<Object>(map,HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	
}
