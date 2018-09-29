package com.springboot.shiro.util;


import java.security.GeneralSecurityException;
import java.security.MessageDigest;

/**
 * @author ztgreat
 */
public class Digests {

	private static final String SHA = "SHA-512";

	private static byte[] sha(byte[] input, byte[] salt, int iterations) {
		
		return digest(input, SHA, salt, iterations);
	}

	private static byte[] digest(byte[] input, String algorithm, byte[] salt, int iterations) {
		
		try {
			MessageDigest digest = MessageDigest.getInstance(algorithm);
			if (salt != null) {
				digest.update(salt);
			}
			byte[] result = digest.digest(input);
			iterations = iterations + 323;
			for (int i = 1; i < iterations; i++) {
				digest.reset();
				result = digest.digest(result);
			}
			return result;
		} catch (GeneralSecurityException e) {
			throw new RuntimeException(e);
		}
	}

	public static String shaHex(String input, String salt) {
		
		return EncodeUtils.hexEncode(sha(input.getBytes(), salt.getBytes(), (input + salt).length()));
	}

	public static String shaHex(String input, String salt, int iterations) {
		
		return EncodeUtils.hexEncode(sha(input.getBytes(), salt.getBytes(), iterations));
	}

	public static String shaBase64(String input, String salt) {
		
		return EncodeUtils.base64Encode(sha(input.getBytes(), salt.getBytes(), (input + salt).length()));
	}

	public static String shaBase64(String input, String salt, int iterations) {
		
		return EncodeUtils.base64Encode(sha(input.getBytes(), salt.getBytes(), iterations));
	}

	public static void main(String[] args) {
		
		String anc = "雷波稷丰农业科技有限公司";
		String pwd = "welcomeypsc123";
		System.out.println(shaHex(pwd, anc));
	}

}