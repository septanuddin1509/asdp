package com.infoasdp.auth;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {
	public static final String HASH_ALGORITHM = "SHA-256";

	public static String getPasswordHash(String password, String salt) throws NoSuchAlgorithmException {
		String hash = hash(salt + password);
		hash = hash(hash + salt);
		return hash;
	}

	private static String hash(String str) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(str.getBytes());
		return String.format("%064x", new Object[]{new BigInteger(1, md.digest())});
	}
}
