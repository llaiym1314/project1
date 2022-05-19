package com.liliang.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test01 {
	
	public static void main(String[] args) {
		BCryptPasswordEncoder bCryptPasswordEncoder  = new BCryptPasswordEncoder();
		String encode = bCryptPasswordEncoder.encode("12345");
		System.out.println(encode);
		//$2a$10$EziP2nM2p2vgGwwRPcxJ.uWVvRke0fhYYctP1iifcP5ioBhONbDru
	}
	

}
