/**
 *
 */
package com.example.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * md5工具类
 *
 * @author 黄永琦
 * @since 2021-02-07
 */
public class Md5Util {
	private static final Logger log = LoggerFactory.getLogger(Md5Util.class);

	private Md5Util() {

	}

	/***
	 * MD5加码 生成32位md5码
	 */
	public static String stringToMd5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			log.info("md5加密失败:{}", e.getMessage());
			return "加密失败";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		IntStream.range(0, charArray.length).forEach(i -> byteArray[i] = (byte) charArray[i]);
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuilder hexValue = new StringBuilder();
		for (byte md5Byte : md5Bytes) {
			int val = ((int) md5Byte) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();

	}

	/**
	 * 加密解密算法 执行一次加密，两次解密
	 * Md5Util.convertMd5(Md5Util.convertMd5(password));
	 */
	public static String convertMd5(String inStr) {

		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		return new String(a);

	}

	/**
	 * 判断输入的密码和数据库中保存的MD5密码是否一致
	 *
	 * @param inputPassword 输入的密码
	 * @param md5DB         数据库保存的密码
	 * @return
	 */
	public static boolean passwordIsTrue(String inputPassword, String md5DB) {
		String md5 = stringToMd5(inputPassword);
		return Objects.equals(md5DB, md5);

	}
}
