package com.gyumaru.util;

import java.util.Random;
import java.util.UUID;

public class UuidUtil {
	/* public static User UT = new User(); */
	// 系统生成Uuid
	public static String GetUuid() throws Exception {
		/**
		 * 获得一个UUID
		 * 
		 * @return String UUID
		 */
		String s = UUID.randomUUID().toString();
		// 去掉“-”符号
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);

	}

	public static void main(String[] args) {

		try {
			for (int i = 0; i < 1000000; i++) {
				String a = UuidUtil.GetUuid();
				System.out.println(a);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}