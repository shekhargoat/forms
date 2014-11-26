package com.forms.server.common;

public class CommonMethods {

	public static boolean isEmpty(Integer value){
		boolean flag = true;
		//TODO return false in case of null also
		if(value != null && value > 0){
			flag = false;
		}
		return flag;		
	}
	
	public static boolean isEmpty(String name){
		boolean flag = true;
		if(name != null && !name.trim().isEmpty()){
			flag = false;
		}
		return flag;
	}
}
