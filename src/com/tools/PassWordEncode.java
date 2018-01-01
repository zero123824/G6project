package com.tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PassWordEncode {
	
	public static String getHashCode(String authCode) {
		StringBuilder sb = new StringBuilder();
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256") ;
			md.update(authCode.getBytes());
			byte[] bs = md.digest();			 
			for(byte b :bs) {
				sb.append(String.format("%x", b));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		String hashCode = sb.toString();
		return hashCode;
	}
	
	
	private static char engLetter() {
		char letter = (char) ((int) (Math.random() * 26) + 65);
		return letter;
	}

	private static char engLetterS() {
		char letter = (char) ((int) (Math.random() * 26) + 97);
		return letter;
	}

	private static char number() {
		char letter = (char) ((int) (Math.random() * 10) + 48);
		return letter;
	}
	
	@SuppressWarnings("unused")	
	public static String getAuthCode() {
		char[] code = new char[8];
		StringBuilder sb = new StringBuilder();
		for(char c : code) {		
			switch((int)(Math.random() * 3) + 1) {
				case 1:sb.append(PassWordEncode.number());break;
				case 2:sb.append(PassWordEncode.engLetterS());break;
				default:sb.append(PassWordEncode.engLetter());
			}
		}
		String authCode = sb.toString();
		return authCode;
	}
	
	public static boolean verifyPsw(String inputPsw,String hashCode) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256") ;
			md.update(inputPsw.getBytes());
			byte[] bs = md.digest();
			StringBuilder sb = new StringBuilder();
			for(byte b :bs) {
				sb.append(String.format("%x", b));
			}
			inputPsw = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		if(inputPsw.equals(hashCode)) {
			return true;
		}else if(!inputPsw.equals(hashCode)) {
			return false;
		}else return false;
	}	
}
