package com.fzc.pay.utils;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

public class DesCryptUtil {

	private static String CHARSETNAME = "UTF-8";// 编码
	private static String ALGORITHM = "DES";// 加密类型

	/**
	 * 对str进行DES加密
	 *
	 * @param str
	 * @return
	 */
	public static String getEncryptString(String str, String keyStr) {
		try {
			KeyGenerator generator = KeyGenerator.getInstance(ALGORITHM);
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(keyStr.getBytes());

			generator.init(secureRandom);
			Key key = generator.generateKey();
			generator = null;

			byte[] bytes = str.getBytes(CHARSETNAME);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] doFinal = cipher.doFinal(bytes);
			
			String rtnString = Base64.getEncoder().encodeToString(doFinal);
			return rtnString;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 对str进行DES解密
	 *
	 * @param str
	 * @return
	 */
	public static String getDecryptString(String str, String keyStr) {
		try {
			KeyGenerator generator = KeyGenerator.getInstance(ALGORITHM);
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(keyStr.getBytes());

			generator.init(secureRandom);
			Key key = generator.generateKey();
			generator = null;

			byte[] bytes = Base64.getDecoder().decode(str);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] doFinal = cipher.doFinal(bytes);
			System.out.println(new String(doFinal));
			return new String(doFinal, "UTF8");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
