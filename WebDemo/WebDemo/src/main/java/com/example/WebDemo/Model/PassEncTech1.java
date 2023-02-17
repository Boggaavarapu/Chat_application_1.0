package com.example.WebDemo.Model;

import java.security.MessageDigest;

public class PassEncTech1 {
	public PassEncTech1() {
		
	}
	public  String hasing(String password) { 
		String encryptedpassword=null;
		try {
			 MessageDigest m = MessageDigest.getInstance("MD5");  
	            m.update(password.getBytes());  
	            byte[] bytes = m.digest();   
	            StringBuilder s = new StringBuilder();  
	            for(int i=0; i< bytes.length ;i++)  
	            {  
	                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));  
	            }
	           encryptedpassword = s.toString();  
		}catch(Exception e) {	
		}
		return encryptedpassword;
	}

}
